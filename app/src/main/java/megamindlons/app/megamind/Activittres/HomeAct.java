package megamindlons.app.megamind.Activittres;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.hdodenhof.circleimageview.CircleImageView;
import megamindlons.app.megamind.Activittres.profile.ProfileAct;
import megamindlons.app.megamind.Fragments.AddLeads;
import megamindlons.app.megamind.Fragments.HomeFragment;
import megamindlons.app.megamind.Fragments.HtmlContentFragment;
import megamindlons.app.megamind.Fragments.MyIcome;
import megamindlons.app.megamind.Fragments.MyReferal;
import megamindlons.app.megamind.Fragments.services.ServicesFragment;
import megamindlons.app.megamind.Fragments.offer.OffersListFragment;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.AppRater;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.event.ProfileEvent;
import megamindlons.app.megamind.ui.videos.VideosFragment;

import static com.glidepool.Util.calculateInSampleSize;

public class HomeAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView dashboard, mProfile, profile, MyLead;
    CircleImageView addLead;
    RoundedImageView imageViewMenu;
    public static TextView title;
    public static RoundedImageView imageView;
    TextView name, email;
    public static String mFragmemnt;
    File f = null;
    AlertDialog.Builder builder;
    Boolean doubleBackToExitPressedOnce = false;
    String currentVersion;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dashboard = findViewById(R.id.dashboard);
        mProfile = findViewById(R.id.mProfile);
        addLead = findViewById(R.id.addLead);
        profile = findViewById(R.id.profile);
        MyLead = findViewById(R.id.MyLead);

        EventBus.getDefault().register(this);

        title = findViewById(R.id.title);
        imageView = findViewById(R.id.imageView);

        HomeAct.imageView.setVisibility(View.VISIBLE);

        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }





        new FetchAppVersionFromGooglePlayStore().execute();

        String input = "" + MyPrefrences.getUSENAME(getApplicationContext());
        title.setText(input.substring(0, 1).toUpperCase() + input.substring(1));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        imageViewMenu = header.findViewById(R.id.imageView);
        name = header.findViewById(R.id.name);
        email = header.findViewById(R.id.email);


        name.setText("" + input.substring(0, 1).toUpperCase() + input.substring(1));
        email.setText("+91 " + MyPrefrences.getMobile(getApplicationContext()));


        Utility.setImageViaGlide(imageView, MyPrefrences.getUserImage(getApplicationContext()));
        Utility.setImageViaGlide(imageViewMenu, MyPrefrences.getUserImage(getApplicationContext()));

        Fragment fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content_frame, fragment).commit();
        ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);




        mFragmemnt = "HomeFragment";

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mFragmemnt != "HomeFragment") {
                    mFragmemnt = "HomeFragment";
                    Fragment fragment = new HomeFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmemnt != "Profile") {
                    mFragmemnt = "Profile";
                    Fragment fragment = new OffersListFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
                }
            }
        });

        MyLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmemnt != "MyLead") {
                    mFragmemnt = "MyLead";
                    Fragment fragment = new MyIcome();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
                }
            }
        });

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmemnt != "mProfile") {
                    mFragmemnt = "mProfile";

                    Fragment fragment = new ServicesFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

                }
            }
        });
        addLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmemnt != "AddLead") {
                    mFragmemnt = "AddLead";
                    Fragment fragment = new AddLeads();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                    ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_wallet) {
//            Double myBal = Double.valueOf(HomeFragment.myBalance.getText().toString());
//            Double myRefBal = Double.valueOf(HomeFragment.refBal.getText().toString());
//            Double totalBal = myBal + myRefBal;

            Fragment fragment=new MyIcome();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout,R.anim.frag_fade_right, R.anim.frag_fad_left);

//            openAlertDialog(totalBal);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAlertDialog(Double bal) {
        builder = new AlertDialog.Builder(this);
        //builder.setMessage("sdfsd").setTitle("sdfdsgdfg");

        //Setting message manually and performing action on button click
        builder.setMessage("₹ "+bal)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Total Balance is");
        alert.show();

}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.dashboard) {
            Fragment fragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.profile) {
            startActivity(new Intent(HomeAct.this, ProfileAct.class));

        } else if (id == R.id.tersuser) {

            Fragment fragment = new HtmlContentFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("flag", "tnc");
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            fragment.setArguments(bundle);
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.privacy) {
            Fragment fragment = new HtmlContentFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("flag", "privacy");
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            fragment.setArguments(bundle);
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);
        } else if (id == R.id.discliamer) {
            Fragment fragment = new HtmlContentFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("flag", "disclimer");
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            fragment.setArguments(bundle);
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.refBanlace) {
            Fragment fragment = new HtmlContentFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("flag", "referal");
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            fragment.setArguments(bundle);
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.addlead) {
            Fragment fragment = new AddLeads();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.addbank) {
            Fragment fragment = new AddBankAcount();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);


        } else if (id == R.id.offers) {
            Fragment fragment = new OffersListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);


        } else if (id == R.id.myDirectIncome) {
            Fragment fragment = new MyIcome();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.myRefferal) {
            Fragment fragment = new MyReferal();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);


        } else if (id == R.id.videos) {
            Fragment fragment = new VideosFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
            ft.setCustomAnimations(R.anim.frag_fadein, R.anim.frag_fadeout, R.anim.frag_fade_right, R.anim.frag_fad_left);

        } else if (id == R.id.rating) {
//            AppRater.app_launched(this);

            Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
            }

//
        } else if (id == R.id.logout) {
            startActivity(new Intent(HomeAct.this, Login.class));
            MyPrefrences.resetPrefrences(getApplicationContext());
            finishAffinity();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ProfileEvent event) {


        Utility.setImageViaGlide(imageView, MyPrefrences.getUserImage(getApplicationContext()));
        Utility.setImageViaGlide(imageViewMenu, MyPrefrences.getUserImage(getApplicationContext()));

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
                Util.errorDialog(HomeAct.this, "Please Select Image");
            } else {
                //Toast.makeText(AddProduct.this, "yes", Toast.LENGTH_SHORT).show();

            }


            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class FetchAppVersionFromGooglePlayStore extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;

            try {
                Document document = (Document) Jsoup.connect("https://play.google.com/store/apps/details?id="+HomeAct.this.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newVersion;

        }


        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            Log.d("update", "Current version " + currentVersion + "playstore version " + onlineVersion);
            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    //show dialog

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeAct.this);

                    alertDialogBuilder.setTitle(HomeAct.this.getString(R.string.app_name));
                    alertDialogBuilder.setMessage("Your application update is available.");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setPositiveButton("UPDATE NOW", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HomeAct.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                            dialog.cancel();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    alertDialogBuilder.show();
                }

            }

        }



    }


}
