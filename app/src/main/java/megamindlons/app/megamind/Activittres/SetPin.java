package megamindlons.app.megamind.Activittres;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.JSONResponse;
import megamindlons.app.megamind.Retrofit.MSG;
import megamindlons.app.megamind.Retrofit.UserData;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.ModelRegistration;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;

public class SetPin extends AppCompatActivity {

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

    TextView textHeading,texttitle;
    String Mpin1,Mpin2;
    private ArrayList<UserData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        textHeading=findViewById(R.id.textHeading);
        texttitle=findViewById(R.id.texttitle);

        textHeading.setText("Registration");

        if (getIntent().getStringExtra("oldPin").equals("")) {
            texttitle.setText("Enter New MPIN");
        }
        else if (!getIntent().getStringExtra("oldPin").equals("")) {
            texttitle.setText("Enter Confirm New MPIN");
        }

        dialog=new Dialog(SetPin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        mFrameCloseKeyboard = (FrameLayout) findViewById(R.id.frameLayout_close_keyboard);

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
                    Toast.makeText(SetPin.this, R.string.msg_complete_your_pin, Toast.LENGTH_LONG).show();
                    return;
                }

                //print your PIN
                String yourPin = "";
                for(int i=0; i<mListPin.size(); i++){
                    yourPin += mListPin.get(i);
                }

                //loginApi(yourPin);

                if (getIntent().getStringExtra("oldPin").equals("")){
                    Intent intent=new Intent(SetPin.this,SetPin.class);
                    intent.putExtra("oldPin",yourPin);
                    startActivity(intent);
                    texttitle.setText("Confirm MPIN");
                }
                else  if (!getIntent().getStringExtra("oldPin").equals("")){
                    Mpin1=getIntent().getStringExtra("oldPin");
                    Mpin2 =yourPin;

                    Log.d("fdgdfgdfgdfgdfg1",Mpin1);
                    Log.d("fdgdfgdfgdfgdfg2",Mpin2);

                    if (Mpin1.equals(Mpin2)){
                        //Toast.makeText(getApplicationContext(), "same", Toast.LENGTH_SHORT).show();
                        RegsiterUser();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "MPIN not same", Toast.LENGTH_SHORT).show();
                    }
                }

               // RegsiterUser();

                tvYourPIN.setText("Your PIN : " + yourPin);

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



    private void RegsiterUser() {

        Util.showPgDialog(dialog);


        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> userCall = service.registration(ModelRegistration.getName().toString(),ModelRegistration.getMobile().toString(),
                ModelRegistration.getEmail().toString(),ModelRegistration.getOccupation().toString(),ModelRegistration.getPancard_no().toString(),
                ModelRegistration.getAadharcard_no().toString(),ModelRegistration.getDob().toString(),ModelRegistration.getState_id().toString(),
                ModelRegistration.getCity_id().toString(),ModelRegistration.getGender().toString(),Mpin2, ModelRegistration.getRefferCOdeMenual().toString());

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                Util.cancelPgDialog(dialog);
                Log.d("onResponseRegistration", "" + response.body().getMessage());

                if(response.body().getStatus().equals("success")) {


                    loginApi(ModelRegistration.getMobile(),Mpin2);

//                    Intent intent=new Intent(getApplicationContext(),Login.class);
//                    startActivity(intent);


                    //finish();
                }else  if(response.body().getStatus().equals("failure")) {
                    Util.errorDialog(SetPin.this,response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailureRegistration", t.toString());
            }
        });



