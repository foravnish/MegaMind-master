package megamindlons.app.megamind.Activittres;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.databinding.ActivityTermConditionBinding;

public class TermConditionActivity extends AppCompatActivity {
    Dialog dialog;
    ActivityTermConditionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_term_condition);

        dialog = new Dialog(TermConditionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        getTNCDataApi();

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
                    Toast.makeText(TermConditionActivity.this,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Util.cancelPgDialog(dialog);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Respose", "Error: " + error.getMessage());
                Toast.makeText(TermConditionActivity.this,
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
