package megamindlons.app.megamind.Fragments;


import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.databinding.FragmentHtmlContentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class HtmlContentFragment extends Fragment {


    public HtmlContentFragment() {
        // Required empty public constructor
    }

    FragmentHtmlContentBinding binding;
    Dialog dialog;
    String flagValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_html_content, container, false);
        flagValue=getArguments().getString("flag");

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        if (flagValue.equals("tnc")){
            HomeAct.title.setText("Terms & Conditions");
            getTNCDataApi();
        }else  if (flagValue.equals("privacy")){
            HomeAct.title.setText("Privacy Policy");
            getPrivacyCDataApi();
        }else  if (flagValue.equals("disclimer")){
            HomeAct.title.setText("Disclaimer");
            getDisclimerDataApi();
        }
        else  if (flagValue.equals("referal")){
            HomeAct.title.setText("Referral / Expected Payout T&C");
            getReferalDataApi();
        }



        return  binding.getRoot();

    }

    private void getTNCDataApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReqAmount = new JsonObjectRequest(Request.Method.GET,
                Api.term_details, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeAmount", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        JSONArray jsonArray= response.getJSONArray("message");
                        JSONObject jsonObject=jsonArray.getJSONObject(0);

                        binding.contentData.setText(Html.fromHtml(jsonObject.getString("term")));

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


    private void getPrivacyCDataApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReqAmount = new JsonObjectRequest(Request.Method.GET,
                Api.privacy_details, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeAmount", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        JSONArray jsonArray= response.getJSONArray("message");
                        JSONObject jsonObject=jsonArray.getJSONObject(0);

                        binding.contentData.setText(Html.fromHtml(jsonObject.getString("privacy")));


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

    private void getDisclimerDataApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReqAmount = new JsonObjectRequest(Request.Method.GET,
                Api.disclaimer_details, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeAmount", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        JSONArray jsonArray= response.getJSONArray("message");
                        JSONObject jsonObject=jsonArray.getJSONObject(0);

                        binding.contentData.setText(Html.fromHtml(jsonObject.getString("disclaimer")));


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

    private void getReferalDataApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReqAmount = new JsonObjectRequest(Request.Method.GET,
                Api.payout_tc_details, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeAmount", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {

                        JSONArray jsonArray= response.getJSONArray("message");
                        JSONObject jsonObject=jsonArray.getJSONObject(0);

                        binding.contentData.setText(Html.fromHtml(jsonObject.getString("payout_tc")));


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

}
