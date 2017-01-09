package com.sommayah.writewithmeinarabic2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import static com.sommayah.writewithmeinarabic2.R.id.gridView;

public class AlphabetActivity extends AppCompatActivity {

    static final String[] arabicLettersArray = new String[] {
            "أ", "ب", "ت", "ث", "ج",
            "ح", "خ", "د", "ذ", "ر",
            "ز", "س", "ش", "ص", "ض",
            "ط", "ظ", "ع", "غ", "ف",
            "ق", "ك", "ل", "م", "ن", "هـ"
            , "و", "ي","ا","ة","ئ","ء"};
    static final String[] lettersFilesArray = new String[]{
            "a", "b", "ta", "tha", "jeem", "ha",
            "kha", "dal", "thal", "ra", "zeen", "seen",
            "sheen", "sad", "dad", "Tta", "Zza", "ain",
            "ghain", "fa", "qaf", "kaf", "lam", "meem",
            "noon", "haaa", "waw", "ya", "a", "tamarbota", "hamza", "hamza"

    };

    static ArrayList<String[]> workSheetsArray;
    static {
        workSheetsArray = new ArrayList<>();
        workSheetsArray.add(new String[]{"lion","rabbit","swing","pineapple"});
        workSheetsArray.add(new String[]{"duck","door","house","egg","girl"});
        workSheetsArray.add(new String[]{"apple","berry","date","crown","alligator"});
        workSheetsArray.add(new String[]{"dress","fox","garlic","snake","thawr"});
        workSheetsArray.add(new String[]{"bell","camel","carrot","cheese"});
        workSheetsArray.add(new String[]{"donkey","horse","milk","shoe"});
        workSheetsArray.add(new String[]{"bread","cucumber","ladybug","sheep"});
        workSheetsArray.add(new String[]{"bear","bucket","chicken","rooster"});
        workSheetsArray.add(new String[]{"corn","fly","tail","th2b"});
        workSheetsArray.add(new String[]{"feather","man","pomegranate","sand"});
        workSheetsArray.add(new String[]{"flower","giraffe","oil","olives"});
        workSheetsArray.add(new String[]{"bed","car","clock","fish","turtle"});
        workSheetsArray.add(new String[]{"candle","net","ribbon","sun","tree"});
        workSheetsArray.add(new String[]{"box","falcoon","picture","rock","shell"});
        workSheetsArray.add(new String[]{"frog","tooth","light","fog"});
        workSheetsArray.add(new String[]{"peackok","plane","table","bird","doctor"});
        workSheetsArray.add(new String[]{"envelope","fingernail","deer","back"});
        workSheetsArray.add(new String[]{"grapes","eye","necklace","container","birdie"});
        workSheetsArray.add(new String[]{"crow","leave","washer","ghazelle","forest"});
        workSheetsArray.add(new String[]{"butterfly","elephant","mouse","strawberry"});
        workSheetsArray.add(new String[]{"cat","monkey","moon","train","pencil","boat"});
        workSheetsArray.add(new String[]{"ball","book","cake","chair","dog"});
        workSheetsArray.add(new String[]{"lemon","meat","tongue","beard"});
        workSheetsArray.add(new String[]{"banana","salt","school","scisors"});
        workSheetsArray.add(new String[]{"bee","star","ant","tiger","eagle"});
        workSheetsArray.add(new String[]{"crescent","hoopoe","pyramid","telephone"});
        workSheetsArray.add(new String[]{"boy","paper","rose","pillow"});
        workSheetsArray.add(new String[]{"hand","dove","left","right"});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        GridView gridview = (GridView) findViewById(gridView);

        final String[] letters = new String[] {
                "أ", "ب", "ت", "ث", "ج",
                "ح", "خ", "د", "ذ", "ر",
                "ز", "س", "ش", "ص", "ض",
                "ط", "ظ", "ع", "غ", "ف",
                "ق", "ك", "ل", "م", "ن", "هـ"
                , "و", "ي", "B","E"};

        AlphabetAdapter adapter = new AlphabetAdapter(this,
                letters);

        gridview.setAdapter(adapter);
    }


}
