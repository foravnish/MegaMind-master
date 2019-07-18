package megamindlons.app.megamind.Activittres.resetpassword;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.Activittres.Login;
import megamindlons.app.megamind.Activittres.Password;
import megamindlons.app.megamind.Activittres.otp.OtpVerfityActivity;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.JSONResponse;
import megamindlons.app.megamind.Retrofit.UserData;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.response.OTPResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class ResetPasswordActivity extends AppCompatActivity {

    private FrameLayout mFrameCloseKeyboard;

    private FrameLayout mFrameNumber1;
    private FrameLayout mFrameNumber2;
    private FrameLayout mFrameNumber3;
    private FrameLayout mFrameNumber4;
    private FrameLayout mFrameNumber5;
    private FrameLayout mFrameNumber6;
    private FrameLayout mFrameNumber7;
    private FrameLayout mFrameNumber8;
    private FrameLayout mFrameNumber9;
    private FrameLayout mFrameNumber0;
    private FrameLayout mFrameNumberDeleteSpace;
    private FrameLayout mFrameNumberNext;

    private List<String> mListPin;

    private TextView mPin1;
    private TextView mPin2;
    private TextView mPin3;
    private TextView mPin4;

    private TextView tvYourPIN;
    Dialog dialog;
    TextView texttitle;
    private ArrayList<UserData> data;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        dialog=new Dialog(ResetPasswordActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        texttitle=findViewById(R.id.texttitle);
        texttitle.setText("Enter Your MPIN");

        mFrameCloseKeyboard = (FrameLayout) findViewById(R.id.frameLayout_close_keyboard);

        userId = getIntent().getStringExtra("userId");

        mFrameNumber1 = (FrameLayout) findViewById(R.id.frameLayout_number1);
        mFrameNumber2 = (FrameLayout) findViewById(R.id.frameLayout_number2);
        mFrameNumber3 = (FrameLayout) findViewById(R.id.frameLayout_number3);
        mFrameNumber4 = (FrameLayout) findViewById(R.id.frameLayout_number4);
        mFrameNumber5 = (FrameLayout) findViewById(R.id.frameLayout_number5);
        mFrameNumber6 = (FrameLayout) findViewById(R.id.frameLayout_number6);
        mFrameNumber7 = (FrameLayout) findViewById(R.id.frameLayout_number7);
        mFrameNumber8 = (FrameLayout) findViewById(R.id.frameLayout_number8);
        mFrameNumber9 = (FrameLayout) findViewById(R.id.frameLayout_number9);
        mFrameNumber0 = (FrameLayout) findViewById(R.id.frameLayout_number0);
        mFrameNumberDeleteSpace = (FrameLayout) findViewById(R.id.frameLayout_deletePin);
        mFrameNumberNext = (FrameLayout) findViewById(R.id.frameLayout_next);
        tvYourPIN        =  (TextView) findViewById(R.id.textview_your_pin);

        mPin1 = (TextView) findViewById(R.id.textView_pin1);
        mPin2 = (TextView) findViewById(R.id.textView_pin2);
        mPin3 = (TextView) findViewById(R.id.textView_pin3);
        mPin4 = (TextView) findViewById(R.id.textView_pin4);

        mFrameCloseKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setPin();

    }

    private void setPin(){

        mListPin = new ArrayList<>();

        mFrameNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("1");
                    conditioningPinButton();
                }else{

                }

            }
        });

        mFrameNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("2");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("3");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("4");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("5");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("6");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("7");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("8");
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("9");
                    //Toast.makeText(InputPinActivity.this, "Size : "+ mListPin.size(), Toast.LENGTH_LONG).show();
                    conditioningPinButton();
                }else{

                }
            }
        });

        mFrameNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size() <=3){
                    mListPin.add("0");
                    conditioningPinButton();
                }else{

                }
            }
        });

        /**
         * Delete pin one by one, after that, change the background of indicator
         */
        mFrameNumberDeleteSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mListPin.size() != 0){
                    mListPin.remove(mListPin.size()-1);
                    if(mListPin.size()==3){
                        mPin4.setBackgroundResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==2){
                        mPin3.setBackgroundResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==1){
                        mPin2.setBackgroundResource(R.drawable.nonselected_item);
                    }else if(mListPin.size()==0){
                        mPin1.setBackgroundResource(R.drawable.nonselected_item);
                    }
                }
            }
        });

        mFrameNumberNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListPin.size()<4){
                    Toast.makeText(ResetPasswordActivity.this, R.string.msg_complete_your_pin, Toast.LENGTH_LONG).show();
                    return;
                }
                // Toast.makeText(Password.this, R.string.msg_open_your_next_activity, Toast.LENGTH_LONG).show();

                //print your PIN
                String yourPin = "";
                for(int i=0; i<mListPin.size(); i++){
                    yourPin += mListPin.get(i);
                }

                callResetAPi(yourPin);

                tvYourPIN.setText("Your PIN : " + yourPin);
                Util.showPgDialog(dialog);




            }
        });
    }

    private void callResetAPi(String yourPin) {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<OTPResponse> userCall = service.callresetPassword(
                ""+userId,
                ""+yourPin);
        userCall.enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                Util.cancelPgDialog(dialog);

                if(response.body().getStatus().equals("success")) {

                    Toast.makeText(ResetPasswordActivity.this, "MPIN reset successfully!", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finishAffinity();

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

    private void conditioningPinButton(){
        if(mListPin.size()==1){
            mPin1.setBackgroundResource(R.drawable.selected_item);
        }else if(mListPin.size()==2){
            mPin2.setBackgroundResource(R.drawable.selected_item);
        }else if(mListPin.size()==3){
            mPin3.setBackgroundResource(R.drawable.selected_item);
        }else if(mListPin.size()==4){
            mPin4.setBackgroundResource(R.drawable.selected_item);
        }
    }




}
