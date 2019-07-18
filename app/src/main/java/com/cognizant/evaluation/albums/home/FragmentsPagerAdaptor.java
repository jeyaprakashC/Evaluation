package com.cognizant.evaluation.albums.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cognizant.evaluation.albums.home.albums.AlbumsFragment;
import com.cognizant.evaluation.albums.home.photos.PhotosFragment;
import com.cognizant.evaluation.albums.home.users.UsersFragment;

public class FragmentsPagerAdaptor extends FragmentPagerAdapter {

    int COUNT = 3;

    public FragmentsPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment = null;

        switch (position) {

            case 0:
                mFragment = new AlbumsFragment();
                break;
            case 1:
                mFragment = new PhotosFragment();
                break;
            case 2:
                mFragment = new UsersFragment();
                break;


        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
