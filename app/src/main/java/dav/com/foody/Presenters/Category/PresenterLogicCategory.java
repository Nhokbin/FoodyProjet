package dav.com.foody.Presenters.Category;

import android.content.Context;

import java.util.List;

import dav.com.foody.Model.ModelCategory;
import dav.com.foody.Objects.Category;
import dav.com.foody.Views.Home.Fragments.IViewNew;

import static dav.com.foody.Views.Home.MainActivity.database;

/**
 * Created by binhb on 04/03/2017.
 */

public class PresenterLogicCategory implements IPresenterCateogry {

    IViewNew iViewNew;
    ModelCategory modelNew;
    Context context;
    public PresenterLogicCategory(IViewNew iViewNew, Context context){
        this.iViewNew = iViewNew;
        modelNew = new ModelCategory();
        this.context = context;

    }

    @Override
    public void getListNews() {
        List<Category> cateogories = modelNew.getListNews(database);

        if(cateogories.size()>0){
            iViewNew.showList(cateogories);
        }else{
            iViewNew.error();
        }

    }

    @Override
    public void getListNewsByWhat() {
        List<Category> categories = modelNew.getListNewsByWhat(database);
        if(categories.size()>0){
            iViewNew.showList(categories);
        }else{
            iViewNew.error();
        }
    }
}
