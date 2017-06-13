package com.example.henriquefilho.frog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO: document your custom view class.
 */
public class FrogView extends View {

    private Context ctx;
    private Paint white;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    public static int screenW, screenH;
    Canvas c ;
    Frog Jorge = new Frog((Activity)  getContext(),10,10,0);
    public static List<Frog> frogs;
    public  static int Score =0;
    public static List<Frog> letters;
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();
    public  static int LetterCount =0;
    int timerend=0;
    public FrogView(Context context) {
        super(context);
        init(context);
    }

    public FrogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FrogView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        letters= new ArrayList<>();
         frogs = new ArrayList<>();
        LetterCount =0;
        frogs.add(Jorge);
        this.ctx = context;
        screenW = ctx.getResources().getDisplayMetrics().widthPixels;
        screenH = ctx.getResources().getDisplayMetrics().heightPixels;

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();

        white = new Paint();
        white.setARGB(255, 0, 0, 0);
        ativaTimer();
        InstaceNew();
        timerend=0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c = canvas;
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

       for (int i=0;i<frogs.size();i++)
       {
           frogs.get(i).Draw(canvas);

       }
        for (int i=0;i<letters.size();i++) {
            if (letters.get(i) != null) {
                letters.get(i).Draw(canvas);
            }
        }
        // Draw the text.
        canvas.drawText("Score = "+Score,
                paddingLeft + (contentWidth - 100),
                paddingTop +100,
                white);
    }

    private void ativaTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {

                        for (int i=0;i<frogs.size();i++) {
                            if (frogs.get(i) != null&&frogs.get(i).controller==0) {
                                if (frogs.get(i).lifeTime > 400) frogs.remove(i);
                                frogs.get(i).update();
                            }
                        }

                        for (int i=0;i<letters.size();i++) {
                            if (letters.get(i) != null) {
                                letters.get(i).update();
                            }
                        }
                        if (frogs.size()==3)
                        {
                        }


                        invalidate();
                    }
                });
            }
        };
        timerAtual.schedule(task, 0, 30);
    }
    private void InstaceNew(){
        TimerTask task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Log.d("heu",String.valueOf(LetterCount));
                        Frog f = new Frog((Activity)getContext(),randInt(-20,20),randInt(-20,20),0);
                        frogs.add(f);
                        if (Score%200 ==0&&Score/200>=1)
                        {
                            int controle= Score/200;
                            if (controle>3)controle=3;
                            if (letters.size()==controle-1){
                                Frog letter = new Frog((Activity) getContext(), randInt(-20, 20), randInt(-20, 20), controle);
                                letters.add(letter);
                                switch (letters.size())
                                {
                                    case 1:
                                        Intent i = new Intent("ForcaGame");
                                        i.addCategory("Forca");
                                        i.putExtra("game", "Jogo1");
                                        ctx.startActivity(i);
                                        break;
                                    case 2:
                                        Intent i2 = new Intent("ForcaGame");
                                        i2.addCategory("Forca");
                                        i2.putExtra("game", "Jogo6");
                                        ctx.startActivity(i2);
                                        break;
                                    case 3:

                                        Intent i3 = new Intent("ForcaGame");

                                        i3.addCategory("Forca");
                                        i3.putExtra("game", "Jogo7");
                                        ctx.startActivity(i3);
                                        break;

                            }
                        }}
                    }
                });
            }
        };

        timerAtual.schedule(task, 0, 1000);
    }
    public static int randInt(int min, int max) {


        Random rand= new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        for (int i=0;i<frogs.size();i++) {
            if (frogs.get(i) != null) {
                if (frogs.get(i).lifeTime > 400) frogs.remove(i);
                frogs.get(i).preUpdate(event);
            }
        }

        return true;
    }
}
