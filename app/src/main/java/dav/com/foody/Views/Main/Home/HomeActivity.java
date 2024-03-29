package dav.com.foody.Views.Main.Home;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import dav.com.foody.Adapters.ViewPagerAdapter;
import dav.com.foody.R;
import dav.com.foody.Views.Main.Home.Fragments.FragmentFooterMenu;

import static dav.com.foody.Views.Main.MainActivity.HOME;


public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;


    public static String nameCity = "TP.HCM";
    public static Integer cityPosition = 0;


    public static String nameCityWhere = "TP.HCM";
    public static Integer cityPositionWhere = 0;

    FrameLayout mExtraLayout;


    public final static String DATABASE_NAME = "Foody.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    TabWidget tabWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HandlerCoppy();
        database = this.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabWidget = (TabWidget) getParent().findViewById(android.R.id.tabs);

        mExtraLayout = (FrameLayout) findViewById(R.id.extra_layout);
       /* mExtraLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mExtraLayout.setVisibility(View.GONE);
                return true;
            }
        });*/


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), HOME);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        View logoView = getToolBarLogoIcon(toolbar);

        logoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Click Icon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static View getToolBarLogoIcon(Toolbar toolbar) {
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
        toolbar.setLogoDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews, contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
        View logoIcon = null;
        if (potentialViews.size() > 0) {
            logoIcon = potentialViews.get(0);
        }
        //Clear content description if not previously present
        if (hadContentDescription)
            toolbar.setLogoDescription(null);
        return logoIcon;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentFooterMenu newFragment = new FragmentFooterMenu();


        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.extra_layout, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();

        tabWidget.setVisibility(View.GONE);

        mExtraLayout.setVisibility(View.VISIBLE);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    private String getPath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void HandlerCoppy() {
        File file = getDatabasePath(DATABASE_NAME);
        if (!file.exists()) {
            try {
                CopyDatabaseFromAsset();
                Toast.makeText(this, "Chép thành công", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Thất bại", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFile = getPath();

            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);

            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (Exception ex) {
            Log.d("Error", ex.toString());
        }
    }


}
