package emad.foody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import emad.foody.Model.Item;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        String category = "Women";
//        String categoryID = "12";
//        Item item= new Item("0",category, categoryID,category +" 0", "http://i.imgur.com/DvpvklR.png", "2100$" ,"This is Description" ,"20 Aug 2019 ","2500$ ","25 ","6 ","Shop Name 0","0");
//
//        for (int x = 0; x < 30; x++) {
//            item.setItemID(x+"");
//            item.setName(category +" " + x);
//            item.setDescription("This is Description " + x);
//            item.setShopName("Shop Name " + x);
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("items").child(category);
//            databaseReference.push().setValue(item);
//        }
    }
}
