package dav.com.foody.Presenters.Item;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import dav.com.foody.Model.ModelItem;
import dav.com.foody.Objects.Item;
import dav.com.foody.Views.Main.Home.Fragments.IViewNew;

import static dav.com.foody.Views.Main.Home.HomeActivity.database;

/**
 * Created by binhb on 13/03/2017.
 */

public class PresenterLogicItem implements IPresenterItem {

    IViewNew iViewNew;
    ModelItem modelItem;
    Context context;

    private static final int LOAD_MORE_BY_CATE = 12321;
    private static final int LOAD_MORE_BY_TYPE = 33121;
    private static final int LOAD_MODE_BY_DIST = 22113;
    private List<Item> listDishLoadMore;

    public PresenterLogicItem(Context context, IViewNew iViewNew) {
        this.context = context;
        this.iViewNew = iViewNew;
        modelItem = new ModelItem();
    }


    @Override
    public void getListItemByCategory(int categoryId) {
        List<Item> items = modelItem.getListItemByOption(categoryId, 1);
        if (items.size() > 0) {
            iViewNew.showListItemByCategory(items);
        } else {
            iViewNew.error();
        }
    }



    @Override
    public List<Item> getListDishByTypeLoadMore(int categoryId, int typeID, int countItem, FrameLayout frameProgress) {
        return getListDishLoadMore(new int[]{categoryId, typeID}, countItem, frameProgress, LOAD_MORE_BY_TYPE);
    }
    @Override
    public void getListDishByType(int categoryId, int typeID) {
        List<Item> items = modelItem.getListDishByType(categoryId, typeID, 1);
        if (items.size() > 0) {
            iViewNew.showListItemByType(items);
        } else {
            iViewNew.error();
        }
    }

    @Override
    public void getListItemByType(int categoryID, int typeId) {
        List<Item> items = modelItem.getListItemByType(categoryID, typeId, 1);
        if (items.size() > 0) {
            iViewNew.showListItemByType(items);
        } else {
            iViewNew.error();
        }
    }

    @Override
    public void getListItemByAddress(int[] id) {
        List<Item> items;
        items = modelItem.getListItemByAddress(id[0], id[1], id[2], 1);

        if (items.size() > 0) {
            iViewNew.showListItemByAddress(items);
        } else {
            iViewNew.error();
        }
    }

    @Override
    public List<Item> getListItemByCategoryLoadMore(int categoryId, int index, FrameLayout frameProgress) {
        return getListLoadMore(new int[]{categoryId}, index, frameProgress, LOAD_MORE_BY_CATE);
    }
    @Override
    public List<Item> getListItemByTypeLoadMore(int categoryId, int typeId, int index, FrameLayout frameProgress) {

        return getListLoadMore(new int[]{categoryId, typeId}, index, frameProgress, LOAD_MORE_BY_TYPE);
    }

    @Override
    public List<Item> getLisstItemByAddressLoadMore(int[] id, int index, FrameLayout frameProgress) {
        return getListLoadMore(id, index, frameProgress, LOAD_MODE_BY_DIST);
    }



    private List<Item> getListLoadMore(int[] id, int index, final FrameLayout frameProgress, int by) {
        List<Item> items;
        if (by == LOAD_MORE_BY_CATE) {
            items = modelItem.getListItemByOption(id[0], index);
        } else if (by == LOAD_MORE_BY_TYPE) {
            items = modelItem.getListItemByType(id[0], id[1], index);
        } else {
            items = modelItem.getListItemByAddress(id[0], id[1], id[2], index);
        }
        if (!items.isEmpty()) {
            frameProgress.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frameProgress.setVisibility(View.GONE);
                }
            }, 1000);
        }
        return items;
    }

    @Override
    public List<Item> getListDishByCategoryLoadMore(int categoryId, int index, FrameLayout frameProgress) {
        return getListDishLoadMore(new int[]{categoryId}, index, frameProgress, LOAD_MORE_BY_CATE);
    }


    @Override
    public void getListDishByCategory(int categoryId) {
        List<Item> items = modelItem.getListDishByOption(categoryId, 1);
        if (items.size() > 0) {
            iViewNew.showListItemByCategory(items);
        } else {
            iViewNew.error();
        }
    }
    private List<Item> getListDishLoadMore(int[] id, int index, final FrameLayout frameProgress, int by) {
        List<Item> items;
        if (by == LOAD_MORE_BY_CATE) {
            items = modelItem.getListDishByOption(id[0], index);
        } else if (by == LOAD_MORE_BY_TYPE) {
            items = modelItem.getListDishByType(id[0], id[1], index);
        } else {
            items = modelItem.getListItemByAddress(database, id[0], id[1], id[2], index);
        }
        if (!items.isEmpty()) {
            frameProgress.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frameProgress.setVisibility(View.GONE);
                }
            }, 1000);
        }
        return items;
    }



}
