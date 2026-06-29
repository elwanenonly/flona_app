package com.example.projeck_uas.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.projeck_uas.ui.EndemikViewModel;
import com.example.projeck_uas.ui.adapters.SpeciesAdapter;
import com.example.projeck_uas.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private EndemikViewModel viewModel;
    private SpeciesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);

        setupRecyclerView();
        setupSearch();
        setupFilters();
        observeViewModel();
    }

    private void setupRecyclerView() {
        adapter = new SpeciesAdapter(item -> {
            Bundle args = new Bundle();
            args.putString("itemId", item.getId());
            Navigation.findNavController(binding.getRoot()).navigate(
                    com.example.projeck_uas.R.id.action_homeFragment_to_detailFragment, args);
        });
        binding.rvSpecies.setAdapter(adapter);
    }

    private void setupSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setSearchQuery(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void setupFilters() {
        binding.chipGroupFilter.setOnCheckedChangeListener((group, checkedId) -> {
            String type = "Semua";
            if (checkedId == com.example.projeck_uas.R.id.chip_animal) type = "Hewan";
            else if (checkedId == com.example.projeck_uas.R.id.chip_plant) type = "Tumbuhan";
            viewModel.setSelectedType(type);
        });
    }

    private void observeViewModel() {
        viewModel.getFilteredData().observe(getViewLifecycleOwner(), items -> {
            adapter.submitList(items);
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
