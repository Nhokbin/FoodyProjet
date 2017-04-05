package dav.com.foody.Views.Home.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dav.com.foody.Adapters.AddressAdapter;
import dav.com.foody.Adapters.CategoryAdapter;
import dav.com.foody.Adapters.ItemWhereAdapter;
import dav.com.foody.Adapters.OptionAdapter;
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
import dav.com.foody.Views.ChangeCity.ChangeCityActivity;

import static android.view.View.GONE;
import static dav.com.foody.Views.Home.MainActivity.nameCityWhere;


/**
 * Created by binhb on 03/03/2017.
 */

public class FragmentWhere extends Fragment implements TabHost.OnTabChangeListener, View.OnClickListener, IViewNew, ILoadMore, AdapterView.OnItemClickListener {


    FrameLayout frameProgress, tabcontent;
    ListView lvCategory, lvType, lvAddress;
    LinearLayout lnContent, linearButtonsHome, lnContentView;
    Button cancelCategory, cancelType, cancelAddress, btnChangeCity;
    RecyclerView recyclerItems, recyclerCategory;
    ProgressBar progressBar;
    TextView txtNothing;
    TabHost tabHost;
    TabWidget tabWidget;
    TextView tvCategory, tvType, tvAddress,txtNameCity;
    NestedScrollView scrollView;

    public static final int REQUEST_CITY_WHERE = 2133;
    public static final int RESULT_CITY_WHERE = 3321;

    boolean loadMoreByCate = true;
    boolean loadMoreByType = false;
    boolean loadMoreByDis = false;

    int showTab; // 0 ->tab=0 1->tab=1 2->tab=2 3->tab=3

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

        btnChangeCity = (Button) view.findViewById(R.id.btn_change_city);
        lvCategory = (ListView) view.findViewById(R.id.lvCategory);
        lvType = (ListView) view.findViewById(R.id.lvType);
        lvAddress = (ListView) view.findViewById(R.id.lvAddress);
        lnContent = (LinearLayout) view.findViewById(R.id.lnContent);
        lnContentView = (LinearLayout) view.findViewById(R.id.linear_conent);
        frameProgress = (FrameLayout) view.findViewById(R.id.frame_progress);
        cancelCategory = (Button) view.findViewById(R.id.btn_cancel_category);
        cancelType = (Button) view.findViewById(R.id.btn_cancel_type);
        cancelAddress = (Button) view.findViewById(R.id.btn_cancel_address);
        scrollView = (NestedScrollView) view.findViewById(R.id.scrollView);
        recyclerItems = (RecyclerView) view.findViewById(R.id.recycler_list_items);
        recyclerCategory = (RecyclerView) view.findViewById(R.id.recyclerCategory);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        txtNothing = (TextView) view.findViewById(R.id.txt_nothing);
        txtNameCity = (TextView) view.findViewById(R.id.txt_name_city);

        tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabcontent = (FrameLayout) view.findViewById(android.R.id.tabcontent);
        linearButtonsHome = (LinearLayout) getActivity().findViewById(R.id.linearButtonsHome);

        tabHost.setup();
        //Lets add the fake Tab
        TabHost.TabSpec mSpec = tabHost.newTabSpec("Fake Tab");
        mSpec.setContent(R.id.ln_category);
        mSpec.setIndicator("Fake tab");
        tabHost.addTab(mSpec);
        //Lets add the first Tab
        mSpec = tabHost.newTabSpec("First Tab");
        mSpec.setContent(R.id.ln_category);
        mSpec.setIndicator("Mới nhất");
        tabHost.addTab(mSpec);
        //Lets add the second Tab
        mSpec = tabHost.newTabSpec("Second Tab");
        mSpec.setContent(R.id.ln_type);
        mSpec.setIndicator("Danh mục");
        tabHost.addTab(mSpec);
        //Lets add the third Tab
        mSpec = tabHost.newTabSpec("Third Tab");
        mSpec.setContent(R.id.ln_address);
        mSpec.setIndicator("TP.HCM");
        tabHost.addTab(mSpec);

        tabHost.setCurrentTab(0);
        tabHost.getTabWidget().getChildTabViewAt(0).setVisibility(GONE);

        tabWidget = tabHost.getTabWidget();
        tabWidget.getChildAt(2).setBackgroundResource(R.drawable.tab_bg_selected);

        tvCategory = (TextView) tabWidget.getChildTabViewAt(1).findViewById(android.R.id.title);
        tvType = (TextView) tabWidget.getChildTabViewAt(2).findViewById(android.R.id.title);
        tvAddress = (TextView) tabWidget.getChildTabViewAt(3).findViewById(android.R.id.title);

