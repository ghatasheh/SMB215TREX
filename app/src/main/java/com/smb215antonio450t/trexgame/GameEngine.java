package com.smb215antonio450t.trexgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    /*GameCard pCardOnTable = null;*/
    String Nextturn;
    boolean pec = false;
    boolean pnc = false;
    boolean pwc = false;
    List<GameCard> CardsOnTableList;
    String PlayerRoundOwner = "";
    boolean PlayerShouldPlay = false;
    GameCard PECardOnTable = null;
    GameCard PNCardOnTable = null;
    GameCard PWCardOnTable = null;
    GameCard PSCardOnTable = null;
    double GSpeed = 1.0d;
    boolean EndOfPlayRound = false;
    boolean RequestNewRound = false;
    boolean AllPlayersFinished = false;
    int PositionCounterX = 0;
    int PositionCounterY = 0;


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
        distributeAllCards();
        startInitialRound();
        this.CardsOnTableList = new ArrayList();
        ComplexGameLoop();

    }

    public void drawCrown(Canvas canvas, float x, float y) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(ct.getResources(), this.imgCrown), 30, 30, true), x, y, null);
    }

    public void distributeAllCards() {

        this.randomCard = new GameRandomCardShuffle();
        this.pcs = new GamePlayerCards();
        this.pce = new GamePlayerCards();
        this.pcn = new GamePlayerCards();
        this.pcw = new GamePlayerCards();
        String st2 = "";
        for (int i = 0; i < 13; i++) {
            String st = this.randomCard.getRandom();
            this.pcs.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0), 0));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pce.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0), 1));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pcn.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0), 2));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
            st = this.randomCard.getRandom();
            this.pcw.AddOneCard(new GameCard(st.trim().substring(1), st.charAt(0), 3));
            st2 = new StringBuilder(String.valueOf(st2)).append(" ").append(st).toString();
        }
        this.ps.setPlayerCards(this.pcs);
        this.pe.setPlayerCards(this.pce);
        this.pn.setPlayerCards(this.pcn);
        this.pw.setPlayerCards(this.pcw);
        this.CardSet = this.pcs.AllCardsDeck();

    }

    public void startInitialRound() {
        if (this.ps.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "ps";
            this.Nextturn = "pe";
        } else if (this.pe.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pe";
            this.Nextturn = "pn";
        } else if (this.pn.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pn";
            this.Nextturn = "pw";
        } else if (this.pw.PlayerWithSevenOfHearts()) {
            this.CurrentTurn = "pw";
            this.Nextturn = "ps";
        } else {
            Toast.makeText(this.ct, "Error ", Toast.LENGTH_LONG).show();
        }


    }

    public void PauseForPCE(final int SleepTime, final boolean EndOfRound) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep((long) SleepTime);
                } catch (InterruptedException e) {
                }
                return null;
            }

            protected void onPostExecute(Void result) {

                GameEngine.this.pec = true;
                if (EndOfRound) {
                    GameEngine.this.EndOfPlayRound = true;
                    GameEngine.this.RequestNewRound = true;
                    GameEngine.this.invalidate();
                    GameEngine.this.PositionCounterX = 0;
                    GameEngine.this.AllPlayersFinished = true;
                    return;
                }
                GameEngine.this.invalidate();
                return;
            }
        }.execute();
    }

    public void PauseForPCN(final int SleepTime, final boolean EndOfRound) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep((long) SleepTime);
                } catch (InterruptedException e) {
                }
                return null;
            }

            protected void onPostExecute(Void result) {

                GameEngine.this.pnc = true;
                if (EndOfRound) {
                    GameEngine.this.EndOfPlayRound = true;
                    GameEngine.this.RequestNewRound = true;
                    GameEngine.this.invalidate();
                    GameEngine.this.PositionCounterX = 0;
                    GameEngine.this.PositionCounterY = 0;
                    GameEngine.this.AllPlayersFinished = true;
                    return;
                }
                GameEngine.this.invalidate();
                return;
            }
        }.execute();
    }

    public void PauseForPCW(final int SleepTime, final boolean EndOfRound) {
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep((long) SleepTime);
                } catch (InterruptedException e) {
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                GameEngine.this.pwc = true;
                if (EndOfRound) {
                    GameEngine.this.EndOfPlayRound = true;
                    GameEngine.this.RequestNewRound = true;
                    GameEngine.this.invalidate();
                    GameEngine.this.PositionCounterX = 0;
                    GameEngine.this.AllPlayersFinished = true;
                    return;
                }
                GameEngine.this.invalidate();
                return;
            }

        }.execute();
    }

    public void playOneRound(String player) {

        this.pec = false;
        this.pnc = false;
        this.pwc = false;
        this.CardsOnTableList = new ArrayList();
        if (player.compareTo("ps") == 0) {
            this.PlayerRoundOwner = "ps";
            this.PlayerShouldPlay = true;
        }
        if (player.compareTo("pe") == 0) {
            this.PlayerRoundOwner = "pe";
            this.PECardOnTable = this.pce.OneRandomCard();
            this.CardsOnTableList.add(this.PECardOnTable);
            this.PNCardOnTable = this.pcn.CardValidOfSameType(this.PECardOnTable);
            this.CardsOnTableList.add(this.PNCardOnTable);
            this.PWCardOnTable = this.pcw.CardValidOfSameType(this.PECardOnTable);
            this.CardsOnTableList.add(this.PWCardOnTable);
            PauseForPCE((int) (this.GSpeed * 500.0d), false);
            PauseForPCN((int) (this.GSpeed * 2000.0d), false);
            PauseForPCW((int) (3000.0d * this.GSpeed), false);
            this.PlayerShouldPlay = true;
        }
        if (player.compareTo("pn") == 0) {
            this.PlayerRoundOwner = "pn";
            this.PNCardOnTable = this.pcn.OneRandomCard();
            this.CardsOnTableList.add(this.PNCardOnTable);
            this.PWCardOnTable = this.pcw.CardValidOfSameType(this.PNCardOnTable);
            this.CardsOnTableList.add(this.PWCardOnTable);
            PauseForPCN((int) (this.GSpeed * 500.0d), false);
            PauseForPCW((int) (this.GSpeed * 2000.0d), false);
            this.PlayerShouldPlay = true;
        }
        if (player.compareTo("pw") == 0) {
            this.PlayerRoundOwner = "pw";
            this.PWCardOnTable = this.pcw.OneRandomCard();
            this.CardsOnTableList.add(this.PWCardOnTable);
            PauseForPCW((int) (this.GSpeed * 500.0d), false);
            this.PlayerShouldPlay = true;
        }
    }

    public void ComplexGameLoop() {
        invalidate();
        if (this.CurrentTurn.compareTo("pe") == 0) {
            playOneRound("pe");
        } else if (this.CurrentTurn.compareTo("pn") == 0) {
            playOneRound("pn");
        } else if (this.CurrentTurn.compareTo("pw") == 0) {
            playOneRound("pw");
        } else if (this.CurrentTurn.compareTo("ps") == 0) {
            playOneRound("ps");
        }
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        this.bm = Bitmap.createScaledBitmap(this.bm, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(this.bm, 0.0f, 0.0f, null);

        GameCard[] Cardlists;
        GameCard[] Cardliste;
        GameCard[] Cardlistn;
        GameCard[] Cardlistw;

        Cardlists = this.pcs.CardsNotBurned();
        Cardliste = this.pce.CardsNotBurned();
        Cardlistn = this.pcn.CardsNotBurned();
        Cardlistw = this.pcw.CardsNotBurned();

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
            Cardlistn[i].drawHorinzontalClosedCard((canvas.getWidth() - GameCard.CardHeight) - 10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas, ct);
        }

        for (i = 0; i < Cardlistw.length; i++) {
            Log.println(Log.ERROR, "++++++++++++++++++++", String.valueOf(Cardlistw[i].CardNum));
            Cardlistw[i].drawHorinzontalClosedCard(10, (((canvas.getHeight() / 2) - 65) - 35) + (i * 10), canvas, ct);
        }

        /*---------------*/

        if (this.CurrentTurn.compareTo("ps") == 0) {
            drawCrown(canvas, (float) (((canvas.getWidth() / 2) - 150) - 120), (float) ((canvas.getHeight() - GameCard.CardHeight - 50)));
            Toast.makeText(ct, "You Has The Seven of Heart", Toast.LENGTH_LONG).show();
        }
        if (this.CurrentTurn.compareTo("pe") == 0) {
            drawCrown(canvas, (float) ((canvas.getWidth() - 80) + 20), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 80));
            Toast.makeText(ct, "Farid Has The Seven of Heart", Toast.LENGTH_LONG).show();
        }
        if (this.CurrentTurn.compareTo("pn") == 0) {
            drawCrown(canvas, (float) ((canvas.getWidth() / 2) - 150), (float) (GameCard.CardHeight / 2));
            Toast.makeText(ct, "Maroun Has The Seven of Heart", Toast.LENGTH_LONG).show();
        }
        if (this.CurrentTurn.compareTo("pw") == 0) {
            drawCrown(canvas, (float) ((GameCard.CardHeight / 2) - 5), (float) ((((canvas.getHeight() / 2) - 65) - 50) - 80));
            Toast.makeText(ct, "Pascal Has The Seven of Heart", Toast.LENGTH_LONG).show();
        }

        if (this.PSCardOnTable != null) {
            this.PSCardOnTable.drawCardOntable(canvas, ct, 0, 0);
        }

        if (this.PNCardOnTable != null) {
            this.PNCardOnTable.drawCardOntable(canvas, ct, 0, 0);
        }
        if (this.PECardOnTable != null) {
            this.PECardOnTable.drawCardOntable(canvas, ct, 0, 0);
        }
        if (this.PWCardOnTable != null) {
            this.PWCardOnTable.drawCardOntable(canvas, ct, 0, 0);
        }


    }

    public boolean onTouch(View view, MotionEvent event) {
        this.TouchedPoint = new Point();
        this.TouchedPoint.x = (int) event.getX();
        this.TouchedPoint.y = (int) event.getY();
        int x1 = this.TouchedPoint.x;
        int y1 = this.TouchedPoint.y;

        /*int j = 0;
        while (j < this.CardSet.length) {
            if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !CardSet[j].CardTouched) {
                CardSet[j].CardTouched = true;
                Log.println(Log.ERROR, "////////////////////", String.valueOf(CardSet[j].CardNum));
                Log.println(Log.ERROR, "////////////////////", String.valueOf(CardSet[j].CardType));
                this.PSCardOnTable = CardSet[j];
                invalidate();
            }
            j++;
        }*/

        if (this.PlayerShouldPlay && this.PlayerRoundOwner.compareTo("ps") == 0) {
            int j = 0;
            while (j < this.CardSet.length) {
                if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !this.CardSet[j].CardTouched) {
                    this.PSCardOnTable = this.CardSet[j];
                    this.CardsOnTableList.add(this.PSCardOnTable);
                    this.CardSet[j].CardTouched = true;
                    this.PSCardOnTable.CardTouched = true;
                    this.PSCardOnTable.IsBurned = true;
                    this.PlayerShouldPlay = false;
                    invalidate();
                    this.PECardOnTable = this.pce.CardValidOfSameType(this.PSCardOnTable);
                    this.CardsOnTableList.add(this.PECardOnTable);
                    this.PNCardOnTable = this.pcn.CardValidOfSameType(this.PSCardOnTable);
                    this.CardsOnTableList.add(this.PNCardOnTable);
                    this.PWCardOnTable = this.pcw.CardValidOfSameType(this.PSCardOnTable);
                    this.CardsOnTableList.add(this.PWCardOnTable);
                    PauseForPCE((int) (500.0d * this.GSpeed), false);
                    PauseForPCN((int) (2000.0d * this.GSpeed), false);
                    PauseForPCW((int) (3000.0d * this.GSpeed), true);
                }
                j++;
            }
        }
        if (this.PlayerShouldPlay && this.PlayerRoundOwner.compareTo("pe") == 0 && this.pwc) {
            int j = 0;
            while (j < this.CardSet.length) {
                if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !this.CardSet[j].CardTouched && this.pcs.CardValid(this.PECardOnTable, this.CardSet[j])) {
                    this.PSCardOnTable = this.CardSet[j];
                    this.CardsOnTableList.add(this.PSCardOnTable);
                    this.CardSet[j].CardTouched = true;
                    this.PSCardOnTable.CardTouched = true;
                    this.PSCardOnTable.IsBurned = true;
                    this.PlayerShouldPlay = false;
                    invalidate();
                    this.EndOfPlayRound = true;
                    this.RequestNewRound = true;
                    this.PositionCounterX = 0;
                    this.AllPlayersFinished = true;
                    j++;
                }
            }
        }
        if (this.PlayerShouldPlay && this.PlayerRoundOwner.compareTo("pn") == 0 && this.pwc) {
            int j = 0;
            while (j < this.CardSet.length) {
                if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !this.CardSet[j].CardTouched && this.pcs.CardValid(this.PNCardOnTable, this.CardSet[j])) {
                    this.PSCardOnTable = this.CardSet[j];
                    this.CardsOnTableList.add(this.PSCardOnTable);
                    this.CardSet[j].CardTouched = true;
                    this.PSCardOnTable.CardTouched = true;
                    this.PSCardOnTable.IsBurned = true;
                    this.PlayerShouldPlay = false;
                    invalidate();
                    this.PECardOnTable = this.pce.CardValidOfSameType(this.PNCardOnTable);
                    this.CardsOnTableList.add(this.PECardOnTable);
                    PauseForPCE((int) (500.0d * this.GSpeed), true);
                    invalidate();
                }
                j++;
            }
        }
        if (this.PlayerShouldPlay && this.PlayerRoundOwner.compareTo("pw") == 0 && this.pwc) {
            int j = 0;
            while (j < this.CardSet.length) {
                if (this.CardSet[j].checkIfCardIsClicked((float) x1, (float) y1) && !this.CardSet[j].CardTouched && this.pcs.CardValid(this.PWCardOnTable, this.CardSet[j])) {
                    this.PSCardOnTable = this.CardSet[j];
                    this.CardsOnTableList.add(this.PSCardOnTable);
                    this.CardSet[j].CardTouched = true;
                    this.PSCardOnTable.CardTouched = true;
                    this.PSCardOnTable.IsBurned = true;
                    this.PlayerShouldPlay = false;
                    invalidate();
                    this.PECardOnTable = this.pce.CardValidOfSameType(this.PWCardOnTable);
                    this.CardsOnTableList.add(this.PECardOnTable);
                    this.PNCardOnTable = this.pcn.CardValidOfSameType(this.PWCardOnTable);
                    this.CardsOnTableList.add(this.PNCardOnTable);
                    PauseForPCE((int) (500.0d * this.GSpeed), false);
                    PauseForPCN((int) (2000.0d * this.GSpeed), true);
                    invalidate();
                }
                j++;
            }
        }

        return true;
    }
}



