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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.ModelRegistration;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;

public class Registration extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner spinDate,spinMonth,spinYear,spinGender,spinCity,spinState,spinOccupation;
    Button submitLogin;
    EditText mobileNo,namePerson,emailD,panCard,aadharCard,refferalId;
    CheckBox checkBox;
    ArrayAdapter adapterDate;
    ArrayAdapter adapterMonth;
    ArrayAdapter adapterYear;
    ArrayAdapter adapterspinGender;
    ArrayAdapter adapterspinOccupation;

    List<String> dateList = new ArrayList<String>(); ArrayAdapter adapterState;
    ArrayAdapter adapterCity;
    List<String> monthList = new ArrayList<String>();
    List<String> yearList = new ArrayList<String>();
    List<String> genderList = new ArrayList<String>();
    List<String> occList = new ArrayList<String>();
    List<String> stateList = new ArrayList<String>();
    List<String> cityList = new ArrayList<String>();

    Dialog dialog;
    List<HashMap<String,String>> AllStateList ;
    List<HashMap<String,String>> AllCity ;
    String idstate="",idcity="";


    String gender;
    String DayVal,MonVal,Yearval;
    Boolean mCheckBobValue = false;
    Boolean flagGender,flagDate,flagMonth,flagYear;
    String strOccupation;
    TextView tncBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        spinYear=findViewById(R.id.spinYear);
        spinMonth=findViewById(R.id.spinMonth);
        spinDate=findViewById(R.id.spinDate);
        spinGender=findViewById(R.id.spinGender);
        spinState=findViewById(R.id.spinState);
        spinOccupation=findViewById(R.id.spinOccupation);
        spinCity=findViewById(R.id.spinCity);
        submitLogin=findViewById(R.id.submitLogin);
        mobileNo=findViewById(R.id.mobileNo);
        namePerson=findViewById(R.id.namePerson);
        refferalId=findViewById(R.id.refferalId);

        emailD=findViewById(R.id.emailD);
        panCard=findViewById(R.id.panCard);
        aadharCard=findViewById(R.id.aadharCard);
        checkBox=findViewById(R.id.checkBox);
        tncBtn=findViewById(R.id.tncBtn);
        dialog=new Dialog(Registration.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);


        dateList.clear();
        dateList.add("Day");
        for (int i=1;i<=31;i++){
            dateList.add(String.valueOf(i));
        }

        spinDate.setSelection(1);
        adapterDate = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item,dateList);
        adapterDate.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinDate.setAdapter(adapterDate);

        AllStateList = new ArrayList<>();
        AllCity = new ArrayList<>();


        if (getIntent().hasExtra("mobileNo")) {
            mobileNo.setText(getIntent().getStringExtra("mobileNo"));
        }

        monthList.clear();
        monthList.add("Month");
        monthList.add("January");
        monthList.add("February");
        monthList.add("March");
        monthList.add("April");
        monthList.add("May");
        monthList.add("June");
        monthList.add("July");
        monthList.add("August");
        monthList.add("September");
        monthList.add("October");
        monthList.add("November");
        monthList.add("December");

        spinMonth.setSelection(1);
        adapterMonth = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item,monthList);
        adapterMonth.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinMonth.setAdapter(adapterMonth);


        refferalId.setText(""+ MyPrefrences.getRefer(getApplicationContext()));

        yearList.clear();
        yearList.add("Year");

        int year=Calendar.getInstance().get(Calendar.YEAR);
        for (int i =year; i>1947; i--){
            yearList.add(String.valueOf(i));
        }
        spinYear.setSelection(1);
        adapterYear = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item,yearList);
        adapterYear.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinYear.setAdapter(adapterYear);


        genderList.clear();
        genderList.add("Select Gender");
        genderList.add("Male");
        genderList.add("Female");

        occList.clear();
        occList.add("Select Occupation");
        occList.add("Salaried");
        occList.add("Non Salaried");


        spinGender.setSelection(1);
        adapterspinGender = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item_left,genderList);
        adapterspinGender.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinGender.setAdapter(adapterspinGender);


        adapterspinOccupation = new ArrayAdapter(getApplicationContext(), R.layout.simple_spinner_item_left, occList);
        adapterspinOccupation.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinOccupation.setAdapter(adapterspinOccupation);

        tncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,TermConditionActivity.class);
                startActivity(intent);
            }
        });

        spinOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strOccupation = String.valueOf(spinOccupation.getSelectedItem());
                Log.d("dfsdfsdfsdfsdf", strOccupation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCheckBobValue=true;
                }else{
                    mCheckBobValue=false;
                }
            }
        });
        spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("dfsdfsdfsdfsdf",spinGender.getSelectedItem().toString());
                //gender=spinGender.getSelectedItem().toString();
                int pos =spinGender.getSelectedItemPosition();
                if (pos!=0){
                    flagGender=true;
                }else{
                    flagGender=false;
                }
                if (pos==1){
                    gender="0";
                }else if (pos==2){
                    gender="1";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("dfsdfsdfsdfsdf",spinGender.getSelectedItem().toString());
                DayVal=spinDate.getSelectedItem().toString();
                int pos =spinDate.getSelectedItemPosition();
                if (pos!=0){
                    flagDate=true;
                }else{
                    flagDate=false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("dfsdfsdfsdfsdf",spinGender.getSelectedItem().toString());
                MonVal=spinMonth.getSelectedItem().toString();

                int pos =spinMonth.getSelectedItemPosition();
                if (pos!=0){
                    flagMonth=true;
                }else{
                    flagMonth=false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("dfsdfsdfsdfsdf",spinGender.getSelectedItem().toString());
                Yearval=spinYear.getSelectedItem().toString();

                int pos =spinYear.getSelectedItemPosition();
                if (pos!=0){
                    flagYear=true;
                }else{
                    flagYear=false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getStateData();

        spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!AllStateList.get(i).get("id").equals("")) {
                    idstate=AllStateList.get(i).get("id");
                    cityListData(idstate);
                    Log.d("sdfsdfsdfsd",idstate);
                    Util.showPgDialog(dialog);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!AllCity.get(i).get("id").equals("")) {
                    idcity=AllCity.get(i).get("id");
                    Log.d("sdfsdfsdfsd",idcity);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){

                    ModelRegistration.setName(namePerson.getText().toString());
                    ModelRegistration.setRefferCOdeMenual(""+refferalId.getText().toString());
                    ModelRegistration.setMobile(mobileNo.getText().toString());
                    ModelRegistration.setEmail(emailD.getText().toString());
                    ModelRegistration.setOccupation(strOccupation);
                    ModelRegistration.setPancard_no(panCard.getText().toString());
                    ModelRegistration.setAadharcard_no(aadharCard.getText().toString());
                    ModelRegistration.setDob(DayVal+"-"+MonVal+"-"+Yearval);
                    ModelRegistration.setState_id(idstate);
                    ModelRegistration.setCity_id(idcity);
                    ModelRegistration.setGender(gender);


                    Intent intent=new Intent(Registration.this,SetPin.class);
                    intent.putExtra("oldPin","");
                    startActivity(intent);


//                    RegsiterUser();

//                    Intent intent=new Intent(getApplicationContext(),HomeAct.class);
//                    startActivity(intent);

                }
            }
        });
    }

    private void cityListData(String stateId) {

        Log.d("gfsdgsdgdgdsfgd",stateId);
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.activeCitiesList+"?stateId="+stateId, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Resposecategory", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")){

                        AllCity.clear();
                        cityList.clear();

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("id", "");
                        AllCity.add(map2);
                        cityList.add("Select City");


                        JSONArray jsonArray=response.getJSONArray("message");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);

                            HashMap<String,String> map=new HashMap<>();

                            map.put("id", jsonObject.optString("id"));
                            map.put("city", jsonObject.optString("city"));

                            cityList.add(jsonObject.optString("city"));

                            adapterCity = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item_left,cityList);
                            adapterCity.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            spinCity.setAdapter(adapterCity);

                            AllCity.add(map);

                        }
                    }

                    else{
                        cityList.clear();
                        cityList.add("City not available");
                        adapterCity = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item_left,cityList);
                        adapterCity.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        spinCity.setAdapter(adapterCity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    private void getStateData() {

         Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.activeStatesList, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Resposecategory", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")){

                        AllStateList.clear();
                        stateList.clear();

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("id", "");
                        AllStateList.add(map2);
                        stateList.add("Select State");


                        JSONArray jsonArray=response.getJSONArray("message");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);

                            HashMap<String,String> map=new HashMap<>();

                            map.put("id", jsonObject.optString("id"));
                            map.put("state", jsonObject.optString("state"));

                            stateList.add(jsonObject.optString("state"));

                            adapterState = new ArrayAdapter(getApplicationContext(),R.layout.simple_spinner_item_left,stateList);
                            adapterState.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            spinState.setAdapter(adapterState);

                            AllStateList.add(map);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


    private boolean validate(){

        if (TextUtils.isEmpty(namePerson.getText().toString()))
        {
            Util.errorDialog(Registration.this,"Enter your name");
            return false;
        }
        else if (!flagGender){
            Util.errorDialog(Registration.this,"Select gender");
            return false;
        }
        else if (!flagDate){
            Util.errorDialog(Registration.this,"Select birth day");
            return false;
        }
        else if (!flagMonth){
            Util.errorDialog(Registration.this,"Select birth month");
            return false;
        }
        else if (!flagYear){
            Util.errorDialog(Registration.this,"Select birth year");
            return false;
        }
        else if (TextUtils.isEmpty(mobileNo.getText().toString()))
        {
            Util.errorDialog(Registration.this,"Enter mobile no.");
            return false;
        }
        else if (mobileNo.getText().toString().length()<10)
        {
            Util.errorDialog(Registration.this,"Enter 10 digist mobile  no.");
            return false;
        }

//        else if (TextUtils.isEmpty(emailD.getText().toString()))
//        {
//            Util.errorDialog(Registration.this,"Enter email id");
//            return false;
//        }
//        else if (!emailD.getText().toString().trim().matches(emailPattern)) {
//            Util.errorDialog(Registration.this, "Enter valid email id.");
//            return false;
//        }

        else if (strOccupation.equalsIgnoreCase("Select Occupation"))
        {
            Util.errorDialog(Registration.this,"Enter occupation");
            return false;
        }
//        else if (TextUtils.isEmpty(panCard.getText().toString()))
//        {
//            Util.errorDialog(Registration.this,"Enter pan card No" );
//            return false;
//        }
//        else if (TextUtils.isEmpty(aadharCard.getText().toString()))
//        {
//            Util.errorDialog(Registration.this,"Enter aadhar card no" );
//            return false;
//        }
        else if (idstate.equals(""))
        {
            Util.errorDialog(Registration.this,"Select state");
            return false;
        }
        else if (idcity.equals(""))
        {
            Util.errorDialog(Registration.this,"Select city");
            return false;
        }
        else if (gender.equals(""))
        {
            Util.errorDialog(Registration.this,"Select gender");
            return false;
        }

        else if (!mCheckBobValue){
            Util.errorDialog(Registration.this,"Accept Terms and conditions");
            return false;
        }

        return true;

    }



}
