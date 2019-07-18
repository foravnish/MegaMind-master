package megamindlons.app.megamind.Fragments;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.Activittres.Login;
import megamindlons.app.megamind.Activittres.Password;
import megamindlons.app.megamind.Activittres.Registration;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.JSONResponse;
import megamindlons.app.megamind.Retrofit.ListingData;
import megamindlons.app.megamind.Retrofit.MSG;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyIcome extends Fragment {


    public MyIcome() {
        // Required empty public constructor
    }


    RelativeLayout rejected,inprocess,approved,disbursed;
    Dialog dialog;
    TextView totalRecord,disbursedTxt,approvedTxt,inprogressTxt,rejectTxt,txtExBal,txtMyBal;
    ArrayList<HashMap<String,String>> Data;
    int inprogressCount=0,approvedCount=0,disburesCout=0,rejectCount=0;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_icome, container, false);
        disbursed=view.findViewById(R.id.disbursed);
        approved=view.findViewById(R.id.approved);
        inprocess=view.findViewById(R.id.inprocess);
        rejected=view.findViewById(R.id.rejected);
        totalRecord=view.findViewById(R.id.totalRecord);
        txtExBal=view.findViewById(R.id.txtExBal);
        txtMyBal=view.findViewById(R.id.txtMyBal);
        disbursedTxt=view.findViewById(R.id.disbursedTxt);
        approvedTxt=view.findViewById(R.id.approvedTxt);
        inprogressTxt=view.findViewById(R.id.inprogressTxt);
        rejectTxt=view.findViewById(R.id.rejectTxt);

        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        HomeAct.title.setText("My Income");
        HomeAct.imageView.setVisibility(View.GONE);



        Data=new ArrayList<>();

        disbursed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordListPage("Disbursed",1);

            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordListPage("Approved",2);
            }
        });

        inprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordListPage("In Process",3);
            }
        });

        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordListPage("Rejected",4);
            }
        });


        getData();

        return  view;
    }

    private void getData() {

        Util.showPgDialog(dialog);

        disburesCout=0;
        approvedCount=0;
        inprogressCount=0;
        rejectCount =0;


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.userAllLeads+"?user_id="+ MyPrefrences.getUserID(getActivity()), null, new Response.Listener<JSONObject>() {
//            Api.userAllLeads+"?user_id=7", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeuserAllLeads", response.toString());
                Util.cancelPgDialog(dialog);

                if (response.optString("status").equals("success")) {

                    try {
                         jsonArray = response.getJSONArray("message");


                        JSONObject jsonObject1=response.optJSONObject("ref_result");
                        NumberFormat formatter = new DecimalFormat("#,###");

                        txtMyBal.setText(""+formatter.format(Double.parseDouble(jsonObject1.optString("ref_bal_amt"))));
                        txtExBal.setText(""+formatter.format(Double.parseDouble(jsonObject1.optString("ref_expected_amt"))));


                        totalRecord.setText(""+jsonArray.length());
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            if (jsonObject.optString("app_status").equals("disbursed")){
                                disburesCout++;
                            }
                            else if (jsonObject.optString("app_status").equals("approved")){
                                approvedCount++;
                            }
                            else if (jsonObject.optString("app_status").equals("inprogress")){
                                inprogressCount++;
                            }
                            else if (jsonObject.optString("app_status").equals("rejected")){
                                rejectCount++;
                            }

                        }


                        disbursedTxt.setText(""+disburesCout);
                        approvedTxt.setText(""+approvedCount);
                        inprogressTxt.setText(""+inprogressCount);
                        rejectTxt.setText(""+rejectCount);





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ResposeUserData", "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        "Error! Please Connect to the internet", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);



    }

    private void recordListPage(String mode, int cMode) {

        Fragment fragment=new RecordList();
        Bundle bundle=new Bundle();
        bundle.putString("mode",mode);
        bundle.putString("array", String.valueOf(jsonArray));
        bundle.putInt("count", cMode);

        bundle.putInt("disburesCout", disburesCout);
        bundle.putInt("approvedCount", approvedCount);
        bundle.putInt("inprogressCount", inprogressCount);
        bundle.putInt("rejectCount", rejectCount);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
        fragment.setArguments(bundle);
        ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout,R.anim.frag_fade_right, R.anim.frag_fad_left);

    }

}
