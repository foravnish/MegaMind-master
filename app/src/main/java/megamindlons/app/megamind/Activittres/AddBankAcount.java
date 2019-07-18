package megamindlons.app.megamind.Activittres;


import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import megamindlons.app.megamind.Fragments.ThankYouFrag;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Retrofit.APIService;
import megamindlons.app.megamind.Retrofit.ApiClient;
import megamindlons.app.megamind.Retrofit.MSG;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.databinding.FragmentAddBankAcountBinding;
import megamindlons.app.megamind.databinding.FragmentServicesDetailBinding;
import megamindlons.app.megamind.response.BankDataRersponse;
import megamindlons.app.megamind.response.OTPResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBankAcount extends Fragment {


    FragmentAddBankAcountBinding binding;
    private Context mContext;
    Dialog dialog;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_bank_acount, container, false);
        mContext = getContext();

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        getBankData();
        HomeAct.title.setText("Add Bank Details");
        HomeAct.imageView.setVisibility(View.GONE);

        binding.submitDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    addBankData();

                }
                
                
            }
        });
        return binding.getRoot();

    }

    private void getBankData() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<BankDataRersponse> userCall = service.callGetBankData(""+MyPrefrences.getUserID(getActivity()));

        userCall.enqueue(new Callback<BankDataRersponse>() {
            @Override
            public void onResponse(Call<BankDataRersponse> call, retrofit2.Response<BankDataRersponse> response) {
                Util.cancelPgDialog(dialog);
                // Log.d("onResponseLeads", "" + response.body().getMessage());

                if (response.body().getStatus().equals("success")) {

                    id=response.body().getMessage().get(0).getId();

                    binding.bankName.setText(""+response.body().getMessage().get(0).getBank_name());
                    binding.bankHolderName.setText(""+response.body().getMessage().get(0).getAc_holder());
                    binding.acountNo.setText(""+response.body().getMessage().get(0).getAc_number());
                    binding.branchName.setText(""+response.body().getMessage().get(0).getBranch());
                    binding.ifscCode.setText(""+response.body().getMessage().get(0).getIfsc());
                    binding.paytmNo.setText(""+response.body().getMessage().get(0).getPaytm_no());

                } else if (response.body().getStatus().equals("failure")) {

                    id="";

                }

            }

            @Override
            public void onFailure(Call<BankDataRersponse> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailureLeads", t.toString());
            }


        });


    }

    private void addBankData() {

        Util.showPgDialog(dialog);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> userCall = service.postBankData(
                ""+id,
                ""+binding.bankName.getText().toString(),
                ""+binding.bankHolderName.getText().toString(),
                ""+binding.acountNo.getText().toString(),
                ""+binding.branchName.getText().toString(),
                ""+binding.ifscCode.getText().toString(),
                ""+binding.paytmNo.getText().toString(),
                ""+MyPrefrences.getUserID(getActivity()),
                "AGENT");

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, retrofit2.Response<MSG> response) {
                Util.cancelPgDialog(dialog);
                // Log.d("onResponseLeads", "" + response.body().getMessage());

                if (response.body().getStatus().equals("success")) {


                } else if (response.body().getStatus().equals("failure")) {

                }
                Util.errorDialog(getActivity(), response.body().getMessage());

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Util.cancelPgDialog(dialog);
                Log.d("onFailureLeads", t.toString());
            }
        });
    }


    private boolean validate() {

        if (TextUtils.isEmpty(binding.bankName.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Bank Name");
            return false;
        } else if (TextUtils.isEmpty(binding.bankHolderName.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Bank Holder Name");
            return false;
        } else if (TextUtils.isEmpty(binding.acountNo.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Account number");
            return false;
        } else if (TextUtils.isEmpty(binding.branchName.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter Branch Name");
            return false;
        } else if (TextUtils.isEmpty(binding.ifscCode.getText().toString())) {
            Util.errorDialog(getActivity(), "Enter IFSC Code");
            return false;
        }

        return true;

    }


}
