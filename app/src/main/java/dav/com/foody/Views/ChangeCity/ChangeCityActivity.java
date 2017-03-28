package dav.com.foody.Views.ChangeCity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dav.com.foody.Adapters.CityAdapter;
import dav.com.foody.Objects.City;
import dav.com.foody.Presenters.City.IPresenterCity;
import dav.com.foody.Presenters.City.PresenterLogicCity;
import dav.com.foody.R;

public class ChangeCityActivity extends AppCompatActivity implements View.OnClickListener, IVewCity, AdapterView.OnItemClickListener {

    Toolbar toolbar;
    ListView lvChangeCity;

    IPresenterCity iPresenterCity;

    CityAdapter cityAdapter;

    int flag = -1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);

        toolbar = (Toolbar) findViewById(R.id.tb_change_city);
        lvChangeCity = (ListView) findViewById(R.id.lv_city);

        toolbar.setTitle("Chọn Tỉnh/Thành phố");
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_backspace_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iPresenterCity = new PresenterLogicCity(this, this);

        iPresenterCity.getListCity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuchangecity, menu);
        return true;
    }


    @Override
    public void showListCities(List<City> cities) {
        cityAdapter = new CityAdapter(this, R.layout.custome_one_row_city, cities);
        lvChangeCity.setAdapter(cityAdapter);

       /* lvChangeCity.post(new Runnable() {
            @Override
            public void run() {

            }
        });*/
        if (flag == -1) {
            cityAdapter.setSelectedPost(0);
        }

        lvChangeCity.setOnItemClickListener(this);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {

    }
}
