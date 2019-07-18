package megamindlons.app.megamind.Activittres.forgotpassword;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;

import megamindlons.app.megamind.Activittres.otp.OtpVerfityActivity;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.ActivityForgotPasswordBinding;
import megamindlons.app.megamind.response.OTPResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    ForgotPasswordViewModel mViewModel;
    private Context mContext;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        mViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);
        mContext = this;

        dialog=new Dialog(ForgotPasswordActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utility.isNetworkAvailable(mContext)) {

                    if (!TextUtils.isEmpty(binding.editMobile.getText().toString())){

                        if (binding.editMobile.getText().length()<10){
                            Util.errorDialog(ForgotPasswordActivity.this,"Enter 10 digits Mobile Number");
                        }
                        else {
                            callForgotPasswordApi();

                        }

                    }else{
                        Utility.showSnackBar(binding.getRoot(), getString(R.string.err_check_mobile));
                    }
                } else
                    Utility.showSnackBar(binding.getRoot(), getString(R.string.err_check_interner));
            }
        });

    }

    private void callForgotPasswordApi() {


        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<OTPResponse> userCall = service.callOTP(binding.editMobile.getText().toString());
        userCall.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                Util.cancelPgDialog(dialog);
                //Log.d("onResponse", "" + response.body().getMessage());

                if(response.body().getStatus().equals("success")) {

                    Intent intent=new Intent(getApplicationContext(), OtpVerfityActivity.class);
                    intent.putExtra("mobileno", binding.editMobile.getText().toString());
                    intent.putExtra("mode", "forgotpassword");
                    intent.putExtra("OTP", ""+response.body().getOTP());
                    intent.putExtra("userId", ""+response.body().getId());
                    startActivity(intent);


                }else  if(response.body().getStatus().equals("failure")) {

                    Utility.showSnackBar(binding.getRoot(), "Mobile no not exist");
                }

            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailure", t.toString());
            }
        });


    }


}
