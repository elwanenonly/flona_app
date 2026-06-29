package com.example.projeck_uas.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.projeck_uas.R;
import com.example.projeck_uas.ui.EndemikViewModel;
import com.example.projeck_uas.ui.adapters.FavoriteAdapter;
import com.example.projeck_uas.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    private EndemikViewModel viewModel;
    private FavoriteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);

        adapter = new FavoriteAdapter(item -> {
            Bundle args = new Bundle();
            args.putString("itemId", item.getId());
            Navigation.findNavController(binding.getRoot()).navigate(
                    R.id.action_favoriteFragment_to_detailFragment, args);
        });
        binding.rvFavorites.setAdapter(adapter);

        viewModel.getFavorites().observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            binding.tvEmptyFavorites.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
