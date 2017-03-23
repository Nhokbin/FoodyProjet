package dav.com.foody.Presenters.Address;

import android.content.Context;

import dav.com.foody.Model.ModelAddress;
import dav.com.foody.Objects.City;
import dav.com.foody.Views.Home.Fragments.IViewNew;

import static dav.com.foody.Views.Home.MainActivity.database;

/**
 * Created by binhb on 13/03/2017.
 */

public class PresenterLogicAddress implements IPresenterAddress {

    IViewNew iViewNew;
    ModelAddress modelAddress;
    Context context;

    public PresenterLogicAddress(IViewNew iViewNew, Context context){
        this.iViewNew = iViewNew;
        this.context = context;
        modelAddress = new ModelAddress();
    }

    @Override
    public void getListAddress(int cityId) {
        City city = modelAddress.getListAddress(database,cityId);

        if(city != null){
            iViewNew.showListAddress(city.getDistricts());
        }else{
            iViewNew.error();
        }
    }
}
