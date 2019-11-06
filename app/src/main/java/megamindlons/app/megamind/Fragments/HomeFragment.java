package megamindlons.app.megamind.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fr.tvbarthel.intentshare.IntentShare;
import me.relex.circleindicator.CircleIndicator;
import megamindlons.app.megamind.Activittres.HomeAct;
import megamindlons.app.megamind.Activittres.PlayerutubeActivity;
import megamindlons.app.megamind.Activittres.PopupActivity;
import megamindlons.app.megamind.Activittres.Registration;
import megamindlons.app.megamind.Fragments.offer.OffersListFragment;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Api;
import megamindlons.app.megamind.Utils.AppController;
import megamindlons.app.megamind.Utils.GeterSeterOffers;
import megamindlons.app.megamind.Utils.Getseter;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.ui.videos.WebViewPayment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {




    ImageView  imgview;
    String url;
    private InterstitialAd interstitial;
    public static String balance;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    List<HashMap<String, String>> AllProducts;
    TextView addLeads,txtMyRef;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, layoutReferal;
    public static TextView myrefer, myBalance, myExpected, exptrefBal, refBal;
    //    public static final String DEVELOPER_KEY = "AIzaSyAHL3L9F-kdm0lEey7NrsSSzbEuRUa8aTo";
//    public static final String YOUTUBE_VIDEO_CODE = "_oEA18Y8gM0";
    List<String> code = new ArrayList<>();
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    Dialog dialog;
    List<Getseter> ImageList = new ArrayList<Getseter>();
    List<GeterSeterOffers> ImageList2 = new ArrayList<GeterSeterOffers>();

    ViewPager viewPager, viewPager2;
    CircleIndicator indicator, indicator2;
    CustomPagerAdapter customPagerAdapter;
    CustomPagerAdapter2 customPagerAdapter2;
    ImageView callback;
    int position, position2;
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.
    final long PERIOD_MS2 = 2000; // time in milliseconds between successive task executions.
    private static int NUM_PAGES = 0;
    private static int NUM_PAGES2 = 0;

    private Integer[] imageIDs = {
            R.drawable.share_img

    };


    private Handler handler = new Handler();
    private Handler handler2 = new Handler();
    private Runnable runnale = new Runnable() {
        public void run() {
            viewPager.setCurrentItem(position, true);
            if (position >= NUM_PAGES) position = 0;
            else position++;
            // Move to the next page after 10s
            handler.postDelayed(runnale, PERIOD_MS);
        }
    };

    private Runnable runnale2 = new Runnable() {
        public void run() {
            viewPager2.setCurrentItem(position2, true);
            if (position2 >= NUM_PAGES2) position2 = 0;
            else position2++;
            // Move to the next page after 10s
            handler2.postDelayed(runnale2, PERIOD_MS2);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);

        addLeads = view.findViewById(R.id.addLeads);
        linearLayout1 = view.findViewById(R.id.linearLayout1);
        linearLayout2 = view.findViewById(R.id.linearLayout2);
        callback = view.findViewById(R.id.callback);
        linearLayout3 = view.findViewById(R.id.linearLayout3);
        linearLayout4 = view.findViewById(R.id.linearLayout4);
        myrefer = view.findViewById(R.id.myrefer);
        layoutReferal = view.findViewById(R.id.layoutReferal);


        myBalance = view.findViewById(R.id.myBalance);
        myExpected = view.findViewById(R.id.myExpected);

        refBal = view.findViewById(R.id.refBal);
        exptrefBal = view.findViewById(R.id.exptrefBal);
        txtMyRef= view.findViewById(R.id.txtMyRef);


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager2 = (ViewPager) view.findViewById(R.id.viewpager2);
        customPagerAdapter = new CustomPagerAdapter();
        customPagerAdapter2 = new CustomPagerAdapter2();

//        viewPager.setClipToPadding(false);
//        viewPager.setPadding(40, 0, 40, 0);
//        viewPager.setPageMargin(100);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator2 = (CircleIndicator) view.findViewById(R.id.indicator2);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AllProducts = new ArrayList<>();

        String input = "" + MyPrefrences.getUSENAME(getActivity());
        HomeAct.title.setText(input.substring(0, 1).toUpperCase() + input.substring(1));

        HomeAct.imageView.setVisibility(View.VISIBLE);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        getVideoData();

        getBannerApi();
        getOfferApi();

        txtMyRef.setText(""+MyPrefrences.getMyRefrel(getActivity()));

//        AdRequest adIRequest = new AdRequest.Builder().build();
//        interstitial = new InterstitialAd(getActivity());
//        interstitial.setAdUnitId(getString(R.string.id_add_unit));
//
//        interstitial.loadAd(adIRequest);
//
//        interstitial.setAdListener(new AdListener()
//        {
//            public void onAdLoaded()
//            {
//                if (interstitial.isLoaded()) {
//                    interstitial.show();
//                }
//            }
//        });


        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callKere();
            }
        });
        layoutReferal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                final String shareBody = "Hey, \n" + "Check out MegaMind App on Android | Earn Upto 2 Lakhs Per Month Like Salary By Using Your Social Network Whatsapp, Facebook,Instagram,YouTube etc.Install the App Via this https://play.google.com/store/apps/details?id=megamindlons.app.megamind&referrer=" + MyPrefrences.getMyRefrel(getActivity())+"or use my referral code: "+"<b>"+MyPrefrences.getMyRefrel(getActivity())+"</b>"+" MegaMind App Download Kar Aur Life Ko Easy Kar.";
                final String shareBody = "Hey, \n" + "Check out MegaMind App on Android | Earn Upto 2 Lakhs Per Month Like Salary By Using Your Social Network Whatsapp, Facebook,Instagram,YouTube etc.Install the App Via this https://play.google.com/store/apps/details?id=megamindlons.app.megamind&referrer=" + MyPrefrences.getMyRefrel(getActivity())+" or use my referral code: "+MyPrefrences.getMyRefrel(getActivity())+" MegaMind App Download Kar Aur Life Ko Easy Kar.";
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(i, "Share"));



