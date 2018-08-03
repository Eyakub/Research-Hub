package com.easytechhub.helloeyakub.researchhub.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.ImageSlidingActivity;
import com.easytechhub.helloeyakub.researchhub.R;
import com.easytechhub.helloeyakub.researchhub.model.ImageSlidingDetails;

import java.util.ArrayList;

/**
 * Created by Eyakub on 11/3/2017.
 */

public class ViewPagerAdapter extends PagerAdapter{

    int[] image;
    //LayoutInflater inflater;
    Context fragmentHome;
    ArrayList<ImageSlidingDetails> arrayList = new ArrayList<>();
    ImageSlidingDetails img;
    TextView mTitleBar;


    public ViewPagerAdapter(Context fragmentHome, int[] image) {
        this.fragmentHome = fragmentHome;
        this.image = image;
    }


    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView slideImage;
        /*
        Activity.LAYOUT_INFLATER_SERVICE if we want to use it on fragment
        else we can use Context.LAYOUT_INFLATER_SERVICE
         */
        LayoutInflater inflater = (LayoutInflater) fragmentHome.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.view_pager_slide, container, false);


        //image click sliding
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 0){
                    Intent i = new Intent(fragmentHome, ImageSlidingActivity.class);
                    String selectValue = fragmentHome.getResources().getString(R.string.how_to_research);
                    String appTitle = String.valueOf(fragmentHome.getResources().getString(R.string.howToResearch));
                    Bundle b = new Bundle();
                    b.putString("value", selectValue);
                    b.putString("appTitle", appTitle);
                    i.putExtras(b);
                    fragmentHome.startActivity(i);
                }else if(position == 1){
                    Intent i = new Intent(fragmentHome, ImageSlidingActivity.class);
                    String selectValue = fragmentHome.getResources().getString(R.string.selecting_domain);
                    String appTitle = String.valueOf(fragmentHome.getResources().getString(R.string.selectingDomain));
                    Bundle b = new Bundle();
                    b.putString("value", selectValue);
                    b.putString("appTitle", appTitle);
                    i.putExtras(b);
                    fragmentHome.startActivity(i);
                }else if(position == 2){
                    Intent i = new Intent(fragmentHome, ImageSlidingActivity.class);
                    String selectValue = fragmentHome.getResources().getString(R.string.research_as_career);
                    String appTitle = String.valueOf(fragmentHome.getResources().getString(R.string.researchCareer));
                    Bundle b = new Bundle();
                    b.putString("value", selectValue);
                    b.putString("appTitle", appTitle);
                    i.putExtras(b);
                    fragmentHome.startActivity(i);
                }
            }
        });

        slideImage = (ImageView) itemView.findViewById(R.id.frontSlideImageView);
        slideImage.setImageResource(image[position]);

        //((ViewPager)container).addView(itemView);
        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //((ViewPager)container).removeView((RelativeLayout)object);
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
