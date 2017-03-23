package dav.com.foody.Views.Home.Fragments;

import java.util.List;

import dav.com.foody.Objects.Category;
import dav.com.foody.Objects.District;
import dav.com.foody.Objects.Item;
import dav.com.foody.Objects.Type;

/**
 * Created by binhb on 04/03/2017.
 */

public interface IViewNew {
    void showList(List<Category> cateogories);
    void showListType(List<Type> types);
    void showListAddress(List<District> districts);

    void showListItemByCategory (List<Item> items);
    void showListItemByType(List<Item> items);
    void showListItemByAddress(List<Item> items);

    void error();
}
