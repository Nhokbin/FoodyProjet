package dav.com.foody.Presenters.Item;

import android.widget.FrameLayout;

import java.util.List;

import dav.com.foody.Objects.Item;

/**
 * Created by binhb on 13/03/2017.
 */

public interface IPresenterItem {

    void getListItemByCategory(int categoryId);
    List<Item> getListItemByCategoryLoadMore(int categoryId, int index, FrameLayout frameProgress);

    void getListItemByType(int categoryID, int typeId);
    List<Item> getListItemByTypeLoadMore(int categoryID ,int typeId, int index, FrameLayout frameProgress);

    void getListItemByAddress(int[] id);
    List<Item> getLisstItemByAddressLoadMore(int[] id, int index, FrameLayout frameProgress);
}
