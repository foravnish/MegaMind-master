package megamindlons.app.megamind.Fragments.offer;


import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.EndlessRecyclerViewScrollListener;
import megamindlons.app.megamind.Utils.FilterData;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.adapter.OfferListAdapter;
import megamindlons.app.megamind.adapter.OfferListFilterAdapter;
import megamindlons.app.megamind.databinding.FragmentOffersListBinding;
import megamindlons.app.megamind.response.FilterCatResponse;
import megamindlons.app.megamind.response.OfferResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersListFragment extends Fragment implements OfferListFilterAdapter.ListOffer {

    private Context mContext;
    FragmentOffersListBinding binding;
    OfferViewModel mViewModel;
    private List<OfferResponse.MessageBean> mResult;
    private List<FilterCatResponse.MessageBean> mResultCat;
    String API_KEY = "aaa546583fad53bc2d78ea75a8885f8a84e91c2f1d7c8781643b2c89bc022eff";
    Dialog dialog;
    List<HashMap<String, String>> AllProducts;
    List<HashMap<String, String>> AllProductsFilter;
    JSONArray responseBackup;
    RecyclerView gridView;
    List<String> CatItem;
    OfferListAdapter mAdapter;
    OfferListFilterAdapter offerListFilterAdapter;
    int pageNo = 1;
    TextView catAll;
    CardView cardView;
    AlertDialog alertDialog;
    private EndlessRecyclerViewScrollListener scrollListener = null;
    private InterstitialAd interstitial;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_offers_list, container, false);
        mViewModel = ViewModelProviders.of(this).get(OfferViewModel.class);
        mContext = getContext();

        HomeAct.title.setText("Offers");
        HomeAct.imageView.setVisibility(View.GONE);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        AllProducts = new ArrayList<>();
        AllProductsFilter = new ArrayList<>();
        CatItem = new ArrayList<>();
        // setTransAdapter();
        // mAdapter.setViewCallback(this);

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
            getOfferListData("");

            setOffersAdapter();


        } else
            Utility.showSnackBar(binding.getRoot(), getString(R.string.err_check_interner));

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilterpopup();
            }
        });


        binding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("sdfsdfgdsg", String.valueOf(s));
                getOfferListData(s);

                setOffersAdapter();
                binding.simpleSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });

        return binding.getRoot();
    }

    private void openFilterpopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
        gridView = dialogView.findViewById(R.id.gridView);
//        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
//        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        catAll = dialogView.findViewById(R.id.catAll);
        cardView = dialogView.findViewById(R.id.cardView);


//        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(gridView.getContext(), 1);
//        gridView.addItemDecoration(mDividerItemDecoration);

        dialogBuilder.setView(dialogView);

        alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setFilterAdapter();
        offerListFilterAdapter.setCallback(this);
        getOfferListDataFilter();

        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                getOfferListData("");
                setOffersAdapter();
            }
        });


    }


    private void setOffersAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
        RecyclerView recyclerView = binding.transRecyclerView;
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new OfferListAdapter();
        //set job type
        // mAdapter.setJobType(jobType);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                //check if old items were in multiples of 10
                if (mResult != null && mResult.size() % 10 == 0) {
                    pageNo = page;
                    getOfferListData(String.valueOf(FilterData.mData));
                }
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }


    private void setFilterAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
        RecyclerView recyclerView = gridView;
        recyclerView.setLayoutManager(layoutManager);

        offerListFilterAdapter = new OfferListFilterAdapter();
        //set job type
        // mAdapter.setJobType(jobType);

        recyclerView.setAdapter(offerListFilterAdapter);
        // Adds the scroll listener to RecyclerView
    }


    private void getOfferListData(String filter) {

        Util.showPgDialog(dialog);
        mViewModel.callOffers(String.valueOf(pageNo), filter).observe(this, new Observer<OfferResponse>() {
            @Override
            public void onChanged(@Nullable OfferResponse resp) {

                Util.cancelPgDialog(dialog);
                if (resp != null) {
                    if (resp.getStatus().equals("success")) {

                        if (pageNo == 1)
                            mResult = resp.getMessage();
                        else {
                            mResult.addAll(resp.getMessage());
                        }
                        mAdapter.setData(mResult);

                    }
                } else {

                    Toast.makeText(mContext, "No result found.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void getOfferListDataFilter() {

        Util.showPgDialog(dialog);
        mViewModel.callOffersCat().observe(this, new Observer<FilterCatResponse>() {
            @Override
            public void onChanged(@Nullable FilterCatResponse resp) {
                Util.cancelPgDialog(dialog);

                cardView.setVisibility(View.VISIBLE);
                if (resp != null) {
                    if (resp != null) {
                        if (pageNo == 1)
                            mResultCat = resp.getMessage();
                        else {
                            mResultCat.addAll(resp.getMessage());
                        }
                        offerListFilterAdapter.setData(mResultCat);
                    }
                }

            }
        });

    }


    @Override
    public void onListOffer(String value) {
        alertDialog.dismiss();
        getOfferListData(value);
    }
}
