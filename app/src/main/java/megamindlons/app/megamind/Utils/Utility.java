package megamindlons.app.megamind.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.rahul.media.main.MediaFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import megamindlons.app.megamind.R;

public class Utility {
    private static boolean IS_CONNECTED;
    private static MediaFactory mediaFactory;
    public static void startActivityWithLeftToRightAnimation(Activity ctx, Intent in) {
        if (ctx != null && in != null) {
            ctx.startActivity(in);
            //ctx.overridePendingTransition(R.anim.slide_in_right, R.anim.scale_down);
        }
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void showSnackBar(View parentLayout, String msg) {
        if (parentLayout != null) {
            Snackbar snackBar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT);
            snackBar.setActionTextColor(Color.WHITE);
            View view = snackBar.getView();
            TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snackBar.show();
        }
    }

    public static void setImageViaGlide2(ImageView imgVw, String imageUrl, int placeholderId) {
        if (!TextUtils.isEmpty(imageUrl) && imgVw != null) {
            RequestOptions requestOptions = new RequestOptions().placeholder(placeholderId).error(placeholderId).fallback(placeholderId).diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(imgVw.getContext()).load(imageUrl).apply(requestOptions).into(imgVw);
        }
    }

    public static void setImageViaGlide(ImageView imgVw, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl) && imgVw != null) {
            RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(imgVw.getContext()).load(imageUrl).apply(requestOptions).into(imgVw);
        }
    }


    public static void closeKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



//    public static void getFbDetails(AccessToken accessToken) {
//        GraphRequest request = GraphRequest.newMeRequest(
//                accessToken,
//                (object, response) -> {
//                    SocialMediaBean socialMediaBean = new Gson().fromJson(object.toString(), SocialMediaBean.class);
//                    socialMediaBean.setProfile_image("https://graph.facebook.com/" + socialMediaBean.getId() + "/picture?type=large&width=720&height=720");
//
//                    FbEvent fbEvent = new FbEvent(socialMediaBean);
//                    EventBus.getDefault().post(fbEvent);
//
//                });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,email,first_name,last_name");
//        request.setParameters(parameters);
//        request.executeAsync();
//    }



//    public static void setRecyclerViewAnimation(RecyclerView recyclerView, Context mContext) {
//        recyclerView.clearAnimation();
//        recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(mContext, R.anim.layout_animation_fall_down));
//    }

    public static void showLoadingView(Boolean show, com.wang.avi.AVLoadingIndicatorView loadingView, View view) {
        if (show) {
            loadingView.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        } else {
            loadingView.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
    }


    public static void showImageChooserDialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_image_dialog);

        // set the custom dialog components - text, image and button
        TextView imageGallery = (TextView) dialog.findViewById(R.id.gallery);
        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                MediaFactory.MediaBuilder mediaBuilder = new MediaFactory.MediaBuilder(context)
                        .doCropping()
                        .fromGallery();
                mediaFactory = MediaFactory.create().start(mediaBuilder);
            }
        });
        TextView imageCamera = (TextView) dialog.findViewById(R.id.camera);
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                MediaFactory.MediaBuilder mediaBuilder = new MediaFactory.MediaBuilder(context)
                        .fromCamera()
                        .doCropping();
                mediaFactory = MediaFactory.create().start(mediaBuilder);
            }
        });

        TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
