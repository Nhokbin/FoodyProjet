package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.Item;

import static dav.com.foody.Views.Home.MainActivity.database;

/**
 * Created by binhb on 13/03/2017.
 */

public class ModelItem {

    ModelReview modelReview = new ModelReview();

    public List<Item> getListItemByCategory(SQLiteDatabase database, int categoryId, int index) {
        String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_CATEGORYID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
        Cursor cursor = database.rawQuery(query, new String[]{categoryId + ""});
        return  getListItem(cursor);
    }

    public List<Item> getListItemByType(SQLiteDatabase database, int categoryID, int typeId, int index) {
        Cursor cursor;
        if (categoryID != 2) {
            String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_CATEGORYID + " = ? AND " + CreateDatabase.TB_ITEM_TYPEID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
            cursor = database.rawQuery(query, new String[]{categoryID + "", typeId + ""});
        } else {
            String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_TYPEID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
            cursor = database.rawQuery(query, new String[]{typeId + ""});
        }
        return  getListItem(cursor);
    }

    public List<Item> getListItemByAddress(SQLiteDatabase database, int categoryId, int typeId, int districtId, int index) {
        List<Item> items = new ArrayList<>();
        Cursor cursor;
        if (typeId != -1) {
            if (categoryId != 2) {
                String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_CATEGORYID + " = ? " +
                        "AND " + CreateDatabase.TB_ITEM_TYPEID + " = ? " +
                        "AND " + CreateDatabase.TB_ITEM_DISTRICTID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
                cursor = database.rawQuery(query, new String[]{categoryId + "", typeId + "", districtId + ""});
            } else {
                String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_TYPEID + " = ? " +
                        "AND " + CreateDatabase.TB_ITEM_DISTRICTID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
                cursor = database.rawQuery(query, new String[]{typeId + "", districtId + ""});
            }
        } else {// type = -1
            if (categoryId != 2) { //category != 2
                String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE " + CreateDatabase.TB_ITEM_CATEGORYID + " = ? " +
                        "AND " + CreateDatabase.TB_ITEM_DISTRICTID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
                cursor = database.rawQuery(query, new String[]{categoryId + "", districtId + ""});
            } else {
                String query = "SELECT * FROM " + CreateDatabase.TB_ITEM + " WHERE "
                        + CreateDatabase.TB_ITEM_DISTRICTID + " = ? ORDER BY " + CreateDatabase.TB_ITEM_ID + " LIMIT " + index + ", 10";
                cursor = database.rawQuery(query, new String[]{districtId + ""});
            }
        }
        return  getListItem(cursor);
    }

    private List<Item> getListItem(Cursor cursor){
        List<Item> items = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = new Item();
            item.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_ID)));
            item.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ITEM_NAME)));
            item.setAddress(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ITEM_ADDRESS)));
            item.setImg(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_ITEM_IMG)));
            item.setRestaurantId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_RESTAURANTID)));
            item.setAvgRating(cursor.getDouble(cursor.getColumnIndex(CreateDatabase.TB_ITEM_AVGRATING)));
            item.setCategoryId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_CATEGORYID)));
            item.setDistrictId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_DISTRICTID)));
            item.setTotalPictures(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_TOTAL_PICTURES)));
            item.setTotalReviews(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_TOTAL_REVIEWS)));
            item.setTypeId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ITEM_TYPEID)));
            item.setReviews(modelReview.getListReviewByItemId(database, item.getId()));

            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

}
