package com.easytechhub.helloeyakub.researchhub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.fragment.FragmentHome;
import com.easytechhub.helloeyakub.researchhub.fragment.FragmentProfile;
import com.easytechhub.helloeyakub.researchhub.fragment.FragmentStudy;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private TextView mTextMessage, appTitle;
    protected Toolbar toolbar;

    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, 1);
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Eyakub");
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//
//        FirebaseCrash.log("Activity created");
//        FirebaseCrash.logcat(Log.ERROR, "hi", "NPE caught");
        //FirebaseCrash.report(ex);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//using this app name is not with desire title

        appTitle = (TextView) findViewById(R.id.toolbar_title);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framLayout, new FragmentHome()).commit();

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        appTitle.setText("Home");
                        fragmentTransaction.replace(R.id.framLayout, new FragmentHome()).commit();
                        /*FragmentHome fragmentHome = new FragmentHome();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.framLayout, fragmentHome, "FragmentHome");
                        fragmentTransaction.commit(); //remeber to comit fragment*/
                        return true;

                    case R.id.navigation_study:
                        appTitle.setText("Study");
                        fragmentTransaction.replace(R.id.framLayout, new FragmentStudy()).commit();
                        /*FragmentStudy fragmentStudy = new FragmentStudy();
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.framLayout, fragmentStudy, "FragmentHome");
                        fragmentTransaction1.commit(); //remeber to comit fragment*/
                        return true;

                    case R.id.navigation_profile:
                        appTitle.setText("Profile");
                        fragmentTransaction.replace(R.id.framLayout, new FragmentProfile()).commit();
                        /*FragmentProfile fragmentProfile = new FragmentProfile();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.framLayout, fragmentProfile, "FragmentHome");
                        fragmentTransaction2.commit(); //remeber to comit fragment*/
                        return true;
                }
                return false;
            }
        });

    }

}
