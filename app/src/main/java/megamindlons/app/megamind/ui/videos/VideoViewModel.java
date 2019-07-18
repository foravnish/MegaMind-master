package megamindlons.app.megamind.ui.videos;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import megamindlons.app.megamind.response.VideosResponse;

public class VideoViewModel extends ViewModel {

    VideoReposatory mRepo;

    public VideoViewModel(){
        mRepo=new VideoReposatory();
    }

    public MutableLiveData<VideosResponse> callVideos() {
        return mRepo.callVideos();
    }
}
