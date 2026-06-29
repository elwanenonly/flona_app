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
import com.example.projeck_uas.ui.adapters.RegionAdapter;
import com.example.projeck_uas.databinding.FragmentExploreBinding;
import java.util.Arrays;
import java.util.List;

public class ExploreFragment extends Fragment {
    private FragmentExploreBinding binding;
    private EndemikViewModel viewModel;
    private RegionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);

        setupRegions();
        observeViewModel();
        
        binding.btnResetFilter.setOnClickListener(v -> {
            viewModel.setSearchQuery("");
            adapter.setSelectedRegion(null);
            binding.btnResetFilter.setVisibility(View.GONE);
            
            // Auto navigate back to Home to show all
            Navigation.findNavController(v).navigate(R.id.navigation_home);
        });
    }

    private void setupRegions() {
        List<String> regions = Arrays.asList(
            "Sumatra", "Jawa", "Bali", "Kalimantan", 
            "Sulawesi", "Maluku", "Papua", "Nusa Tenggara"
        );
        
        adapter = new RegionAdapter(regions, region -> {
            viewModel.setSearchQuery(region);
            adapter.setSelectedRegion(region);
            binding.btnResetFilter.setVisibility(View.VISIBLE);
            
            // Per requirements: "Saat dipilih, tampilkan spesies dari wilayah tersebut"
            // We navigate to Home which will show the filtered list
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_home);
        });
        
        binding.rvRegions.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getFilteredData().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                binding.tvStatTotal.setText(String.valueOf(items.size()));
                long animals = items.stream().filter(i -> "Hewan".equals(i.getTipe())).count();
                long plants = items.stream().filter(i -> "Tumbuhan".equals(i.getTipe())).count();
                long threatened = items.stream().filter(i -> "Terancam Punah".equals(i.getStatus())).count();
                
                binding.tvStatAnimal.setText(String.valueOf(animals));
                binding.tvStatPlant.setText(String.valueOf(plants));
                binding.tvStatThreatened.setText(String.valueOf(threatened));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
