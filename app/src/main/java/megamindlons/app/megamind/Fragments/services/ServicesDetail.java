package megamindlons.app.megamind.Fragments.services;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.Fragments.AddLeads;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.FragmentServicesDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesDetail extends Fragment {


    private Context mContext;

    FragmentServicesDetailBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_services_detail, container, false);
        mContext = getContext();

        HomeAct.title.setText(""+getArguments().getString("serName"));
        HomeAct.imageView.setVisibility(View.GONE);


        binding.txtDetail.setText(""+ Html.fromHtml(getArguments().getString("serDesc")));
        Utility.setImageViaGlide(binding.imgDelServices, getArguments().getString("serImage"));
        binding.addLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddLeads();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("serName",""+getArguments().getString("serName"));
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                fragment.setArguments(bundle);
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });
        return binding.getRoot();

    }

}
