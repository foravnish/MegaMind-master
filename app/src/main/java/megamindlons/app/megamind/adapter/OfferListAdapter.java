package megamindlons.app.megamind.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Random;

import megamindlons.app.megamind.Fragments.offer.OfferDetailActivity;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.RowOfferListBinding;
import megamindlons.app.megamind.response.OfferResponse;

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.ViewHolder> {

    List<OfferResponse.MessageBean> list;

    String mJobType = "1";


    public void setData(List<OfferResponse.MessageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setJobType(String mJobType) {
        this.mJobType = mJobType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RowOfferListBinding binding = RowOfferListBinding.inflate(inflater, viewGroup, false);
        return new OfferListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int pos) {
        if (holder != null) {

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            final int randomColor = Color.rgb(r,g,b);

            GradientDrawable gradientDrawable = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM, //set a gradient direction
                    new int[] {randomColor,randomColor}); //set the color of gradient
            gradientDrawable.setCornerRadius(10f); //set corner radius
            if(Build.VERSION.SDK_INT>=16)
                holder.binding.linarForColor.setBackground(gradientDrawable);
            else  holder.binding.linarForColor.setBackgroundDrawable(gradientDrawable);

//            holder.binding.linarForColor.setBackgroundColor(randomColor);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(holder.itemView.getContext(), OfferDetailActivity.class);

                    intent.putExtra("offer_name", list.get(pos).getOffer_name());
                    intent.putExtra("coupon_title",  list.get(pos).getTitle());
                    intent.putExtra("category",  list.get(pos).getCategory());
                    intent.putExtra("coupon_type",  list.get(pos).getType());
                    intent.putExtra("coupon_description",  list.get(pos).getDescription());
                    intent.putExtra("coupon_code",  list.get(pos).getCode());
                    intent.putExtra("coupon_expiry",  list.get(pos).getExpiry());
                    intent.putExtra("link",  list.get(pos).getOffer_page());
                    intent.putExtra("color",  randomColor);

                    holder.itemView.getContext().startActivity(intent);


                }
            });


            Utility.setImageViaGlide(holder.binding.imgBack, list.get(pos).getCat_image());

            holder.binding.offerName.setText(list.get(pos).getOffer_name());
            holder.binding. offerTitle.setText(list.get(pos).getTitle());
            holder.binding.codeType.setText(list.get(pos).getType());
            holder.binding.code.setText(list.get(pos).getCode());
            holder.binding.date.setText(list.get(pos).getExpiry());
        }
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowOfferListBinding binding;

        public ViewHolder(RowOfferListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            binding.executePendingBindings();
        }
    }




}
