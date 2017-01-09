package com.sommayah.writewithmeinarabic2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sommayahsoliman on 1/3/17.
 */

public class AlphabetAdapter extends BaseAdapter {
    private Context context;
    private final String[] alphabetValues;
    private final String LETTER = "letter";
    private final String LETTERPOSITION = "letter_position";

    public AlphabetAdapter(Context context, String[] alphabetValues) {
        this.context = context;
        this.alphabetValues = alphabetValues;
    }


    @Override
    public int getCount() {
        return alphabetValues.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
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
            gridView = inflater.inflate(R.layout.alphabet_card_item, null);

        } else {
            gridView = (View) convertView;

        }
        // set value into textview
        TextView textView = (TextView) gridView
                .findViewById(R.id.letter);
        textView.setText(alphabetValues[position]);
        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + alphabetValues[position],
                        Toast.LENGTH_SHORT).show();
                Intent practice = new Intent(context, PracticeActivity.class);
                practice.putExtra(LETTER, alphabetValues[position]);
                practice.putExtra(LETTERPOSITION, position);
                context.startActivity(practice);

            }
        });
        return gridView;
    }
}