//        RequestQueue queue = Volley.newRequestQueue(SetPin.this);
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                Api.registration, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Util.cancelPgDialog(dialog);
//                Log.e("PaswordResponse:", " " + response);
//
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    // if (jsonObject.getString("status").equalsIgnoreCase("success")){
//
//                    if (jsonObject.optString("status").equals("success")) {
//
//                        Intent intent=new Intent(getApplicationContext(),Login.class);
//                        startActivity(intent);
//
//                    }
//                    if (jsonObject.optString("status").equals("failure")) {
//
//                        Util.errorDialog(SetPin.this,jsonObject.getString("message"));
//
//
//                    }
//                    else{
//                        Toast.makeText(getApplicationContext(),jsonObject.getString("message") , Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Util.cancelPgDialog(dialog);
//                Log.e("PasswordError: ", "" + error.getMessage());
//                Toast.makeText(getApplicationContext(),"Please Connect to the Internet or Wrong Password", Toast.LENGTH_LONG).show();
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<>();
//                params.put("fname", ModelRegistration.getName().toString());
//                params.put("mobile", ModelRegistration.getMobile().toString());
//                params.put("email", ModelRegistration.getEmail().toString());
//                params.put("occupation", ModelRegistration.getOccupation().toString());
//                params.put("pancard_no", ModelRegistration.getPancard_no().toString());
//                params.put("aadharcard_no", ModelRegistration.getAadharcard_no().toString());
//                params.put("dob", ModelRegistration.getDob().toString());
//                params.put("state_id",ModelRegistration.getState_id().toString());
//                params.put("city_id",ModelRegistration.getCity_id().toString());
//                params.put("gender", ModelRegistration.getGender().toString());
//                params.put("password", Mpin2);
//
//                Log.d("fname", ModelRegistration.getName().toString());
//                Log.d("mobile", ModelRegistration.getMobile().toString());
//                Log.d("email", ModelRegistration.getEmail().toString());
//                Log.d("occupation", ModelRegistration.getOccupation().toString());
//                Log.d("pancard_no", ModelRegistration.getPancard_no().toString());
//                Log.d("aadharcard_no", ModelRegistration.getAadharcard_no().toString());
//                Log.d("dob", ModelRegistration.getDob().toString());
//                Log.d("state_id",ModelRegistration.getState_id().toString());
//                Log.d("city_id",ModelRegistration.getCity_id().toString());
//                Log.d("gender", ModelRegistration.getGender().toString());
//                Log.d("password", Mpin2);
//
//                return params;
//            }
//
////                        @Override
////                        public Map<String, String> getHeaders() throws AuthFailureError {
////                            Log.e("fdgdfgdfgdfg","Inside getHeaders()");
////                            Map<String,String> headers=new HashMap<>();
////                            headers.put("Content-Type","application/x-www-form-urlencoded");
////                            return headers;
////                        }
//        };
//        // Adding request to request queue
//        strReq.setShouldCache(false);
//        queue.add(strReq);

    }


    private void loginApi(String mobile, String mPin) {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);

        Call<JSONResponse> call=service.login(mobile,mPin);
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, retrofit2.Response<JSONResponse> response) {
                Util.cancelPgDialog(dialog);

                JSONResponse jsonResponse = response.body();

                data = new ArrayList<>(Arrays.asList(jsonResponse.getData()));

                Log.d("sdfsdfsdf", String.valueOf(data));

                Log.d("fgdgdgdfgdfg",response.body().getStatus());

                if (response.body().getStatus().equals("success")) {
                    for (UserData e : data) {
                        Log.d("fsdgfsgfsdgdfg", String.valueOf(e.getName()));

                        MyPrefrences.setUserLogin(getApplicationContext(), true);
                        MyPrefrences.setUserID(getApplicationContext(), e.getId().toString());
                        MyPrefrences.setUSENAME(getApplicationContext(), e.getName().toString());
                        MyPrefrences.setDOB(getApplicationContext(), e.getDob().toString());
                        MyPrefrences.setUserImage(getApplicationContext(),e.getImage_thumb());
                        MyPrefrences.setMobile(getApplicationContext(), e.getMobile().toString());
                        //  MyPrefrences.setImage(getApplicationContext(),jsonObject1.optString("image").toString());
                        //MyPrefrences.setCityID2(getApplicationContext(),jsonObject1.optString("city_id").toString());
                        MyPrefrences.setMyRefrel(getApplicationContext(),e.getReferer_code().toString());

                        MyPrefrences.setEMAILID(getApplicationContext(), e.getEmail().toString());

                        Intent intent=new Intent(getApplicationContext(),PopupForRegistrationActivity.class);
                        startActivity(intent);
                        finish();

//                        Intent intent = new Intent(getApplicationContext(), HomeAct.class);
//                        startActivity(intent);
//                        finish();
                    }
                }
                else  if (response.body().getStatus().equals("failure")) {

                    Util.errorDialog(SetPin.this,""+response.body().getStatus().equals("message"));

                }



            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("sdfsdfbnvnnsdf", String.valueOf(t));



                Intent intent=new Intent(getApplicationContext(),Password.class);
                intent.putExtra("mobileNo",getIntent().getStringExtra("mobileNo"));
                startActivity(intent);
                finish();
                Toast.makeText(SetPin.this, "Please enter valid credential", Toast.LENGTH_SHORT).show();
//
            }
        });


    }


}
