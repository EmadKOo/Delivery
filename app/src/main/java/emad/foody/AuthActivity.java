package emad.foody;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import emad.foody.Fragments.Login.LoginFragment;

public class AuthActivity extends AppCompatActivity {

    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loadFragment(new LoginFragment());

    }

        public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.authFrame, fragment)
                    .commit();
            currentFragment = fragment;
            return true;
        }
        return false;
    }
}
