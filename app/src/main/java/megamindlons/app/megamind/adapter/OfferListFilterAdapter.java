package megamindlons.app.megamind.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import megamindlons.app.megamind.Utils.FilterData;
import megamindlons.app.megamind.databinding.RowOfferListFilterBinding;
import megamindlons.app.megamind.response.FilterCatResponse;

public class OfferListFilterAdapter extends RecyclerView.Adapter<OfferListFilterAdapter.ViewHolder> {

    List<FilterCatResponse.MessageBean> list;
    ListOffer mCallback;

    String mJobType = "1";
    private RadioButton lastCheckedRB = null;
    public void setData(List<FilterCatResponse.MessageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setJobType(String mJobType) {
        this.mJobType = mJobType;
    }

    public void setCallback(ListOffer mCallback){
        this.mCallback=mCallback;
    }
    @Override
    public OfferListFilterAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RowOfferListFilterBinding binding = RowOfferListFilterBinding.inflate(inflater, viewGroup, false);
        return new OfferListFilterAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final OfferListFilterAdapter.ViewHolder holder, final int pos) {
        if (holder != null) {

            if (!list.get(pos).getCategory().equals("")){
                holder.binding.layout.setVisibility(View.VISIBLE);
                holder.binding.catName.setText(list.get(pos).getCategory());
            }else{
                holder.binding.layout.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onListOffer(list.get(pos).getCategory());
//                    FilterData.mData=list.get(pos).getCategory();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowOfferListFilterBinding binding;

        public ViewHolder(RowOfferListFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            binding.executePendingBindings();
        }
    }


    public  interface ListOffer{
        void onListOffer(String id);
    }

}

