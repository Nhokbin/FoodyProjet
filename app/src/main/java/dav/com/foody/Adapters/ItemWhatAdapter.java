package dav.com.foody.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dav.com.foody.Objects.Item;
import dav.com.foody.Objects.Review;
import dav.com.foody.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static dav.com.foody.Database.CreateDatabase.TB_TYPE_FIRST_ID;

/**
 * Created by binhb on 21/03/2017.
 */

public class ItemWhatAdapter extends RecyclerView.Adapter<ItemWhatAdapter.ViewHolderShowListItem> {

    Context context;
    int layout, width;
    List<Item> items;

    private String[] type =
            new String[] {"Sang trọng","Buffer",
            "Nhà hàng","Ăn vặt/vỉ hè",
            "Ăn chay","Cafe/Dessert",
            "Quán ăn","Bar/Pub",
            "Quán nhậu","Beer club",
            "Tiệm bánh", "Tiệc tận nơi",
            "Shop Online", "Giao cơm văn phòng",
            "Khu ẩm thực"};

    public ItemWhatAdapter(Context context, int layout, List<Item> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }

    public class ViewHolderShowListItem extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView txtName, txtType, txtAddress, txtNameReview, txtDate;
        CircleImageView imgAvatarReview;

        public ViewHolderShowListItem(View itemView) {
            super(itemView);
            itemView.setMinimumHeight(width/2);
            imgItem = (ImageView) itemView.findViewById(R.id.img_what_one_item);
            imgAvatarReview = (CircleImageView) itemView.findViewById(R.id.img_avatar_what_one_item);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_what_one_item);
            txtType = (TextView) itemView.findViewById(R.id.txt_type_what_one_item);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address_what_one_item);
            txtNameReview = (TextView) itemView.findViewById(R.id.txt_name_what_one_review);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date_what_one_review);
        }
    }

    @Override
    public ViewHolderShowListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        width = parent.getMeasuredWidth();
        ViewHolderShowListItem viewHolderShowListItem = new ViewHolderShowListItem(view);

        return viewHolderShowListItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderShowListItem holder, int position) {
        Item item = items.get(position);

        if (!item.getImg().equals("")) {
            Log.d("KT!@#!@#", "drawable/fdi" + item.getImg());
            int imageResource = context.getResources().getIdentifier("fdi" + item.getImg(), "drawable", context.getPackageName());
            if (imageResource != 0) {
                Picasso.with(context).load(imageResource).fit().centerInside().into(holder.imgItem);
            } else {
                Picasso.with(context).load(R.drawable.fdi1).fit().centerInside().into(holder.imgItem);
            }

        } else {
            holder.imgItem.setImageDrawable(null);
        }

        holder.txtType.setText(type[item.getTypeId()- TB_TYPE_FIRST_ID]);

        holder.txtName.setText(item.getName());

        holder.txtAddress.setText(item.getAddress());

        if(item.getReviews().size()>0){
            Review review = item.getReviews().get(0);
            int imageResource = context.getResources().getIdentifier("ava" + review.getAvatar(), "drawable", context.getPackageName());
            if (imageResource != 0) {
                Picasso.with(context).load(imageResource).fit().centerInside().into(holder.imgAvatarReview);
            } else {
                Picasso.with(context).load(R.drawable.ava3).fit().centerInside().into(holder.imgAvatarReview);
            }
            holder.txtNameReview.setText(review.getName());
        }
    }

    public void clearData() {
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
