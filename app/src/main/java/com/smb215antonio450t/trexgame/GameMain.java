package com.smb215antonio450t.trexgame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
/*import android.view.Display;*/

/**
 * Created by user on 8/13/16.
 */
public class GameMain extends Activity{
    GameEngine complexGame;
    public static float density;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        density = getResources().getDisplayMetrics().density;
        Log.println(Log.ERROR, "Density:", String.valueOf(density));
        this.complexGame = new GameEngine(this);
        setContentView(this.complexGame);
        this.complexGame.requestFocus();

    }

}
