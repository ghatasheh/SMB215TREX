package com.smb215antonio450t.trexgame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;

public class GameMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_menu);

    }
}
