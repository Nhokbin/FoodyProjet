package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.City;

/**
 * Created by binhb on 27/03/2017.
 */

public class ModelCity {

    public List<City> getListCities(SQLiteDatabase database){
        List<City> cities = new ArrayList<>();

        Cursor cursor = database.query(CreateDatabase.TB_CITY,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CITY_ID)));
            city.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CITY_NAME)));

            cities.add(city);
            cursor.moveToNext();
        }
        cursor.close();
        return cities;
    }
}
