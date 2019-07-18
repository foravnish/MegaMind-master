package megamindlons.app.megamind.Fragments.services;


import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.Fragments.AddLeads;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.EndlessRecyclerViewScrollListener;
import megamindlons.app.megamind.Utils.FilterData;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.adapter.OfferListAdapter;
import megamindlons.app.megamind.adapter.ServiceAdapter;
import megamindlons.app.megamind.databinding.FragmentServicesBinding;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ServicesResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment implements ServiceAdapter.ServiceCallback {

    private Context mContext;

    FragmentServicesBinding binding;
    ServiceViewModel mViewModel;
    Dialog dialog;
    ServiceAdapter mAdapter;
    private List<ServicesResponse.MessageBean> mResult;
    private InterstitialAd interstitial;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_services, container, false);
        mViewModel = ViewModelProviders.of(this).get(ServiceViewModel.class);
        mContext = getContext();

        HomeAct.title.setText("Services");
        HomeAct.imageView.setVisibility(View.GONE);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


//        AdRequest adIRequest = new AdRequest.Builder().build();
//        interstitial.setAdUnitId(getString(R.string.id_add_unit));
//
//        interstitial.loadAd(adIRequest);
//
//        interstitial.setAdListener(new AdListener()
//        {
//            public void onAdLoaded()
//            {
//                if (interstitial.isLoaded()) {
//                    interstitial.show();
//                }
//            }
//        });

        if (Utility.isNetworkAvailable(mContext)) {
            getServiceApi();

            setServicesAdapter();


        } else
            Utility.showSnackBar(binding.getRoot(), getString(R.string.err_check_interner));
        return  binding.getRoot();
    }
    private void setServicesAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
        RecyclerView recyclerView = binding.rcServices;
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ServiceAdapter();
        //set job type
         mAdapter.setViewCallback(this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        // Adds the scroll listener to RecyclerView
    }

    private void getServiceApi() {

        Util.showPgDialog(dialog);
        mViewModel.callServices().observe(this, new Observer<ServicesResponse>() {
            @Override
            public void onChanged(@Nullable ServicesResponse resp) {

                Util.cancelPgDialog(dialog);
                if (resp != null) {
                    if (resp.getStatus().equals("success")) {

                            mResult = resp.getMessage();

//                            mResult.addAll(resp.getMessage());

                        mAdapter.setData(mResult);

                    }
                } else {

                    Toast.makeText(mContext, "No result found.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onServiceCallback(String id, String name, String image, String desc) {
        Fragment fragment = new ServicesDetail();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Bundle bundle=new Bundle();
        bundle.putString("serName",""+name);
        bundle.putString("serImage",""+image);
        bundle.putString("serDesc",""+desc);
        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        fragment.setArguments(bundle);
        ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
    }
}
