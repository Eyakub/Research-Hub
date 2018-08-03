package com.easytechhub.helloeyakub.researchhub.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.R;
import com.easytechhub.helloeyakub.researchhub.account.ActivityLogin;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Eyakub on 11/5/2017.
 */

public class FragmentProfile extends Fragment {

    Toolbar mToolbar;
    TextView setEmailTv;
    Button btnLogOut, sendMail;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    public FragmentProfile() {

    }


/*    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setEmailTv = rootView.findViewById(R.id.user_email);
        /*ActivityLogin activityLogin = new ActivityLogin();
        setEmailTv.setText(activityLogin.userEmailStore);*/
        /*if(firebaseAuth.getCurrentUser() != null){
            setEmailTv.setText(firebaseAuth.getCurrentUser().getEmail());
        }*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.activity_profile, container, false);

        setEmailTv = rootView.findViewById(R.id.user_email);
        ActivityLogin activityLogin = new ActivityLogin();
        setEmailTv.setText(activityLogin.userEmailStore);

        sendMail = rootView.findViewById(R.id.btn_sendMail);

        /*if(firebaseAuth.getCurrentUser() != null){
            setEmailTv.setText(firebaseAuth.getCurrentUser().getEmail());
        }*/

        btnLogOut = rootView.findViewById(R.id.user_logout);
        /*
        if we are using logout on fragment this method works
        (OTHERWISE) fireBaseAuth.getInstance.signOut() is fine with single line INTENT
         */
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(), ActivityLogin.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "eyakubsorkar@gmail.com", null
                ));
                //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                //intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Choose an Email client"));
            }
        });
        return rootView;

    }
}
