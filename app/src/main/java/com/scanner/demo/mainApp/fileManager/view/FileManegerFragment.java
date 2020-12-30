package com.scanner.demo.mainApp.fileManager.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scanner.demo.R;
import com.scanner.demo.databinding.FragmentFileManegerBinding;
import com.scanner.demo.mainApp.homePage.view.HomePageFragmentDirections;

public class FileManegerFragment extends Fragment {
    FragmentFileManegerBinding fragmentFileManegerBinding;
    Fragment me;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFileManegerBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_file_maneger,container,false);
        return fragmentFileManegerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getFragmentManager().popBackStack();
        fragmentFileManegerBinding.archiveIcon.setOnClickListener(View ->{
            NavDirections navDirections = FileManegerFragmentDirections.actionFileManegerFragmentToArchiveFragment();
            Navigation.findNavController(fragmentFileManegerBinding.archiveIcon).navigate(navDirections);
        });
        fragmentFileManegerBinding.kartableIcon.setOnClickListener(View->{
            NavDirections navDirections = FileManegerFragmentDirections.actionFileManegerFragmentToKartableFragment();
            Navigation.findNavController(fragmentFileManegerBinding.kartableIcon).navigate(navDirections);
            getActivity().getFragmentManager().popBackStack();
        });
        fragmentFileManegerBinding.homePageIcon.setOnClickListener(View->{
            NavDirections navDirections = FileManegerFragmentDirections.actionFileManegerFragmentToHomePageFragment();
            Navigation.findNavController(fragmentFileManegerBinding.kartableIcon).navigate(navDirections);
            getActivity().getFragmentManager().popBackStack();
        });
    }

}