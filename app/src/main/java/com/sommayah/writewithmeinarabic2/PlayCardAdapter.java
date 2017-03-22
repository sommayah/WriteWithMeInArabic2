package com.sommayah.writewithmeinarabic2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return gridView;
    }

}
