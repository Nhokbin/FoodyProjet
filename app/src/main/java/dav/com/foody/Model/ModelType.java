package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.Type;

/**
 * Created by binhb on 10/03/2017.
 */

public class ModelType {

    public List<Type> getListTypes(SQLiteDatabase database){
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

    }
}
