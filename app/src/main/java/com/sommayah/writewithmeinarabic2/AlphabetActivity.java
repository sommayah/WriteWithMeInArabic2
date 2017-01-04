package com.sommayah.writewithmeinarabic2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import static com.sommayah.writewithmeinarabic2.R.id.gridView;

public class AlphabetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        GridView gridview = (GridView) findViewById(gridView);

        final String[] numbers = new String[] {
                "أ", "ب", "ت", "ث", "ج",
                "ح", "خ", "د", "ذ", "ر",
                "ز", "س", "ش", "ص", "ض",
                "ط", "ظ", "ع", "غ", "ف",
                "ق", "ك", "ل", "م", "ن", "هـ"
                , "و", "ي", "B","E"};

        AlphabetAdapter adapter = new AlphabetAdapter(this,
                 numbers);

        gridview.setAdapter(adapter);
    }

}
