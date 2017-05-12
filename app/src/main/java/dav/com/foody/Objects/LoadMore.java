package dav.com.foody.Objects;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import dav.com.foody.Views.Main.Home.Fragments.ILoadMore;

/**
 * Created by binhb on 15/03/2017.
 */

public class LoadMore extends RecyclerView.OnScrollListener {

    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;
    int firstItem;
    int countItem;
    int loadFirstItem;
    private boolean loading = true;

    public LoadMore(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore, boolean byWhat){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
        firstItem = 0;
        countItem = 0;
      /*  if(!byWhat){
            loadFirstItem = 5;
        }else{
            loadFirstItem = 8;
        }*/
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(dy>0){
            Log.d("KT123213", "!@#@!#!@3");
            countItem = layoutManager.getItemCount();

            if(layoutManager instanceof LinearLayoutManager){
                firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            }else if(layoutManager instanceof GridLayoutManager){
                firstItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            loadFirstItem = layoutManager.getChildCount();


            if(loading && countItem <= (firstItem + loadFirstItem)){
                loading = false;
                iLoadMore.loadMore(countItem+1);
            }
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
