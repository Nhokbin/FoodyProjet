package dav.com.foody.Objects;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dav.com.foody.Views.Main.Home.Fragments.ILoadMore;

/**
 * Created by binhb on 05/04/2017.
 */

public class NestedLoadMore implements NestedScrollView.OnScrollChangeListener {


    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    int firstItem;
    int countItem;
    int loadFirstItem;

    public NestedLoadMore(ILoadMore iLoadMore, RecyclerView.LayoutManager layoutManager){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(v.getChildAt(v.getChildCount()-1)!=null){
            countItem = layoutManager.getItemCount();
            firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            loadFirstItem = layoutManager.getChildCount();

            if(scrollY >= (v.getChildAt(v.getChildCount()-1).getMeasuredHeight()- v.getMeasuredHeight()) && scrollY > oldScrollY){
                iLoadMore.loadMore(countItem/20 +1);
            }
        }
    }
}
