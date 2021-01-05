package emad.foody.Fragments.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import emad.foody.AuthActivity;
import emad.foody.HomeActivity;

public class LoginController {
    FirebaseAuth mAuth;
    boolean result = false;
    private static final String TAG = "LoginController";

    public LoginController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean login(final Context context, String name, String password){
        mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    result = true;
                    context.startActivity(new Intent(context, HomeActivity.class));
                    ((AuthActivity) context).finish();
                }
                else
                    result = false;
            }
        });

        return result;
    }

}
