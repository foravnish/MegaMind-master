package megamindlons.app.megamind.Activittres.profile;

import android.arch.lifecycle.MutableLiveData;

import megamindlons.app.megamind.Retrofit.AppRetrofit;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ProfileUploadResponse;
import megamindlons.app.megamind.response.UserDataResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileReposatory {

    public MutableLiveData<ProfileUploadResponse> postImage(MultipartBody.Part page, RequestBody category) {

        final MutableLiveData<ProfileUploadResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().postImage(page,category).enqueue(new Callback<ProfileUploadResponse>() {
            @Override
            public void onResponse(Call<ProfileUploadResponse> call, Response<ProfileUploadResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProfileUploadResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public MutableLiveData<OfferResponse> uploadDoc(MultipartBody.Part panImage, RequestBody panNo,
                                                    MultipartBody.Part aadharImage, RequestBody aadharNo,
                                                    MultipartBody.Part voterImage, RequestBody voterNo,RequestBody userId) {

        final MutableLiveData<OfferResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().uploadDoc(panImage,panNo,aadharImage,aadharNo,voterImage,voterNo,userId).enqueue(new Callback<OfferResponse>() {
            @Override
            public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<OfferResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


    public MutableLiveData<UserDataResponse> callUserData(String userId) {

        final MutableLiveData<UserDataResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().callUserData(userId).enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}
