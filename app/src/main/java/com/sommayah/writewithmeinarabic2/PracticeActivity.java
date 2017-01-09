package com.sommayah.writewithmeinarabic2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PracticeActivity extends AppCompatActivity {
    @BindView(R.id.previous)
    ImageButton mPreviousButton;
    @BindView(R.id.next)
    ImageButton mNextButton;
    @BindView(R.id.background_layout)
    RelativeLayout mLayout;

    private DrawingView drawView;
    private float smallBrush;

    private ImageButton eraseBtn;
    private ImageButton currPaint;

    int currentWordIndex = 0;
    int numberOfWords = 0;

    private final String LETTERPOSITION = "letter_position";
    private String[] letterArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        initPaint();
        int letter_position = getIntent().getIntExtra(LETTERPOSITION, 0);
        letterArray = AlphabetActivity.workSheetsArray.get(letter_position);
        numberOfWords = letterArray.length - 1;
        syncWithIndex();


    }

    private void initPaint(){
        currPaint = (ImageButton)findViewById(R.id.blue);
        eraseBtn = (ImageButton)findViewById(R.id.erase);
        //currPaint.setBackgroundColor(Color.WHITE);
        currPaint.setSelected(true);
        drawView = (DrawingView)findViewById(R.id.drawing);
        drawView.setColor("#0000FF");
        drawView.setanyText(false); //no text to trace
        drawView.writeText("");
        smallBrush = getResources().getInteger(R.integer.small_size);
    }

    private void syncWithIndex() {
//        TextView letter = (TextView) findViewById(R.id.letterView);
//        if(Type == 1)
//            letter.setText(LETTERS[index]);
//        else
//            letter.setText(NUMBERSTEXT[index]);
//
//        TextView word = (TextView) findViewById(R.id.wordView);
//        if(Type == 1)
//
//            word.setText(WORDS[index]);
//        else
//            word.setText(NUMBERSWORDS[index]);
//        ImageView image = (ImageView) findViewById(R.id.imageView);
//        Resources res = getResources();
//
//        int resourceId;
//        if(Type == 1){
//            resourceId = res.getIdentifier(WORDSNAMES[index], "drawable",
//                    getPackageName());
//        }
//        else if(Type == 2){ //ss. still IN CASE OF TYPE 3 WE HAVE TO PUT IT transparent
//            resourceId = res.getIdentifier(NUMBERSNAMES[index], "drawable",
//                    getPackageName());
//        }
//        else{
//            resourceId = res.getIdentifier(NUMBERSNAMES[index], "drawable",
//                    getPackageName());
//        }
//
//        image.setImageResource(resourceId);
        setHiddenButtonSettings();
        Resources bgres = getResources();
        int bgresourceId;
        bgresourceId = bgres.getIdentifier(letterArray[currentWordIndex], "drawable"
                    , getPackageName());


//        View drawingView = (View) findViewById(R.id.drawing);
//        drawingView.setBackgroundResource(bgresourceId);
        mLayout.setBackgroundResource(bgresourceId);
    }


    public void onEraseClicked(View view){
        if (view != currPaint) {
            drawView.setErase(true);
            drawView.setBrushSize(smallBrush);
            //eraseBtn.setSelected(true);
            currPaint.setSelected(!currPaint.isSelected());
            currPaint=(ImageButton)view;
            currPaint.setSelected(!currPaint.isSelected());
        }
    }

    public void onDrawClicked(View view){
        drawView.setBrushSize(smallBrush);
        drawView.setLastBrushSize(smallBrush);
        drawView.setBrushSize(smallBrush);
        if(view!=currPaint /*|| drawView.getErase()*/){
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            currPaint.setSelected(!currPaint.isSelected()); //set last choice to "not selected"
            currPaint=(ImageButton)view;
            currPaint.setSelected(!currPaint.isSelected());
        }
        drawView.setErase(false);
        eraseBtn.setSelected(false);

    }

    public void onNewClicked(View view){
        drawView.startNew();
    }

    public void onNextClicked(View view){
        if(currentWordIndex < numberOfWords){
            currentWordIndex++;
            syncWithIndex();
        }
        setHiddenButtonSettings();
        drawView.startNew();

    }

    public void onPreviousClicked(View view){
        if(currentWordIndex > 0){
            currentWordIndex--;
            syncWithIndex();
        }
        setHiddenButtonSettings();
        drawView.startNew();

    }

    public void onPlaySoundClicked(View view){

    }

    public void setHiddenButtonSettings(){
        mPreviousButton.setVisibility((currentWordIndex == 0)? View.INVISIBLE: View.VISIBLE);
        mNextButton.setVisibility((currentWordIndex == numberOfWords)? View.INVISIBLE: View.VISIBLE);
    }

}
