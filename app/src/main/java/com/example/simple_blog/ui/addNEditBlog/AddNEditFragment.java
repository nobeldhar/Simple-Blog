package com.example.simple_blog.ui.addNEditBlog;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.simple_blog.BlogApplication;
import com.example.simple_blog.R;
import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.data.remote.response.Author;
import com.example.simple_blog.databinding.AddBlogFragmentBinding;
import com.example.simple_blog.databinding.FragmentBlogDetailsBinding;
import com.example.simple_blog.utils.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class AddNEditFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private static final String ARG_PARAM1 = "param1";
    public static String BUSINESS = "Business";
    public static String LIFESTYLE = "Lifestyle";
    public static String ENTERTAINMENT = "Entertainment";
    public static String PRODUCTIVITY = "Productivity";


    private AddBlogFragmentBinding binding;

    private Integer mBlogId = null;

    @Inject
    AddNEditViewModel mViewModel;


    public static AddNEditFragment newInstance(Integer param1) {
        AddNEditFragment fragment = new AddNEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public static AddNEditFragment newInstance() {
        return new AddNEditFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_blog_fragment, container, false);
        initListener();
        return binding.getRoot();
    }

    private void initListener() {
        binding.cbBusiness.setOnCheckedChangeListener(this);
        binding.cbEntertainment.setOnCheckedChangeListener(this);
        binding.cbLifestyle.setOnCheckedChangeListener(this);
        binding.cbProductivity.setOnCheckedChangeListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel.getSubmitResult().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();

            if (message.contains("Successful"))
                requireActivity().onBackPressed();
        });


        if (mBlogId != null)
            initEditBlog();
        else
            initAddBLog();

    }


    private void initEditBlog() {
        Objects.requireNonNull(requireActivity()).setTitle("Edit Blog");
        binding.btPostText.setText("UPDATE");
        mViewModel.setUpdate(true);
        mViewModel.getBlogById(mBlogId).observe(getViewLifecycleOwner(), new Observer<Blog>() {
            @Override
            public void onChanged(Blog blog) {
                Log.d("TAG", "onChanged: "+ blog.getTitle());
                mViewModel.setMBlog(blog);
                binding.setViewModel(mViewModel);
            }
        });

    }


    private void initAddBLog() {
        Objects.requireNonNull(requireActivity()).setTitle("Add Blog");
        binding.btPostText.setText("POST");
        mViewModel.setUpdate(false);

        Author author = new Author("https://i.pravatar.cc/250", 1, "John Doe", "Content Writer");
        Blog blog = new Blog(author, new ArrayList<String>(),
                "https://i.picsum.photos/id/608/200/300.jpg?hmac=b-eDmVyhr3rI_4k3z2J_PNwOxEwSKa5EDC9uFH4jERU",
                null, null, null );
        mViewModel.setMBlog(blog);
        binding.setViewModel(mViewModel);

    }


    //Blog Category Selection Change
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked){

            switch (buttonView.getId()){
                case R.id.cb_business:
                    if (!mViewModel.getMBlog().getCategories().contains(BUSINESS))
                        mViewModel.getMBlog().getCategories().add(BUSINESS);
                    break;
                case R.id.cb_entertainment:
                    if (!mViewModel.getMBlog().getCategories().contains(ENTERTAINMENT))
                        mViewModel.getMBlog().getCategories().add(ENTERTAINMENT);
                    break;
                case R.id.cb_lifestyle:
                    if (!mViewModel.getMBlog().getCategories().contains(LIFESTYLE))
                        mViewModel.getMBlog().getCategories().add(LIFESTYLE);
                    break;
                case R.id.cb_productivity:
                    if (!mViewModel.getMBlog().getCategories().contains(PRODUCTIVITY))
                        mViewModel.getMBlog().getCategories().add(PRODUCTIVITY);
                    break;
            }
        }else {
            switch (buttonView.getId()){
                case R.id.cb_business:
                    mViewModel.getMBlog().getCategories().remove(BUSINESS);
                    break;
                case R.id.cb_entertainment:
                    mViewModel.getMBlog().getCategories().remove(ENTERTAINMENT);
                    break;
                case R.id.cb_lifestyle:
                    mViewModel.getMBlog().getCategories().remove(LIFESTYLE);
                    break;
                case R.id.cb_productivity:
                    mViewModel.getMBlog().getCategories().remove(PRODUCTIVITY);
                    break;
            }
        }

    }
}