package dav.com.foody.CustomView;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dav.com.foody.Adapters.CityAdapter;
import dav.com.foody.Adapters.DistrictAdapter;
import dav.com.foody.Objects.City;
import dav.com.foody.Objects.District;
import dav.com.foody.Presenters.Address.IPresenterAddress;
import dav.com.foody.Presenters.Address.PresenterLogicAddress;
import dav.com.foody.R;
import dav.com.foody.Views.AddItem.AddItemActivity;
import dav.com.foody.Views.AddItem.IViewAddItem;

import static dav.com.foody.Views.AddItem.AddItemActivity.cities;
import static dav.com.foody.Views.AddItem.AddItemActivity.cityId;
import static dav.com.foody.Views.AddItem.AddItemActivity.citySelectPos;
import static dav.com.foody.Views.AddItem.AddItemActivity.districtId;
import static dav.com.foody.Views.AddItem.AddItemActivity.districtSelectPos;
import static dav.com.foody.Views.AddItem.AddItemActivity.districts;

/**
 * Created by binhb on 10/05/2017.
 */

public class DialogAddItem extends DialogFragment implements View.OnClickListener, IViewAddItem, AdapterView.OnItemClickListener{

    Button btnSkip;
    TextView txtTitle, txtNameCity, txtNameDistrict;
    Dialog dialog;
    ListView lvAdapter;
    RelativeLayout relativeLayout;

    IPresenterAddress presenterAddress;
    CityAdapter cityAdapter;
    DistrictAdapter districtAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_add_item_address);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnSkip = (Button) dialog.findViewById(R.id.btn_custom_dialog_skip);
        txtTitle = (TextView) dialog.findViewById(R.id.txt_custom_dialog_title);
        lvAdapter = (ListView) dialog.findViewById(R.id.lv_custom_dialog_address);
        relativeLayout = (RelativeLayout) dialog.findViewById(R.id.frame_custom_dialog);


        btnSkip.setOnClickListener(this);

        final Bundle args = getArguments();
        Log.d("kt12312", String.valueOf(getArguments().getInt("key")));
        txtTitle.setText(args.getString("title"));

        presenterAddress = new PresenterLogicAddress(this,getActivity());

        switch (args.getString("type")){
            case "city":
                txtNameCity = (TextView) getActivity().findViewById(R.id.txt_add_item_name_city);
                if(cities.isEmpty()){
                    presenterAddress.getListCities();
                }else{
                    cityAdapter = new CityAdapter(getActivity(), R.layout.custome_one_row_city, cities);
                    lvAdapter.setAdapter(cityAdapter);
                    lvAdapter.setOnItemClickListener(this);
                    cityAdapter.notifyDataSetChanged();
                }

                break;
            case "district":
                txtNameDistrict = (TextView) getActivity().findViewById(R.id.txt_add_item_name_district);
                if(districts.isEmpty()){
                    presenterAddress.getListDistricts(args.getInt("cityId"));
                }else{
                    districtAdapter = new DistrictAdapter(getActivity(),R.layout.custom_dialog_one_row_address,districts);
                    lvAdapter.setAdapter(districtAdapter);
                    lvAdapter.setOnItemClickListener(this);
                    districtAdapter.notifyDataSetChanged();
                }
                break;
        }

        return dialog;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_custom_dialog_skip:
                dismiss();
                break;
        }
    }


    @Override
    public void showListCities(List<City> cities) {
        AddItemActivity.cities = cities;
        cityAdapter = new CityAdapter(getActivity(), R.layout.custome_one_row_city, cities);

        lvAdapter.setAdapter(cityAdapter);
        cityAdapter.setSelectedPost(citySelectPos);
        lvAdapter.setOnItemClickListener(this);

        cityAdapter.notifyDataSetChanged();

    }

    @Override
    public void showListDistricts(List<District> districts) {

        AddItemActivity.districts = districts;
        districtAdapter = new DistrictAdapter(getActivity(),R.layout.custom_dialog_one_row_address,districts);

        lvAdapter.setAdapter(districtAdapter);
        lvAdapter.setOnItemClickListener(this);

        districtAdapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(txtNameCity != null){
            citySelectPos = position;
            cityAdapter.setSelectedPost(position);
            cityId = cities.get(position).getId();
            txtNameCity.setText(cities.get(position).getName());
            districts.clear();
            dialog.dismiss();
        }else if(txtNameDistrict !=null){
            districtSelectPos = position;
            districtAdapter.setSelectedPost(position);
            districtId = districts.get(position).getId();
            txtNameDistrict.setText(districts.get(position).getName());
            dialog.dismiss();
        }
    }
}
