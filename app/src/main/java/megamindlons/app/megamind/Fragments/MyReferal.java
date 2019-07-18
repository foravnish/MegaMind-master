package megamindlons.app.megamind.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReferal extends Fragment {


    public MyReferal() {
        // Required empty public constructor
    }

    GridView listView;
    List<HashMap<String, String>> AllProducts;
    LinearLayout active, inactive, allReferrals, inProcess,noRecode;
    ImageView icon1, icon2, icon3;
    TextView text1, text2, text3, text4, exptRefBal, refBalance;
    Dialog dialog;
    JSONArray jsonArray;
    int lenOfArray=0;
    int activeCount,inactiveCount,inprocessingCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_referal, container, false);

        listView = view.findViewById(R.id.listView);

        inactive = view.findViewById(R.id.inactive);
        allReferrals = view.findViewById(R.id.allReferrals);
        active = view.findViewById(R.id.active);
        inProcess = view.findViewById(R.id.inProcess);
        noRecode= view.findViewById(R.id.noRecode);
        icon1 = view.findViewById(R.id.icon1);
        icon2 = view.findViewById(R.id.icon2);
        icon3 = view.findViewById(R.id.icon3);

        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);

        refBalance = view.findViewById(R.id.refBalance);
        exptRefBal = view.findViewById(R.id.exptRefBal);

        AllProducts = new ArrayList<>();

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        HomeAct.title.setText("My Referrals");
        HomeAct.imageView.setVisibility(View.GONE);

        HashMap<String, String> map = new HashMap();

        getReferealData();


        allReferrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pupulateData("1");
                allReferrals.setBackgroundResource(R.drawable.strock_ref_btn2);
                inactive.setBackgroundResource(R.drawable.strock_ref_btn);
                active.setBackgroundResource(R.drawable.strock_ref_btn);
                inProcess.setBackgroundResource(R.drawable.strock_ref_btn);

                icon1.setColorFilter(getContext().getResources().getColor(R.color.white));
                icon2.setColorFilter(getContext().getResources().getColor(R.color.blue));
                icon3.setColorFilter(getContext().getResources().getColor(R.color.blue));

                text1.setTextColor(Color.parseColor("#ffffff"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#000000"));

            }
        });
        inactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pupulateData("2");
                allReferrals.setBackgroundResource(R.drawable.strock_ref_btn);
                inactive.setBackgroundResource(R.drawable.strock_ref_btn2);
                active.setBackgroundResource(R.drawable.strock_ref_btn);
                inProcess.setBackgroundResource(R.drawable.strock_ref_btn);

                icon1.setColorFilter(getContext().getResources().getColor(R.color.blue));
                icon2.setColorFilter(getContext().getResources().getColor(R.color.white));
                icon3.setColorFilter(getContext().getResources().getColor(R.color.blue));

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#ffffff"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#000000"));

            }
        });
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pupulateData("3");
                allReferrals.setBackgroundResource(R.drawable.strock_ref_btn);
                inactive.setBackgroundResource(R.drawable.strock_ref_btn);
                active.setBackgroundResource(R.drawable.strock_ref_btn2);
                inProcess.setBackgroundResource(R.drawable.strock_ref_btn);

                icon1.setColorFilter(getContext().getResources().getColor(R.color.blue));
                icon2.setColorFilter(getContext().getResources().getColor(R.color.blue));
                icon3.setColorFilter(getContext().getResources().getColor(R.color.white));

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#ffffff"));
                text4.setTextColor(Color.parseColor("#000000"));
            }
        });

        inProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pupulateData("4");
                allReferrals.setBackgroundResource(R.drawable.strock_ref_btn);
                inactive.setBackgroundResource(R.drawable.strock_ref_btn);
                active.setBackgroundResource(R.drawable.strock_ref_btn);
                inProcess.setBackgroundResource(R.drawable.strock_ref_btn2);

                icon1.setColorFilter(getContext().getResources().getColor(R.color.white));
                icon2.setColorFilter(getContext().getResources().getColor(R.color.blue));
                icon3.setColorFilter(getContext().getResources().getColor(R.color.blue));

                text1.setTextColor(Color.parseColor("#000000"));
                text2.setTextColor(Color.parseColor("#000000"));
                text3.setTextColor(Color.parseColor("#000000"));
                text4.setTextColor(Color.parseColor("#ffffff"));


            }
        });


        return view;
    }

    private void pupulateData(String s) {


        if (s.equals("1")) {
            if (lenOfArray==-1){
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
            }else {
                if (jsonArray.length() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    noRecode.setVisibility(View.GONE);
                    AllProducts.clear();
                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            HashMap<String, String> map = new HashMap();

                            map.put("id", jsonObject.optString("id"));
                            map.put("reg_date", jsonObject.optString("reg_date"));
                            map.put("name", jsonObject.optString("name"));
                            map.put("status", jsonObject.optString("status"));

                            Adapter adapter = new Adapter();
                            listView.setAdapter(adapter);
                            AllProducts.add(map);


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    listView.setVisibility(View.GONE);
                    noRecode.setVisibility(View.VISIBLE);
                }
            }


        }
        else if (s.equals("2")) {
            if (inactiveCount>0) {
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
                AllProducts.clear();

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap();

                        if (jsonObject.optString("status").equalsIgnoreCase("INACTIVE")) {

                            map.put("id", jsonObject.optString("id"));
                            map.put("reg_date", jsonObject.optString("reg_date"));
                            map.put("name", jsonObject.optString("name"));
                            map.put("status", jsonObject.optString("status"));

                            Adapter adapter = new Adapter();
                            listView.setAdapter(adapter);
                            AllProducts.add(map);
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
            }

        } else if (s.equals("3")) {

            if (activeCount>0) {
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
                AllProducts.clear();

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap();

                        if (jsonObject.optString("status").equalsIgnoreCase("ACTIVE")) {

                            map.put("id", jsonObject.optString("id"));
                            map.put("reg_date", jsonObject.optString("reg_date"));
                            map.put("name", jsonObject.optString("name"));
                            map.put("status", jsonObject.optString("status"));

                            Adapter adapter = new Adapter();
                            listView.setAdapter(adapter);
                            AllProducts.add(map);
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
            }

        } else if (s.equals("4")) {

            if (inprocessingCount>0) {
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
                AllProducts.clear();
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap();

                        if (jsonObject.optString("status").equalsIgnoreCase("IN PROCESSING")) {

                            map.put("id", jsonObject.optString("id"));
                            map.put("reg_date", jsonObject.optString("reg_date"));
                            map.put("name", jsonObject.optString("name"));
                            map.put("status", jsonObject.optString("status"));

                            Adapter adapter = new Adapter();
                            listView.setAdapter(adapter);
                            AllProducts.add(map);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
            }

        }
    }

    private void getReferealData() {

        Util.showPgDialog(dialog);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.ref_accountData + "?agent_id=" + MyPrefrences.getUserID(getActivity()), null, new Response.Listener<JSONObject>() {
            //                Api.ref_accountData + "?agent_id=16", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("ResposeuserReferral", response.toString());
                Util.cancelPgDialog(dialog);

                if (response.optString("status").equals("success")) {

                    try {
                        JSONObject jsonObjectRef = response.getJSONObject("ref_agents_report");
                        NumberFormat formatter = new DecimalFormat("#,###");
                        refBalance.setText("" + formatter.format(Double.parseDouble(jsonObjectRef.optString("ref_bal_amt"))));
                        exptRefBal.setText("" + formatter.format(Double.parseDouble(jsonObjectRef.optString("ref_expected_amt"))));


                        activeCount=jsonObjectRef.optInt("active");
                        inactiveCount=jsonObjectRef.optInt("inactive");
                        inprocessingCount=jsonObjectRef.optInt("inprocessing");


                        listView.setVisibility(View.VISIBLE);
                        noRecode.setVisibility(View.GONE);
                        jsonArray = response.getJSONArray("ref_agents");

                        if (jsonArray.length() > 0) {
                            listView.setVisibility(View.VISIBLE);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                HashMap<String, String> map = new HashMap();

                                map.put("id", jsonObject.optString("id"));
                                map.put("reg_date", jsonObject.optString("reg_date"));
                                map.put("name", jsonObject.optString("name"));
                                map.put("status", jsonObject.optString("status"));

                                Adapter adapter = new Adapter();
                                listView.setAdapter(adapter);
                                AllProducts.add(map);

                            }
                        } else {

                            listView.setVisibility(View.GONE);
                            noRecode.setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    lenOfArray=-1;
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


    class Adapter extends BaseAdapter {

        LayoutInflater inflater;
        TextView id, status, namePerson, date, disbarsed;


        Adapter() {
            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//            if (inflater == null) {
//                throw new AssertionError("LayoutInflater not found.");
//            }
        }

        @Override
        public int getCount() {
            return AllProducts.size();
        }

        @Override
        public Object getItem(int position) {
            return AllProducts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView = inflater.inflate(R.layout.sub_cat_list, parent, false);


            disbarsed = convertView.findViewById(R.id.disbarsed);
            date = convertView.findViewById(R.id.date);
            namePerson = convertView.findViewById(R.id.namePerson);
            status = convertView.findViewById(R.id.status);
            id = convertView.findViewById(R.id.id);


            id.setText("ID #" + AllProducts.get(position).get("id"));
            date.setText("" + AllProducts.get(position).get("reg_date"));
            status.setText("" + AllProducts.get(position).get("status"));
            namePerson.setText("" + AllProducts.get(position).get("name"));



            if (AllProducts.get(position).get("status").equalsIgnoreCase("active")){
                status.setTextColor(getActivity().getColor(R.color.green));
            }else  if (AllProducts.get(position).get("status").equalsIgnoreCase("inactive")){
                status.setTextColor(getActivity().getColor(R.color.red));
            }
            else  if (AllProducts.get(position).get("status").equalsIgnoreCase("in process")){
                status.setTextColor(getActivity().getColor(R.color.colorAccent));
            }

            Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "muli.ttf");
            Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "muli_bold.ttf");
            Typeface face3 = Typeface.createFromAsset(getActivity().getAssets(), "muli_semibold.ttf");
            id.setTypeface(face);
            namePerson.setTypeface(face2);
            status.setTypeface(face2);
            disbarsed.setTypeface(face2);
            date.setTypeface(face3);


            return convertView;
        }
    }


}
