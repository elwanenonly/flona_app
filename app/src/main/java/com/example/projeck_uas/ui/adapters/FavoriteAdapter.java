package com.example.projeck_uas.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.projeck_uas.R;
import com.example.projeck_uas.data.model.EndemikItem;
import com.example.projeck_uas.data.model.FavoriteItem;
import com.example.projeck_uas.databinding.ItemFavoriteBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FavoriteAdapter extends ListAdapter<FavoriteItem, FavoriteAdapter.ViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EndemikItem item);
    }

    public FavoriteAdapter(OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<FavoriteItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull FavoriteItem oldItem, @NonNull FavoriteItem newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull FavoriteItem oldItem, @NonNull FavoriteItem newItem) {
                return oldItem.getNama().equals(newItem.getNama()) && 
                       oldItem.getSavedAt() == newItem.getSavedAt();
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFavoriteBinding binding;
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", new Locale("id", "ID"));

        ViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FavoriteItem favorite, OnItemClickListener listener) {
            binding.tvFavName.setText(favorite.getNama());
            binding.tvFavLatin.setText(favorite.getNamaLatin());
            binding.tvFavSavedAt.setText("Disimpan: " + dateFormat.format(new Date(favorite.getSavedAt())));

            Glide.with(binding.ivFavSpecies.getContext())
                    .load(favorite.getFoto())
                    .placeholder(R.drawable.ic_home)
                    .error(R.drawable.ic_home)
                    .into(binding.ivFavSpecies);

            binding.getRoot().setOnClickListener(v -> {
                EndemikItem item = new EndemikItem();
                item.setId(favorite.getId());
                item.setNama(favorite.getNama());
                item.setNamaLatin(favorite.getNamaLatin());
                item.setFoto(favorite.getFoto());
                item.setStatus(favorite.getStatus());
                item.setTipe(favorite.getTipe());
                listener.onItemClick(item);
            });
        }
    }
}
