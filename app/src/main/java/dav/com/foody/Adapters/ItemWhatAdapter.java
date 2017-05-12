package dav.com.foody.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import dav.com.foody.Objects.Item;
import dav.com.foody.Objects.User;
import dav.com.foody.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by binhb on 21/03/2017.
 */

public class ItemWhatAdapter extends RecyclerView.Adapter<ItemWhatAdapter.ViewHolderShowListItem> {

    Context context;
    int layout, width;
    List<Item> items;


    public ItemWhatAdapter(Context context, int layout, List<Item> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
    }

    public class ViewHolderShowListItem extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView txtName, txtType, txtAddress, txtNameReview, txtDate;
        ProgressBar progressBar;
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
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
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
    public void onBindViewHolder(final ViewHolderShowListItem holder, int position) {
        Item item = items.get(position);

        if(!item.getImg().equals("")){
            Picasso.with(context).load(item.getImg()).fit().centerInside().into(holder.imgItem, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }else{
            holder.imgItem.setImageDrawable(null);
        }
        holder.txtType.setText(item.getName());

        holder.txtName.setText(item.getResName());

        holder.txtAddress.setText(item.getAddress());
        holder.txtDate.setText(item.getCreateDate());
        try{
            User user = item.getUser();
            if(!user.getAvatar().equals("")){
                Picasso.with(context).load(item.getImg()).fit().centerInside().into(holder.imgAvatarReview);
            }else{
                holder.imgItem.setImageDrawable(null);
            }

            holder.txtNameReview.setText(user.getName());
        }catch (Exception ex){
            ex.printStackTrace();
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
