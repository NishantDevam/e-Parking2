package com.example.demo.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demo.Fragments.ChatFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {


    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new ChatFragment();
        }
        return new ChatFragment();


    }

    @Override
    public int getCount() {

        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        if (position == 0){
            title = "CHATS";
        }
        return title;
    }
}
