package dav.com.foody.Views.Home.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import dav.com.foody.Adapters.AddressAdapter;
import dav.com.foody.Adapters.CategoryAdapter;
import dav.com.foody.Adapters.ItemWhereAdapter;
import dav.com.foody.Adapters.TypeAdapter;
import dav.com.foody.Objects.Category;
import dav.com.foody.Objects.District;
import dav.com.foody.Objects.Item;
import dav.com.foody.Objects.LoadMore;
import dav.com.foody.Objects.Type;
import dav.com.foody.Presenters.Address.PresenterLogicAddress;
import dav.com.foody.Presenters.Category.PresenterLogicCategory;
import dav.com.foody.Presenters.Item.PresenterLogicItem;
import dav.com.foody.Presenters.Type.PresenterLogicType;
import dav.com.foody.R;

import static android.view.View.GONE;

/**
 * Created by binhb on 03/03/2017.
 */

public class FragmentWhere extends Fragment implements View.OnClickListener, IViewNew, ILoadMore, AdapterView.OnItemClickListener {


    ToggleButton btnCategory, btnType, btnAddress;
    FrameLayout frameCategory, frameType, frameAddress, frameProgress;
    ListView lvCategory, lvType, lvAddress;
    LinearLayout lnContent, linearButtonsHome, lnContentView;
    Button cancelCategory, cancelType, cancelAddress;
    RecyclerView recyclerItems;
    ProgressBar progressBar;
    TextView txtNothing;

    boolean showCategory = false;
    boolean showType = false;
    boolean showAddress = false;
    boolean loadMoreByCate = true;
    boolean loadMoreByType = false;
    boolean loadMoreByDis = false;

    int categoryId = 2;
    int typeID = -1;
    int districtId = -1;

    ItemWhereAdapter itemWhereAdapter;
    CategoryAdapter categoryAdapter;
    TypeAdapter typeAdapter;
    AddressAdapter addressAdapter;

    List<Item> items;
    List<Category> categories;
    List<Type> types;
    List<District> districts;

    PresenterLogicCategory presenterLogicCategory;
    PresenterLogicType presenterLogicType;
    PresenterLogicAddress presenterLogicAddress;
    PresenterLogicItem presenterLogicItem;

