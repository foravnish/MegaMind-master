package megamindlons.app.megamind.Fragments.services;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ServicesResponse;

public class ServiceViewModel extends ViewModel {
    ServiceReposatory mRepo;

    public ServiceViewModel(){
        mRepo=new ServiceReposatory();
    }

    public MutableLiveData<ServicesResponse> callServices() {
        return mRepo.callServices();
    }
}
