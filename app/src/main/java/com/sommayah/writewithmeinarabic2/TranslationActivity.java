package com.sommayah.writewithmeinarabic2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TranslationActivity extends AppCompatActivity {
    
    private static final String[] translationArray = {
    "Lion: أَسَد \n\nRabbit: أرْنَب \n\nPineapple: أنَانَاس \n\nSwing: أُرْجُوحَة",
    "Duck: بَطَة \n\nHouse: بَيْت \n\nGirl: بِنْت \n\nDoor: بَاب \n\nEgg: بَيْضَة",
    "Apple: تُفَّاحَة \n\nBerry: تُوت \n\nDates: تَمْر  \n\nCrown: تَاج  \n\nAlligator: تِمسَاح",
    "Dress: ثَوْب \n\nFox: ثَعْلَب \n\nGarlic: ثَوْم \n\nSnake: ُثُعْبَان \n\nOx: ثَوْر",
    "Bell: جَرَس \n\nCamel: جَمَل \n\nCarrot: جَزَر \n\nCheese: جُبْن",
    "Donkey: حِمَار \n\nHorse: حِصَان \n\nMilk: حَلِيب \n\nShoes: حِذَاء",
    "Bread: خُبْز \n\nCucumber: خِيَار \n\nLadybug: خُنْفُسَاء \n\nSheep: خَرُوف",
    "Bear: دُب \n\nBucket: دَلْوْ \n\nChicken: دَجَاجَة \n\nRooster: دِيك",
    "Corn: ذُرَة \n\nFly: ذُبَابَة \n\nTail: ذَيْل \n\nWolf: ذِئْب",
    "Feather: رِيشَة \n\nMan: رَجُل \n\nPomegranate: رُمَّان \n\nSand: رِمَال",
    "Flower: زَهْرَة \n\nGiraffe: زَرَافَة \n\nOil: زَيْت \n\nOlives: زَيْتُون",
    "Bed: سَرِير \n\nCar: سَيَّارَة \n\nClock: سَاعَة \n\nFish: سَمَكَة \n\nTurtle: سُلَحْفاة",
    "Candle: شَمْعَة \n\nNet: شَبَكَة \n\nRibbon: شَرِيط \n\nSun: شَمْس \n\nTree: شَجَرَة",
    "Box: صُنْدُوق \n\nFalcon: صَقْر \n\nPicture: صُورَة \n\nRock: صَخْرَة \n\nShell: صَدَف",
    "Frog: ضِفْدَع \n\nMolar: ضِرْس \n\nLight: ضَوْء \n\nFog: ضَبَاب",
    "Peacock: طَاووس \n\nPlane: طَائِرَة \n\nTable: طاوِلَة \n\nBird: طَائِرَ  \n\nDoctor: طَبِيب",
    "Envelope: ظَرْف \n\nFingernail: ظِفْر \n\nDeer: ظَبْيّ \n\nBack: ظَهْر",
    "Eye: عَيْن \n\nNecklace: عِقْد \n\nContainer: عُلْبَة \n\nBird: عُصْفُور \n\nGrapes: عِنَب",
    "Crow: غُرَاب \n\nBranch: غُصْن \n\nWasher: غَسَّالَة \n\nGazelle: غَزَال \n\nForest: غَابَة",
    "Butterfly: فَرَاشَة \n\nElephant: فِيل \n\nMouse: فَأر \n\nStrawberry: فَرَاوْلَة",
    "Cat: قِطَّة \n\nMonkey: قِرْد \n\nMoon: قَمَر \n\nTrain: قِطَار \n\nPencil: قَلَم \n\nBoat: قارِب",
    "Ball: كُرَة \n\nBook: كِتَاب \n\nCake: كَعْكَة \n\nChair: كُرْسِيّ \n\nDog: كَلْب",
    "Lemon: لَيْمُون \n\nMeat: لَحْم \n\nTongue: لِسَان \n\nBeard: لِحْيَة",
    "Banana: مَوْز \n\nSalt: مِلْح \n\nSchool: مَدْرَسَة \n\nScissors: مِقَص",
    "Bee: نَحْلَة \n\nStar: نَجْمَة \n\nAnt: نَمْلَة \n\nTiger: نِمْر \n\nEagle: نَسْر",
    "Crescent: هِلَال \n\nHoopoe: هُدْهُد \n\nPyramid: هَرَم \n\nTelephone: هَاتِف",
    "Boy: وَلَد \n\nPaper: وَرَقَة \n\nRose: وَرْدَة \n\nPillow: وِسَادَة",
    "Hand: يَد \n\nDove: يَمَامَة \n\nLeft: يَسَار \n\nRight: يَمِين",
    };

    final String[] letters = new String[] {
            "أ", "ب", "ت", "ث", "ج",
            "ح", "خ", "د", "ذ", "ر",
            "ز", "س", "ش", "ص", "ض",
            "ط", "ظ", "ع", "غ", "ف",
            "ق", "ك", "ل", "م", "ن", "هـ"
            , "و", "ي"};

    private static MediaPlayer letterMediaPlayer;
    private final String TAG = TranslationActivity.class.getSimpleName();

    @BindView(R.id.letterTranslations)
    TextView mLetterTransation;

    @BindView(R.id.lettersGrid)
    GridView mGridView;

    @BindView(R.id.translationTextView)
    TextView mLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        ButterKnife.bind(this);
        mLetterTransation.setText(translationArray[0]);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.traslation_grid_item,letters);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                mLetterTransation.setText(translationArray[position]);
                mLetter.setText(letters[position]);
                stopAndResetSounds();
                playAudio(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        letterMediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(letterMediaPlayer != null)
            letterMediaPlayer.release();
        letterMediaPlayer = null;
    }

    private void stopAndResetSounds() {
        if(letterMediaPlayer != null){
            letterMediaPlayer.reset();
        }
    }

    private void playAudio(int position) {
            try {
                prepareSound(AlphabetActivity.lettersFilesArray[position], letterMediaPlayer);
                letterMediaPlayer.start();

            } catch (Exception e) {
                Log.e(TAG, "error: " + e.getMessage(), e);
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
