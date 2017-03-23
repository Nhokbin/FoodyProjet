package dav.com.foody.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dav.com.foody.Database.CreateDatabase;
import dav.com.foody.Objects.Review;


/**
 * Created by binhb on 20/03/2017.
 */

public class ModelReview {

    public List<Review> getListReviewByItemId(SQLiteDatabase database, int itemId){
        List<Review> reviews = new ArrayList<>();

        Cursor cursor = database.query(CreateDatabase.TB_REVIEW,null,CreateDatabase.TB_REVIEW_ITEM_ID + " = ?",new String[]{itemId +""},null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Review review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_ID)));
            review.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_NAME)));
            review.setRating(cursor.getDouble(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_RATING)));
            review.setComment(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_COMMENT)));
            review.setCommmentTrim(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_COMMENT_TRIM)));
            review.setAvatar(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_AVATAR)));
            review.setItemId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_ITEM_ID)));
            review.setReviewUrl(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_REVIEW_REVIEWURL)));

            reviews.add(review);
            cursor.moveToNext();
        }
        cursor.close();
        return reviews;
    }
}
