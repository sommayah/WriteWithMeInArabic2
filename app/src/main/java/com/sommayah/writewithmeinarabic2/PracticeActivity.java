package com.sommayah.writewithmeinarabic2;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.IOException;

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
    private final int NUMLETTERS = 28;
    private boolean blank_page = false;

    private final String LETTERPOSITION = "letter_position";
    private String[] letterArray;
    private static MediaPlayer letterMediaPlayer;
    private final String TAG = PracticeActivity.class.getSimpleName();
    private int letter_position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        initPaint();
        letter_position = getIntent().getIntExtra(LETTERPOSITION, 0);
        if(letter_position < NUMLETTERS) {
            letterArray = AlphabetActivity.workSheetsArray.get(letter_position);
            numberOfWords = letterArray.length - 1;
            syncWithIndex();
        }else{
            blank_page = true;
            setUpBlankPage();
        }


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

    private void setUpBlankPage(){
        Resources bgres = getResources();
        int bgresourceId;
        bgresourceId = bgres.getIdentifier("blank", "drawable"
                , getPackageName());
        mLayout.setBackgroundResource(bgresourceId);
        mPreviousButton.setVisibility(View.INVISIBLE);
        mNextButton.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        letterMediaPlayer = new MediaPlayer();
        playAudio();
        if(blank_page == true){
            setUpBlankPage();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        //currPaint.setBackgroundColor(Color.TRANSPARENT);
        currPaint.setSelected(!currPaint.isSelected());
        //added check null to prevent crash that happens if th
        if(letterMediaPlayer != null)
            letterMediaPlayer.release();
        letterMediaPlayer = null;
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
        stopAndResetSounds();

    }

    public void onPreviousClicked(View view){
        if(currentWordIndex > 0){
            currentWordIndex--;
            syncWithIndex();
        }
        setHiddenButtonSettings();
        drawView.startNew();
        stopAndResetSounds();

    }

    public void onPlaySoundClicked(View view){
        playAudio();
    }

    public void setHiddenButtonSettings(){
        mPreviousButton.setVisibility((currentWordIndex == 0)? View.INVISIBLE: View.VISIBLE);
        mNextButton.setVisibility((currentWordIndex == numberOfWords)? View.INVISIBLE: View.VISIBLE);
    }

    private void stopAndResetSounds() {
        if(letterMediaPlayer != null){
            letterMediaPlayer.reset();
        }
    }

    private void playAudio() {
        if(letter_position < NUMLETTERS) {
            try {
                prepareSound(AlphabetActivity.lettersFilesArray[letter_position], letterMediaPlayer);
                letterMediaPlayer.start();

            } catch (Exception e) {
                Log.e(TAG, "error: " + e.getMessage(), e);
            }
        }

    }

    private void prepareSound(String soundFile, MediaPlayer mediaPlayer)
            throws IOException {
        mediaPlayer.reset();
        mediaPlayer.setDataSource(this, Uri.parse(
                "android.resource://" + getPackageName()
                        + "/" + "raw" + "/" + soundFile));
        mediaPlayer.prepare();
    }

}
