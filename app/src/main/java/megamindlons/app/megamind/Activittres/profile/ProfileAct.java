package megamindlons.app.megamind.Activittres.profile;

import android.Manifest;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import megamindlons.app.megamind.CameraAct.ImagePickerActivity;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.event.ProfileEvent;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ProfileUploadResponse;
import megamindlons.app.megamind.response.UserDataResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileAct extends AppCompatActivity {

    Dialog dialog;
    CircleImageView profileImage;
    TextView personName, mobileNo, tve_gender, tve_dob, tve_email, tve_user_id,tve_ref;
    Button manageProfile, submitChangePassword;
    EditText oldPassword, newPassword, newPassword2;
    ImageView imgChange;
    private static final int REQUEST_PICK_IMAGE = 1002;
    File f = null;
    ProfileViewModel mViewModel;
    List<UserDataResponse.MessageBean> messageBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        dialog = new Dialog(ProfileAct.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        profileImage = findViewById(R.id.profileImage);
        personName = findViewById(R.id.personName);
        mobileNo = findViewById(R.id.mobileNo);
        tve_gender = findViewById(R.id.tve_gender);
        tve_dob = findViewById(R.id.tve_dob);
        tve_email = findViewById(R.id.tve_email);
        tve_user_id = findViewById(R.id.tve_user_id);
        manageProfile = findViewById(R.id.manageProfile);
        imgChange = findViewById(R.id.imgChange);
        tve_ref= findViewById(R.id.tve_ref);

        tve_ref.setText(""+MyPrefrences.getMyRefrel(getApplicationContext()));
        getUserDatacallApi();
        manageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ManageProfile.class);
                intent.putExtra("data", (Serializable) messageBean);
                startActivity(intent);
            }
        });
        imgChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("fsdfsdfsdf", "main");
                if (isPermissionGranted()) {
                    Log.d("fsdfsdfdfdfsdf", "true");
                    pickImage();
                } else {
                    Log.d("fsdfsdfdfdfsdf", "false");
                    ActivityCompat.requestPermissions(ProfileAct.this, new String[]{Manifest.permission.CAMERA}, 1);
                }


                   //postProfilePic();
            }
        });

        tve_user_id.setText("#" + MyPrefrences.getUserID(getApplicationContext()));

        String input = ""+MyPrefrences.getUSENAME(getApplicationContext());
        personName.setText("" + input.substring(0, 1).toUpperCase() + input.substring(1));
        mobileNo.setText("+91-" + MyPrefrences.getMobile(getApplicationContext()));
        tve_dob.setText("" + MyPrefrences.getDOB(getApplicationContext()));
        tve_email.setText("" + MyPrefrences.getEMAILID(getApplicationContext()));


    }

    private void getUserDatacallApi() {
        Util.showPgDialog(dialog);

        mViewModel.callUserData(MyPrefrences.getUserID(getApplicationContext())).observe(this, new Observer<UserDataResponse>() {
            @Override
            public void onChanged(@Nullable UserDataResponse resp) {

                Util.cancelPgDialog(dialog);
                if (resp != null) {
                    if (resp.getStatus().equals("success")) {


                        messageBean= resp.getMessage();

                        if (resp.getMessage().get(0).getGender().equals("0")){
                            tve_gender.setText("Male");

                        }else{
                            tve_gender.setText("Female");
                        }
                        Utility.setImageViaGlide(profileImage, resp.getMessage().get(0).getImage_thumb());
                    }
                }

            }
        });

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

    public void pickImage() {
        startActivityForResult(new Intent(this, ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissions[0].equals(Manifest.permission.CAMERA) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_IMAGE:
                    String imagePath = data.getStringExtra("image_path");

                    //Log.d("dfsdfsdfsdfs", CropImageActivity.picName);

                    setImage(imagePath);
                    break;
            }
        } else {
            System.out.println("Failed to load image");
        }
    }

    private void setImage(String imagePath) {

        profileImage.setImageBitmap(getImageFromStorage(imagePath));

    }


    private Bitmap getImageFromStorage(String path) {
        try {
            f = new File(path);
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 512, 512);

            Log.d("sdfasafsdfsdfsdfsdf", f.toString());
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f), null, options);


            String path2 = null;
            String filename = null;

            try {
                path2 = f.toString();
                filename = path2.substring(path.lastIndexOf("/") + 1);
                Log.d("dsfdfsdfsfs", filename);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (filename == null) {
                Util.errorDialog(ProfileAct.this, "Please Select Image");
            } else {
                //Toast.makeText(AddProduct.this, "yes", Toast.LENGTH_SHORT).show();
                PostData(path, filename);
            }


            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void PostData(String filePath, String fileName) {

        try {
            Log.d("sdfsdfasdfsdfsdf1", filePath);
            Log.d("sdfsdfasdfsdfsdf2", fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        new EditProfileImg(filePath,fileName).execute();

        postProfilePic(filePath);

    }

    private void postProfilePic(final String path) {

        Log.d("sdfgsdgsdfgsfs", path);
        File file = new File(path);
//        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", reqFile);
//        Log.d("ghfhgfhfghf", file.getName());

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part image =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        RequestBody userId =
                RequestBody.create(MediaType.parse("multipart/form-data"), MyPrefrences.getUserID(getApplicationContext()));

        Util.showPgDialog(dialog);

        mViewModel.postImage(image, userId).observe(this, new Observer<ProfileUploadResponse>() {
            @Override
            public void onChanged(@Nullable ProfileUploadResponse resp) {

                Util.cancelPgDialog(dialog);
                if (resp != null) {
                    if (resp.getStatus().equals("success")) {

                        MyPrefrences.setUserImage(getApplicationContext(),resp.getImage_url());

                        ProfileEvent profileEvent=new ProfileEvent();
                        profileEvent.setmProfileImage(resp.getImage_url());
                        EventBus.getDefault().post(profileEvent);
                        Toast.makeText(ProfileAct.this, ""+resp.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }


}