//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    final String shareBody = "Hey, \n" + "Check out MegaMind App on Android | Earn Upto 2 Lakhs Per Month Like Salary By Using Your Social Network Whatsapp, Facebook,Instagram,YouTube etc.Install the App Via this https://play.google.com/store/apps/details?id=megamindlons.app.megamind&referrer=" + MyPrefrences.getMyRefrel(getActivity())+" MegaMind App Download Kar Aur Life Ko Easy Kar.";
//                    Intent i = new Intent(Intent.ACTION_SEND);
//                    i.setType("text/plain");
//                    i.putExtra(Intent.EXTRA_TEXT, shareBody);
//                    startActivity(Intent.createChooser(i, "Share"));
//
//                }else{
//                    final String shareBody = "Hey, \n" + "Check out MegaMind App on Android | Earn Upto 2 Lakhs Per Month Like Salary By Using Your Social Network Whatsapp, Facebook,Instagram,YouTube etc.Install the App Via this https://play.google.com/store/apps/details?id=megamindlons.app.megamind&referrer=" + MyPrefrences.getMyRefrel(getActivity())+" MegaMind App Download Kar Aur Life Ko Easy Kar.";
//                    Picasso.with(getActivity()).load(Api.Ref_image).into(new Target() {
//                        @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            Intent i = new Intent(Intent.ACTION_SEND);
//                            i.setType("image/*");
//                            i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap, getActivity()));
//                            i.putExtra(Intent.EXTRA_TEXT, shareBody);
//                            startActivity(Intent.createChooser(i, "Share Image"));
//                        }
//                        @Override public void onBitmapFailed(Drawable errorDrawable) { }
//                        @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
//                    });
//                }


