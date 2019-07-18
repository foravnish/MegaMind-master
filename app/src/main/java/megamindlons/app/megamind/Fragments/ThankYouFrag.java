package megamindlons.app.megamind.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.databinding.FragmentThankYouBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThankYouFrag extends Fragment {


    public ThankYouFrag() {
        // Required empty public constructor
    }

    FragmentThankYouBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_thank_you, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thank_you, container, false);

        HomeAct.title.setText("Thank you");

        binding.deshboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddLeads();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

            }
        });

        return  binding.getRoot();
    }

}
