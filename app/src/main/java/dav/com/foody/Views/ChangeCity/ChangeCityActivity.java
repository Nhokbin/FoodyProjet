package dav.com.foody.Views.ChangeCity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import static dav.com.foody.Views.Main.Home.Fragments.FragmentWhat.RESULT_CITY;
import static dav.com.foody.Views.Main.Home.Fragments.FragmentWhere.RESULT_CITY_WHERE;
import static dav.com.foody.Views.Main.Home.HomeActivity.cityPosition;
import static dav.com.foody.Views.Main.Home.HomeActivity.cityPositionWhere;
import static dav.com.foody.Views.Main.Home.HomeActivity.nameCity;
import static dav.com.foody.Views.Main.Home.HomeActivity.nameCityWhere;

public class ChangeCityActivity extends AppCompatActivity implements View.OnClickListener, IVewCity, AdapterView.OnItemClickListener {

    Toolbar toolbar;
    ListView lvChangeCity;

    IPresenterCity iPresenterCity;

    CityAdapter cityAdapter;

    List<City> cities;

    int flag = -1; //what = 1 , where = 2

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


        if (getIntent().getAction().equals("What")) {
            flag = 1;
        } else {
            flag = 2;
        }

        iPresenterCity = new PresenterLogicCity(this, this);

        if(cities == null || cities.size()<=0){
            Log.d("getdata","123123123");
            iPresenterCity.getListCity();
        }
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
        this.cities = cities;
        cityAdapter = new CityAdapter(this, R.layout.custome_one_row_city, cities);
        lvChangeCity.setAdapter(cityAdapter);

        if(flag == 1){
            cityAdapter.setSelectedPost(cityPosition);
        }else {
            cityAdapter.setSelectedPost(cityPositionWhere);
        }
        lvChangeCity.setOnItemClickListener(this);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }

    private void settingResult(int position){
        cityAdapter.setSelectedPost(position);
        Intent intent = getIntent();
        intent.putExtra("id", cities.get(position).getId());
        if(flag == 1){
            nameCity = cities.get(position).getName();
            cityPosition = position;
            setResult(RESULT_CITY, intent);
        }else{
            //where
            nameCityWhere = cities.get(position).getName();
            cityPositionWhere = position;
            setResult(RESULT_CITY_WHERE, intent);
        }

        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        settingResult(position);
    }

    @Override
    public void onClick(View v) {
    }
}
