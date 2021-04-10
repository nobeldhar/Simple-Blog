package com.example.simple_blog.ui.bloglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simple_blog.BR;
import com.example.simple_blog.R;
import com.example.simple_blog.data.local.entities.Blog;
import com.example.simple_blog.databinding.ItemBlogBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyHolder> {

    private List<Blog> blogList;
    private BlogAdapterListener listener;

    public BlogAdapter(List<Blog> blogList, BlogAdapterListener listener) {
        this.blogList = blogList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBlogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_blog, parent, false);

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Blog blog = blogList.get(position);

        holder.bind(blog);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBlogClicked(blog);
            }
        });


    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ItemBlogBinding binding;

        public MyHolder(@NonNull ItemBlogBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind (Object object){
            binding.setVariable(BR.model, object);
            binding.executePendingBindings();
        }
    }
}
