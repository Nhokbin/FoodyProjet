package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.City;
import dav.com.foody.Objects.District;

/**
 * Created by binhb on 13/03/2017.
 */

public class ModelAddress {

    public City getListAddress(SQLiteDatabase database, int cityID){
        City city = new City();

        String query1 = "SELECT * FROM "+CreateDatabase.TB_ADDRESS_CITY + " WHERE " +CreateDatabase.TB_ADDRESS_CITY_ID + " = " + cityID +"";

        Cursor cursor=database.rawQuery(query1,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            city.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ADDRESS_CITY_ID)));
            city.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ADDRESS_CITY_NAME)));
            List<District> districts = new ArrayList<>();
            String query = "SELECT * FROM " + CreateDatabase.TB_ADDRESS_DISTRIC + " WHERE " +CreateDatabase.TB_ADDRESS_DISTRIC_CITYID +" = "+cityID +"";
            Cursor cursor1 = database.rawQuery(query,null);
            cursor1.moveToFirst();
            while (!cursor1.isAfterLast()){
                District district = new District();
                district.setId(cursor1.getInt(cursor1.getColumnIndex(CreateDatabase.TB_ADDRESS_DISTRIC_ID)));
                district.setName(cursor1.getString(cursor1.getColumnIndex(CreateDatabase.TB_ADDRESS_DISTRIC_NAME)));
                district.setCount(cursor1.getInt(cursor1.getColumnIndex(CreateDatabase.TB_ADDRESS_DISTRIC_COUNT)));
                district.setCityId(city.getId());

                districts.add(district);
                cursor1.moveToNext();
            }
            cursor1.close();

            city.setDistricts(districts);
            cursor.moveToNext();
        }
        cursor.close();
        return city;

    }
}
