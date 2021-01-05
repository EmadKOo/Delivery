package emad.foody.Fragments.ViewCategory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import emad.foody.Adapters.DisplayAllCategoryAdapter;
import emad.foody.DisplayItemActivity;
import emad.foody.Model.Item;
import emad.foody.R;

public class ViewCategoryController {
    Context context;
    Item item  = new Item();
    DatabaseReference databaseReference;
    DatabaseReference favReference;
    String currentItemReaction;
    private static final String TAG = "ViewCategoryController";

    public ViewCategoryController(Context context){
        this.context = context;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
    }
    public void getAllItemsOfCategory(String category, final DisplayAllCategoryAdapter allCategoriesAdapter, final ArrayList<Item> items){
        items.clear();
        databaseReference.child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: " + snapshot.getValue());
                    item =snapshot.getValue(Item.class);
                    item.setItemID(snapshot.getKey());
                    items.add(item);
                }
                allCategoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addToCart(){
        Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
    }


    public void likeItem(String reaction, final ImageView itemReaction, Item currentItem) {
        currentItemReaction = reaction;
        Log.d(TAG, "likeItem: " + currentItemReaction);
        if (currentItemReaction.equals("0")){

            favReference = FirebaseDatabase.getInstance().getReference().child("fav").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(currentItem.getItemID());
            favReference.setValue(currentItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        favReference.child("reactionBefore").setValue("1");
                        itemReaction.setImageResource(R.drawable.like);
                        currentItemReaction = "1";
                        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        itemReaction.setImageResource(R.drawable.dislike);
                        favReference.removeValue();
                        currentItemReaction = "0";
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            favReference = FirebaseDatabase.getInstance().getReference().child("fav").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(item.getItemID());
            favReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        itemReaction.setImageResource(R.drawable.dislike);
                        Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                    }else {
                        itemReaction.setImageResource(R.drawable.like);
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}