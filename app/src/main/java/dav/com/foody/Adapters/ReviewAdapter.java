package dav.com.foody.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dav.com.foody.Objects.Review;
import dav.com.foody.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by binhb on 20/03/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolderShowListReview>{

    Context context;
    int layout;
    List<Review> reviews;

    public ReviewAdapter(Context context, int layout, List<Review> reviews){
        this.context = context;
        this.layout = layout;
        this.reviews = reviews;
    }

    public class ViewHolderShowListReview extends RecyclerView.ViewHolder {
        CircleImageView imgAvatar;
        TextView txtName, txtComment, txtRating;

        public ViewHolderShowListReview(View itemView) {
            super(itemView);

            imgAvatar = (CircleImageView) itemView.findViewById(R.id.img_avatar_one_review);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_one_review);
            txtRating = (TextView) itemView.findViewById(R.id.txt_rating_one_review);
            txtComment = (TextView) itemView.findViewById(R.id.txt_comment_one_review);

        }
    }

    @Override
    public ReviewAdapter.ViewHolderShowListReview onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,parent,false);
        ViewHolderShowListReview viewHolderShowListReview = new ViewHolderShowListReview(view);
        return viewHolderShowListReview;
    }
    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolderShowListReview holder, int position) {
        Review review = reviews.get(position);

        Picasso.with(context).load(review.getAvatar()).fit().centerInside().into(holder.imgAvatar);
        holder.txtName.setText(review.getName());
        holder.txtRating.setText(String.format("%.1f",review.getRating())+"");
        holder.txtComment.setText(review.getCommmentTrim());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }


}
