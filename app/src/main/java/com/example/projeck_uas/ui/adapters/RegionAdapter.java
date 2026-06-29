package com.example.projeck_uas.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projeck_uas.R;
import com.example.projeck_uas.databinding.ItemRegionBinding;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {

    private final List<String> regions;
    private final OnRegionClickListener listener;
    private String selectedRegion = null;

    public interface OnRegionClickListener {
        void onRegionClick(String region);
    }

    public RegionAdapter(List<String> regions, OnRegionClickListener listener) {
        this.regions = regions;
        this.listener = listener;
    }

    public void setSelectedRegion(String region) {
        this.selectedRegion = region;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemRegionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(regions.get(position), listener, selectedRegion);
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRegionBinding binding;

        ViewHolder(ItemRegionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String region, OnRegionClickListener listener, String selectedRegion) {
            binding.tvRegionName.setText(region);
            
            boolean isSelected = region.equals(selectedRegion);
            
            int strokeColor = isSelected ? 
                    ContextCompat.getColor(binding.getRoot().getContext(), R.color.forest_green) : 
                    ContextCompat.getColor(binding.getRoot().getContext(), android.R.color.darker_gray);
            
            int bgColor = isSelected ? 
                    ContextCompat.getColor(binding.getRoot().getContext(), R.color.aman_bg) : 
                    ContextCompat.getColor(binding.getRoot().getContext(), android.R.color.transparent);

            binding.cardRegion.setStrokeColor(strokeColor);
            binding.cardRegion.setCardBackgroundColor(bgColor);
            
            binding.getRoot().setOnClickListener(v -> listener.onRegionClick(region));
        }
    }
}
