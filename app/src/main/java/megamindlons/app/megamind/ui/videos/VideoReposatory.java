package megamindlons.app.megamind.ui.videos;

import android.arch.lifecycle.MutableLiveData;

import megamindlons.app.megamind.Retrofit.AppRetrofit;
import megamindlons.app.megamind.response.VideosResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoReposatory {

    public MutableLiveData<VideosResponse> callVideos() {

        final MutableLiveData<VideosResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServices().callVideos().enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
