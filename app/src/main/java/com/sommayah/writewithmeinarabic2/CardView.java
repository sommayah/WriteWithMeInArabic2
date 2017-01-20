package com.sommayah.writewithmeinarabic2;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sommayahsoliman on 1/19/17.
 */

public class CardView extends ImageView implements View.OnClickListener{
    private boolean facedown;
    private int image_num;
    private String image_name;
    private final String SQUARE = "square";


    public CardView(Context context) {
        super(context);
        initialize(0,"");
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(0,"");
    }



    public void setNumber(int num){
        image_num = num;
    }

    public void setImage(String name){
        this.setImage(name);
    }

    public int getImage_num(){return image_num;}

    public String getImage_name(){return image_name;}

    public boolean isFaceDown(){return facedown;}

    public void initialize(int num,String name){
        image_num = num;
        image_name = name;
        facedown = true;
       // this.setImage(SQUARE);
    }

    public void flipCard(){
        if(facedown == true){
            Resources bgres = getResources();
            int bgresourceId;
            bgresourceId = bgres.getIdentifier("beecard", "drawable"
                    , getContext().getPackageName());
            setImageResource(bgresourceId);
        }else{
            setImageResource(R.drawable.square);
        }
        facedown = !facedown;

    }



    @Override
    public void onClick(View view) {
        flipCard();
    }

}
