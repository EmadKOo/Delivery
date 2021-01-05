package emad.foody;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

import emad.foody.Model.Item;
import emad.foody.Tools.MuliRegular;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayItemActivity extends AppCompatActivity {

    ImageView   itemImage;
    MuliRegular shopName;
    MuliRegular itemCategory;
    MuliRegular itemName;
    MuliRegular itemPrice;
    ImageView   itemReaction;
    MuliRegular itemDescription;
    MuliRegular datePosted;
    Item item;
    String reaction;
    DatabaseReference favReference;
    DatabaseReference itemsReference;
    private static final String TAG = "DisplayItemActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        itemsReference = FirebaseDatabase.getInstance().getReference();
        item = (Item) getIntent().getSerializableExtra("item");
        Log.d(TAG, "onCreate: " + item.getItemID());
        initViews();
        handleViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpReaction();
    }

    private void initViews(){
        itemImage = findViewById(R.id.itemImage);
        shopName = findViewById(R.id.shopName);
        itemCategory = findViewById(R.id.itemCategory);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        itemReaction = findViewById(R.id.itemReaction);
        itemDescription = findViewById(R.id.itemDescription);
        datePosted = findViewById(R.id.datePosted);
    }

    private void handleViews(){
        Picasso.get().load(item.getImages()).into(itemImage);
        shopName.setText(item.getShopName());
        itemCategory.setText(item.getCategory());
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
        itemDescription.setText(item.getDescription());
        datePosted.setText(item.getDatePosted());
    }

    public void finish(View view) {
        finish();
    }

    public void reaction(View view) {
        if (reaction.equals("0")){
            favReference = FirebaseDatabase.getInstance().getReference().child("fav").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(item.getItemID());
            favReference.setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        favReference.child("reactionBefore").setValue("1");
                        itemReaction.setImageResource(R.drawable.like);
                        reaction = "1";
                        Toast.makeText(DisplayItemActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        reaction = "0";
                        itemReaction.setImageResource(R.drawable.dislike);
                        favReference.removeValue();
                        Toast.makeText(DisplayItemActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DisplayItemActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();
                    }else {
                        itemReaction.setImageResource(R.drawable.like);
                        Toast.makeText(DisplayItemActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void addToCart(View view) {

    }

    private void setUpReaction(){
        favReference = FirebaseDatabase.getInstance().getReference().child("fav").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(item.getItemID());
        favReference.child("reactionBefore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reaction = dataSnapshot.getValue(String.class);
                if (reaction!=null){
                    if (reaction.equals("0")){
                        itemReaction.setImageResource(R.drawable.dislike);
                    }else {
                        itemReaction.setImageResource(R.drawable.like);
                    }
                }else reaction ="0";
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}