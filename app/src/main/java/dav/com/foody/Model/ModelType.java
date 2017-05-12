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
import dav.com.foody.Objects.Type;

/**
 * Created by binhb on 10/03/2017.
 */

public class ModelType {

  /*  public List<Type> getListTypes(SQLiteDatabase database){
        List<Type> types = new ArrayList<>();

        Cursor cursor = database.query(CreateDatabase.TB_TYPE,null,CreateDatabase.TB_TYPE_ID + " < ?",new String[]{"247"},null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Type type = new Type();
            type.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TYPE_ID)));
            type.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TYPE_NAME)));
            type.setImg(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TYPE_IMG)));
            type.setCategoryId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TYPE_CATEGORYID)));

            types.add(type);
            cursor.moveToNext();
        }
        cursor.close();
        return types;

    }*/

    public List<Type> getListTypes(){
        List<Type> types = new ArrayList<>();
        String dataJSON = "";
        String url = SERVER_IP.CATEGORY+ SERVER_IP.GET_ALL;
        DownLoadJSON downLoadJSON = new DownLoadJSON(url);
        downLoadJSON.execute();

        try{
            dataJSON = downLoadJSON.get();

            Log.d("kt",dataJSON);

            JSONArray jsonArray = new JSONArray(dataJSON);

            int count = jsonArray.length();

            for(int i=0;i<count; i++){

                JSONObject object = jsonArray.getJSONObject(i);
                Type type = new Type();
                type.setId(object.getInt("id"));
                type.setName(object.getString("name"));
                type.setImg(SERVER_IP.IMAGE + object.getString("image"));

                types.add(type);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return types;
    }
}
