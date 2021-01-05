package emad.foody.Fragments.Home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import emad.foody.Adapters.AllCategoriesAdapter;
import emad.foody.Adapters.HomePagerAdapter;
import emad.foody.Model.Category;

public class HomeController {

    FirebaseAuth mAuth;
    DatabaseReference imgsReference;
    DatabaseReference categoriesReference;
    private static final String TAG = "HomeController";

    public HomeController(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void getPagerImages(final HomePagerAdapter adapter, final ArrayList<String> images){
        images.clear();
        imgsReference = FirebaseDatabase.getInstance().getReference().child("pagerImages");
        imgsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    images.add(snapshot.child("link").getValue().toString());
                    Log.d(TAG, "onDataChange: " +snapshot.child("link").getValue());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getAllCategories(final AllCategoriesAdapter adapter, final ArrayList<Category> categories){
        categories.clear();
        final Category[] category = {null};
        categoriesReference = FirebaseDatabase.getInstance().getReference().child("categories");
        categoriesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: " + snapshot.getValue().toString());
                      categories.add(snapshot.getValue(Category.class));
                }
                Log.d(TAG, "onDataChange: " + categories.size());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}