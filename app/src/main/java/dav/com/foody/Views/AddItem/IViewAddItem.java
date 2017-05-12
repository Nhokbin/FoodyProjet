package dav.com.foody.Views.AddItem;

import java.util.List;

import dav.com.foody.Objects.City;
import dav.com.foody.Objects.District;

/**
 * Created by binhb on 10/05/2017.
 */

public interface IViewAddItem {
    void showListCities(List<City> cities);
    void showListDistricts(List<District> districts);

    void error();
}
