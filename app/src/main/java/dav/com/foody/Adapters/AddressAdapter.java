package dav.com.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dav.com.foody.Objects.District;
import dav.com.foody.R;

/**
 * Created by binhb on 13/03/2017.
 */

public class AddressAdapter extends BaseAdapter{

    Context context;
    int layout;
    List<District> districts;
    ViewHolderShowListAddress  viewHolder;
    private int selectPos = -1;

    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
        notifyDataSetChanged();
    }

    public AddressAdapter(Context context, int layout, List<District> districts){
        this.context = context;
        this.layout = layout;
        this.districts = districts;
    }

    private class ViewHolderShowListAddress{
        TextView txtNNameDistrict,txtCountDistrict;
    }

    @Override
    public int getCount() {
        return districts.size();
    }

    @Override
    public Object getItem(int position) {
        return districts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return districts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolderShowListAddress();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.txtNNameDistrict = (TextView) view.findViewById(R.id.txt_name_district_one_address);
            viewHolder.txtCountDistrict = (TextView) view.findViewById(R.id.txt_count_district_one_address);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderShowListAddress) view.getTag();
        }

        District district = districts.get(position);
        viewHolder.txtNNameDistrict.setText(district.getName());
        viewHolder.txtCountDistrict.setText(district.getCount().toString());

        if(selectPos == position){
            viewHolder.txtNNameDistrict.setTextColor(context.getResources().getColor(R.color.colorFoody));
        }else{
            viewHolder.txtNNameDistrict.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }

        return view;
    }
}
