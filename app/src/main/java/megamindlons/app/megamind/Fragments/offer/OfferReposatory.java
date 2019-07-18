package megamindlons.app.megamind.Fragments.offer;

import android.arch.lifecycle.MutableLiveData;

import megamindlons.app.megamind.Retrofit.AppRetrofit;
import megamindlons.app.megamind.response.FilterCatResponse;
import megamindlons.app.megamind.response.OfferResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferReposatory {

    public MutableLiveData<OfferResponse> callOffers(String page,String category) {

        final MutableLiveData<OfferResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().callOffers(page,category).enqueue(new Callback<OfferResponse>() {
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



    public MutableLiveData<FilterCatResponse> callOffersCat() {

        final MutableLiveData<FilterCatResponse> data = new MutableLiveData<>();

        AppRetrofit.getApiServicesOffers().callOffersCat().enqueue(new Callback<FilterCatResponse>() {
            @Override
            public void onResponse(Call<FilterCatResponse> call, Response<FilterCatResponse> response) {
                if (response == null || response.body() == null)
                    data.setValue(null);
                else
                    data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FilterCatResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}


