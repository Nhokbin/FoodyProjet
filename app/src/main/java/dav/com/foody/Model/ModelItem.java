package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dav.com.foody.Connect.DownLoadJSON;
import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.Item;
import dav.com.foody.Objects.Review;
import dav.com.foody.Objects.User;

import static dav.com.foody.Connect.SERVER_IP.GET_BY_ADDRESS;
import static dav.com.foody.Connect.SERVER_IP.GET_BY_CATEGORY;
import static dav.com.foody.Connect.SERVER_IP.GET_BY_OPTIONS;
import static dav.com.foody.Connect.SERVER_IP.GET_DISH_BY_CATEGORY;
import static dav.com.foody.Connect.SERVER_IP.GET_DISH_BY_OPTIONS;
import static dav.com.foody.Connect.SERVER_IP.IMAGE;
import static dav.com.foody.Connect.SERVER_IP.IMAGE_AVATAR;
import static dav.com.foody.Views.Main.Home.HomeActivity.database;

/**
 * Created by binhb on 13/03/2017.
 */

public class ModelItem {

    ModelReview modelReview = new ModelReview();

    public List<Item> getListDishByOption(int optionId, int page){
        String url = GET_DISH_BY_OPTIONS;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String, String> hmOption = new HashMap<>();
        hmOption.put("options",optionId+"");

        HashMap<String,String> hmPage = new HashMap<>();
        hmPage.put("page", page+"");

        attrs.add(hmOption);
        attrs.add(hmPage);

        DownLoadJSON downLoadJSON = new DownLoadJSON(url, attrs);
        downLoadJSON.execute();

        try {
            String data = downLoadJSON.get();
            Log.d("kt123123",data);

            return  getListDish(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  null;
    }
    public List<Item> getListDishByType(int optionId, int typeId, int page) {

        String url = GET_DISH_BY_CATEGORY;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hmCategoryId =new HashMap<>();
        hmCategoryId.put("categoryId",typeId+"");

        HashMap<String,String> hmPage = new HashMap<>();
        hmPage.put("page",page+"");

        HashMap<String,String> hmOption = new HashMap<>();
        hmOption.put("option",optionId+"");



        attrs.add(hmCategoryId);
        attrs.add(hmOption);
        attrs.add(hmPage);

        DownLoadJSON downloadJSON = new DownLoadJSON(url,attrs);
        downloadJSON.execute();
        try {
            String data =downloadJSON.get();
            Log.d("kt123123",data);
            return  getListDish(data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> getListDish(String data){
        List<Item> items = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            int count = array.length();

            for(int i=0; i<count; i++){
                JSONObject object = array.getJSONObject(i);
                Item item = new Item();
                item.setId(object.getInt("id"));
                item.setName(object.getString("name"));
                item.setResName(object.getString("resName"));
                item.setAddress(object.getString("address"));
                item.setImg(IMAGE+"/item/"+object.getString("image")+".jpg");

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
                Date date = (Date)formatter.parse(object.getString("createDate"));
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                String finalString = newFormat.format(date);

                item.setCreateDate(finalString);

                User user = new User();
                user.setName(object.getString("userName"));
                user.setAvatar(IMAGE_AVATAR +object.getString("userImage"));
                item.setUser(user);
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<Item> getListItemByOption(int optionId, int page){
        String url = GET_BY_OPTIONS;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String, String> hmOption = new HashMap<>();
        hmOption.put("options",optionId+"");

        HashMap<String,String> hmPage = new HashMap<>();
        hmPage.put("page", page+"");

        attrs.add(hmOption);
        attrs.add(hmPage);

        DownLoadJSON downLoadJSON = new DownLoadJSON(url, attrs);
        downLoadJSON.execute();

        try {
            String data = downLoadJSON.get();
            Log.d("kt123123",data);

            return  getListItem(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public List<Item> getListItemByType(int optionId, int typeId, int page) {

        String url = GET_BY_CATEGORY;

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hmCategoryId =new HashMap<>();
        hmCategoryId.put("categoryId",typeId+"");

        HashMap<String,String> hmPage = new HashMap<>();
        hmPage.put("page",page+"");

        HashMap<String,String> hmOption = new HashMap<>();
        hmOption.put("option",optionId+"");



        attrs.add(hmCategoryId);
        attrs.add(hmOption);
        attrs.add(hmPage);

        DownLoadJSON downloadJSON = new DownLoadJSON(url,attrs);
        downloadJSON.execute();
        try {
            String data =downloadJSON.get();
            Log.d("kt123123",data);
            return getListItem(data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getListItemByAddress(int optionId, int categoryId, int districtId, int page) {
        String url = GET_BY_ADDRESS;

        List<HashMap<String,String>> attrs = new ArrayList<>();


        HashMap<String,String> hmPage = new HashMap<>();
        hmPage.put("page",page+"");


        HashMap<String,String> hmParams = new HashMap<>();
        List<Integer> params = new ArrayList<>();
        params.add(optionId);
        params.add(categoryId);
        params.add(districtId);

        hmParams.put("params", params.toString());

        attrs.add(hmPage);
        attrs.add(hmParams);

        DownLoadJSON downloadJSON = new DownLoadJSON(url,attrs);
        downloadJSON.execute();
        try {
            String data =downloadJSON.get();
            Log.d("kt123123",data);
            return getListItem(data);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
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

    private List<Item> getListItem(String data){
        List<Item> items = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            int count = array.length();

            for(int i=0; i<count; i++){
                JSONObject object = array.getJSONObject(i);
                Item item = new Item();
                item.setId(object.getInt("id"));
                item.setName(object.getString("name"));
                item.setAddress(object.getString("address"));
                item.setTotalReviews(object.getInt("totalReviews"));
                item.setTotalPictures(object.getInt("totalPictures"));
                item.setAvgRating(object.getDouble("avgrating"));
                item.setImg(IMAGE+"/item/"+object.getString("image")+".jpg");

                List<Review> reviews = new ArrayList<>();
                JSONArray jsReviews = object.getJSONArray("reviews");
                int countReview = jsReviews.length();

                for(int j=0; j<countReview; j++){
                    JSONObject jsReview = jsReviews.getJSONObject(j);
                    Review review = new Review();
                    review.setId(jsReview.getInt("id"));
                    review.setCommmentTrim(jsReview.getString("content"));
                    review.setRating(Double.parseDouble(jsReview.getString("avgRating")));
                    review.setAvatar(IMAGE_AVATAR + jsReview.getString("imageReview"));
                    review.setName(jsReview.getString("nameReivew"));
                    reviews.add(review);
                }
                item.setReviews(reviews);
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
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
