package dav.com.foody.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dav.com.foody.R;

/**
 * Created by binhb on 05/04/2017.
 */

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolderOptionItem>{

    Context context;
    int layout, width;

    String[] name = new String[]{
            "Gần tôi",
            "Coupon",
            "Đặt chỗ ưu đãi",
            "Đặt giao hàng",
            "E-card",
            "Bình luận",
            "Blogs",
            "Sự kiện",
            "Video"
    };

    int[] img = new int[]{
            R.drawable.icon356_nearby,
            R.drawable.icon356_e_coupon,
            R.drawable.icon356_tablenow,
            R.drawable.icon356_delivery,
            R.drawable.icon356_e_card,
            R.drawable.icon356_review,
            R.drawable.icon356_blogs,
            R.drawable.icon356_events,
            R.drawable.icon356_tv
    };

    public OptionAdapter(Context context, int layout) {
        this.context = context;
        this.layout = layout;
    }

    public class ViewHolderOptionItem extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtName;
        public ViewHolderOptionItem(View itemView) {
            super(itemView);
            itemView.setMinimumWidth(width/2);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_option);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_option);
        }
    }

    @Override
    public ViewHolderOptionItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,parent,false);
        width = parent.getMeasuredWidth();
        ViewHolderOptionItem viewHolderOptionItem = new ViewHolderOptionItem(view);

        return viewHolderOptionItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderOptionItem holder, int position) {
        Picasso.with(context).load(img[position]).fit().centerInside().into(holder.imgIcon);
        holder.txtName.setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }



}
