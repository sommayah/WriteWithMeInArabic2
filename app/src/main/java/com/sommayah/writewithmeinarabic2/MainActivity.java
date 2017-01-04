package com.sommayah.writewithmeinarabic2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPracticeClicked(View view){
        Intent alphabetIntent = new Intent(this, AlphabetActivity.class);
        startActivity(alphabetIntent);

    }

    public void onPlayClicked(View view){
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);
    }

}
