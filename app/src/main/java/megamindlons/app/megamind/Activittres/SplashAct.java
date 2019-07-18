package megamindlons.app.megamind.Activittres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.mPopupVarible="1";

        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(3*1000);

                    if (MyPrefrences.getUserLogin(SplashAct.this)){
                        Intent intent=new Intent(SplashAct.this,HomeAct.class);
                        intent.putExtra("userType","");
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else {
                        Intent intent=new Intent(SplashAct.this,Login.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }




                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}
