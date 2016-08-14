package com.smb215antonio450t.trexgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/14/16.
 */
public class GamePlayerCards {
    int[] C = new int[15];
    int[] D = new int[15];
    int[] H = new int[15];
    int[] S = new int[15];
    List<GameCard> ClubList = new ArrayList();
    List<GameCard> DimondList = new ArrayList();
    List<GameCard> HeartList = new ArrayList();
    List<GameCard> SpadiList = new ArrayList();

    public GamePlayerCards() {
        for (int i = 0; i < 15; i++) {
            this.D[i] = 0;
            this.C[i] = 0;
            this.S[i] = 0;
            this.H[i] = 0;
        }
    }

    public void AddOneCard(GameCard card) {
        if (card.CardType == 'd') {
            this.DimondList.add(card);
            this.D[card.CardNum] = 2;
        }
        if (card.CardType == 'h') {
            this.HeartList.add(card);
            this.H[card.CardNum] = 2;
        }
        if (card.CardType == 's') {
            this.SpadiList.add(card);
            this.S[card.CardNum] = 2;
        }
        if (card.CardType == 'c') {
            this.ClubList.add(card);
            this.C[card.CardNum] = 2;
        }
    }

    public GameCard[] NotBurnedCards() {
        int i;
        List<GameCard> notBurned = new ArrayList();
        GameCard[] all = AllCardsDeck();
        for (i = 0; i < all.length; i++) {
            if (!all[i].IsBurned) {
                notBurned.add(all[i]);
            }
        }
        if (notBurned.size() <= 0) {
            return null;
        }
        GameCard[] m = new GameCard[notBurned.size()];
        for (i = 0; i < m.length; i++) {
            m[i] = (GameCard) notBurned.get(i);
        }
        return m;
    }
    public GameCard[] AllCardsDeck() {
        int i;
        GameCard[] all = new GameCard[13];
        int index = 0;
        this.ClubList = CardsSorting(this.ClubList);
        for (i = 0; i < this.ClubList.size(); i++) {
            all[index] = (GameCard) this.ClubList.get(i);
            index++;
        }
        this.DimondList = CardsSorting(this.DimondList);
        for (i = 0; i < this.DimondList.size(); i++) {
            all[index] = (GameCard) this.DimondList.get(i);
            index++;
        }
        this.SpadiList = CardsSorting(this.SpadiList);
        for (i = 0; i < this.SpadiList.size(); i++) {
            all[index] = (GameCard) this.SpadiList.get(i);
            index++;
        }
        this.HeartList = CardsSorting(this.HeartList);
        for (i = 0; i < this.HeartList.size(); i++) {
            all[index] = (GameCard) this.HeartList.get(i);
            index++;
        }
        return all;
    }

    public List<GameCard> CardsSorting(List<GameCard> m) {
        List<GameCard> list = m;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (((GameCard) list.get(i)).CardNum > ((GameCard) list.get(j)).CardNum) {
                    GameCard tmp = (GameCard) list.get(j);
                    list.set(j, (GameCard) list.get(i));
                    list.set(i, tmp);
                }
            }
        }
        return list;
    }
}