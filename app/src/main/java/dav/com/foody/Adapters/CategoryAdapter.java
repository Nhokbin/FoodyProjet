package dav.com.foody.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dav.com.foody.Objects.Category;
import dav.com.foody.R;

/**
 * Created by binhb on 09/03/2017.
 */

public class CategoryAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Category> cateogories;
    ViewHolderShowListCategory viewHolder;

    private int selectedPos = -1;

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }



    public CategoryAdapter(Context context, int layout, List<Category> cateogories){
        this.context = context;
        this.layout = layout;
        this.cateogories = cateogories;

    }

    public class ViewHolderShowListCategory{
        ImageView imgIcon,imgIsChoose;
        TextView txtName,txtIsNew;
        LinearLayout lnOneCategory;
    }

    @Override
    public int getCount() {
        return cateogories.size();
    }

    @Override
    public Object getItem(int position) {
        return cateogories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cateogories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolderShowListCategory();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            viewHolder.imgIsChoose = (ImageView) view.findViewById(R.id.imgIsChoose);
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
            viewHolder.txtIsNew = (TextView) view.findViewById(R.id.txtIsNew);
            viewHolder.lnOneCategory = (LinearLayout) view.findViewById(R.id.lnOneCategory);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderShowListCategory) view.getTag();
        }
        Category category = cateogories.get(position);
        String img = category.getImg().toString();
        if(img == null || img.equals("")){
            viewHolder.imgIcon.setImageResource(R.drawable.iconfoody);
        }else{
            Uri uri = Uri.parse(img);
            viewHolder.imgIcon.setImageURI(uri);
        }

        viewHolder.txtName.setText(category.getName());

        if(selectedPos == position){
            viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorFoody));
            viewHolder.imgIsChoose.setVisibility(View.VISIBLE);
        }else{
            viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorBlack));
            viewHolder.imgIsChoose.setVisibility(View.INVISIBLE);
        }

        return view;
    }



}
