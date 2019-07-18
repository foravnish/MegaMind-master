package megamindlons.app.megamind.Activittres.profile;

import android.Manifest;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.rahul.media.main.MediaFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.ActivityManageProfileBinding;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.UserDataResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ManageProfile extends AppCompatActivity {

    ActivityManageProfileBinding binding;
    private static final int REQUEST_PICK_IMAGE = 1002;
    private Context mContext;
    File f = null;
    private MediaFactory mediaFactory;
    private int TARGET_code = 0;
    private static final int REQUEST_PICK_IMAGE_PAN_CARD = 101;
    private static final int REQUEST_PICK_IMAGE_AADHAR_CARD = 102;
    private static final int REQUEST_PICK_IMAGE_VOTER_ID_CARD = 103;
    Boolean isPanCard=false,isAadharCard=false,isVoterCrd=false;
    String imagePan,imageAdgar,imageVoter;
    Dialog dialog;
    ProfileViewModel mViewModel;
    MultipartBody.Part imagePanM;
    MultipartBody.Part imageAdharM;
    MultipartBody.Part imageVoterM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_profile);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        mContext = this;

        dialog = new Dialog(ManageProfile.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        ArrayList<UserDataResponse.MessageBean> myList = (ArrayList<UserDataResponse.MessageBean>) getIntent().getSerializableExtra("data");

        if (myList!=null) {
            binding.panCard.setText("" + myList.get(0).getPancard_no());
            binding.aadharCard.setText("" + myList.get(0).getAadharcard_no());
            binding.voterId.setText("" + myList.get(0).getVoter_no());

            Utility.setImageViaGlide(binding.imagePan, myList.get(0).getPancard_img());
            Utility.setImageViaGlide(binding.imageAadhar, myList.get(0).getAadhar_img());
            Utility.setImageViaGlide(binding.imageVoter, myList.get(0).getVoter_img());

            if (!myList.get(0).getPancard_no().equals("")){
                binding.txt1.setVisibility(View.GONE);
            }
            if (!myList.get(0).getAadharcard_no().equals("")){
                binding.txt2.setVisibility(View.GONE);
            }
            if (!myList.get(0).getVoter_no().equals("")){
                binding.txt3.setVisibility(View.GONE);
            }


        }

        if (isPermissionGrantedForStorage()) {
        } else {
        }

        binding.submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageData();
            }
        });
        binding.pancardFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPermissionGrantedForStorage()) {
                } else {
                    if (isPermissionGranted()) {
                        TARGET_code=REQUEST_PICK_IMAGE_PAN_CARD;
                        Utility.showImageChooserDialog(mContext);
                    } else {
                        ActivityCompat.requestPermissions(ManageProfile.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }

            }
        });

        binding.aadhaCardFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGrantedForStorage()) {
                } else {
                    if (isPermissionGranted()) {
                        TARGET_code=REQUEST_PICK_IMAGE_AADHAR_CARD;
                        Utility.showImageChooserDialog(mContext);
                    } else {
                        ActivityCompat.requestPermissions(ManageProfile.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }
            }
        });

        binding.voterFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPermissionGrantedForStorage()) {
                } else {
                    if (isPermissionGranted()) {
                        TARGET_code=REQUEST_PICK_IMAGE_VOTER_ID_CARD;
                        Utility.showImageChooserDialog(mContext);
                    } else {
                        ActivityCompat.requestPermissions(ManageProfile.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }
            }
        });
    }

    private void sendImageData() {

        if (imagePan!=null) {
            File filePan = new File(imagePan);
            RequestBody requestFilePan =
                    RequestBody.create(MediaType.parse("multipart/form-data"), filePan);
            imagePanM =
                    MultipartBody.Part.createFormData("pan_img", filePan.getName(), requestFilePan);
        }

        if (imageAdgar!=null){
            File fileAadhar = new File(imageAdgar);
            RequestBody requestFileAadhar =
                    RequestBody.create(MediaType.parse("multipart/form-data"), fileAadhar);
            imageAdharM =
                    MultipartBody.Part.createFormData("aadhar_img", fileAadhar.getName(), requestFileAadhar);
        }

        if (imageVoter!=null){
            File fileVoter = new File(imageVoter);
            RequestBody requestFileVoter =
                    RequestBody.create(MediaType.parse("multipart/form-data"), fileVoter);
            imageVoterM =
                    MultipartBody.Part.createFormData("voter_img", fileVoter.getName(), requestFileVoter);
        }

        RequestBody panNo =
                RequestBody.create(MediaType.parse("multipart/form-data"), binding.panCard.getText().toString());

        RequestBody AdharNo =
                RequestBody.create(MediaType.parse("multipart/form-data"), binding.aadharCard.getText().toString());

        RequestBody voterNo =
                RequestBody.create(MediaType.parse("multipart/form-data"), binding.voterId.getText().toString());

        RequestBody userId =
                RequestBody.create(MediaType.parse("multipart/form-data"), MyPrefrences.getUserID(getApplicationContext()));

        Util.showPgDialog(dialog);

        mViewModel.uploadDoc(imagePanM, panNo,imageAdharM,AdharNo,imageVoterM,voterNo,userId).observe(this, new Observer<OfferResponse>() {
            @Override
            public void onChanged(@Nullable OfferResponse resp) {

                Util.cancelPgDialog(dialog);
                if (resp != null) {
                    if (resp.getStatus().equals("success")) {


                        Toast.makeText(ManageProfile.this, "Document upload successfully.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isPermissionGrantedForStorage() {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    5);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return true;
        } else {
            return false;
        }

    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mediaFactory == null) {
            mediaFactory = MediaFactory.create();
        }

        try {
            ArrayList<String> all_path = mediaFactory.onActivityResult(requestCode, resultCode, data);
            if (all_path != null && all_path.size() > 0) {
                for (String string : all_path) {
                    if (TARGET_code==REQUEST_PICK_IMAGE_PAN_CARD) {
                        Utility.setImageViaGlide(binding.imagePan, string);
                        isPanCard=true;
                        imagePan=string;
                        Log.d("sdfvdvdvd", imagePan);
                    }else if(TARGET_code==REQUEST_PICK_IMAGE_AADHAR_CARD){
                        Utility.setImageViaGlide(binding.imageAadhar, string);
                        isAadharCard=true;
                        imageAdgar=string;
                        Log.d("sdfvdvdvd", imageAdgar);
                    }else if(TARGET_code==REQUEST_PICK_IMAGE_VOTER_ID_CARD){
                        Utility.setImageViaGlide(binding.imageVoter, string);
                        isVoterCrd=true;
                        imageVoter=string;
                        Log.d("sdfvdvdvd", imageVoter);
                    }

                }
            }
        } catch (Exception e) {
        }
    }

}
