package emad.foody.Fragments.Login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import emad.foody.AuthActivity;
import emad.foody.Fragments.Register.RegisterFragment;
import emad.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    Button btnLogin;
    EditText edEmailLogin,edPasswordLogin;
    TextView forgotPassword;
    LinearLayout goToRegister;

    LoginController loginController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        loginController = new LoginController();
        initViews(view);
        handleViews();
        return view;
    }

    private void initViews(View view) {
        btnLogin = view.findViewById(R.id.btnLogin);
        edEmailLogin = view.findViewById(R.id.edEmailLogin);
        edPasswordLogin = view.findViewById(R.id.edPasswordLogin);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        goToRegister = view.findViewById(R.id.goToRegister);
    }

    private void handleViews(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.login(getActivity(), edEmailLogin.getText().toString(), edPasswordLogin.getText().toString());
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthActivity) getActivity()).loadFragment(new RegisterFragment());
            }
        });
    }
}