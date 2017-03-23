package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.Category;

/**
 * Created by binhb on 04/03/2017.
 */

public class ModelCategory {

    public List<Category> getListNews(SQLiteDatabase database){
        List<Category> cateogories = new ArrayList<>();
        Cursor cursor=database.query(CreateDatabase.TB_CATEGORY,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Category aCategory = new Category();
            aCategory.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_ID)));
            aCategory.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_NAME)));
            aCategory.setImg(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGOGRY_IMG)));

            cateogories.add(aCategory);
            cursor.moveToNext();
        }
        cursor.close();
        return cateogories;
    }

    public List<Category> getListNewsByWhat(SQLiteDatabase database){
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM " + CreateDatabase.TB_CATEGORY +" WHERE " + CreateDatabase.TB_CATEGORY_ID +" < ?";
        Cursor cursor =database.rawQuery(query,new String[]{6+""});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Category aCategory = new Category();
            aCategory.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_ID)));
            aCategory.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_NAME)));
            aCategory.setImg(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGOGRY_IMG)));

            categories.add(aCategory);
            cursor.moveToNext();
        }
        cursor.close();
        return categories;
    }
}
