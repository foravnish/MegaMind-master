package megamindlons.app.megamind.Fragments.services;

import android.arch.lifecycle.MutableLiveData;

import megamindlons.app.megamind.Retrofit.AppRetrofit;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ServicesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceReposatory {

    public MutableLiveData<ServicesResponse> callServices() {

        final MutableLiveData<ServicesResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().callServices().enqueue(new Callback<ServicesResponse>() {
            @Override
            public void onResponse(Call<ServicesResponse> call, Response<ServicesResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ServicesResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
