package megamindlons.app.megamind.Activittres;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.adapter.VideoAdapter;
import megamindlons.app.megamind.databinding.ActivityPopupForRegistrationBinding;

public class PopupForRegistrationActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    ActivityPopupForRegistrationBinding binding;
    private YouTubePlayerView youTubeView;
    String YOUTUBE_VIDEO_CODE="";
    private YouTubeThumbnailLoader youTubeThumbnailLoader;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popup_for_registration);
        YOUTUBE_VIDEO_CODE= "Y2NWGQMvvC0";

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        // Initializing video player with developer key
        youTubeView.initialize(Util.DEVELOPER_KEY, this);

        binding.goToDasgboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),HomeAct.class);
                startActivity(intent);
                finishAffinity();
            }
        });


//        binding.youtubeView.initialize(Util.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
//                                                YouTubeThumbnailLoader thumbnailLoader) {
//                youTubeThumbnailLoader = thumbnailLoader;
//                thumbnailLoader.setOnThumbnailLoadedListener(new VideoAdapter.ThumbnailListener());
//                    youTubeThumbnailLoader.setVideo("Y2NWGQMvvC0");
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
//                String errorMessage =
//                        String.format("onInitializationFailure (%1$s)",
//                                youTubeInitializationResult.toString());
//                //  Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        //Log.e("onInitializationSuccess",": "+wasRestored);

        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
//            player.cueVideo(YOUTUBE_VIDEO_CODE);
            player.loadVideo(YOUTUBE_VIDEO_CODE);
            // Hiding player controls
            //  player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        //Log.e("on failure",": "+errorReason);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("getting error", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.e("onActivityResult",": "+requestCode);

        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Util.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}
