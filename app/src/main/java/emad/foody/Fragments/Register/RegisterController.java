package emad.foody.Fragments.Register;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.github.loadingview.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hussain_chachuliya.gifdialog.GifDialog;

import emad.foody.AuthActivity;
import emad.foody.HomeActivity;
import emad.foody.Model.User;

public class RegisterController {

    FirebaseAuth mAuth;
    DatabaseReference usersRef;
    ProgressDialog progressDialog;
    boolean result = false;

    public RegisterController() {
        mAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public boolean registerUser(final Context context, final String email, final String password, final String name){
       showDialog(context);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    saveUserinDB(context, email, name, password);
                }else {
                    result = false;
                    dismissDialog();
                }
            }
        });
        return result;
    }

    private void saveUserinDB(final Context context, String email, String name, String password){
        usersRef.child(mAuth.getCurrentUser().getUid()).setValue(new User(name, email, password)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    result = true;
                    context.startActivity(new Intent(context, HomeActivity.class));
                    ((AuthActivity) context).finish();
                    dismissDialog();
                }else {
                    dismissDialog();
                    result = false;
                }
            }
        });
    }
    public void showDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("please wait ....");
        progressDialog.show();
    }

    public void dismissDialog() {
        progressDialog.hide();
    }

}
