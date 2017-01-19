package com.sommayah.writewithmeinarabic2;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by sommayahsoliman on 1/19/17.
 */

public class CardView extends ImageView {
    private boolean facedown;
    private int image_num;
    private String image_name;
    private final String SQUARE = "square";

    public CardView(Context context) {
        super(context);
        facedown = true;
        image_num = 0;
        image_name = "";
    }

    public void setNumber(int num){
        image_num = num;
    }

    public void setImage(String name){
        this.setImage(name);
    }

    public int getImage_num(){return image_num;}

    public String getImage_name(){return image_name;}

    public void initialize(int num,String name){
        image_num = num;
        image_name = name;
        facedown = true;
        this.setImage(SQUARE);
    }

    public void flipCard(){
        if(facedown == true){
            this.setImage("bee"+"card");
        }else{
            this.setImage(SQUARE);
        }
        facedown = !facedown;

    }



}
