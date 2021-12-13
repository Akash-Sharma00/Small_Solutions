package com.example.smallsolutions;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    EditText username, password;
    TextView forgetPassword;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        animation(root);
        return root;
    }

//    Function to create animation for login page
    public void animation(View root){
//        Creating Hooks
        username = root.findViewById(R.id.UserName_login);
        password = root.findViewById(R.id.password_login);
        forgetPassword = root.findViewById(R.id.forget_password);
        login = root.findViewById(R.id.login_button);


//        Setting initial opacity for all elements
        username.setAlpha(0);
        password.setAlpha(0);
        forgetPassword.setAlpha(0);
        login.setAlpha(0);

//        Setting initial translation
        username.setTranslationX(200);
        password.setTranslationX(200);
        forgetPassword.setTranslationX(200);
        login.setTranslationX(200);

//        Creating animation
        username.animate().translationX(0).alpha(1).setDuration(800);
        password.animate().translationX(0).alpha(1).setDuration(950);
        forgetPassword.animate().translationX(0).alpha(1).setDuration(1100);
        login.animate().translationX(0).alpha(1).setDuration(1250);
    }
}