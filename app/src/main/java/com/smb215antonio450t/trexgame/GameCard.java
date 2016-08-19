package com.smb215antonio450t.trexgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by user on 8/14/16.
 */
public class GameCard {
    int CardImage;
    String CardName;
    int CardNum;
    char CardType;
    int x = 0;
    int y = 0;
    static int CardHeight = ((int) (GameMain.density * 52.5f));
    static int CardWidth = ((int) (35.0f * GameMain.density));
    boolean IsBurned = false;
    int CardBackVertical;
    int CardBackHorizantal;

    public GameCard(String CardName, char CardType) {
        this.CardName = CardName;
        this.CardNum = Integer.valueOf(this.CardName.trim()).intValue();
        Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(CardNum));

        this.CardType = CardType;
        if (this.CardName.trim().compareTo("11") == 0) {
            this.CardName = "J";
        }
        if (this.CardName.trim().compareTo("12") == 0) {
            this.CardName = "Q";
        }
        if (this.CardName.trim().compareTo("13") == 0) {
            this.CardName = "K";
        }
        if (this.CardName.trim().compareTo("14") == 0) {
            this.CardName = "A";
        }
        if (CardType == 'd') {
            ImageDiamonds();
        }
        if (CardType == 'c') {
            ImageClubs();
        }
        if (CardType == 'h') {
            ImageHeart();
        }
        if (CardType == 's') {
            ImageSpades();
        }
    }

    public void ImageHeart() {
        switch (this.CardNum) {
            case 2:
                this.CardImage = R.drawable.h2;
                return;
            case 3:
                this.CardImage = R.drawable.h3;
                return;
            case 4:
                this.CardImage = R.drawable.h4;
                return;
            case 5:
                this.CardImage = R.drawable.h5;
                return;
            case 6:
                this.CardImage = R.drawable.h6;
                return;
            case 7:
                this.CardImage = R.drawable.h7;
                return;
            case 8:
                this.CardImage = R.drawable.h8;
                return;
            case 9:
                this.CardImage = R.drawable.h9;
                return;
            case 10:
                this.CardImage = R.drawable.h10;
                return;
            case 11:
                this.CardImage = R.drawable.h11;
                return;
            case 12:
                this.CardImage = R.drawable.h12;
                return;
            case 13:
                this.CardImage = R.drawable.h13;
                return;
            case 14:
                this.CardImage = R.drawable.h14;
                return;
            default:
                return;
        }
    }

    public void ImageClubs() {
        switch (this.CardNum) {
            case 2:
                this.CardImage = R.drawable.c2;
                return;
            case 3:
                this.CardImage = R.drawable.c3;
                return;
            case 4:
                this.CardImage = R.drawable.c4;
                return;
            case 5:
                this.CardImage = R.drawable.c5;
                return;
            case 6:
                this.CardImage = R.drawable.c6;
                return;
            case 7:
                this.CardImage = R.drawable.c7;
                return;
            case 8:
                this.CardImage = R.drawable.c8;
                return;
            case 9:
                this.CardImage = R.drawable.c9;
                return;
            case 10:
                this.CardImage = R.drawable.c10;
                return;
            case 11:
                this.CardImage = R.drawable.c11;
                return;
            case 12:
                this.CardImage = R.drawable.c12;
                return;
            case 13:
                this.CardImage = R.drawable.c13;
                return;
            case 14:
                this.CardImage = R.drawable.c14;
                return;
            default:
                return;
        }
    }

    public void ImageSpades() {
        switch (this.CardNum) {
            case 2:
                this.CardImage = R.drawable.s2;
                return;
            case 3:
                this.CardImage = R.drawable.s3;
                return;
            case 4:
                this.CardImage = R.drawable.s4;
                return;
            case 5:
                this.CardImage = R.drawable.s5;
                return;
            case 6:
                this.CardImage = R.drawable.s6;
                return;
            case 7:
                this.CardImage = R.drawable.s7;
                return;
            case 8:
                this.CardImage = R.drawable.s8;
                return;
            case 9:
                this.CardImage = R.drawable.s9;
                return;
            case 10:
                this.CardImage = R.drawable.s10;
                return;
            case 11:
                this.CardImage = R.drawable.s11;
                return;
            case 12:
                this.CardImage = R.drawable.s12;
                return;
            case 13:
                this.CardImage = R.drawable.s13;
                return;
            case 14:
                this.CardImage = R.drawable.s14;
                return;
            default:
                return;
        }
    }

    public void ImageDiamonds() {
        switch (this.CardNum) {
            case 2:
                this.CardImage = R.drawable.d2;
                return;
            case 3:
                this.CardImage = R.drawable.d3;
                return;
            case 4:
                this.CardImage = R.drawable.d4;
                return;
            case 5:
                this.CardImage = R.drawable.d5;
                return;
            case 6:
                this.CardImage = R.drawable.d6;
                return;
            case 7:
                this.CardImage = R.drawable.d7;
                return;
            case 8:
                this.CardImage = R.drawable.d8;
                return;
            case 9:
                this.CardImage = R.drawable.d9;
                return;
            case 10:
                this.CardImage = R.drawable.d10;
                return;
            case 11:
                this.CardImage = R.drawable.d11;
                return;
            case 12:
                this.CardImage = R.drawable.d12;
                return;
            case 13:
                this.CardImage = R.drawable.d13;
                return;
            case 14:
                this.CardImage = R.drawable.d14;
                return;
            default:
                return;
        }
    }

    public void drawOneCard(int x, int y, Canvas canvas, Context context) {
        this.x = x;
        this.y = y;
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), this.CardImage), CardWidth, CardHeight, true), (float) x, (float) y, null);

    }

    public void drawVerticalClosedCard(int x, int y, Canvas canvas, Context context) {
        this.x = x;
        this.y = y;
        this.CardBackVertical = R.drawable.vertical;
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), this.CardBackVertical), CardWidth, CardHeight, true), (float) x, (float) y, null);

    }

    public void drawHorinzantolClosedCard(int x, int y, Canvas canvas, Context context) {
        this.x = x;
        this.y = y;
        this.CardBackHorizantal = R.drawable.horizontal;
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), this.CardBackHorizantal),CardHeight ,CardWidth , true), (float) x, (float) y, null);
    }
}