//Avnish new Update on Github
            }
        });
        myrefer.setText(MyPrefrences.getMyRefrel(getActivity()));

        addLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeAct.mFragmemnt = "AddLead";
                Fragment fragment = new AddLeads();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.mFragmemnt = "";
                Fragment fragment = new MyIcome();
                Bundle bundle = new Bundle();
                bundle.putString("myBal", myBalance.getText().toString());
                bundle.putString("expBal", myExpected.getText().toString());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                fragment.setArguments(bundle);
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.mFragmemnt = "";
                Fragment fragment = new MyIcome();
                Bundle bundle = new Bundle();
                bundle.putString("myBal", myBalance.getText().toString());
                bundle.putString("expBal", myExpected.getText().toString());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                fragment.setArguments(bundle);
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });


        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.mFragmemnt = "";
                Fragment fragment = new MyReferal();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAct.mFragmemnt = "";
                Fragment fragment = new MyReferal();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
            }
        });

        return view;
    }

    private void callKere() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "7505888666"));
        startActivity(intent);
    }


    private void getOfferApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, Api.OFFERS + "?page=1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Util.cancelPgDialog(dialog);
                ImageList2.clear();
                Log.d("jsonBannerResponse", "" + jsonObject);


                JSONArray jsonArray = jsonObject.optJSONArray("message");


                Log.d("sdfdsgdgd", jsonArray.toString());
                for (int i = 0; i < 5; i++) {
                    try {
                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        NUM_PAGES2 = jsonArray.length();


                        //if (jsonObject11.optString("TowerName").equals(uppertowername.toString()) ||  jsonObject11.optString("TowerName").equals("AllTower")) {
                        ImageList2.add(new GeterSeterOffers(jsonObject11.optString("offer_name"), jsonObject11.optString("title"), jsonObject11.optString("type"), jsonObject11.optString("code"), jsonObject11.optString("created")));
                        //}

                        viewPager2.setAdapter(customPagerAdapter2);

                        indicator2.setViewPager(viewPager2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                position2 = 0;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Please connect to the Internet", Toast.LENGTH_SHORT).show();

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest2);
    }

    private void getBannerApi() {
        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, Api.BANNER + "?agent_id=" + MyPrefrences.getUserID(getActivity()), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Util.cancelPgDialog(dialog);
                ImageList.clear();
                Log.d("jsonBannerResponse2", "" + jsonObject);
                NumberFormat formatter = new DecimalFormat("#,###");

                JSONArray jsonArray = jsonObject.optJSONArray("message");
                JSONArray jsonArrayMyBal = jsonObject.optJSONArray("mybalance");
                JSONArray jsonArrayExpBal = jsonObject.optJSONArray("expected");
                JSONObject jsonObject1 = jsonObject.optJSONObject("ref_result");
                JSONObject jsonPopup = jsonObject.optJSONObject("popup");

                refBal.setText("" + formatter.format(Double.parseDouble(jsonObject1.optString("ref_bal_amt"))));
                exptrefBal.setText("" + formatter.format(Double.parseDouble(jsonObject1.optString("ref_expected_amt"))));

                if (jsonPopup.optString("popup_status").equals("1") &&  Util.mPopupVarible.equals("1")) {

                    Intent intent = new Intent(getActivity(), PopupActivity.class);
                    intent.putExtra("image", jsonPopup.optString("image"));
                    intent.putExtra("url", jsonPopup.optString("url"));
                    startActivity(intent);
                }
                try {

                    JSONObject jsonObjectMyBal = jsonArrayMyBal.getJSONObject(0);
                    JSONObject jsonObjectExpBal = jsonArrayExpBal.getJSONObject(0);
                    myBalance.setText("" + formatter.format(Double.parseDouble(jsonObjectMyBal.getString("balance"))));
                    myExpected.setText("" + formatter.format(Double.parseDouble(jsonObjectExpBal.getString("expected"))));
                    balance = myBalance.getText().toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("sdfdsgdgd", jsonArray.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        NUM_PAGES = jsonArray.length();


                        //if (jsonObject11.optString("TowerName").equals(uppertowername.toString()) ||  jsonObject11.optString("TowerName").equals("AllTower")) {
                        ImageList.add(new Getseter(jsonObject11.optString("id"), jsonObject11.optString("banner"), jsonObject11.optString("url"), jsonObject11.optString("status")));
                        //}

                            viewPager.setAdapter(customPagerAdapter);

                        indicator.setViewPager(viewPager);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                position = 0;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Please connect to the Internet", Toast.LENGTH_SHORT).show();

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest2);
    }

    private void getVideoData() {

        Util.showPgDialog(dialog);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Api.activeYouTubeVideos, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Resposecategory", response.toString());
                Util.cancelPgDialog(dialog);
                try {

                    if (response.getString("status").equalsIgnoreCase("success")) {


                        JSONArray jsonArray = response.getJSONArray("message");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            HashMap<String, String> map = new HashMap();
                            map.put("name", jsonObject.optString("title"));
                            map.put("code", jsonObject.optString("url_key"));

                            mAdapter = new HLVAdapter(getActivity());

                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                            AllProducts.add(map);

                        }


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
                        "Error! Please Connect to the internet", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                Util.cancelPgDialog(dialog);
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


    public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {

        ArrayList<String> alName;
        ArrayList<Integer> alImage;
        Context context;
        YouTubeThumbnailView youTubeView;
        private YouTubeThumbnailLoader youTubeThumbnailLoader;
        RelativeLayout relVideo;

        public HLVAdapter(Context context) {
            super();
            this.context = context;
            this.alName = alName;
            this.alImage = alImage;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_video, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {


            viewHolder.tvSpecies.setText(AllProducts.get(i).get("name"));
//            viewHolder.actPrice.setText(AllProducts.get(i).get("offer_price"));
//            viewHolder.desc.setText(AllProducts.get(i).get("description"));
//            viewHolder.oldPrice.setText(AllProducts.get(i).get("actual_price"));


//            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//            viewHolder.imgThumbnail.setImageUrl(AllProducts.get(i).get("image_thumb").replace(" ","%20"),imageLoader);

//            viewHolder.actPrice.setPaintFlags(viewHolder.actPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            final Typeface tvFont = Typeface.createFromAsset(getActivity().getAssets(), "muli_bold.ttf");
//            final Typeface tvFont2 = Typeface.createFromAsset(getActivity().getAssets(), "muli.ttf");
            viewHolder.tvSpecies.setTypeface(tvFont);

//            youTubeView.initialize(DEVELOPER_KEY, (YouTubeThumbnailView.OnInitializedListener) getActivity());

            youTubeView.initialize(Util.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
                                                    YouTubeThumbnailLoader thumbnailLoader) {
                    youTubeThumbnailLoader = thumbnailLoader;
                    thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailListener());
//                    youTubeThumbnailLoader.setVideo(YOUTUBE_VIDEO_CODE);
                    youTubeThumbnailLoader.setVideo(AllProducts.get(i).get("code"));
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    String errorMessage =
                            String.format("onInitializationFailure (%1$s)",
                                    youTubeInitializationResult.toString());
                    //  Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
                }
            });


            relVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String VideoURL = AllProducts.get(i).get("code");
                    //  Log.d("gdfdhbfdgdhbfh",videoView.toString());
                    view.getContext().startActivity(new Intent(view.getContext(), PlayerutubeActivity.class).putExtra("video_url", VideoURL));

              /*  Log.e("video", ": " + videoItems.get(position).get("video").toString());
                String VideoURL = videoItems.get(position).get("video").toString();
                Intent videoIntent = new Intent(view.getContext(), VideoViewActivity.class);
                videoIntent.putExtra("video_url", VideoURL);
                view.getContext().startActivity(videoIntent);*/
                }
            });


            //viewHolder.imgThumbnail.setImageResource(alImage.get(i));

//            viewHolder.setClickListener(new ItemClickListener() {
//                @Override
//                public void onClick(View view, int position, boolean isLongClick) {
//                    if (isLongClick) {
//                        Toast.makeText(context, "#" + position + " - " + alName.get(position) + " (Long click)", Toast.LENGTH_SHORT).show();
//                        context.startActivity(new Intent(context, MainActivity.class));
//                    } else {
//                        Toast.makeText(context, "#" + position + " - " + alName.get(position), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return AllProducts.size();
        }

        //
