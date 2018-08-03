package com.easytechhub.helloeyakub.researchhub.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.BbaDetailsActivity;
import com.easytechhub.helloeyakub.researchhub.R;
import com.easytechhub.helloeyakub.researchhub.model.BbaData;

import static com.easytechhub.helloeyakub.researchhub.model.BbaData.bbaStringID;

/**
 * Created by Eyakub on 11/28/2017.
 */

public class BbaAdapter extends RecyclerView.Adapter {

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item, parent, false);

        return new BbaHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BbaHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return BbaData.title.length;
    }

    private class BbaHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private ImageView mCover;

        public BbaHolder(final View itemView){
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.cvTitleScience);
            mCover = (ImageView) itemView.findViewById(R.id.scImageScience);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = getAdapterPosition();
                    /*Toast.makeText(
                            itemView.getContext(),
                            String.valueOf(mTitle.getText()),
                            Toast.LENGTH_SHORT).show();*/
                    Intent intent = new Intent(itemView.getContext(), BbaDetailsActivity.class);
                    String selectValue = String.valueOf(itemView.getResources().getString(bbaStringID[n]));
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
            mTitle.setText(BbaData.title[position]);
            mCover.setImageResource(BbaData.picturePath[position]);

        }

        public void onClick(View view){

        }
    }

}
