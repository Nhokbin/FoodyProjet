package dav.com.foody.Views.Main;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

import dav.com.foody.R;
import dav.com.foody.Views.Main.Album.AlbumActivity;
import dav.com.foody.Views.Main.Home.HomeActivity;
import dav.com.foody.Views.Main.Notification.NotificationActivity;
import dav.com.foody.Views.Main.Profile.ProfileActivity;
import dav.com.foody.Views.Main.Search.SearchActivity;

public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener{
    TabHost tabHost;
    TabWidget tabWidget;

    public static final int HOME = 1;
    public static final int ALBUM = 2;
    public static final int SEARCH = 3;
    public static final int NOTIFICATION = 4;
    public static final int PROFILE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();
        tabHost.getTabWidget().setStripEnabled(false);
        TabHost.TabSpec tabHome = tabHost.newTabSpec("Home");
        tabHome.setIndicator("",getResources().getDrawable(R.drawable.tab_home_icon));
        tabHome.setContent(new Intent(this, HomeActivity.class));
        tabHost.addTab(tabHome);


        TabHost.TabSpec tabAlbum = tabHost.newTabSpec("Album");
        tabAlbum.setIndicator("",getResources().getDrawable(R.drawable.tab_album_icon));
        tabAlbum.setContent(new Intent(this, AlbumActivity.class));
        tabHost.addTab(tabAlbum);


        TabHost.TabSpec tabSearch = tabHost.newTabSpec("Search");
        tabSearch.setIndicator("",getResources().getDrawable(R.drawable.tab_search_icon));
        tabSearch.setContent(new Intent(this, SearchActivity.class));
        tabHost.addTab(tabSearch);


        TabHost.TabSpec tabNotification = tabHost.newTabSpec("Notification");
        tabNotification.setIndicator("",getResources().getDrawable(R.drawable.tab_notification_icon));
        tabNotification.setContent(new Intent(this, NotificationActivity.class));
        tabHost.addTab(tabNotification);


        TabHost.TabSpec tabProfile = tabHost.newTabSpec("Notification");
        tabProfile.setIndicator("",getResources().getDrawable(R.drawable.tab_profile_icon));
        tabProfile.setContent(new Intent(this, ProfileActivity.class));
        tabHost.addTab(tabProfile);

        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(this);
        tabWidget = tabHost.getTabWidget();

    }

    @Override
    public void onTabChanged(String tabId) {
        //textView.setDra
    }
}
