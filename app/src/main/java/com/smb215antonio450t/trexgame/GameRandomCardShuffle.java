package com.smb215antonio450t.trexgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 8/14/16.
 */
public class GameRandomCardShuffle {
    List<String> cardList = new ArrayList();
    Random random = new Random();

    public GameRandomCardShuffle() {
        int i;
        for (i = 2; i <= 14; i++) {
            this.cardList.add("c" + i);
        }
        for (i = 2; i <= 14; i++) {
            this.cardList.add("h" + i);
        }
        for (i = 2; i <= 14; i++) {
            this.cardList.add("s" + i);
        }
        for (i = 2; i <= 14; i++) {
            this.cardList.add("d" + i);
        }
    }

    public String getRandom() {
        String st = "";
        return (String) this.cardList.remove(this.random.nextInt(this.cardList.size()));
    }
}
