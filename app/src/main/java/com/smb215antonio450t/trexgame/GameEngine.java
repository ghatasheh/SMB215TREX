package com.smb215antonio450t.trexgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by user on 8/13/16.
 */
public class GameEngine extends View implements View.OnTouchListener {

    Bitmap bm;
    int TableBg;
    Context ct = getContext();

    GamePlayerCards pcs;
    GameRandomCardShuffle randomCard;
    GamePlayerCards pce;/**/
    GamePlayerCards pcn;
    GamePlayerCards pcw;

    GamePlayer ps = new GamePlayer("You", "ps");
    GamePlayer pe = new GamePlayer("Farid", "pe");
    GamePlayer pn = new GamePlayer("Maroun", "pn");
    GamePlayer pw = new GamePlayer("Pascal", "pw");

    Paint PlayerNames = new Paint();
    String CurrentTurn;
    int imgCrown;
    Point TouchedPoint;
    GameCard[] CardSet;
    GameCard pCardOnTable = null;

    public GameEngine(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
        this.TableBg = R.drawable.playtable;
        this.bm = BitmapFactory.decodeResource(context.getResources(), this.TableBg);
        this.bm = Bitmap.createScaledBitmap(this.bm, this.bm.getWidth(), this.bm.getHeight(), true);
        this.imgCrown = R.drawable.crown;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
        this.CardSet = new GameCard[13];

    }

    public void drawCrown(Canvas canvas, float x, float y) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(ct.getResources(), this.imgCrown), 30, 30, true), x, y, null);
    }

    public void onDraw(Canvas canvas) {
        this.bm = Bitmap.createScaledBitmap(this.bm, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(this.bm, 0.0f, 0.0f, null);
        /*GameCard GC,GC2;
        GC = new GameCard("2",'s');
        GC2 = new GameCard("14",'h');
        GC.drawOneCard(0,0,canvas,ct);
        GC2.drawOneCard(0,500,canvas,ct);*/
        GameCard[] Cardlists;
        GameCard[] Cardliste;
        GameCard[] Cardlistn;
        GameCard[] Cardlistw;
        this.pcs = new GamePlayerCards();
        this.randomCard = new GameRandomCardShuffle();
        this.pce = new GamePlayerCards();
        this.pcn = new GamePlayerCards();
        this.pcw = new GamePlayerCards();
        String st2 = "";
        for (int i = 0; i < 13; i++) {
            String st = this.randomCard.getRandom();
            this.pcs.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pce.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pcn.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pcw.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0)));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
        }
        Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(st2));

        Cardlists = this.pcs.NotBurnedCards();
        Cardliste = this.pce.NotBurnedCards();
        Cardlistn = this.pcn.NotBurnedCards();
        Cardlistw = this.pcw.NotBurnedCards();

        /*Log.println(Log.ERROR, "--------------------", String.valueOf(canvas.getWidth()));
        Log.println(Log.ERROR, "--------------------", String.valueOf(canvas.getHeight()));
        Log.println(Log.ERROR, "--------------------", String.valueOf(canvas.getHeight()-GameCard.CardHeight-100));
        canvas.drawText("**************", 100,100, this.PlayerNames);
        canvas.drawText("**************", 200,200, this.PlayerNames);
        canvas.drawText("**************", 300,300, this.PlayerNames);
        canvas.drawText("**************", 400,400, this.PlayerNames);*/
        this.PlayerNames.setTextSize(25.0f);
        this.PlayerNames.setColor(Color.WHITE);
        canvas.drawText(this.ps.name, (float) (((canvas.getWidth() / 2) - 150) - 80), (float) ((canvas.getHeight() - GameCard.CardHeight - 50)), this.PlayerNames);
        canvas.drawText(this.pe.name, (float) ((canvas.getWidth() - 80) - 10), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 25), this.PlayerNames);
        canvas.drawText(this.pn.name, (float) (((canvas.getWidth() / 2) - 200) - 20), 25.0f, this.PlayerNames);
        canvas.drawText(this.pw.name, (float) (((GameCard.CardHeight / 2) - 5) - 15), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 25), this.PlayerNames);

        int i;
        for (i = 0; i < Cardlists.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(Cardlists[i].CardNum));
            Cardlists[i].drawOneCard(((canvas.getWidth() - ((GameCard.CardWidth + 14) * Cardlists.length)) / 2) + ((GameCard.CardWidth + 15) * i), (canvas.getHeight() - GameCard.CardHeight) - 10, canvas, ct);
        }

        for (i = 0; i < Cardliste.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(Cardliste[i].CardNum));
            Cardliste[i].drawVerticalClosedCard((((canvas.getWidth() / 2) - 65) - 25) + (i * 10), 10, canvas, ct);
        }

        for (i = 0; i < Cardlistn.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(Cardlistn[i].CardNum));
            Cardlistn[i].drawHorinzantolClosedCard((canvas.getWidth() - GameCard.CardHeight) - 10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas, ct);
        }

        for (i = 0; i < Cardlistw.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(Cardlistw[i].CardNum));
            Cardlistw[i].drawHorinzantolClosedCard(10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas, ct);
        }

        this.ps.setPlayerCards(this.pcs);
        this.pe.setPlayerCards(this.pce);
        this.pn.setPlayerCards(this.pcn);
        this.pw.setPlayerCards(this.pcw);
        this.CardSet = this.pcs.AllCardsDeck();
        if (this.ps.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "ps";
            drawCrown(canvas, (float) (((canvas.getWidth() / 2) - 150) - 120), (float) ((canvas.getHeight() - GameCard.CardHeight - 50)));
            Toast.makeText(ct, "You Has The Seven of Heart", Toast.LENGTH_LONG).show();
        } else if (this.pe.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pe";
            drawCrown(canvas, (float) ((canvas.getWidth() - 80) + 20), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 80));
            Toast.makeText(ct, "Farid Has The Seven of Heart", Toast.LENGTH_LONG).show();
        } else if (this.pn.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pn";
            drawCrown(canvas, (float) ((canvas.getWidth() / 2) - 150), (float) (GameCard.CardHeight / 2));
            Toast.makeText(ct, "Maroun Has The Seven of Heart", Toast.LENGTH_LONG).show();
        } else if (this.pw.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pw";
            drawCrown(canvas, (float) ((GameCard.CardHeight / 2) - 5), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 80));
            Toast.makeText(ct, "Pascal Has The Seven of Heart", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(ct, "Error ", Toast.LENGTH_LONG).show();
        }
        if (this.pCardOnTable!= null){
        this.pCardOnTable.drawCardOntable(canvas,ct,0,0);

        }


    }

    public boolean onTouch(View view, MotionEvent event) {
        this.TouchedPoint = new Point();
        this.TouchedPoint.x = (int) event.getX();
        this.TouchedPoint.y = (int) event.getY();
        int x1 = this.TouchedPoint.x;
        int y1 = this.TouchedPoint.y;

        int j = 0;
        while (j < this.CardSet.length) {
            if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !CardSet[j].CardTouched) {
                CardSet[j].CardTouched = true;
                Log.println(Log.ERROR, "////////////////////", String.valueOf(CardSet[j].CardNum));
                Log.println(Log.ERROR, "////////////////////", String.valueOf(CardSet[j].CardType));
                this.pCardOnTable = CardSet[j];
                invalidate();
            }
            j++;
        }
        return true;
    }

}

