package com.example.projeck_uas.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.example.projeck_uas.R;
import com.example.projeck_uas.data.model.EndemikItem;
import com.example.projeck_uas.databinding.FragmentDetailBinding;
import com.example.projeck_uas.ui.EndemikViewModel;
import com.google.android.material.tabs.TabLayout;

public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private EndemikViewModel viewModel;
    private EndemikItem currentItem;
    private boolean isFavorite = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);

        String itemId = getArguments() != null ? getArguments().getString("itemId") : "";
        
        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        viewModel.getFilteredData().observe(getViewLifecycleOwner(), items -> {
            for (EndemikItem item : items) {
                if (item.getId().equals(itemId)) {
                    currentItem = item;
                    bindItem(item);
                    break;
                }
            }
        });

        viewModel.isFavorite(itemId).observe(getViewLifecycleOwner(), fav -> {
            isFavorite = fav;
            binding.fabFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
        });

        binding.fabFavorite.setOnClickListener(v -> {
            if (currentItem != null) {
                viewModel.toggleFavorite(currentItem, isFavorite);
            }
        });

        setupTabs();
    }

    private void bindItem(EndemikItem item) {
        binding.tvDetailName.setText(item.getNama());
        binding.tvDetailLatin.setText(item.getNamaLatin());
        binding.chipDetailType.setText(item.getTipe());
        binding.tvDescription.setText(item.getDeskripsi());
        binding.tvInfoText.setText(String.format("Famili: %s\nGenus: %s\nAsal: %s\nSebaran: %s",
                item.getFamili(), item.getGenus(), item.getAsal(), item.getSebaran()));

        Glide.with(this).load(item.getFoto()).into(binding.ivDetailPhoto);
        
        binding.fabShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Lihat spesies: " + item.getNama());
            startActivity(Intent.createChooser(intent, "Bagikan"));
        });
    }

    private void setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                binding.layoutInfo.setVisibility(tab.getPosition() == 0 ? View.VISIBLE : View.GONE);
                binding.tvDescription.setVisibility(tab.getPosition() == 1 ? View.VISIBLE : View.GONE);
                binding.layoutMedia.setVisibility(tab.getPosition() == 2 ? View.VISIBLE : View.GONE);
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
