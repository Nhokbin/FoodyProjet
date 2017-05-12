package dav.com.foody.Presenters.Address;

/**
 * Created by binhb on 13/03/2017.
 */

public interface IPresenterAddress {
    void getListAddress(int cityId);
    void getListCities();

    void getListDistricts(int cityId);


}
