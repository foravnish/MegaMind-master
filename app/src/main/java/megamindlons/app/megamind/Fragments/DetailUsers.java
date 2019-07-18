package megamindlons.app.megamind.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUsers extends Fragment {


    public DetailUsers() {
        // Required empty public constructor
    }

    JSONArray jsonArray;
    TextView comment,cStatus,date,city,name,id,amount,bankName,loanAmount,serviceName;
    ImageView imageStatus;
    LinearLayout linearDisbursh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail_users, container, false);

        HomeAct.title.setText(getArguments().getString("mode")+" Detail");
        HomeAct.imageView.setVisibility(View.GONE);

        Log.d("gfdgdfgerg",getArguments().getString("array"));
        Log.d("fghdgdfgehgfhfrg",getArguments().getString("id"));


        id=view.findViewById(R.id.id);
        name=view.findViewById(R.id.name);
        city=view.findViewById(R.id.city);
        date=view.findViewById(R.id.date);
        cStatus=view.findViewById(R.id.cStatus);
        comment=view.findViewById(R.id.comment);
        amount=view.findViewById(R.id.amount);
        imageStatus=view.findViewById(R.id.imageStatus);
        loanAmount=view.findViewById(R.id.loanAmount);
        bankName=view.findViewById(R.id.bankName);
        linearDisbursh=view.findViewById(R.id.linearDisbursh);
        serviceName=view.findViewById(R.id.serviceName);



        try {
            jsonArray = new JSONArray(getArguments().getString("array"));
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.optString("id").equals(getArguments().getString("id"))){
                    Log.d("sdfsdfsdfsd",jsonObject.optString("name"));

                    id.setText(jsonObject.optString("id"));
                    name.setText(jsonObject.optString("name"));
                    city.setText(jsonObject.optString("city_name"));
                    date.setText(jsonObject.optString("created"));
                    cStatus.setText(jsonObject.optString("current_status"));
                    comment.setText(jsonObject.optString("comment"));
                    serviceName.setText(jsonObject.optString("service_name"));
                    NumberFormat formatter = new DecimalFormat("#,###");
                    amount.setText(formatter.format(Double.parseDouble(jsonObject.optString("loan_amount"))));




                    if (jsonObject.optString("app_status").equals("disbursed")){
                        imageStatus.setBackgroundResource(R.drawable.disbursed_logo);
                        linearDisbursh.setVisibility(View.VISIBLE);
                        loanAmount.setText(formatter.format(Double.parseDouble(jsonObject.optString("dis_amount"))));
                        bankName.setText(jsonObject.optString("dis_bank"));
                    }
                    else if (jsonObject.optString("app_status").equals("approved")){
                        linearDisbursh.setVisibility(View.GONE);
                        imageStatus.setBackgroundResource(R.drawable.approved_logo);
                    }
                    else if (jsonObject.optString("app_status").equals("inprogress")){
                        imageStatus.setBackgroundResource(R.drawable.in_process);
                        linearDisbursh.setVisibility(View.GONE);
                    }
                    else if (jsonObject.optString("app_status").equals("rejected")){
                        imageStatus.setBackgroundResource(R.drawable.rejeceted);
                        linearDisbursh.setVisibility(View.GONE);
                    }

                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

}
