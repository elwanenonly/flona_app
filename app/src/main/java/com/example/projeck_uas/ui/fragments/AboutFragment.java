package com.example.projeck_uas.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.projeck_uas.R;
import com.example.projeck_uas.ui.EndemikViewModel;
import com.example.projeck_uas.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    private FragmentAboutBinding binding;
    private EndemikViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EndemikViewModel.class);

        viewModel.getIsDarkTheme().observe(getViewLifecycleOwner(), isDark -> {
            binding.switchTheme.setChecked(isDark);
            // Use the specific XMLs as requested
            binding.ivThemeIcon.setImageResource(isDark ? R.drawable.transparent_image__1_ : R.drawable._136881498_4fc7ed20_aca3_491d_89a8_d99dea989b3b);
        });

        binding.switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.toggleTheme(isChecked);
        });

        binding.btnGithub.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hendroprwk08/data_endemik"));
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
