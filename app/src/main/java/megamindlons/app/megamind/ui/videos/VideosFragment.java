package megamindlons.app.megamind.ui.videos;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.adapter.VideoAdapter;
import megamindlons.app.megamind.databinding.FragmentVideosBinding;
import megamindlons.app.megamind.response.VideosResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {


    public VideosFragment() {
        // Required empty public constructor
    }

    FragmentVideosBinding binding;
    private Context mContext;
    VideoAdapter mAdapter;
    VideoViewModel mViewModel;
    private List<VideosResponse.MessageItem> mResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false);
        mViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        mContext = getContext();
        setTransAdapter();

        if (Utility.isNetworkAvailable(mContext)) {
            getVideosData();
        } else
            Utility.showSnackBar(binding.getRoot(), getString(R.string.err_check_interner));

        HomeAct.title.setText("Video Tutorial");
        HomeAct.imageView.setVisibility(View.GONE);

        return binding.getRoot();
    }
    private void setTransAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
        RecyclerView recyclerView = binding.transRecyclerView;
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VideoAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
//        Utility.setRecyclerViewAnimation(recyclerView, mContext);
    }

    private void getVideosData() {

        Utility.showLoadingView(true, binding.loadingView.loadingIndicator, binding.loadingView.container);

        mViewModel.callVideos().observe(this, new Observer<VideosResponse>() {
            @Override
            public void onChanged(@Nullable VideosResponse notificationResponse) {
                Utility.showLoadingView(false, binding.loadingView.loadingIndicator, binding.loadingView.container);
                if (notificationResponse.getStatus().equalsIgnoreCase("success")&& notificationResponse!=null){
                    mResult=notificationResponse.getMessage();
                    mAdapter.setData(mResult);
                }
                else{
                    Utility.showSnackBar(binding.getRoot(),"No videos found");
                }
            }
        });

    }

}
