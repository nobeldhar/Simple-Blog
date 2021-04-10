package com.example.simple_blog.ui.bloglist;


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
import android.widget.Toast;

import com.example.simple_blog.BlogApplication;
import com.example.simple_blog.R;
import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.databinding.BlogListFragmentBinding;
import com.example.simple_blog.ui.addNEditBlog.AddNEditFragment;
import com.example.simple_blog.ui.blogdetails.BlogDetailsFragment;
import com.example.simple_blog.utils.NetworkHelper;
import com.example.simple_blog.utils.Resource;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class BlogListFragment extends Fragment implements View.OnClickListener, BlogAdapterListener {

    @Inject
    BlogListViewModel mViewModel;

    BlogListFragmentBinding binding;

    public static BlogListFragment newInstance() {
        return new BlogListFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        ((BlogApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.blog_list_fragment, container, false);


        binding.btAddBlog.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Objects.requireNonNull(requireActivity()).setTitle("Blogs");

        Log.d("TAG", "onViewCreated: called");

        mViewModel.getBlogPostList().observe(getViewLifecycleOwner(), new Observer<Resource<List<Blog>>>() {
            @Override
            public void onChanged(Resource<List<Blog>> listResource) {
                Log.d("TAG", "onChanged: inside");
                List<Blog> response = listResource.getData();
                switch (listResource.getStatus()) {
                    case SUCCESS:
                        if (!response.isEmpty())
                            binding.rvBlog.setAdapter(new BlogAdapter(response, BlogListFragment.this));
                        binding.pb.setVisibility(View.GONE);
                        break;
                    case ERROR:
                        binding.pb.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "Connection Error", Toast.LENGTH_LONG).show();
                    case LOADING:
                        binding.pb.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_blog:
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_top, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out_bottom)
                        .replace(R.id.container, AddNEditFragment.newInstance())
                        .addToBackStack(null)
                        .commit();


        }
    }

    @Override
    public void onBlogClicked(Blog blog) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.silde_out)
                .replace(R.id.container, BlogDetailsFragment.newInstance(blog.getId()))
                .addToBackStack(null)
                .commit();
    }
}