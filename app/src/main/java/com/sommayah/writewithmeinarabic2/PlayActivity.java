package com.sommayah.writewithmeinarabic2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity {

    @BindView(R.id.playGridView)
    GridView playGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);

        ArrayList<String> cardsDeck =  new ArrayList<>();
        cardsDeck.add("beecard");
        cardsDeck.add("lion");
        cardsDeck.add("lion");
        cardsDeck.add("beecard");
        cardsDeck.add("rabbit");
        cardsDeck.add("rabbit");
        cardsDeck.add("print48");
        cardsDeck.add("print48");

        PlayCardAdapter cardAdapter = new PlayCardAdapter(this, cardsDeck);
        playGridView.setAdapter(cardAdapter);

    }

}
