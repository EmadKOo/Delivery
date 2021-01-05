package emad.foody.Fragments.Register;


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
import emad.foody.Fragments.Login.LoginFragment;
import emad.foody.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText edNameRegister, edEmailRegister, edPasswordRegister;
    Button btnRegister;
    LinearLayout goToLogin;

    RegisterController registerController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        registerController = new RegisterController();
        initViews(view);
        handleViews();
        return view;
    }

    private void initViews(View view) {
        edNameRegister = view.findViewById(R.id.edNameRegister);
        edEmailRegister = view.findViewById(R.id.edEmailRegister);
        edPasswordRegister = view.findViewById(R.id.edPasswordRegister);
        btnRegister = view.findViewById(R.id.btnRegister);
        goToLogin = view.findViewById(R.id.goToLogin);
    }

    private void handleViews(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerController.registerUser(getActivity(), edEmailRegister.getText().toString(), edPasswordRegister.getText().toString(), edNameRegister.getText().toString());
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthActivity) getActivity()).loadFragment(new LoginFragment());
            }
        });
    }

}
