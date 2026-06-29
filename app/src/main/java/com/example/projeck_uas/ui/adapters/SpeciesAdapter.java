package com.example.projeck_uas.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.projeck_uas.R;
import com.example.projeck_uas.data.model.EndemikItem;
import com.example.projeck_uas.databinding.ItemSpeciesBinding;

public class SpeciesAdapter extends ListAdapter<EndemikItem, SpeciesAdapter.ViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EndemikItem item);
    }

    public SpeciesAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<EndemikItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull EndemikItem oldItem, @NonNull EndemikItem newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull EndemikItem oldItem, @NonNull EndemikItem newItem) {
                return oldItem.getNama().equals(newItem.getNama()) && 
                       oldItem.getStatus().equals(newItem.getStatus());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSpeciesBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSpeciesBinding binding;

        ViewHolder(ItemSpeciesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(EndemikItem item, OnItemClickListener listener) {
            binding.tvName.setText(item.getNama());
            binding.tvLatinName.setText(item.getNamaLatin());
            binding.tvType.setText(item.getTipe());
            binding.tvStatus.setText(item.getStatus());

            int bgRes = R.drawable.bg_status_aman;
            int textColor = ContextCompat.getColor(binding.getRoot().getContext(), R.color.success_green);

            if ("Terancam Punah".equals(item.getStatus())) {
                bgRes = R.drawable.bg_status_terancam;
                textColor = ContextCompat.getColor(binding.getRoot().getContext(), R.color.warning_orange);
            } else if ("Punah".equals(item.getStatus())) {
                bgRes = R.drawable.bg_status_punah;
                textColor = ContextCompat.getColor(binding.getRoot().getContext(), R.color.danger_red);
            }

            binding.tvStatus.setBackgroundResource(bgRes);
            binding.tvStatus.setTextColor(textColor);

            Glide.with(binding.ivSpecies.getContext())
                    .load(item.getFoto())
                    .placeholder(R.drawable.ic_home)
                    .error(R.drawable.ic_home)
                    .into(binding.ivSpecies);

            binding.getRoot().setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
