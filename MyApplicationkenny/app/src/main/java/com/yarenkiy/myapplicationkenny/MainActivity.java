package com.yarenkiy.myapplicationkenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView score ;
TextView time;
int scoree ;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imagearray;
    Handler handler ;
    Runnable runnable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (TextView) findViewById(R.id.time);

        score = (TextView) findViewById(R.id.score);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imagearray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImage();





        scoree = 0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time : "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
              time.setText("time off.");
              handler.removeCallbacks(runnable);
                for(ImageView image : imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart? ");
                alert.setMessage("Are you sure to restart game ? ");
                alert.setPositiveButton("yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
alert.setNegativeButton("no ", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(MainActivity.this ,"Game over ",Toast.LENGTH_SHORT).show();
    }
});
alert.show();

            }
        }.start();

    }
    public void increaseScore(View view){
        scoree++;
        score.setText("Score: "+scoree);
    }

    public void hideImage (){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imagearray) {
                    image.setVisibility(View.INVISIBLE);
                }
                    Random random = new Random();
                    int i = random.nextInt(9);
                    imagearray[i].setVisibility(View.VISIBLE);

                    handler.postDelayed(this, 500);

            }
        };

        handler.post(runnable);


    }
}