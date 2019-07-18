package megamindlons.app.megamind.Fragments.offer;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import megamindlons.app.megamind.response.FilterCatResponse;
import megamindlons.app.megamind.response.OfferResponse;

public class OfferViewModel extends ViewModel {
    OfferReposatory mRepo;

    public OfferViewModel(){
        mRepo=new OfferReposatory();
    }

    public MutableLiveData<OfferResponse> callOffers(String page,String category) {
        return mRepo.callOffers(page,category);
    }

    public MutableLiveData<FilterCatResponse> callOffersCat() {
        return mRepo.callOffersCat();
    }
}
