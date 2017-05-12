package dav.com.foody.Presenters.City;

import android.content.Context;

import java.util.List;

import dav.com.foody.Model.ModelCity;
import dav.com.foody.Objects.City;
import dav.com.foody.Views.ChangeCity.IVewCity;

/**
 * Created by binhb on 27/03/2017.
 */

public class PresenterLogicCity implements IPresenterCity {

    IVewCity iVewCity;
    ModelCity modelCity;
    Context context;

    public PresenterLogicCity(IVewCity iVewCity, Context context){
        this.iVewCity = iVewCity;
        this.context = context;
        modelCity = new ModelCity();
    }

    @Override
    public void getListCity() {
        List<City> cities = modelCity.getListCities();

        if(cities.size() > 0){
            iVewCity.showListCities(cities);
        }else{
            iVewCity.error();
        }

    }
}
