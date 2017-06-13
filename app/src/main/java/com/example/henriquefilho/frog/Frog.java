package com.example.henriquefilho.frog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by henrique.filho on 16/05/2017.
 */

public class Frog {
    private Activity myActivity;
    private int widht, height, X, Y;
    Bitmap b;
    Paint p;
    float speedx;
    float speedy;
    public int lifeTime = 0;
    int controller;
    public Frog(Activity at, int speed, int speedyy,int Image) {
        myActivity = at;
        widht = 40;
        height = 40;
        controller = Image;
        if (Image==0)
        {
            b = BitmapFactory.decodeResource(at.getResources(), R.mipmap.sapo);
        }
        if (Image==1)
        {
            b = BitmapFactory.decodeResource(at.getResources(), R.mipmap.m);
        }
        if (Image==2)
        {
            b = BitmapFactory.decodeResource(at.getResources(), R.mipmap.b);
        }
        if (Image==3)
        {
            b = BitmapFactory.decodeResource(at.getResources(), R.mipmap.o);
        }
        p = new Paint();
        p.setARGB(200, 0, 0, 255);
        X = 4000;
        Y = 4000;
        speedx = speed;
        speedy = speedyy;

    }

    public void Draw(Canvas canvas) {
        if (X == 4000 && Y == 4000) {
            widht = b.getScaledWidth(canvas);
            height = b.getScaledHeight(canvas);
            X = FrogView.screenW / 2 - widht / 2;
            Y = FrogView.screenH / 2 - height / 2;
        }
        canvas.drawBitmap(b, X, Y, p);
    }

    public void update() {
        lifeTime++;

        this.X += speedx;
        this.Y += speedy;

        if (X < 0) speedx *= -1;
        if (Y < 0) speedy *= -1;
        if (Y + height > FrogView.screenH) speedy *= -1;
        if (X + widht > FrogView.screenW) speedx *= -1;


    }

    public void preUpdate(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                if (x > X && x < X + widht && y > Y && y < Y + height) {
                    //Bitmap touched
                    if (controller == 0) {
                        FrogView.Score += 10;
                        FrogView.frogs.remove(this);
                      //  Toast.makeText(myActivity.getBaseContext(),"Sua pontuação é "+FrogView.Score,Toast.LENGTH_SHORT).show();
                    }
                    }
                }

        }

    }