    LoadMore loadMore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_content, container, false);

        btnCategory = (ToggleButton) view.findViewById(R.id.btnCategory);
        btnType = (ToggleButton) view.findViewById(R.id.btnType);
        btnAddress = (ToggleButton) view.findViewById(R.id.btnAddress);
        lvCategory = (ListView) view.findViewById(R.id.lvCategory);
        lvType = (ListView) view.findViewById(R.id.lvType);
        lvAddress = (ListView) view.findViewById(R.id.lvAddress);
        lnContent = (LinearLayout) view.findViewById(R.id.lnContent);
        lnContentView = (LinearLayout) view.findViewById(R.id.linear_conent);
        frameCategory = (FrameLayout) view.findViewById(R.id.frame_category);
        frameType = (FrameLayout) view.findViewById(R.id.frame_type);
        frameAddress = (FrameLayout) view.findViewById(R.id.frame_address);
        frameProgress = (FrameLayout) view.findViewById(R.id.frame_progress);
        cancelCategory = (Button) view.findViewById(R.id.btn_cancel_category);
        cancelType = (Button) view.findViewById(R.id.btn_cancel_type);
        cancelAddress = (Button) view.findViewById(R.id.btn_cancel_address);
        recyclerItems = (RecyclerView) view.findViewById(R.id.recycler_list_items);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        txtNothing = (TextView) view.findViewById(R.id.txt_nothing);
        linearButtonsHome = (LinearLayout) getActivity().findViewById(R.id.linearButtonsHome);

        btnCategory.setOnClickListener(this);
        btnAddress.setOnClickListener(this);
        btnType.setOnClickListener(this);
        cancelCategory.setOnClickListener(this);
        cancelType.setOnClickListener(this);
        cancelAddress.setOnClickListener(this);

        presenterLogicCategory = new PresenterLogicCategory(this, getContext());
        presenterLogicType = new PresenterLogicType(this, getContext());
        presenterLogicAddress = new PresenterLogicAddress(this, getContext());
        presenterLogicItem = new PresenterLogicItem(getContext(), this);


        presenterLogicCategory.getListNews();
        presenterLogicType.getListTypes();
        presenterLogicAddress.getListAddress(1);
        presenterLogicItem.getListItemByCategory(categoryId);


        return view;
    }

    //setting foreach fragment
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_cancel_category:
                showCategory = !showCategory;
                btnCancel(btnCategory);
                break;

            case R.id.btn_cancel_type:
                showType = !showType;
                btnCancel(btnType);
                break;

            case R.id.btn_cancel_address:
                showAddress = !showType;
                btnCancel(btnAddress);
                break;

            case R.id.btnCategory:
                hideFrame();
                if (btnCategory.isChecked() != false) {
                    showState(lnContent, frameCategory);
                } else {
                    hideState();
                }
                btnType.setChecked(false);
                btnAddress.setChecked(false);
                showCategory = !showCategory;
                break;

            case R.id.btnType:
                hideFrame();
                if (btnType.isChecked() != false) {
                    showState(lnContent, frameType);
                } else {
                    hideState();
                }
                btnCategory.setChecked(false);
                btnAddress.setChecked(false);
                showType = !showType;
                break;

            case R.id.btnAddress:
                hideFrame();
                if (btnAddress.isChecked() != false) {
                    showState(lnContent, frameAddress);
                } else {
                    hideState();
                }
                btnCategory.setChecked(false);
                btnType.setChecked(false);
                showAddress = !showAddress;
                break;
        }
    }

    private void hideFrame() {
        showType = false;
        showAddress = false;
        showCategory = false;
        frameCategory.setVisibility(GONE);
        frameType.setVisibility(GONE);
    }

    private void btnCancel(ToggleButton button) {
        hideState();
        button.setChecked(false);
    }

    private void hideState() {
        lnContent.setVisibility(GONE);
        linearButtonsHome.setVisibility(View.VISIBLE);
        lnContentView.setVisibility(View.VISIBLE);
    }

    private void showState(LinearLayout linearLayout, FrameLayout fragment) {
        linearLayout.setVisibility(View.VISIBLE);
        fragment.setVisibility(View.VISIBLE);
        linearButtonsHome.setVisibility(GONE);
        lnContentView.setVisibility(GONE);
    }
    //end setting foreach fragment


    //load data fragmeLayout
    @Override
    public void showList(final List<Category> cateogories) {
        this.categories = cateogories;
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.custome_one_row, cateogories);
        lvCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        lvCategory.setOnItemClickListener(this);
    }
    @Override
    public void showListType(List<Type> types) {
        this.types = types;
        typeAdapter = new TypeAdapter(getContext(), R.layout.custome_one_row_type, types);
        lvType.setAdapter(typeAdapter);
        typeAdapter.notifyDataSetChanged();
        lvType.setOnItemClickListener(this);
    }
    @Override
    public void showListAddress(List<District> districts) {
        this.districts = districts;
        addressAdapter = new AddressAdapter(getContext(), R.layout.custom_one_row_address, districts);
        lvAddress.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();
        lvAddress.setOnItemClickListener(this);
    }
    //end load data fragmeLayout


    //Show list item foreach option
    private void showListItem(List<Item> items) {
        this.items = items;
        itemWhereAdapter = new ItemWhereAdapter(getContext(), R.layout.custom_one_row_where_item, items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerItems.setLayoutManager(layoutManager);
        recyclerItems.setAdapter(itemWhereAdapter);

        itemWhereAdapter.notifyDataSetChanged();
        loadMore = new LoadMore(layoutManager, this, false);
        recyclerItems.addOnScrollListener(loadMore);
    }
    @Override
    public void showListItemByCategory(List<Item> items) {
        showListItem(items);
    }
    @Override
    public void showListItemByType(List<Item> items) {
        showListItem(items);
    }
    @Override
    public void showListItemByAddress(List<Item> items) {
        showListItem(items);
    }
    //end show list item foreach option

    //setting for load more item
    @Override
    public void loadMore(int countItem) {
        List<Item> items;
        if (loadMoreByCate) {
            items = presenterLogicItem.getListItemByCategoryLoadMore(categoryId, countItem, frameProgress);
        } else if (loadMoreByType) {
            items = presenterLogicItem.getListItemByTypeLoadMore(categoryId, typeID, countItem, frameProgress);
            Log.d("kt123",items.size()+"'");
        } else {
            items = presenterLogicItem.getLisstItemByAddressLoadMore(new int[]{categoryId, typeID, districtId}, countItem, frameProgress);
        }
        this.items.addAll(items);
        itemWhereAdapter.notifyDataSetChanged();
    }
    private void chageLoadMore() {
        loadMoreByCate = false;
        loadMoreByDis = false;
        loadMoreByType = false;
    }
    //end setting for load more item

    //load item when clicked item in listview
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chageLoadMore();
        if (parent.getAdapter() == categoryAdapter) {
            itemCategoryClick(position, btnCategory);
        } else if (parent.getAdapter() == typeAdapter) {
            itemTypeClick(position, btnType);
        } else if (parent.getAdapter() == addressAdapter) {
            itemAddressClick(position, btnAddress);
        }

    }

    private void changeTextButton(String txt, ToggleButton button) {
        button.setText(txt);
        button.setTextOn(txt);
        button.setTextOff(txt);
        button.setTextColor(getResources().getColor(R.color.colorFoody));
        button.setChecked(false);
        items.removeAll(items);
        hideFrame();
        hideState();
    }

    private void itemCategoryClick(int position, ToggleButton button) {
        String txt = categories.get(position).getName();
        changeTextButton(txt, button);

        categoryAdapter.setSelectedPos(position);

        itemWhereAdapter.clearData();
        itemWhereAdapter.notifyDataSetChanged();

        categoryId = categories.get(position).getId();

        recyclerItems.removeOnScrollListener(loadMore);
        if (districtId == -1) {
            if (typeID == -1) {
                loadMoreByCate = true;
                presenterLogicItem.getListItemByCategory(categoryId);
            } else {
                loadMoreByType = true;
                presenterLogicItem.getListItemByType(categoryId, typeID);
            }
        } else {
            loadMoreByDis = true;
            presenterLogicItem.getListItemByAddress(new int[]{categoryId, typeID, districtId});
        }

    }

    private void itemTypeClick(int position, ToggleButton button) {
        String txt = types.get(position).getName();
        changeTextButton(txt, button);

        typeAdapter.setSelectdPos(position);
        itemWhereAdapter.clearData();
        itemWhereAdapter.notifyDataSetChanged();
        typeID = types.get(position).getId();

        recyclerItems.removeOnScrollListener(loadMore);
        if (districtId == -1) {
            loadMoreByType = true;
            presenterLogicItem.getListItemByType(categoryId, typeID);
        } else {
            loadMoreByDis = true;
            presenterLogicItem.getListItemByAddress(new int[]{categoryId, typeID, districtId});
        }

    }

    private void itemAddressClick(int position, ToggleButton button) {
        String txt = districts.get(position).getName();
        changeTextButton(txt, button);

        addressAdapter.setSelectPos(position);
        itemWhereAdapter.clearData();
        itemWhereAdapter.notifyDataSetChanged();

        districtId = districts.get(position).getId();

        recyclerItems.removeOnScrollListener(loadMore);

        presenterLogicItem.getListItemByAddress(new int[]{categoryId, typeID, districtId});

        loadMoreByDis = true;
    }
    //end load item

    @Override
    public void error() {
        txtNothing.setVisibility(View.VISIBLE);
    }


}