        tvCategory.setAllCaps(false);
        tvType.setAllCaps(false);
        tvAddress.setAllCaps(false);

        tabHost.setOnTabChangedListener(this);
        cancelCategory.setOnClickListener(this);
        cancelType.setOnClickListener(this);
        cancelAddress.setOnClickListener(this);

        scrollView.setNestedScrollingEnabled(false);
        recyclerCategory.setNestedScrollingEnabled(false);
        recyclerItems.setNestedScrollingEnabled(false);


        presenterLogicCategory = new PresenterLogicCategory(this, getContext());
        presenterLogicType = new PresenterLogicType(this, getContext());
        presenterLogicAddress = new PresenterLogicAddress(this, getContext());
        presenterLogicItem = new PresenterLogicItem(getContext(), this);


        presenterLogicCategory.getListNews();
        presenterLogicType.getListTypes();
        presenterLogicAddress.getListAddress(1);
        presenterLogicItem.getListItemByCategory(categoryId);

        addListOptions();
        addEvent();

        txtNameCity.setText(nameCityWhere);

        return view;
    }

    private void addListOptions() {
        OptionAdapter optionAdapter = new OptionAdapter(getContext(), R.layout.custom_one_row_option);

        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerCategory.setLayoutManager(layoutManager);
        recyclerCategory.setAdapter(optionAdapter);

        optionAdapter.notifyDataSetChanged();
    }

    private void hideState(){
        showTab = 0;
        tabHost.setCurrentTab(0);
        tabcontent.setVisibility(GONE);
        linearButtonsHome.setVisibility(View.VISIBLE);
        lnContentView.setVisibility(View.VISIBLE);
    }
    private void showState(int tab){
        tabHost.setCurrentTab(tab);
        tabcontent.setVisibility(View.VISIBLE);
        linearButtonsHome.setVisibility(GONE);
        lnContentView.setVisibility(GONE);
        showTab = tab;
    }
    //change state tab with id is a current tab
    private void changeTab(int tab) {
        if (showTab == tab) {
            hideState();
        } else {
            showState(tab);
        }
    }

    //setting for click tab widget
    private void addEvent() {
        tabWidget.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(1);
            }
        });
        tabWidget.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(2);
            }
        });
        tabWidget.getChildAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(3);
            }
        });
    }

    @Override
    public void onTabChanged(String tabId) {
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            tabWidget.getChildAt(i)
                    .setBackgroundResource(R.drawable.tab_bg_selected); // unselected
        }
        tabWidget.getChildAt(tabHost.getCurrentTab())
                .setBackgroundResource(R.drawable.tab_bg_unselected); // selected
    }
    //setting foreach fragment
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            //setting for each button cancel
            case R.id.btn_cancel_category:
            case R.id.btn_cancel_type:
            case R.id.btn_cancel_address:
                hideState();
                break;

            //open activity change city
            case R.id.btn_change_city:
                Intent iChangeCity = new Intent(getContext(), ChangeCityActivity.class);
                iChangeCity.setAction("Where");
                startActivityForResult(iChangeCity,REQUEST_CITY_WHERE);

                break;
        }
    }



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
        btnChangeCity.setOnClickListener(this);
    }
    //end load data fragmeLayout


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CITY_WHERE){
            try{
                presenterLogicAddress.getListAddress(data.getIntExtra("id",0));
                Log.d("kt123213",123213+"");
                Toast.makeText(getContext(),data.getIntExtra("id",0)+"",Toast.LENGTH_LONG).show();
                txtNameCity.setText(nameCityWhere);
            }catch (Exception ex){

            }

        }
    }

    //Show list item foreach option
    private void showListItem(List<Item> items) {
        if(txtNothing.getVisibility() == View.VISIBLE){
            txtNothing.setVisibility(View.INVISIBLE);
        }
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
            itemCategoryClick(position, tvCategory);
        } else if (parent.getAdapter() == typeAdapter) {
            itemTypeClick(position, tvType);
        } else if (parent.getAdapter() == addressAdapter) {
            itemAddressClick(position, tvAddress);
        }

    }
    private void changeTextButton(String txt, TextView textView) {
        textView.setText(txt);
        textView.setTextColor(getResources().getColor(R.color.colorFoody));
        hideState();
    }
    private void itemCategoryClick(int position, TextView textView) {
        String txt = categories.get(position).getName();
        changeTextButton(txt, textView);

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
    private void itemTypeClick(int position, TextView textView) {
        String txt = types.get(position).getName();
        changeTextButton(txt, textView);

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
    private void itemAddressClick(int position, TextView textView) {
        String txt = districts.get(position).getName();
        changeTextButton(txt, textView);

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
