package megamindlons.app.megamind.Activittres;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.ActivityPopupBinding;
import megamindlons.app.megamind.ui.videos.WebViewPayment;

public class PopupActivity extends AppCompatActivity {

    ActivityPopupBinding binding;
    String image,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_popup);

        image=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");

        Util.mPopupVarible="0";

        Utility.setImageViaGlide(binding.imagePopup, image);

        binding.imagePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!url.equalsIgnoreCase("")) {
                    Intent intent = new Intent(getApplicationContext(), WebViewPayment.class);
                    intent.putExtra("url", url);
                    startActivity(intent);

                }

            }
        });
        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
