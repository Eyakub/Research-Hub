package com.easytechhub.helloeyakub.researchhub.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.R;
import com.easytechhub.helloeyakub.researchhub.ScienceDetailsActivity;
import com.easytechhub.helloeyakub.researchhub.model.ScienceData;

import static com.easytechhub.helloeyakub.researchhub.model.ScienceData.scienceStringID;

/**
 * Created by Eyakub on 11/26/2017.
 */

public class ScienceAdapter extends RecyclerView.Adapter {


    CoordinatorLayout coordinatorLayout;
    private Context context;


    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };

    @Override
    public ScienceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item, parent, false);

        return new ScienceHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ScienceHolder) holder).bindView(position);
    }


    @Override
    public int getItemCount() {
        return ScienceData.title.length;
    }


    public static class ScienceHolder extends RecyclerView.ViewHolder{

        public Context context;

        private TextView mTitle;
        private ImageView mCover;

        public TextView getTitle(){
            return mTitle;
        }


        public ScienceHolder(final View itemView /*final Context context*/){
            super(itemView);
            //this.context = context;
            mTitle = (TextView) itemView.findViewById(R.id.cvTitleScience);
            mCover = (ImageView) itemView.findViewById(R.id.scImageScience);
            itemView.setOnClickListener(new View.OnClickListener() {
                //public Context context;

                @Override
                public void onClick(View v) {
                    int n = getAdapterPosition();
                    /*Toast.makeText(
                            itemView.getContext(),
                            String.valueOf(mTitle.getText()),
                            Toast.LENGTH_SHORT).show();*/
                    Intent intent = new Intent(itemView.getContext(), ScienceDetailsActivity.class);
                    String selectValue = String.valueOf(itemView.getResources().getString(scienceStringID[n]));
                    String appTitle = String.valueOf(mTitle.getText());
                    //intent.putExtra("value", selectValue);
                    //intent.putExtra("appTitle",appTitle);
                    Bundle b = new Bundle();
                    b.putString("value", selectValue);
                    b.putString("appTitle", appTitle);
                    intent.putExtras(b);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public void bindView(int position){
            mTitle.setText(ScienceData.title[position]);
            mCover.setImageResource(ScienceData.picturePath[position]);

        }

    }
}
