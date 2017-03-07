package com.sommayah.writewithmeinarabic2;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity {

    @BindView(R.id.playGridView)
    GridView playGridView;
    int capacity = 8;
    private int level = 2;
    private int kind;
    private int bgColor;
    private int firstTappedCard; //keep positon of first tapped card
    private int secondTappedCard; //keep postion of second tapped card
    private ImageView firstImage;
    private ImageView secondImage;
    private int height; //height of board
    private int width;  //widht of board
    private int toEndGameCounter; //to know when all cards are matched
    private ArrayList<CardView> cardsArray;
    private static final String[]IMAGESNAMES={
    "lion","rabbit","swing","pineapple",
    "duck","door","house","egg","girl",
    "apple","berry","date","crown","alligator",
    "dress","fox","garlic","snake","thawr",
    "bell","camel","carrot","cheese",
    "donkey","horse","milk","shoe",
    "bread","cucumber","ladybug","sheep",
    "bear","bucket","chicken","rooster",
    "corn","fly","tail","th2b",
    "feather","man","pomegranate","sand",
    "flower","giraffe","oil","olives",
    "bed","car","clock","fish","turtle",
    "candle","net","ribbon","sun","tree",
    "box","falcoon","picture","rock","shell",
    "frog","tooth","light","fog",
    "peackok","plane","table","bird","doctor",
    "envelope","fingernail","deer","back",
    "grapes","eye","necklace","container","birdie",
    "crow","leave","washer","ghazelle","forest",
    "butterfly","elephant","mouse","strawberry",
    "cat","monkey","moon","train","pencil","boat",
    "ball","book","cake","chair","dog",
    "lemon","meat","tongue","beard",
    "banana","salt","school","scisors",
    "bee","star","ant","tiger","eagle",
    "crescent","hoopoe","pyramid","telephone",
    "boy","paper","rose","pillow",
    "hand","dove","left","right"
    };
    private static final String[] ARABICNAMES = {
    "أَسَد","أرْنَب"," أُرْجُوحَة","أنَانَاس",
    "بَطَة","بَاب","بَيْت","بَيْضَة","بِنْت",
    "تُفَّاحَة","تُوت","تَمْر","تَاج","تِمسَاح",
    "ثَوْب","ثَعْلَب","ثَوْم","ثُعْبَان","ثَوْر",
    "جَرَس","جَمَل","جَزَر","جُبْن",
    "حِمَار","حِصَان","حَلِيب","حِذَاء",
    "خُبْز","خِيَار","خُنْفُسَاء","خَرُوف",
    "دُب","دَلْوْ","دَجَاجَة","دِيك",
    "ذُرَة","ذُبَابَة","ذَيْل","ذِئْب",
    "رِيشَة","رَجُل","رُمَّان","رِمَال",
    "زَهْرَة","زَرَافَة","زَيْت","زَيْتُون",
    "سَرِير","سَيَّارَة","سَاعَة","سَمَكَة","سُلْحُفَاة",
    "شَمْعَة","شَبَكَة","شَرِيط","شَمْس","شَجَرَة",
    "صُنْدُوق","صَقْر","صُورَة","صَخْرَة","صَدَف",
    "ضِفْدَع","ضِرْس","ضَوْء","ضَبَاب",
    "طَاووس","طَائِرَة","طَاوْلَة","طَائِرَ","طَبِيب",
    "ظَرْف","ظِفْر","ظَبْيّ","ظَهْر",
    "عِنَب","عَيْن","عُقْد","عُلْبَة","عُصْفُور",
    "غُرَاب","غُصْن","غَسَّالَة","غَزَال","غَابَة",
    "فَرَاشَة","فِيل","فَأر","فَرَاوْلَة",
    "قِطَّة","قِرْد","قَمَر","قِطَار","قَلَم","قَارَب",
    "كُرَة","كِتَاب","كَعْكَة","كُرْسِيّ","كَلْب",
    "لَيْمُون","لَحْم","لِسَان","لِحْيَة",
    "مَوْز","مِلْح","مَدْرَسَة","مِقَص",
    "نَحْلَة","نَجْمَة","نَمْلَة","نَمْر","نِسْر",
    "هِلَال","هُدْهُد","هَرَم","هَاتِف",
    "وَلَد","وَرَقَة","وَرْدَة","وِسَادَة",
    "يَد","يَمَامَة","يَسَار","يَمِين",
    };

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

//        PlayCardAdapter cardAdapter = new PlayCardAdapter(this, cardsDeck);
//        playGridView.setAdapter(cardAdapter);
        startNewGame();

    }

    public void createCardsWithCapacity(int capacity){
        Log.v("in create cards", "create cards array");
       //ss:add them later shuffleImagesArray();
        cardsArray = new ArrayList<>();
        int k=0;
        for (int i = 0; i< capacity/2; i++) {
            for (int j = 0; j<2; j++) {
                CardView card = new CardView(this);
                card.initialize(i,IMAGESNAMES[i]);
                cardsArray.add(card);
                k++;
                //   Log.v("card number", String.valueOf(k));
            }
        }
        //ss: add them later shuffleCardsArray();
        toEndGameCounter = 0;


    }

    public void setBoard(){
        final PlayCardAdapter cardAdapter = new PlayCardAdapter(this, cardsArray);
        playGridView.setAdapter(cardAdapter);
        playGridView.setNumColumns(width);


        playGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                if(firstTappedCard == -1){
                    firstTappedCard = position;
                    firstImage = (ImageView) v; //save first image to flip back


                }
                else if(firstTappedCard != position){ //different one, two different similar cards
                    if(cardsArray.get(firstTappedCard).getImage_num() == cardsArray.get(position).getImage_num()){
                        Log.d("correct answer c number",String.valueOf(cardsArray.get(firstTappedCard).getImage_num()));
                        toEndGameCounter++;
                        firstTappedCard = -1; //reset first card
                        if(toEndGameCounter == capacity/2){
                            Log.d("end game with pairs ",String.valueOf(toEndGameCounter));
                            endGameReached();
                        }
                        //SS: delete them from the view here
                    }
                    else{
                        //two different cards
                        secondTappedCard = position;
                        //secondImage = (ImageView) v;
                        Log.d("different cards clicked",String.valueOf(firstTappedCard)+" "+String.valueOf(secondTappedCard));
                        flipBackCard(v, cardAdapter);

                    }
                }
                ImageView imageView = (ImageView) v;

                int resourceId;
                Resources res = getResources();
                // int counter=  mGenerator.nextInt(IMAGESNAMES.length);
                resourceId = res.getIdentifier(cardsArray.get(position).getImage_name()+"card", "drawable",
                        getPackageName());
                //Log.d("image name",cardsArray[position].getImageName() );
                imageView.setImageResource(resourceId);
                cardAdapter.setItem(position, resourceId);





            }
        });
        ////////////////////////////
    }

    public void flipBackCard(View v, PlayCardAdapter adp){
        final ImageView imageView = (ImageView) v;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                int resourceId;
                Resources res = getResources();
                // int counter=  mGenerator.nextInt(IMAGESNAMES.length);
                resourceId = res.getIdentifier("square", "drawable",
                        getPackageName());
                //Log.d("image name",cardsArray[pos].getImageName() );
                imageView.setImageResource(resourceId);
                firstImage.setImageResource(resourceId);
                firstTappedCard = -1;
                secondTappedCard = -1;
            }
        }, 200);


    }

    public void endGameReached(){
        Toast.makeText(this, "Game finished", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewGame();
            }
        }, 1000);


    }

    public void startNewGame(){
        toEndGameCounter = 0;
        firstTappedCard = -1;
        secondTappedCard = -1; //initialize tapped card position to -1
        switch(level){
            case 0:
                capacity = 8;
                break;
            case 1:
                capacity = 16;
                break;
            case 2:
                capacity = 36;
                break;
            default:
                capacity = 8;
                break;
        }
        Log.v("capacity", String.valueOf(capacity));
        height = (int) Math.sqrt(capacity);
        width = (int) capacity/height;
        Log.v("height", String.valueOf(height));
        Log.v("width", String.valueOf(width));
        //future step have several arrays to each kind
        //shuffle selected array
        createCardsWithCapacity(capacity);
        setBoard();

    }

    public void levelClicked(View v){
        int resId = v.getId();
        switch (resId){
            case R.id.imageButtonEasy:
                level = 0;
                break;
            case R.id.imageButtonMedium:
                level = 1;
                break;
            case R.id.imageButtonHard:
                level = 2;
                break;
            default:
                level = 0;
                break;
        }
        startNewGame();
    }

}
