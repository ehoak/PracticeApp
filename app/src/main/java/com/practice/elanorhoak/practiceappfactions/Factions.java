package com.practice.elanorhoak.practiceappfactions;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Factions extends AppCompatActivity {

    //create image view for main animal display
    ImageView image;

    //AnimationDrawable animationView = new AnimationDrawable(); //used for animated backgrounds
    //Handler handler = new Handler();

    boolean runLlama, runMallard, runHare;      //select which animal to display
    int i = 0;                                  //set iteration picture

    //create picture arrays to loop through
    int[] llamaArray = {R.mipmap.l_g1a, R.mipmap.l_g2, R.mipmap.l_g3,
            R.mipmap.l_g4, R.mipmap.l_g3, R.mipmap.l_g2};
    int[] mallardArray = {R.mipmap.m_g1, R.mipmap.m_g2, R.mipmap.m_g3,
            R.mipmap.m_g4, R.mipmap.m_g3, R.mipmap.m_g2};
    int[] hareArray = {R.mipmap.h_g1, R.mipmap.h_g2, R.mipmap.h_g3,
            R.mipmap.h_g4, R.mipmap.h_g3, R.mipmap.h_g2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factions);
        //create button

        runLlama    = false;
        runMallard  = false;
        runHare     = false;

        image       = (ImageView) findViewById(R.id.mainView);

        image.removeCallbacks(runnable);
        image.postDelayed(runnable, 100);

    }

    //when button pressed, go to Bluetooth menu
    public void gotoBT(View view){
        Intent intent = new Intent(this, BluetoothActivity.class);
        startActivity(intent);
    }

    //llama
    public void changeToLlama(View v) {

        runHare = false;
        runMallard = false;
        runLlama = true;

    }

    //mallard
    public void changeToMallard(View v) {

        runLlama = false;
        runHare = false;
        runMallard = true;

    }

    //hare
    public void changeToHare(View v) {

        runMallard = false;
        runLlama = false;
        runHare = true;

    }

    //run animation
    Runnable runnable = new Runnable() {
        public void run() {
            if(runLlama == true) {
                image.setImageResource(llamaArray[i]);
                i++;
                if (i > llamaArray.length - 1) {
                    i = 0;
                }
                image.postDelayed(this, 125);
            }
            else if(runMallard == true) {
                image.setImageResource(mallardArray[i]);
                i++;
                if (i > mallardArray.length - 1) {
                    i = 0;
                }

                image.postDelayed(this, 125);
            }
            else if(runHare == true) {
                image.setImageResource(hareArray[i]);
                i++;
                if (i > hareArray.length - 1) {
                    i = 0;
                }

                image.postDelayed(this, 125);
            }
            else{
                image.setImageResource(R.mipmap.ic_egg_logo_nosquare);
                image.postDelayed(this, 125);
            }
        }
    }; //end runnable

}//end Factions


        /*
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g1), 100);
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g2), 100);
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g3), 100);
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g4), 100);
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g3), 100);
        animationView.addFrame(getResources().getDrawable(R.mipmap.l_g2), 100);
        animationView.setOneShot(false);

        image = (ImageView) findViewById(R.id.mainView);
        image.setAlpha(0);
        image.setBackgroundDrawable(animationView); //ImageResource(R.mipmap.llama1);

        animationView.start(); */