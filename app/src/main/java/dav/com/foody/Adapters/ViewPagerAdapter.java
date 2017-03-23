package dav.com.foody.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Views.Home.Fragments.FragmentWhat;
import dav.com.foody.Views.Home.Fragments.FragmentWhere;

/**
 * Created by binhb on 03/03/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> titleFragemnts = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.add(new FragmentWhere());
        fragments.add(new FragmentWhat());

        titleFragemnts.add("Ở đâu");
        titleFragemnts.add("Ăn gì");
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
