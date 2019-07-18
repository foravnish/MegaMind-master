package megamindlons.app.megamind.Fragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.MSG;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddLeads extends Fragment {


    public AddLeads() {
        // Required empty public constructor
    }

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner spinGender, spinCity, spinState, Services,spinOccupation;
    ArrayAdapter adapterspinGender;
    ArrayAdapter adapterspinOccupation;
    List<String> genderList = new ArrayList<String>();
    List<String> occList = new ArrayList<String>();
    String gender;
    String strOccupation;
    List<HashMap<String, String>> AllStateList;
    List<HashMap<String, String>> AllServices;
    List<HashMap<String, String>> AllCity;
    String idstate = "", idcity = "", idServices = "";
    Dialog dialog;
    List<String> stateList = new ArrayList<String>();
    List<String> servivesList = new ArrayList<String>();
    List<String> cityList = new ArrayList<String>();
    ArrayAdapter adapterState;
    ArrayAdapter adapterServices;
    ArrayAdapter adapterCity;
    EditText name, age, emailId, mobileNO, pType, loanAmt, refName, comment;
    Button submitLoan;
    TextView emptAmount;
    Double amount;
    Double comm=0.0;
    Boolean flag=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_leads, container, false);

        HomeAct.title.setText("Add New Lead");
        HomeAct.imageView.setVisibility(View.GONE);


        genderList.clear();
        genderList.add("Male");
        genderList.add("Female");

        occList.clear();
        occList.add("Select Occupation");
        occList.add("Salaried");
        occList.add("Non Salaried");


        AllServices = new ArrayList<>();
        AllStateList = new ArrayList<>();
        AllCity = new ArrayList<>();

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        spinGender = view.findViewById(R.id.spinGender);
        spinState = view.findViewById(R.id.spinState);
        spinCity = view.findViewById(R.id.spinCity);
        Services = view.findViewById(R.id.Services);
        emptAmount = view.findViewById(R.id.emptAmount);

        name = view.findViewById(R.id.name);
        age = view.findViewById(R.id.age);
        emailId = view.findViewById(R.id.emailId);
        mobileNO = view.findViewById(R.id.mobileNO);
        pType = view.findViewById(R.id.pType);
        spinOccupation = view.findViewById(R.id.spinOccupation);
        loanAmt = view.findViewById(R.id.loanAmt);
        refName = view.findViewById(R.id.refName);
        comment = view.findViewById(R.id.comment);
        submitLoan = view.findViewById(R.id.submitLoan);

        adapterspinGender = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, genderList);
        adapterspinGender.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinGender.setAdapter(adapterspinGender);

        adapterspinOccupation = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, occList);
        adapterspinOccupation.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinOccupation.setAdapter(adapterspinOccupation);


        Bundle arguments = getArguments();

        if (arguments != null &&  arguments.containsKey("serName")){
            String name=getArguments().getString("serName");
            Log.d("sdfsdfsdfsd",name);

        }

        getStateData();

        getServices();

        Services.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!AllServices.get(i).get("id").equals("")) {
                    idServices = AllServices.get(i).get("id");

                    Log.d("sdfsdfsdfsd", idServices);
                    getExpectedAmount(idServices);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!AllStateList.get(i).get("id").equals("")) {
                    idstate = AllStateList.get(i).get("id");
                    cityListData(idstate);
                    Log.d("sdfsdfsdfsd", idstate);
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
                    idcity = AllCity.get(i).get("id");
                    Log.d("sdfsdfsdfsd", idcity);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                gender = String.valueOf(spinGender.getSelectedItem());
                Log.d("dfsdfsdfsdfsdf", gender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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



        submitLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    addLead();

                }
            }
        });


        loanAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (flag ){
                    if (amount!=null) {
                        amount = Double.parseDouble(charSequence.toString());
                        Double calComm = comm * amount / 100;
                        emptAmount.setText("" + calComm);
                    }
                }else{
                    emptAmount.setText(""+comm);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void getExpectedAmount(String idServices) {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReqAmount = new JsonObjectRequest(Request.Method.GET,
                Api.accountData+"?agent_id="+MyPrefrences.getUserID(getActivity())+"&service_id="+idServices, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeAmount", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        JSONArray jsonArray = response.getJSONArray("message");
                            JSONObject jsonObject = jsonArray.getJSONObject(1);

                        comm=Double.parseDouble(jsonObject.getString("user_comm"));

                            if (jsonObject.getString("c_type").equalsIgnoreCase("Percentage")){
                                flag=true;
                                if (!TextUtils.isEmpty(loanAmt.getText().toString())) {
                                    amount = Double.parseDouble(loanAmt.getText().toString());
                                    Double calComm=comm*amount/100;
                                    emptAmount.setText(""+calComm);
                                }
                            }else {
                                flag=false;
                                emptAmount.setText(""+comm);
                            }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);

            }
        });

        // Adding request to request queue
        jsonObjReqAmount.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReqAmount);




    }


    private void addLead() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> userCall = service.postNewUserLead(
//                "1",
                MyPrefrences.getUserID(getActivity()),
                gender,
                idstate,
                idcity,
                idServices,
                name.getText().toString(),
                age.getText().toString(),
                comment.getText().toString(),
                emailId.getText().toString(),
                strOccupation,
                loanAmt.getText().toString(),
                mobileNO.getText().toString());

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                Util.cancelPgDialog(dialog);
                // Log.d("onResponseLeads", "" + response.body().getMessage());

                if (response.body().getStatus().equals("success")) {

//                    Util.errorDialog(getActivity(), response.body().getMessage());
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    Fragment fragment = new ThankYouFrag();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);


                } else if (response.body().getStatus().equals("failure")) {
                    Util.errorDialog(getActivity(), response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailureLeads", t.toString());
            }
        });

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

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        AllStateList.clear();
                        stateList.clear();

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("id", "");
                        AllStateList.add(map2);
                        stateList.add("Select State");


                        JSONArray jsonArray = response.getJSONArray("message");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            HashMap<String, String> map = new HashMap<>();

                            map.put("id", jsonObject.optString("id"));
                            map.put("state", jsonObject.optString("state"));

                            stateList.add(jsonObject.optString("state"));

                            adapterState = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, stateList);
                            adapterState.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            spinState.setAdapter(adapterState);

                            AllStateList.add(map);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);


    }


    private void getServices() {

        //  Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.activeServices, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Resposecategory", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        AllServices.clear();
                        servivesList.clear();

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("id", "");
                        AllServices.add(map2);
                        servivesList.add("Select Services");


                        JSONArray jsonArray = response.getJSONArray("message");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            HashMap<String, String> map = new HashMap<>();

                            map.put("id", jsonObject.optString("id"));
                            map.put("title", jsonObject.optString("title"));

                            servivesList.add(jsonObject.optString("title"));

                            adapterServices = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, servivesList);
                            adapterServices.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            Services.setAdapter(adapterServices);

                            AllServices.add(map);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);


    }


    private void cityListData(String stateId) {

        Log.d("gfsdgsdgdgdsfgd", stateId);
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.activeCitiesList + "?stateId=" + stateId, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Resposecategory", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        AllCity.clear();
                        cityList.clear();

                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("id", "");
                        AllCity.add(map2);
                        cityList.add("Select City");


                        JSONArray jsonArray = response.getJSONArray("message");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            HashMap<String, String> map = new HashMap<>();

                            map.put("id", jsonObject.optString("id"));
                            map.put("city", jsonObject.optString("city"));

                            cityList.add(jsonObject.optString("city"));

                            adapterCity = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, cityList);
                            adapterCity.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            spinCity.setAdapter(adapterCity);

                            AllCity.add(map);

                        }
                    } else {
                        cityList.clear();
                        cityList.add("City not available");
                        adapterCity = new ArrayAdapter(getActivity(), R.layout.simple_spinner_item_left, cityList);
                        adapterCity.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                        spinCity.setAdapter(adapterCity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "Error! Please Connect to the internet.", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


    private boolean validate() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Name");
            return false;
        } else if (TextUtils.isEmpty(age.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Age");
            return false;
//        } else if (TextUtils.isEmpty(emailId.getText().toString())) {
//            Util.errorDialog(getActivity(), "Enter Email Id");
//            return false;
//        } else if (!emailId.getText().toString().trim().matches(emailPattern)) {
//            Util.errorDialog(getActivity(), "Enter valid Email Id.");
//            return false;
        } else if (TextUtils.isEmpty(mobileNO.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Mobile No");
            return false;
        } else if (mobileNO.getText().toString().length() < 10) {
            Util.errorDialog(getActivity(), "Enter 10 digit Mobile No");
            return false;
        } else if (idstate.equals("")) {
            Util.errorDialog(getActivity(), "Select State");
            return false;
        } else if (idcity.equals("")) {
            Util.errorDialog(getActivity(), "Select City");
            return false;
        } else if (idServices.equals("")) {
            Util.errorDialog(getActivity(), "Select Services");
            return false;
        } else if (strOccupation.equalsIgnoreCase("Select Occupation")) {
            Util.errorDialog(getActivity(), "Select Occupation");
            return false;
        } else if (TextUtils.isEmpty(loanAmt.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Loan Amount");
            return false;
//        } else if (TextUtils.isEmpty(comment.getText().toString())) {
//            Util.errorDialog(getActivity(), "Enter Comment");
//            return false;
        }

        return true;

    }


}
