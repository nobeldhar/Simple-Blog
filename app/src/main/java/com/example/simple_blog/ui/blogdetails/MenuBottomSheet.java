package com.example.simple_blog.ui.blogdetails;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simple_blog.R;
import com.example.simple_blog.databinding.FragmentMenuBottomSheetBinding;
import com.example.simple_blog.ui.addNEditBlog.AddNEditFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class MenuBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {


    private Integer mBlogId;

    public MenuBottomSheet(Integer mBlogId) {
        this.mBlogId = mBlogId;
    }

    private FragmentMenuBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu_bottom_sheet, container, false);

        binding.cvEditBlog.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_edit_blog:
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.silde_out)
                        .replace(R.id.container, AddNEditFragment.newInstance(mBlogId))
                        .addToBackStack(null)
                        .commit();
                dismiss();
        }
    }
}