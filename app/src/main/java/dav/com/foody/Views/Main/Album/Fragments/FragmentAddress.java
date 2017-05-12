package dav.com.foody.Views.Main.Album.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import dav.com.foody.R;

/**
 * Created by binhb on 07/04/2017.
 */

public class FragmentAddress extends Fragment implements TabHost.OnTabChangeListener {

    TabHost tabHost;
    TabWidget tabWidget;
    TextView txtIsYours, txtTopView, txtHot, txtIsSaved;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_album_content, container, false);
        tabHost = (TabHost) view.findViewById(R.id.tabHost);

        tabHost.setup();
        //Lets add the fake Tab
        TabHost.TabSpec mSpec = tabHost.newTabSpec("Của bạn");
        mSpec.setContent(R.id.recycler_album_content);
        mSpec.setIndicator("Của bạn");
        tabHost.addTab(mSpec);

        //Lets add the first Tab
        mSpec = tabHost.newTabSpec("Xem nhiều");
        mSpec.setContent(R.id.recycler_album_content);
        mSpec.setIndicator("Xem nhiều");
        tabHost.addTab(mSpec);

        mSpec = tabHost.newTabSpec("Nổi bật");
        mSpec.setContent(R.id.recycler_album_content);
        mSpec.setIndicator("Nổi bật");
        tabHost.addTab(mSpec);
        mSpec = tabHost.newTabSpec("Đã lưu");
        mSpec.setContent(R.id.recycler_album_content);
        mSpec.setIndicator("Đã lưu");
        tabHost.addTab(mSpec);

        tabWidget = tabHost.getTabWidget();

        for(int i=0 ;i<tabWidget.getChildCount(); i++){
            tabWidget.getChildAt(i)
                    .setBackgroundResource(R.drawable.tab_bg_selected); // unselected
        }


        txtIsYours  = (TextView) tabWidget.getChildTabViewAt(0).findViewById(android.R.id.title);
        txtTopView  = (TextView) tabWidget.getChildTabViewAt(1).findViewById(android.R.id.title);
        txtHot  = (TextView) tabWidget.getChildTabViewAt(2).findViewById(android.R.id.title);
        txtIsSaved  = (TextView) tabWidget.getChildTabViewAt(3).findViewById(android.R.id.title);

        txtIsYours.setAllCaps(false);
        txtTopView.setAllCaps(false);
        txtHot.setAllCaps(false);
        txtIsSaved.setAllCaps(false);

        txtIsYours.setTextColor(getResources().getColor(R.color.colorFoody));
        tabWidget.getChildTabViewAt(0).setBackgroundResource(R.drawable.tab_bg_unselected);
        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(this);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            tabWidget.getChildAt(i)
                    .setBackgroundResource(R.drawable.tab_bg_selected); // unselected
            TextView txt = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            txt.setTextColor(getResources().getColor(R.color.colorBlack));

        }
        tabWidget.getChildAt(tabHost.getCurrentTab())
                .setBackgroundResource(R.drawable.tab_bg_unselected); // selected
        TextView txt = (TextView) tabWidget.getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title);
        txt.setTextColor(getResources().getColor(R.color.colorFoody));
    }
}
