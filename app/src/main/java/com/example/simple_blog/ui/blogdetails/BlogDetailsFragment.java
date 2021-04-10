package com.example.simple_blog.ui.blogdetails;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.simple_blog.BlogApplication;
import com.example.simple_blog.R;
import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.databinding.FragmentBlogDetailsBinding;

import java.util.Objects;

import javax.inject.Inject;


public class BlogDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private FragmentBlogDetailsBinding binding;

    @Inject
    BlogDetailsViewModel mViewModel;

    // TODO: Rename and change types of parameters
    private Integer mBlogId;

    public BlogDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlogDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlogDetailsFragment newInstance(Integer param1) {
        BlogDetailsFragment fragment = new BlogDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBlogId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        ((BlogApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog_details, container, false);

        setHasOptionsMenu(true);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getBlogById(mBlogId).observe(getViewLifecycleOwner(), new Observer<Blog>() {
            @Override
            public void onChanged(Blog blog) {
                binding.setModel(blog);
                setUI(blog);
            }
        });


    }

    private void setUI(Blog blog) {
        Objects.requireNonNull(requireActivity()).setTitle(blog.getTitle());

        String string = "";
        for (String cat: blog.getCategories()){
            string += (cat + "\n");
        }
        binding.tvCategories.setText(string);
        binding.tvPublisher.setText("Published by "+blog.getAuthor().getName());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.blog_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_edit:
                MenuBottomSheet sheet = new MenuBottomSheet(mBlogId);
                sheet.show(getChildFragmentManager(), "menu");
                return true;
        }
        return false;
    }
}