package dav.com.foody.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dav.com.foody.Connect.DownLoadJSON;
import dav.com.foody.Connect.SERVER_IP;
import dav.com.foody.Objects.City;

/**
 * Created by binhb on 27/03/2017.
 */

public class ModelCity {

   /* public List<City> getListCities(SQLiteDatabase database){
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
    }*/
    public List<City> getListCities(){
        List<City> cites = new ArrayList<>();
        String dataJSON = "";
        String url = SERVER_IP.CITY + SERVER_IP.GET_ALL;
        DownLoadJSON downLoadJSON = new DownLoadJSON(url);
        downLoadJSON.execute();

        try {
            dataJSON = downLoadJSON.get();
            Log.d("kt",dataJSON);
            JSONArray arrayCity = new JSONArray(dataJSON);

            int countCity = arrayCity.length();
            for(int i=0;i<countCity; i++){
                JSONObject object = arrayCity.getJSONObject(i);

                City city = new City();
                city.setId(object.getInt("id"));
                city.setName(object.getString("name"));
                city.setCount(object.getInt("count"));
                city.setUrl(object.getString("url"));
                city.setDataId(object.getInt("dataId"));

                cites.add(city);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cites;
    }
}
