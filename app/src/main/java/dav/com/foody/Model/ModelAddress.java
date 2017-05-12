package dav.com.foody.Model;

import android.content.Context;
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
import dav.com.foody.Objects.District;

import static dav.com.foody.Connect.SERVER_IP.CITY;
import static dav.com.foody.Connect.SERVER_IP.DISTRICT;
import static dav.com.foody.Connect.SERVER_IP.GET_ALL;
import static dav.com.foody.Connect.SERVER_IP.GET_BY_CITY;

/**
 * Created by binhb on 13/03/2017.
 */

public class ModelAddress {

    Context context;
    public ModelAddress(){

    }
    public ModelAddress(Context context){
        this.context = context;
    }
  /*  public City getListAddress(SQLiteDatabase database, int cityID){
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

    }*/
  public List<City> getListCities(){
      List<City> cities = new ArrayList<>();
      String dataJSON = "";
      String url = CITY + GET_ALL;
      DownLoadJSON downLoadJSON = new DownLoadJSON(url);
      downLoadJSON.execute();
      try {

          dataJSON = downLoadJSON.get();
          JSONArray array = new JSONArray(dataJSON);
          int count = array.length();
          for(int i=0; i<count; i++){
              JSONObject object = array.getJSONObject(i);
              City city = new City();
              city.setId(object.getInt("id"));
              city.setName(object.getString("name"));
              city.setCount(object.getInt("count"));
              city.setUrl(object.getString("url"));
              city.setDataId(object.getInt("dataId"));

              cities.add(city);
          }

      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      } catch (JSONException e) {
          e.printStackTrace();
      }
      return cities;
  }
  public City getListAddress(int cityID){
      City city = new City();
      String dataJSON = "";
      String url = SERVER_IP.CITY+ SERVER_IP.GET_BY_ID + cityID;
      DownLoadJSON downLoadJSON = new DownLoadJSON(url);
      downLoadJSON.execute();

      try {
          dataJSON = downLoadJSON.get();
          Log.d("kt123213",dataJSON);
          JSONObject object = new JSONObject(dataJSON);

          city.setId(object.getInt("id"));
          city.setName(object.getString("name"));
          city.setCount(object.getInt("count"));
          city.setUrl(object.getString("url"));
          city.setDataId(object.getInt("dataId"));

          String urlDistrict = DISTRICT + GET_BY_CITY + cityID;
          downLoadJSON = new DownLoadJSON(urlDistrict);
          downLoadJSON.execute();

          dataJSON = downLoadJSON.get();
          JSONArray array = new JSONArray(dataJSON);
          int count = array.length();
          List<District> districts = new ArrayList<>();
          for(int i=0; i<count;i++){
              JSONObject jsDistrict = array.getJSONObject(i);
              District district = new District();
              district.setId(jsDistrict.getInt("id"));
              district.setName(jsDistrict.getString("name"));
              district.setCount(jsDistrict.getInt("count"));
              district.setCityId(cityID);

              districts.add(district);
          }

          city.setDistricts(districts);
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      } catch (JSONException e) {
          e.printStackTrace();
      }
      return city;
  }

    public List<District> getListDistricts(int cityId) {
        List<District> districts = new ArrayList<>();
        String url = DISTRICT + GET_BY_CITY + cityId;
        String dataJSON = "";
        DownLoadJSON downLoadJSON = new DownLoadJSON(url);
        downLoadJSON.execute();

        try {
            dataJSON = downLoadJSON.get();
            Log.d("KT123213",dataJSON);
            JSONArray array = new JSONArray(dataJSON);
            int count = array.length();
            for(int i=0; i<count;i++){
                JSONObject jsDistrict = array.getJSONObject(i);
                District district = new District();
                district.setId(jsDistrict.getInt("id"));
                district.setName(jsDistrict.getString("name"));
                district.setCount(jsDistrict.getInt("count"));
                district.setCityId(cityId);

                districts.add(district);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return districts;
    }
}
