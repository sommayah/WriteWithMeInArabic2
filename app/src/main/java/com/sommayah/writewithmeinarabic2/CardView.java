package com.sommayah.writewithmeinarabic2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import static com.sommayah.writewithmeinarabic2.R.id.card;

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

    public void setImageName(String name){
        image_name = name;
    }

    public int getImage_num(){return image_num;}

    public String getImage_name(){return image_name;}

    public boolean isFaceDown(){return facedown;}

    public void initialize(int num,String name){
        this.image_num = num;
        this.image_name = name;
        this.facedown = true;
    }

    public void flipCard(){
        float rotation1 = (facedown ? 0.0f : 180f);
        float rotation = (facedown ? 180f : 0.0f);

        ObjectAnimator animation = ObjectAnimator.ofFloat(card, "rotationY", rotation1, rotation);  // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
        animation.setDuration(250); // HERE 500 IS THE DURATION OF THE ANIMATION, YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if(facedown == true){
                    Resources bgres = getResources();
                    int bgresourceId;
                    bgresourceId = bgres.getIdentifier(image_name+"card", "drawable"
                            , getContext().getPackageName());
                    setImageResource(bgresourceId);
                }else{
                    setImageResource(R.drawable.square);
                }
                facedown = !facedown;
            }
        }, facedown?200:200);


    }



    @Override
    public void onClick(View view) {
        flipCard();
    }

}
