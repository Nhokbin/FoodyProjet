package dav.com.foody.Views.ChangeCity;

import java.util.List;

import dav.com.foody.Objects.City;

/**
 * Created by binhb on 27/03/2017.
 */

public interface IVewCity {
    void showListCities(List<City> cities);
    void error();
}
