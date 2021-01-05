package emad.foody.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import emad.foody.Fragments.ViewCategory.ViewCategoryFragment;
import emad.foody.HomeActivity;
import emad.foody.Model.Category;
import emad.foody.R;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.MyViewHolder> {

    ArrayList<Category> categories;
    Context context;
    Bundle bundle;
    Fragment fragment;

    public AllCategoriesAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.category_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int i) {
        holder.categoryItemTitle.setText(categories.get(i).getCategoryName());
        Picasso.get().load(categories.get(i).getCategoryImage()).into(holder.categoryItemImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putSerializable("category", categories.get(i));

                fragment = new ViewCategoryFragment();
                fragment.setArguments(bundle);

                try {
                    ((HomeActivity) context).loadFragment(fragment);
                }catch (Exception e){
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryItemTitle;
        ImageView categoryItemImage;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            categoryItemImage = itemView.findViewById(R.id.categoryItemImage);
            categoryItemTitle = itemView.findViewById(R.id.categoryItemTitle);
        }
    }

}