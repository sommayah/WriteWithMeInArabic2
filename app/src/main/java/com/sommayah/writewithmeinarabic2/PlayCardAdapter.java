package com.sommayah.writewithmeinarabic2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by sommayahsoliman on 1/19/17.
 */

public class PlayCardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CardView> cardValues;
    @Override
    public int getCount() {
        return cardValues.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setItem(int pos, int resId){
        cardValues.get(pos).setImageResource(resId);
    }

    public PlayCardAdapter(Context context, ArrayList<CardView> cards) {
        this.context = context;
        this.cardValues = cards;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            gridView = new View(context);
            gridView.setLayoutParams(new GridView.LayoutParams(85, 85));
            gridView.setPadding(8, 8, 8, 8);
            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.play_grid_item, null);

        } else {
            gridView = (View) convertView;

        }
        // set value into textview
        final CardView card = (CardView) gridView
                .findViewById(R.id.card);
        card.setImageResource(R.drawable.square);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rotation1 = (card.isFaceDown()? 0.0f:180f);
                float rotation = (card.isFaceDown()? 180f:0.0f);

                ObjectAnimator animation = ObjectAnimator.ofFloat(card, "rotationY", rotation1, rotation);  // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
                animation.setDuration(250); // HERE 500 IS THE DURATION OF THE ANIMATION, YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        card.flipCard();
                    }
                }, 150);
//                ValueAnimator mFlipAnimator = ValueAnimator.ofFloat(0f,180f);
//                mFlipAnimator.addUpdateListener(new FlipListener(card, card));
//                mFlipAnimator.start();

            }
        });

        return gridView;
    }

}
