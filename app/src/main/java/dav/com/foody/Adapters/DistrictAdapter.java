package dav.com.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dav.com.foody.Objects.District;
import dav.com.foody.R;

/**
 * Created by binhb on 11/05/2017.
 */

public class DistrictAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<District> districts;
    ViewHolderShowListDistrict viewHolder;

    private int selectedPost = -1;

    public int getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(int selectedPost) {
        this.selectedPost = selectedPost;
        notifyDataSetChanged();
    }

    public DistrictAdapter(Context context, int layout, List<District> districts){
        this.context = context;
        this.layout = layout;
        this.districts = districts;
    }

    public class ViewHolderShowListDistrict{
        TextView txtName;
        ImageView imgIsChoose;
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
            viewHolder = new ViewHolderShowListDistrict();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.txtName = (TextView) view.findViewById(R.id.txt_custom_dialog_row_name_address);
            viewHolder.imgIsChoose = (ImageView) view.findViewById(R.id.img_custom_dialog_row_is_choose);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderShowListDistrict) view.getTag();
        }
        District district = districts.get(position);

        viewHolder.txtName.setText(district.getName());

       if(selectedPost == position){
           viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorTextSelect));
           viewHolder.imgIsChoose.setVisibility(View.VISIBLE);
        }else{
           viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorBlack));
           viewHolder.imgIsChoose.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
