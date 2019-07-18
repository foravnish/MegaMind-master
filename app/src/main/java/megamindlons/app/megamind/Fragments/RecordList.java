package megamindlons.app.megamind.Fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordList extends Fragment {


    public RecordList() {
        // Required empty public constructor
    }
    GridView listView;
    LinearLayout noRecode;
    TextView txtNoRecode;
    List<HashMap<String,String>> AllProducts ;
    LinearLayout linerar1,linerar2,linerar3,linerar4;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_record_list, container, false);

        Log.d("fgsdfgsdgfsdfgdf",getArguments().getString("mode"));

        Log.d("gfdgdfgdfgdfgdfgd",getArguments().getString("array"));
        Log.d("gfdgdfgdfgdfgdfgd", String.valueOf(getArguments().getInt("count")));

        HomeAct.title.setText(getArguments().getString("mode")+" Leads");




        HomeAct.imageView.setVisibility(View.GONE);

        listView=view.findViewById(R.id.listView);

        linerar1=view.findViewById(R.id.linerar1);
        linerar2=view.findViewById(R.id.linerar2);
        linerar3=view.findViewById(R.id.linerar3);
        linerar4=view.findViewById(R.id.linerar4);
        noRecode=view.findViewById(R.id.noRecode);
        txtNoRecode=view.findViewById(R.id.txtNoRecode);
        AllProducts = new ArrayList<>();

        getData(getArguments().getInt("count"));

        if (getArguments().getString("mode").equals("Disbursed")){
            getData(1);
            if (getArguments().getInt("disburesCout")>0){
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
                txtNoRecode.setText("No Disbursed Lead here.");

            }
        }else  if (getArguments().getString("mode").equals("Approved")){
            getData(2);
            if (getArguments().getInt("approvedCount")>0){
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
                txtNoRecode.setText("No Approved Lead here.");
            }
        }
        else  if (getArguments().getString("mode").equals("In Process")){
            getData(3);
            if (getArguments().getInt("inprogressCount")>0){
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
                txtNoRecode.setText("No In process Lead here.");
            }
        }else  if (getArguments().getString("mode").equals("Rejected")){
            getData(4);
            if (getArguments().getInt("rejectCount")>0){
                listView.setVisibility(View.VISIBLE);
                noRecode.setVisibility(View.GONE);
            }else{
                listView.setVisibility(View.GONE);
                noRecode.setVisibility(View.VISIBLE);
                txtNoRecode.setText("No Rejected Lead here.");
            }
        }
        linerar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.title.setText("Disbursed Leads");
                HomeAct.imageView.setVisibility(View.GONE);
                getData(1);
                if (getArguments().getInt("disburesCout")>0){
                    listView.setVisibility(View.VISIBLE);
                    noRecode.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.GONE);
                    noRecode.setVisibility(View.VISIBLE);
                    txtNoRecode.setText("No Disbursed Lead here.");
                }

            }
        });
        linerar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.title.setText("Approved Leads");
                HomeAct.imageView.setVisibility(View.GONE);
                getData(2);
                if (getArguments().getInt("approvedCount")>0){
                    listView.setVisibility(View.VISIBLE);
                    noRecode.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.GONE);
                    noRecode.setVisibility(View.VISIBLE);
                    txtNoRecode.setText("No Approved Lead here.");
                }
            }
        });
        linerar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.title.setText("In Process Leads");
                HomeAct.imageView.setVisibility(View.GONE);
                getData(3);
                if (getArguments().getInt("inprogressCount")>0){
                    listView.setVisibility(View.VISIBLE);
                    noRecode.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.GONE);
                    noRecode.setVisibility(View.VISIBLE);
                    txtNoRecode.setText("No In process Lead here.");
                }
            }
        });
        linerar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.title.setText("Rejected Leads");
                HomeAct.imageView.setVisibility(View.GONE);
                getData(4);
                if (getArguments().getInt("rejectCount")>0){
                    listView.setVisibility(View.VISIBLE);
                    noRecode.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.GONE);
                    noRecode.setVisibility(View.VISIBLE);
                    txtNoRecode.setText("No Rejected Lead here.");
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fragment fragment=new DetailUsers();
                Bundle bundle=new Bundle();
                bundle.putString("id",AllProducts.get(i).get("id"));
                bundle.putString("mode",HomeAct.title.getText().toString());
                bundle.putString("array", getArguments().getString("array"));
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
                fragment.setArguments(bundle);
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout,R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });



        return view;
    }

    private void getData(int isStatus) {

        if (isStatus==1) {
            AllProducts.clear();
            try {
                jsonArray = new JSONArray(getArguments().getString("array"));
                Log.d("LenthArray",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap();


                    if (jsonObject.optString("app_status").equals("disbursed")) {
                        map.put("id", jsonObject.optString("id"));
                        map.put("name", jsonObject.optString("name"));
                        map.put("city_name", jsonObject.optString("city_name"));
                        map.put("loan_amount", jsonObject.optString("loan_amount"));
                        map.put("created", jsonObject.optString("created"));
                        map.put("app_status", jsonObject.optString("app_status"));
                        map.put("dis_amount", jsonObject.optString("dis_amount"));
                        map.put("dis_bank", jsonObject.optString("dis_bank"));

                        Adapter adapter = new Adapter(isStatus);
                        listView.setAdapter(adapter);
                        AllProducts.add(map);
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if(isStatus==2){
            AllProducts.clear();
            try {
                jsonArray = new JSONArray(getArguments().getString("array"));
                Log.d("LenthArray",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap();


                    if (jsonObject.optString("app_status").equals("approved")) {
                        map.put("id", jsonObject.optString("id"));
                        map.put("name", jsonObject.optString("name"));
                        map.put("city_name", jsonObject.optString("city_name"));
                        map.put("loan_amount", jsonObject.optString("loan_amount"));
                        map.put("created", jsonObject.optString("created"));
                        map.put("app_status", jsonObject.optString("app_status"));

                        Adapter adapter = new Adapter(isStatus);
                        listView.setAdapter(adapter);
                        AllProducts.add(map);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        else if(isStatus==3){
            AllProducts.clear();
            try {
                jsonArray = new JSONArray(getArguments().getString("array"));
                Log.d("LenthArray",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap();


                    if (jsonObject.optString("app_status").equals("inprogress")) {
                        map.put("id", jsonObject.optString("id"));
                        map.put("name", jsonObject.optString("name"));
                        map.put("city_name", jsonObject.optString("city_name"));
                        map.put("loan_amount", jsonObject.optString("loan_amount"));
                        map.put("created", jsonObject.optString("created"));
                        map.put("app_status", jsonObject.optString("app_status"));

                        Adapter adapter = new Adapter(isStatus);
                        listView.setAdapter(adapter);
                        AllProducts.add(map);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        else if(isStatus==4){
            AllProducts.clear();
            try {
                jsonArray = new JSONArray(getArguments().getString("array"));
                Log.d("LenthArray",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HashMap<String, String> map = new HashMap();


                    if (jsonObject.optString("app_status").equals("rejected")) {
                        map.put("id", jsonObject.optString("id"));
                        map.put("name", jsonObject.optString("name"));
                        map.put("city_name", jsonObject.optString("city_name"));
                        map.put("loan_amount", jsonObject.optString("loan_amount"));
                        map.put("created", jsonObject.optString("created"));
                        map.put("app_status", jsonObject.optString("app_status"));

                        Adapter adapter = new Adapter(isStatus);
                        listView.setAdapter(adapter);
                        AllProducts.add(map);
                    }


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


    class Adapter extends BaseAdapter {

        LayoutInflater inflater;
        TextView id,status,namePerson,date,loanAmount,city;
        int istatus;

        Adapter(int istatus) {
            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            this.istatus=istatus;
            if (inflater == null) {
                throw new AssertionError("LayoutInflater not found.");
            }
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            convertView=inflater.inflate(R.layout.record_list,parent,false);

            loanAmount=convertView.findViewById(R.id.loanAmount);
            date=convertView.findViewById(R.id.date);
            namePerson=convertView.findViewById(R.id.namePerson);
            status=convertView.findViewById(R.id.status);
            id=convertView.findViewById(R.id.id);
            city=convertView.findViewById(R.id.city);

            NumberFormat formatter = new DecimalFormat("#,###");
            loanAmount.setText(formatter.format(Double.parseDouble(AllProducts.get(position).get("loan_amount"))));
            date.setText(AllProducts.get(position).get("created"));
            namePerson.setText(AllProducts.get(position).get("name"));
            id.setText(AllProducts.get(position).get("id"));
            city.setText(AllProducts.get(position).get("city_name"));



            Typeface face=Typeface.createFromAsset(getActivity().getAssets(), "muli.ttf");
            Typeface face2=Typeface.createFromAsset(getActivity().getAssets(), "muli_bold.ttf");
            Typeface face3=Typeface.createFromAsset(getActivity().getAssets(), "muli_semibold.ttf");
            id.setTypeface(face);
            namePerson.setTypeface(face2);
            status.setTypeface(face2);
            loanAmount.setTypeface(face2);
            date.setTypeface(face3);


            return convertView;
        }
    }



}
