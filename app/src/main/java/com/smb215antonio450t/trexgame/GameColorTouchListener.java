package com.smb215antonio450t.trexgame;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/*import android.util.Log;*/

/**
 * Created by user on 8/13/16.
 */
public class GameColorTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                ((TextView) view).setTextColor(Color.GREEN);
                /*Log.println(Log.ERROR,"TEST",String.valueOf(motionEvent.getAction()));*/
                break;
            case 1:
                ((TextView) view).setTextColor(Color.WHITE);
                /*Log.println(Log.ERROR,"TEST",String.valueOf(motionEvent.getAction()));*/
                break;
            case 2:
                ((TextView) view).setTextColor(Color.GREEN);
                /*Log.println(Log.ERROR,"TEST",String.valueOf(motionEvent.getAction()));*/
                break;

        }
        return false;
    }
}
