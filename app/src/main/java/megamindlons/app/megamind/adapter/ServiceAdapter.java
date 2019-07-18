package megamindlons.app.megamind.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import megamindlons.app.megamind.Fragments.AddLeads;
import megamindlons.app.megamind.Fragments.HtmlContentFragment;
import megamindlons.app.megamind.R;
import megamindlons.app.megamind.Utils.MyPrefrences;
import megamindlons.app.megamind.Utils.Utility;
import megamindlons.app.megamind.databinding.RowServicesBinding;
import megamindlons.app.megamind.response.OfferResponse;
import megamindlons.app.megamind.response.ServicesResponse;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    List<ServicesResponse.MessageBean> list;

    ServiceCallback callback;

    String mJobType = "1";


    public void setData(List<ServicesResponse.MessageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setViewCallback(ServiceCallback callback) {
        this.callback = callback;
    }

    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RowServicesBinding binding = RowServicesBinding.inflate(inflater, viewGroup, false);
        return new ServiceAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ServiceAdapter.ViewHolder holder, final int pos) {
        if (holder != null) {


            holder.binding.servicesName.setText(""+list.get(pos).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   callback.onServiceCallback(list.get(pos).getId(),list.get(pos).getTitle(),
                           list.get(pos).getImage(),list.get(pos).getDescription());
                }
            });

            Utility.setImageViaGlide(holder.binding.imageService, list.get(pos).getImage());
        }
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowServicesBinding binding;

        public ViewHolder(RowServicesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            binding.executePendingBindings();
        }
    }


    public interface ServiceCallback{
        void onServiceCallback(String id, String name,String image, String desc);
    }

}

