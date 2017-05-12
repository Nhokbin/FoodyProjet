package dav.com.foody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dav.com.foody.Objects.Type;
import dav.com.foody.R;

/**
 * Created by binhb on 10/03/2017.
 */

public class TypeAdapter extends BaseAdapter{

    Context context;
    int layout;
    List<Type> types;
    ViewHolderShowListType holder;

    private int selectdPos = -1;

    public int getSelectdPos() {
        return selectdPos;
    }

    public void setSelectdPos(int selectdPos) {
        this.selectdPos = selectdPos;
        notifyDataSetChanged();
    }

    private class ViewHolderShowListType{
        ImageView imgType,imgIsChoose;
        TextView txtNameType;
    }

    public TypeAdapter(Context context, int layout, List<Type> types){
        this.context = context;
        this.layout = layout;
        this.types = types;
    }

    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public Object getItem(int position) {
        return types.get(position);
    }

    @Override
    public long getItemId(int position) {
        return types.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            holder = new ViewHolderShowListType();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            holder.imgType = (ImageView) view.findViewById(R.id.img_icon_type);
            holder.imgIsChoose = (ImageView) view.findViewById(R.id.img_is_choose_type);
            holder.txtNameType = (TextView) view.findViewById(R.id.txt_name_one_type);

            view.setTag(holder);
        }else{
            holder = (ViewHolderShowListType) view.getTag();
        }
        Type type = types.get(position);


        Picasso.with(context).load(type.getImg()).fit().centerInside().into(holder.imgType);

        holder.txtNameType.setText(type.getName());

        if(selectdPos == position){
            holder.txtNameType.setTextColor(context.getResources().getColor(R.color.colorFoody));
            holder.imgIsChoose.setVisibility(View.VISIBLE);
        }else{
            holder.txtNameType.setTextColor(context.getResources().getColor(R.color.colorBlack));
            holder.imgIsChoose.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
