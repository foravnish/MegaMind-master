package megamindlons.app.megamind.Activittres;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import megamindlons.app.megamind.Activittres.forgotpassword.ForgotPasswordActivity;
import megamindlons.app.megamind.Activittres.otp.OtpVerfityActivity;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.MSG;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.response.OTPResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {

    EditText mobileNo;
    Button loginBtn;
    Dialog dialog;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileNo = findViewById(R.id.mobileNo);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPassword = findViewById(R.id.forgotPassword);

        dialog = new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mobileNo.getText().toString())) {
                    if (mobileNo.getText().length() < 10) {
                        Util.errorDialog(Login.this, "Enter 10 digits Mobile Number");
                    } else {
                        loginAPi();

                    }
                } else {
                    Util.errorDialog(Login.this, "Enter Mobile Number");
                }

            }
        });
    }

    private void loginAPi() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> userCall = service.checkMobileExist(mobileNo.getText().toString());
        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                Util.cancelPgDialog(dialog);
                Log.d("onResponse", "" + response.body().getMessage());

                if (response.body().getStatus().equals("success")) {
                    Intent intent = new Intent(getApplicationContext(), Password.class);
                    intent.putExtra("mobileNo", mobileNo.getText().toString());
                    startActivity(intent);

                    finish();
                } else if (response.body().getStatus().equals("failure")) {


                    callOtpApi();


                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailure", t.toString());
            }
        });


    }

    private void callOtpApi() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<OTPResponse> userCall = service.callOTPFOrRegister(mobileNo.getText().toString());
        userCall.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                Util.cancelPgDialog(dialog);
                // Log.d("onResponse", "" + response.body().getMessage());

                if (response.body().getStatus().equals("success")) {

                    Intent intent = new Intent(getApplicationContext(), OtpVerfityActivity.class);
                    intent.putExtra("mobileno", mobileNo.getText().toString());
                    intent.putExtra("mode", "registration");
                    intent.putExtra("OTP", "" + response.body().getOTP());
                    intent.putExtra("userId", "" + response.body().getId());
                    startActivity(intent);


                } else if (response.body().getStatus().equals("failure")) {
                    Toast.makeText(Login.this, "Please try again!", Toast.LENGTH_SHORT).show();
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
