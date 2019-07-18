package megamindlons.app.megamind.Activittres.profile;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ProfileUploadResponse;
import megamindlons.app.megamind.response.UserDataResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileViewModel extends ViewModel {
    ProfileReposatory mRepo;

    public ProfileViewModel(){
        mRepo=new ProfileReposatory();
    }

    public MutableLiveData<ProfileUploadResponse> postImage(MultipartBody.Part page, RequestBody category) {
        return mRepo.postImage(page,category);
    }

    public MutableLiveData<OfferResponse> uploadDoc(MultipartBody.Part panImage, RequestBody panNo,
                                                    MultipartBody.Part aadharImage, RequestBody aadharNo,
                                                    MultipartBody.Part voterImage, RequestBody voterNo,RequestBody userId) {
        return mRepo.uploadDoc(panImage,panNo,aadharImage,aadharNo,voterImage,voterNo,userId);
    }

    public MutableLiveData<UserDataResponse> callUserData(String userId) {
        return mRepo.callUserData(userId);
    }
}
