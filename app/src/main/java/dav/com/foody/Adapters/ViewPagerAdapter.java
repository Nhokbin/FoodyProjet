package dav.com.foody.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Views.Main.Album.Fragments.FragmentAddress;
import dav.com.foody.Views.Main.Album.Fragments.FragmentImage;
import dav.com.foody.Views.Main.Home.Fragments.FragmentWhat;
import dav.com.foody.Views.Main.Home.Fragments.FragmentWhere;

import static dav.com.foody.Views.Main.MainActivity.ALBUM;
import static dav.com.foody.Views.Main.MainActivity.HOME;

/**
 * Created by binhb on 03/03/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> titleFragemnts = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, int tab) {
        super(fm);
        switch (tab){
            case HOME :
                fragments.add(new FragmentWhere());
                fragments.add(new FragmentWhat());

                titleFragemnts.add("Ở đâu");
                titleFragemnts.add("Ăn gì");
                break;
            case ALBUM:
                fragments.add(new FragmentAddress());
                fragments.add(new FragmentImage());

                titleFragemnts.add("Bộ sưu tập địa điểm");
                titleFragemnts.add("Bộ sưu tập ảnh");

                break;
        }


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragemnts.get(position);
    }
}
