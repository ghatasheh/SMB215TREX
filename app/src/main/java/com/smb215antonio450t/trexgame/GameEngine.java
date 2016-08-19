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
    GamePlayerCards pc1;
    GamePlayerCards pc2;
    GamePlayerCards pc3;
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
        GameCard[] Cardlist2;
        GameCard[] Cardlist3;
        GameCard[] Cardlist4;
        this.pc = new GamePlayerCards();
        this.randomCard = new GameRandomCardShuffle();
        this.pc1 = new GamePlayerCards();
        this.pc2 = new GamePlayerCards();
        this.pc3 = new GamePlayerCards();
        String st2 = "";
        for (int i = 0; i < 13; i++) {
            String st = this.randomCard.getRandom();
            this.pc.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pc1.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pc2.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pc3.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
        }
        Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(st2));

        Cardlist = this.pc.NotBurnedCards();
        Cardlist2 = this.pc1.NotBurnedCards();
        Cardlist3 = this.pc2.NotBurnedCards();
        Cardlist4 = this.pc3.NotBurnedCards();
        int i;
        for (i = 0; i < Cardlist.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf( Cardlist[i].CardNum));
            Cardlist[i].drawOneCard(((canvas.getWidth() - ((GameCard.CardWidth + 14) * Cardlist.length)) / 2) + ((GameCard.CardWidth + 15) * i), (canvas.getHeight() - GameCard.CardHeight) - 10, canvas, ct);
        }

        for (i = 0; i < Cardlist2.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf( Cardlist2[i].CardNum));
            Cardlist2[i].drawVerticalClosedCard((((canvas.getWidth() / 2) - 65) - 25) + (i * 10), 10, canvas,ct);
        }

        for (i = 0; i < Cardlist3.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf( Cardlist3[i].CardNum));
            Cardlist3[i].drawHorinzantolClosedCard((canvas.getWidth() - GameCard.CardHeight) - 10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas,ct);
        }

        for (i = 0; i < Cardlist4.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf( Cardlist4[i].CardNum));
            Cardlist4[i].drawHorinzantolClosedCard(10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas,ct);
        }

    }
}

