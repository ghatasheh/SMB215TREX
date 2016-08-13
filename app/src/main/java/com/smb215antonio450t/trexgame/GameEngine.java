package com.smb215antonio450t.trexgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by user on 8/13/16.
 */
public class GameEngine extends View {

    Bitmap bm;
    int TableBg;


    public GameEngine(Context context) {
        super(context);
        this.TableBg = R.drawable.playtable;
        this.bm = BitmapFactory.decodeResource(context.getResources(), this.TableBg);
        this.bm = Bitmap.createScaledBitmap(this.bm, this.bm.getWidth(), this.bm.getHeight(), true);

    }

    public void onDraw(Canvas canvas) {
        this.bm = Bitmap.createScaledBitmap(this.bm, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(this.bm, 0.0f, 0.0f, null);
    }
}

