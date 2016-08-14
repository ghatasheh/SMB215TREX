package com.smb215antonio450t.trexgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

/**
 * Created by user on 8/13/16.
 */
public class GameEngine extends View {

    Bitmap bm;
    int TableBg;
    Context ct = getContext();
    GamePlayerCards pc;
    GameRandomCardShuffle randomCard;

    public GameEngine(Context context) {
        super(context);
        this.TableBg = R.drawable.playtable;
        this.bm = BitmapFactory.decodeResource(context.getResources(), this.TableBg);
        this.bm = Bitmap.createScaledBitmap(this.bm, this.bm.getWidth(), this.bm.getHeight(), true);

    }

    public void onDraw(Canvas canvas) {
        this.bm = Bitmap.createScaledBitmap(this.bm, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(this.bm, 0.0f, 0.0f, null);
        /*GameCard GC,GC2;
        GC = new GameCard("2",'s');
        GC2 = new GameCard("14",'h');
        GC.drawOneCard(0,0,canvas,ct);
        GC2.drawOneCard(0,500,canvas,ct);*/
        GameCard[] Cardlist;
        this.pc = new GamePlayerCards();
        this.randomCard = new GameRandomCardShuffle();
        String st2 = "";
        for (int i = 0; i < 13; i++) {
            String st = this.randomCard.getRandom();
            this.pc.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
        }

        Cardlist = this.pc.NotBurnedCards();
        int i;
        for (i = 0; i < Cardlist.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf( Cardlist[i].CardNum));
            Cardlist[i].drawOneCard(((canvas.getWidth() - ((GameCard.CardWidth + 14) * Cardlist.length)) / 2) + ((GameCard.CardWidth + 15) * i), (canvas.getHeight() - GameCard.CardHeight) - 10, canvas, ct);
        }

    }
}

