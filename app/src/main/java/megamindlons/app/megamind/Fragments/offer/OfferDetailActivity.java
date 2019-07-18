package megamindlons.app.megamind.Fragments.offer;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.databinding.ActivityOfferDetailBinding;

public class OfferDetailActivity extends AppCompatActivity {

    ActivityOfferDetailBinding binding;
    private Context mContext;
    String Link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_offer_detail);
        mContext = this;

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.offerName.setText(""+getIntent().getStringExtra("offer_name"));
        binding.offerTitle.setText(""+getIntent().getStringExtra("coupon_title"));
        binding.description.setText(""+getIntent().getStringExtra("category"));
        binding.codeType.setText(""+getIntent().getStringExtra("coupon_type"));
        binding.date.setText("Valid upto: "+getIntent().getStringExtra("coupon_expiry"));
        binding.codeValue.setText(""+getIntent().getStringExtra("coupon_code"));
        binding.tncTxt.setText(""+getIntent().getStringExtra("coupon_description"));

        binding.linearColor.setBackgroundColor(getIntent().getIntExtra("color",0));
        Link=getIntent().getStringExtra("link");

        binding.codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shareBody = getIntent().getStringExtra("link");
                Intent sharingIntent =new  Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });

        binding.codeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(binding.codeValue.getText());
                Toast.makeText(mContext, "Copied code", Toast.LENGTH_SHORT).show();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
                startActivity(browserIntent);


            }
        });

    }
}
