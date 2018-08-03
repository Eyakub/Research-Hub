package com.easytechhub.helloeyakub.researchhub.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easytechhub.helloeyakub.researchhub.R;
import com.easytechhub.helloeyakub.researchhub.adapter.ViewPagerAdapter;
import com.easytechhub.helloeyakub.researchhub.recycler.BbaAdapter;
import com.easytechhub.helloeyakub.researchhub.recycler.ScienceAdapter;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Eyakub on 11/5/2017.
 */

public class FragmentHome extends Fragment {


    private static int currentPage = 0;
    private static int numPage = 0;
    private static final int VERTICAL_ITEM_SPACE = 48;
    View view;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    int[] images;

    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.LayoutManager mLayoutManager1;

    public FragmentHome() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //this.view = view;
        //context = container.getContext();
        view = inflater.inflate(R.layout.activity_home, container, false);

        //science recyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        ScienceAdapter scienceAdapter = new ScienceAdapter();
        recyclerView.setAdapter(scienceAdapter);
        mLayoutManager =
                new LinearLayoutManager(
                        this.getActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false);
        recyclerView.setLayoutManager(mLayoutManager);

        //implementing single item click for science domain
        /*recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(
                        getContext(),
                        recyclerView,
                        new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        //BBA recyclerView
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view_bba);
        recyclerView1.setHasFixedSize(true);
        BbaAdapter bbaAdapter = new BbaAdapter();
        recyclerView1.setAdapter(bbaAdapter);
        mLayoutManager1 =
                new LinearLayoutManager(
                        this.getActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false);
        recyclerView1.setLayoutManager(mLayoutManager1);

        //image sliding resource
        images = new int[]{
                R.drawable.howtoresearch,
                R.drawable.selectingdomain,
                R.drawable.researchascareer
        };

        //viewPager code for image sliding
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(this.getActivity(), images);
        viewPager.setAdapter(pagerAdapter);


        circleIndicator();
        //slideTimer();
        return view;

    }

    /**
     * sliding image with circle Indicator
     */
    public void circleIndicator(){
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == viewPager.SCROLL_STATE_IDLE){
                    int pageCount = images.length;
                    if(currentPage == 0){
                        viewPager.setCurrentItem(pageCount-1, false);
                    }else if(currentPage == pageCount - 1){
                        viewPager.setCurrentItem(0, false);
                    }
                }
            }
        });
    }


    /**
     * images sliding with timer
     */
    public void slideTimer(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(currentPage == numPage){
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipe = new Timer();
        swipe.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1000, 1000);
    }

}
