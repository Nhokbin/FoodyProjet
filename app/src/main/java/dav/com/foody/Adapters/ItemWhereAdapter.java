package dav.com.foody.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import dav.com.foody.Objects.Item;
import dav.com.foody.R;

/**
 * Created by binhb on 13/03/2017.
 */

public class ItemWhereAdapter extends RecyclerView.Adapter<ItemWhereAdapter.ViewHolderShowListItem> {

    Context context;
    int layout;
    List<Item> items;


    public ItemWhereAdapter(Context context, int layout, List<Item> items){
        this.context = context;
        this.layout = layout;
        this.items = items;

    }

    public class ViewHolderShowListItem extends RecyclerView.ViewHolder{
        TextView txtAvgRating, txtName, txtAddress,txtState;
        ImageView imgItem;
        VideoView videoItem;
        RecyclerView recyclerImg, recyclerReview;
        Button btnComment, btnPicture, btnOderNow, btnCountComment, btnCountPicture;
        ProgressBar progressBar;

        public ViewHolderShowListItem(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_one_item);
            txtAvgRating = (TextView) itemView.findViewById(R.id.txt_avg_rating);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address_one_item);
            txtState = (TextView) itemView.findViewById(R.id.txt_state_one_item);
            btnComment = (Button) itemView.findViewById(R.id.btn_count_comment);
            btnPicture = (Button) itemView.findViewById(R.id.btn_count_picture);
            btnOderNow = (Button) itemView.findViewById(R.id.btn_oder_now);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
            btnCountComment = (Button) itemView.findViewById(R.id.btn_count_comment);
            btnCountPicture = (Button) itemView.findViewById(R.id.btn_count_picture);
            imgItem = (ImageView) itemView.findViewById(R.id.img_one_item);
            videoItem= (VideoView) itemView.findViewById(R.id.video_one_item);
            recyclerReview = (RecyclerView) itemView.findViewById(R.id.recycler_list_reviews);
            recyclerImg = (RecyclerView) itemView.findViewById(R.id.recycler_img_item);
        }
    }

    @Override
    public ViewHolderShowListItem onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,parent,false);
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
        holder.txtName.setText(item.getName().toString());
        holder.txtAvgRating.setText(String.format("%.1f",item.getAvgRating())+"");
        holder.txtAddress.setText(item.getAddress().toString());
        holder.btnCountComment.setText(String.valueOf(item.getTotalReviews()));
        holder.btnCountPicture.setText(String.valueOf(item.getTotalPictures()));

        ReviewAdapter reviewAdapter = new ReviewAdapter(context,R.layout.custom_one_row_reivew,item.getReviews());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        holder.recyclerReview.setLayoutManager(layoutManager);
        holder.recyclerReview.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();
    }


    public void clearData(){
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
