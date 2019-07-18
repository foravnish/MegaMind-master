package megamindlons.app.megamind.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;


public class ApplicationClass extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {

    private static boolean IS_CONNECTED;
    private static int numRunningActivities = 0;
    private static String TAG = ApplicationClass.class.getName();

    public static String  TOKEN="";
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);
        //register for connectivity change receiver
        MultiDex.install(this);
        // Network service for S3 upload
        //getApplicationContext().startService(new Intent(getApplicationContext(), TransferService.class));


    }



    public static boolean isAppInForeground() {
        return numRunningActivities > 0;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static boolean isConnectedToInternet() {
        return IS_CONNECTED;
    }

    public static void setIsConnected(boolean isConnected) {
        IS_CONNECTED = isConnected;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "Running activities = " + numRunningActivities);

//        TOKEN= Prefences.getAuthToken(this);
//        Log.d("TokenAUTH","token:- "+TOKEN);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        numRunningActivities++;
        Log.d(TAG, "Running activities = " + numRunningActivities);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (numRunningActivities == 1) {
            Log.i(TAG, "No running activities left, app has likely entered the background.");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        numRunningActivities--;
        Log.d(TAG, "Running activities = " + numRunningActivities);
        if (numRunningActivities == 0) {
            Log.i(TAG, "No running activities left, app has likely entered the background.");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
