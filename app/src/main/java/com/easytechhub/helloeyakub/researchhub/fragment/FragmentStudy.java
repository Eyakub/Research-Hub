package com.easytechhub.helloeyakub.researchhub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easytechhub.helloeyakub.researchhub.R;

/**
 * Created by Eyakub on 11/5/2017.
 */

public class FragmentStudy extends Fragment {

    public FragmentStudy() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_study, container, false);
    }
}
