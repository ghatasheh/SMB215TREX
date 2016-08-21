package com.smb215antonio450t.trexgame;



/**
 * Created by user on 8/20/16.
 */
public class GamePlayer {

    public String name;
    public GamePlayerCards playerCards;
    public String shortName;
    int total;

    public GamePlayer(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
        this.total = 0;
    }
    public void setPlayerCards(GamePlayerCards pc) {
        this.playerCards = pc;
    }

    public boolean PlayerWithSevenOfHearts() {
        GameCard[] cards = this.playerCards.AllCardsDeck();
        int i = 0;
        while (i < cards.length) {
            if (cards[i].CardNum == 7 && cards[i].CardType == 'h') {
                return true;
            }
            i++;
        }
        return false;
    }
}
