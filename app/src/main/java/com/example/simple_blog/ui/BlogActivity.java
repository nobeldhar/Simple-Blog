package com.example.simple_blog.ui;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.simple_blog.R;
import com.example.simple_blog.ui.bloglist.BlogListFragment;

import java.util.ArrayList;

public class BlogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BlogListFragment.newInstance())
                    .commitNow();
        }
    }

    public void replaceFragment(Fragment fragment,
                                @AnimatorRes @AnimRes int enter,
                                @AnimatorRes @AnimRes int exit,
                                @AnimatorRes @AnimRes int popEnter,
                                @AnimatorRes @AnimRes int popExit) {

    }
}