package com.oguzhan.acatch;

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

    int score;
    TextView scoreTextView,timeTextView;
    ImageView imageView00,imageView01,imageView02,imageView03,imageView04,imageView10,imageView11,imageView12,imageView13,imageView14,imageView20,imageView21,imageView22,imageView23,imageView24,imageView30,imageView31,imageView32,imageView33,imageView34,imageView40,imageView41,imageView42,imageView43,imageView44;
    ImageView[] imageArray ;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize

        score = 0;
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        imageView00 = findViewById(R.id.imageView00);
        imageView01 = findViewById(R.id.imageView01);
        imageView02 = findViewById(R.id.imageView02);
        imageView03 = findViewById(R.id.imageView03);
        imageView04 = findViewById(R.id.imageView04);

        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);

        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);

        imageView30 = findViewById(R.id.imageView30);
        imageView31 = findViewById(R.id.imageView31);
        imageView32 = findViewById(R.id.imageView32);
        imageView33 = findViewById(R.id.imageView33);
        imageView34 = findViewById(R.id.imageView34);

        imageView40 = findViewById(R.id.imageView40);
        imageView41 = findViewById(R.id.imageView41);
        imageView42 = findViewById(R.id.imageView42);
        imageView43 = findViewById(R.id.imageView43);
        imageView44 = findViewById(R.id.imageView44);

        imageArray = new ImageView[] {imageView00,imageView01,imageView02,imageView03,imageView04,imageView10,imageView11,imageView12,imageView13,imageView14,imageView20,imageView21,imageView22,imageView23,imageView24,imageView30,imageView31,imageView32,imageView33,imageView34,imageView40,imageView41,imageView42,imageView43,imageView44};

        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText("Time: " + l/1000);
            }

            @Override
            public void onFinish() {
                timeTextView.setText("Time Off !!!");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you want to play again ?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();
            }
        }.start();

    }

    public void increaseScore ( View view )
    {
        score++;
        scoreTextView.setText("Score: " + score);
    }

    public void hideImages()
    {
        final Random random = new Random();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                int i = random.nextInt(25);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,700);
            }
        };
        handler.post(runnable);
    }
}
