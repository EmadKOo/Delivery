package emad.foody.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.comix.rounded.RoundedCornerImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import emad.foody.DisplayItemActivity;
import emad.foody.Fragments.ViewCategory.ViewCategoryController;
import emad.foody.Model.Item;
import emad.foody.R;

public class DisplayAllCategoryAdapter extends RecyclerView.Adapter<DisplayAllCategoryAdapter.MyViewHolder>{

    ArrayList<Item> items;
    Context context;
    Intent intent;
    ViewCategoryController viewCategoryController;
    DatabaseReference favReference;
    String reaction;
    public DisplayAllCategoryAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
        viewCategoryController = new ViewCategoryController(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.all_category_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        holder.itemTitle.setText(items.get(i).getName());
        holder.itemPrice.setText(items.get(i).getPrice());
        holder.addToCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewCategoryController.addToCart();
            }
        });

        if (items.get(i).getDiscount().equals("0"))
            holder.itemDiscount.setVisibility(View.GONE);
        else{
            holder.itemDiscount.setText(items.get(i).getDiscount());
            holder.itemDiscount.setPaintFlags(holder.itemDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        Picasso.get().load(items.get(i).getImages()).into(holder.itemImage);

        holder.likeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reaction ==null)
                    reaction = "0";
                setUpReaction(holder.likeItem, items.get(i).getItemID());
                Log.d("TAG", "onClick: " + reaction);
                viewCategoryController.likeItem(reaction, holder.likeItem, items.get(i));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, DisplayItemActivity.class);
                intent.putExtra("item", items.get(i));
                context.startActivity(intent);
            }
        });

        setUpReaction(holder.likeItem, items.get(i).getItemID());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemPrice;
        TextView itemDiscount;
        Button addToCartItem;
        ImageView likeItem;
        RoundedCornerImageView itemImage;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDiscount = itemView.findViewById(R.id.itemDiscount);
            addToCartItem = itemView.findViewById(R.id.addToCartItem);
            likeItem = itemView.findViewById(R.id.likeItem);
        }
    }
    private void setUpReaction(final ImageView itemReaction, final String itemID){
        favReference = FirebaseDatabase.getInstance().getReference().child("fav").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(itemID);
        favReference.child("reactionBefore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reaction = dataSnapshot.getValue(String.class);
                Log.d("AG", "onDataChange: reaction " + reaction);
                Log.d("AG", "onDataChange: reaction " + itemID);
                if (reaction!=null){
                    if (reaction.equals("0")){
                        itemReaction.setImageResource(R.drawable.dislike);
                    }else {
                        itemReaction.setImageResource(R.drawable.like);
                    }
                }else reaction = "0";
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
