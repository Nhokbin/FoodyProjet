package dav.com.foody.Presenters.Type;

import android.content.Context;

import java.util.List;

import dav.com.foody.Model.ModelType;
import dav.com.foody.Objects.Type;
import dav.com.foody.Views.Main.Home.Fragments.IViewNew;

/**
 * Created by binhb on 10/03/2017.
 */

public class PresenterLogicType implements IPresenterType{

    IViewNew iViewNew;
    ModelType modelType;
    Context context;
    public PresenterLogicType(IViewNew iViewNew, Context context){
        this.iViewNew = iViewNew;
        this.context = context;
        modelType = new ModelType();
    }

    @Override
    public void getListTypes() {
        List<Type> types =modelType.getListTypes();

        if(types.size()>0){
            iViewNew.showListType(types);
        }else{
            iViewNew.error();
        }
    }
}