//
//
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            //  public NetworkImageView imgThumbnail;


            //private ItemClickListener clickListener;

            public ViewHolder(View itemView) {
                super(itemView);

                tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
                //imgThumbnail = (NetworkImageView) itemView.findViewById(R.id.s1_15);
                youTubeView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_view);
                relVideo = (RelativeLayout) itemView.findViewById(R.id.relVideo);

                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            public TextView tvSpecies, act_price, oldPrice, actPrice, desc;

            @Override
            public void onClick(View view) {


            }

            @Override
            public boolean onLongClick(View view) {
                return false;
            }

        }

    }


    public static final class ThumbnailListener implements
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView thumbnail, String videoId) {
          /*  Toast.makeText(mContext,
                    "onThumbnailLoaded", Toast.LENGTH_SHORT).show();*/
        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView thumbnail,
                                     YouTubeThumbnailLoader.ErrorReason reason) {
          /*  Toast.makeText(mContext,
                    "onThumbnailError", Toast.LENGTH_SHORT).show();*/
        }
    }


    private class CustomPagerAdapter extends PagerAdapter {
        LayoutInflater layoutInflater;
        Button download;

        public CustomPagerAdapter() {
        }

        @Override
        public int getCount() {
            return ImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            NetworkImageView networkImageView;

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_photogallery, container, false);
            networkImageView = (NetworkImageView) view.findViewById(R.id.networkImageView);


            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
            networkImageView.setImageUrl(ImageList.get(position).getBanner(), imageLoader);

            networkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    url=ImageList.get(position).getUrl();
                    if (!url.equalsIgnoreCase("")) {
                        Intent intent = new Intent(getActivity(), WebViewPayment.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                }
            });

            (container).addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((LinearLayout) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.POSITION_NONE;
        }
    }

    private class CustomPagerAdapter2 extends PagerAdapter {
        LayoutInflater layoutInflater;
        Button download;

        public CustomPagerAdapter2() {
        }

        @Override
        public int getCount() {
            return ImageList2.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            NetworkImageView networkImageView;

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_photogallery_offer, container, false);

            LinearLayout linarForColor = view.findViewById(R.id.linarForColor);

            TextView offerName = view.findViewById(R.id.offerName);
            TextView offerTitle = view.findViewById(R.id.offerTitle);
            TextView codeType = view.findViewById(R.id.codeType);
            TextView code = view.findViewById(R.id.code);
            TextView date = view.findViewById(R.id.date);

            offerName.setText("" + ImageList2.get(position).getId().toString());
            offerTitle.setText("" + ImageList2.get(position).getBanner().toString());
            codeType.setText("" + ImageList2.get(position).getUrl().toString());
            code.setText("" + ImageList2.get(position).getStatus().toString());
            date.setText("" + ImageList2.get(position).getDate().toString());

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            final int randomColor = Color.rgb(r, g, b);

            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, //set a gradient direction
                    new int[]{randomColor, randomColor}); //set the color of gradient
            gradientDrawable.setCornerRadius(10f); //set corner radius
            if (Build.VERSION.SDK_INT >= 16)
                linarForColor.setBackground(gradientDrawable);
            else linarForColor.setBackgroundDrawable(gradientDrawable);

//            ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//            networkImageView.setImageUrl(ImageList2.get(position).getBanner().toString(), imageLoader);

            linarForColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Fragment fragment = new OffersListFragment();
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
                }
            });
            (container).addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((LinearLayout) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.POSITION_NONE;
        }
    }


    public void onPause() {
        super.onPause();
        if (handler != null)
            handler.removeCallbacks(runnale);

        if (handler2 != null)
            handler2.removeCallbacks(runnale2);
    }

    public void onResume() {
        super.onResume();
        // Start auto screen slideshow after 1s
        handler.postDelayed(runnale, PERIOD_MS);
        handler2.postDelayed(runnale2, PERIOD_MS2);
    }


    public Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file =  new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 30, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }




}
