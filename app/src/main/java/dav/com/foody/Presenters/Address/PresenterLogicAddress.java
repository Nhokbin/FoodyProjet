package dav.com.foody.Presenters.Address;

import android.content.Context;

import java.util.List;

import dav.com.foody.Model.ModelAddress;
import dav.com.foody.Objects.City;
import dav.com.foody.Objects.District;
import dav.com.foody.Views.AddItem.IViewAddItem;
import dav.com.foody.Views.Main.Home.Fragments.IViewNew;

/**
 * Created by binhb on 13/03/2017.
 */

public class PresenterLogicAddress implements IPresenterAddress {

    IViewNew iViewNew;
    IViewAddItem iViewAddItem;

    ModelAddress modelAddress;
    Context context;

    public PresenterLogicAddress(IViewAddItem iViewAddItem, Context context){
        this.iViewAddItem= iViewAddItem;
        this.context = context;
        modelAddress = new ModelAddress();
    }

    public PresenterLogicAddress(IViewNew iViewNew, Context context){
        this.iViewNew = iViewNew;
        this.context = context;
        modelAddress = new ModelAddress();
    }

    @Override
    public void getListAddress(int cityId) {
        City city = modelAddress.getListAddress(cityId);

        if(city != null){
            iViewNew.showListAddress(city.getDistricts());
        }else{
            iViewNew.error();
        }
    }

    @Override
    public void getListCities() {
        List<City> cities = modelAddress.getListCities();

        if (!cities.isEmpty() && iViewAddItem != null){
            iViewAddItem.showListCities(cities);
        }else{
            iViewAddItem.error();
        }
    }

    @Override
    public void getListDistricts(int cityId) {
        List<District> districts = modelAddress.getListDistricts(cityId);
        if(!districts.isEmpty() && iViewAddItem != null){
            iViewAddItem.showListDistricts(districts);
        }else{
            iViewAddItem.error();
        }

    }
}
