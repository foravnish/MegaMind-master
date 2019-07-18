package megamindlons.app.megamind.Activittres.forgotpassword;

import android.arch.lifecycle.ViewModel;

public class ForgotPasswordViewModel extends ViewModel {

    ForgotPasswordReposatory mRepo;

    public ForgotPasswordViewModel(){
        mRepo=new ForgotPasswordReposatory();
    }

}
