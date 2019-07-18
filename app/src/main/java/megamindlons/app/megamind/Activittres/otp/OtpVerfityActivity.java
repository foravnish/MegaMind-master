package megamindlons.app.megamind.Activittres.otp;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import megamindlons.app.megamind.Activittres.Login;
import megamindlons.app.megamind.Activittres.Registration;
import megamindlons.app.megamind.Activittres.forgotpassword.ForgotPasswordActivity;
import megamindlons.app.megamind.Activittres.forgotpassword.ForgotPasswordViewModel;
import megamindlons.app.megamind.Activittres.resetpassword.ResetPasswordActivity;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.ActivityForgotPasswordBinding;
import megamindlons.app.megamind.databinding.ActivityOtpVerfityBinding;
import megamindlons.app.megamind.response.OTPResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class OtpVerfityActivity extends AppCompatActivity {
    ActivityOtpVerfityBinding binding;
    ForgotPasswordViewModel mViewModel;
    private Context mContext;
    String otpVal;
    String mode, otp,userId;
    String mobileNo;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verfity);
        mViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);
        mContext = this;
        mode = getIntent().getStringExtra("mode");
        mobileNo = getIntent().getStringExtra("mobileno");
        otp = getIntent().getStringExtra("OTP");
        userId = getIntent().getStringExtra("userId");

        dialog=new Dialog(OtpVerfityActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        binding.getMobileNo.setText("+91 " + getIntent().getStringExtra("mobileno"));

        binding.resetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callResetOTP();

            }
        });
        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (otpVal!=null){

                if (otpVal == "") {
                    Log.d("OTPVALUE", otpVal);
                    Utility.showSnackBar(binding.getRoot(), "Please Enter one time password");
                } else if (otpVal.length() < 4) {
                    Utility.showSnackBar(binding.getRoot(), "The one-time code you have entered in invalid");
                } else {

                    if (mode.equalsIgnoreCase("registration")) {


                        if (otp.equalsIgnoreCase(otpVal)) {
                            Intent intent = new Intent(getApplicationContext(), Registration.class);
                            intent.putExtra("mobileNo", mobileNo);
                            startActivity(intent);
                        }else{
                            Utility.showSnackBar(binding.getRoot(), "Entered OTP is invalid");
                        }

                    } else {

                        if (otp.equalsIgnoreCase(otpVal)) {
                            Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                            intent.putExtra("userId", ""+userId);
                            startActivity(intent);
                        }else{
                            Utility.showSnackBar(binding.getRoot(), "Entered OTP is invalid");
                        }

                    }
                }

                }
                else{
                    Utility.showSnackBar(binding.getRoot(), "Please Enter one time password");
                }

            }
        });

        binding.pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                otpVal = editable.toString();
            }
        });
    }

    private void callResetOTP() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<OTPResponse> userCall = service.callOTP(mobileNo);
        userCall.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                Util.cancelPgDialog(dialog);
                //Log.d("onResponse", "" + response.body().getMessage());

                if(response.body().getStatus().equals("success")) {


                    otp= String.valueOf(response.body().getOTP());

                }else  if(response.body().getStatus().equals("failure")) {


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
