package com.ooemma.flyingfishgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View
{
    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed = 7;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 10;
    private Paint greenPaint = new Paint();


    private int redX, redY, redSpeed = 10;
    private Paint redPaint = new Paint();

    private int dangerX, dangerY, dangerSpeed = 20;
    private Paint dangerPaint = new Paint();


    private int liveX, liveY, liveSpeed = 40;
    private Paint livePaint = new Paint();

    private int score, liveCounterofFish;
    private String level;

    private boolean touch = false;


    private Bitmap backgroundImage, fish9;
    private Paint scorePaint  = new Paint();
    private Paint levelPaint = new Paint();
    private Bitmap live[] = new Bitmap[2];

    public FlyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish3);

        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.sea14);
        fish9 = BitmapFactory.decodeResource(getResources(), R.drawable.fish9);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);


        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        dangerPaint.setColor(Color.RED);
        dangerPaint.setAntiAlias(false);


        livePaint.setColor(Color.WHITE);
        livePaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(30);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);


        levelPaint.setColor(Color.WHITE);
        levelPaint.setTextSize(30);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setAntiAlias(true);

        live[0] = BitmapFactory.decodeResource(getResources(), R.drawable.love7);
        live[1] = BitmapFactory.decodeResource(getResources(), R.drawable.love6);

        fishY = 550;
        score = 0;
        liveCounterofFish = 3;
        level = "1";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0,0, null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;
        if(fishY < minFishY)
        {
            fishY = minFishY;
        }
        if(fishY > maxFishY)
        {
            fishY = maxFishY;
        }
            fishSpeed = fishSpeed + 2;
        if(touch)
        {
            canvas.drawBitmap(fish[1], fishX, fishY, null );
            touch = false;
        }
        else
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null );
        }


        yellowX = yellowX - yellowSpeed;
        if(score == 40 )
        {
            level = "2";
            yellowSpeed = 10;

        }else  if(score == 70 )
        {
            yellowSpeed = 15;
            level = "4";
        }else  if(score == 100 )
        {
            yellowSpeed = 25;
            level = "6";
        }else  if(score == 120 )
        {
            yellowSpeed = 35;
            level = "8";
        }else if(score == 140 )
        {
            yellowSpeed = 50;
            level = "10";
        }
        if(hitBallChecker(yellowX, yellowY))
        {
            score = score + 5;
            yellowX = - 100;
        }
        if(yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }

        canvas.drawCircle(yellowX, yellowY, 30, yellowPaint);
//        canvas.drawBitmap(fish9, yellowX, yellowY, null);





        greenX = greenX - greenSpeed;
        if(score == 60 )
        {
            greenSpeed = 14;
            level = "3";
        }else if(score == 90 )
        {
            greenSpeed = 20;
            level = "5";

        }else if(score == 110 )
        {
            greenSpeed = 30;
            level = "7";
        }else if(score == 130 )
        {
            greenSpeed = 40;
            level = "9";
        }
        if(hitBallChecker(greenX, greenY))
        {
            score = score + 10;
            greenX = - 100;
        }
        if(greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);



    if (liveCounterofFish == 1 ) {
        liveX = liveX - liveSpeed;
        if (hitBallChecker(liveX, liveY)) {
            score = score + 15;
            liveX = -100;
            liveCounterofFish = liveCounterofFish + 1;
        }
        if (liveX < 0) {
            liveX = canvasWidth + 21;
            liveY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(liveX, liveY, 25, livePaint);
    }else if(liveCounterofFish == 2)
    {
        greenX = greenX - greenSpeed;
        if(hitBallChecker(greenX, greenY))
        {
            score = score + 10;
            greenX = - 100;
        }
        if(greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

    }

        redX = redX - redSpeed;
        if(level == "4")
        {
            redSpeed = 20;
        }else if(level == "6")
        {
            redSpeed = 25;
        }else if(level == "7")
        {
            redSpeed = 30;
        }
        if(hitBallChecker(redX, redY))
        {
            redX = - 100;
            liveCounterofFish --;
            if(liveCounterofFish == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);

            }
        }
        if(redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 25, redPaint);



        dangerX = dangerX - dangerSpeed;
        if(hitBallChecker(dangerX, dangerY))
        {
            dangerX = - 100;
            liveCounterofFish --;
            if(liveCounterofFish == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_LONG).show();
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);

            }
        }
        if(dangerX < 0)
        {
            dangerX = canvasWidth + 21;
            dangerY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(dangerX, dangerY, 25, dangerPaint);

        canvas.drawText("Score: " + score, 20, 60, scorePaint);
        canvas.drawText("Level: " + level, 20, 120, scorePaint);

        for(int i=0; i<3; i++ )
        {
            int x =  (int)(480 + live[0].getWidth() * 1.2 * i);
            int y = 30;

            if(i < liveCounterofFish)
            {
                canvas.drawBitmap(live[0], x, y, null);
            }
            else
            {
                canvas.drawBitmap(live[1], x, y, null);
            }
        }


//        canvas.drawBitmap(live[0], 480, 10, null);
//        canvas.drawBitmap(live[0], 550, 10, null);
//        canvas.drawBitmap(live[0], 620, 10, null);
    }



    public boolean hitBallChecker(int x, int y)
    {
        if(fishX < x && x <(fishX + fish[0].getWidth()) && fishY < y && y <(fishY + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;

            fishSpeed = -25;
        }

        return true;
    }
}
