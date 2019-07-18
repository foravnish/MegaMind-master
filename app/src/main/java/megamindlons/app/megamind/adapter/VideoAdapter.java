package megamindlons.app.megamind.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.List;

import megamindlons.app.megamind.Activittres.PlayerutubeActivity;
import megamindlons.app.megamind.Fragments.HomeFragment;
import megamindlons.app.megamind.Utils.Util;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.RowVideosBinding;
import megamindlons.app.megamind.response.VideosResponse;

public class VideoAdapter  extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    VideoAdapter.ItemClick callback;
    boolean status_follow=true;
    private List<VideosResponse.MessageItem> mLikeData = new ArrayList<>();
    private YouTubeThumbnailLoader youTubeThumbnailLoader;

    public void setViewCallback(VideoAdapter.ItemClick callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RowVideosBinding binding = RowVideosBinding.inflate(inflater, viewGroup, false);
        return new VideoAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.ViewHolder viewHolder, final int i) {

        final VideosResponse.MessageItem resultBean = mLikeData.get(i);
        viewHolder.binding.tvSpecies.setText(""+resultBean.getTitle());
//        viewHolder.binding.txtNotification.setText(""+resultBean.getNotification());
//        Utility.setImageViaGlide(viewHolder.binding.profileImage, "" + resultBean.getPosting_detail().get(0).getPosting_image(), R.drawable.user_placeholder);

        viewHolder.binding.youtubeView.initialize(Util.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView,
                                                YouTubeThumbnailLoader thumbnailLoader) {
                youTubeThumbnailLoader = thumbnailLoader;
                thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailListener());
//                    youTubeThumbnailLoader.setVideo(YOUTUBE_VIDEO_CODE);
                youTubeThumbnailLoader.setVideo(resultBean.getUrl_key());
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                String errorMessage =
                        String.format("onInitializationFailure (%1$s)",
                                youTubeInitializationResult.toString());
                //  Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.binding.relVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VideoURL = resultBean.getUrl_key();
                //  Log.d("gdfdhbfdgdhbfh",videoView.toString());
                view.getContext().startActivity(new Intent(view.getContext(), PlayerutubeActivity.class).putExtra("video_url", VideoURL));

              /*  Log.e("video", ": " + videoItems.get(position).get("video").toString());
                String VideoURL = videoItems.get(position).get("video").toString();
                Intent videoIntent = new Intent(view.getContext(), VideoViewActivity.class);
                videoIntent.putExtra("video_url", VideoURL);
                view.getContext().startActivity(videoIntent);*/
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLikeData.size();
    }

    public void setData(List<VideosResponse.MessageItem > result) {
        this.mLikeData = result;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowVideosBinding binding;


        public ViewHolder(RowVideosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            binding.executePendingBindings();
        }
    }

    public interface ItemClick {
        public void onItemClick(int position);
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


}
