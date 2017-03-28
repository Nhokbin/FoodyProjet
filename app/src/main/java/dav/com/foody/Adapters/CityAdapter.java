package dav.com.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dav.com.foody.Objects.City;
import dav.com.foody.R;

/**
 * Created by binhb on 27/03/2017.
 */

public class CityAdapter extends BaseAdapter{

    Context context;
    int layout;
    List<City> cities;
    ViewHolderShowListCity viewHolider;

    private int selectedPost = -1;

    public int getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(int selectedPost) {
        this.selectedPost = selectedPost;
        notifyDataSetChanged();
    }

    public CityAdapter(Context context, int layout, List<City> cities){
        this.context = context;
        this.layout = layout;
        this.cities = cities;
    }

    public class ViewHolderShowListCity{
        TextView txtName, txtCount;
    }
    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolider = new ViewHolderShowListCity();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolider.txtName = (TextView) view.findViewById(R.id.txt_city_name);
            viewHolider.txtCount = (TextView) view.findViewById(R.id.txt_city_count);

            view.setTag(viewHolider);
        }else{
            viewHolider = (ViewHolderShowListCity) view.getTag();
        }
        City city = cities.get(position);

        viewHolider.txtName.setText(city.getName());

        if(selectedPost == position){
            viewHolider.txtName.setTextColor(context.getResources().getColor(R.color.colorTextSelect));
            viewHolider.txtName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_white_24dp,0,0,0);
        }else{
            viewHolider.txtName.setTextColor(context.getResources().getColor(R.color.colorBlack));
            viewHolider.txtName.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        return view;
    }
}
