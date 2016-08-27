package com.smb215antonio450t.trexgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 8/14/16.
 */
public class GamePlayerCards {
    int[] C = new int[15];
    int[] D = new int[15];
    int[] H = new int[15];
    int[] S = new int[15];
    List<GameCard> ClubList = new ArrayList();
    List<GameCard> DiamondList = new ArrayList();
    List<GameCard> HeartList = new ArrayList();
    List<GameCard> SpadeList = new ArrayList();

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
            this.DiamondList.add(card);
            this.D[card.CardNum] = 2;
        }
        if (card.CardType == 'h') {
            this.HeartList.add(card);
            this.H[card.CardNum] = 2;
        }
        if (card.CardType == 's') {
            this.SpadeList.add(card);
            this.S[card.CardNum] = 2;
        }
        if (card.CardType == 'c') {
            this.ClubList.add(card);
            this.C[card.CardNum] = 2;
        }
    }

    public GameCard[] AllCardsDeck() {
        int i;
        GameCard[] all = new GameCard[13];
        int index = 0;
        this.ClubList = CardsSorting(this.ClubList);
        for (i = 0; i < this.ClubList.size(); i++) {
            all[index] = this.ClubList.get(i);
            index++;
        }
        this.DiamondList = CardsSorting(this.DiamondList);
        for (i = 0; i < this.DiamondList.size(); i++) {
            all[index] = this.DiamondList.get(i);
            index++;
        }
        this.SpadeList = CardsSorting(this.SpadeList);
        for (i = 0; i < this.SpadeList.size(); i++) {
            all[index] = this.SpadeList.get(i);
            index++;
        }
        this.HeartList = CardsSorting(this.HeartList);
        for (i = 0; i < this.HeartList.size(); i++) {
            all[index] = this.HeartList.get(i);
            index++;
        }
        return all;
    }

    public List<GameCard> CardsSorting(List<GameCard> m) {
        List<GameCard> list = m;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if ((list.get(i)).CardNum > (list.get(j)).CardNum) {
                    GameCard tmp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, tmp);
                }
            }
        }
        return list;
    }

    public boolean CardOutOfTheList(int[] list) {
        for (int i : list) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    public GameCard[] CardsNotBurned() {
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
            m[i] = notBurned.get(i);
        }
        return m;
    }

    public GameCard[] CardsNotBurnedAndNotOut() {
        int i;
        GameCard[] all = new GameCard[13];
        int index = 0;
        if (!CardOutOfTheList(this.C)) {
            this.ClubList = CardsSorting(this.ClubList);
            for (i = 0; i < this.ClubList.size(); i++) {
                all[index] = this.ClubList.get(i);
                index++;
            }
        }
        if (!CardOutOfTheList(this.D)) {
            this.DiamondList = CardsSorting(this.DiamondList);
            for (i = 0; i < this.DiamondList.size(); i++) {
                all[index] = this.DiamondList.get(i);
                index++;
            }
        }
        if (!CardOutOfTheList(this.S)) {
            this.SpadeList = CardsSorting(this.SpadeList);
            for (i = 0; i < this.SpadeList.size(); i++) {
                all[index] = this.SpadeList.get(i);
                index++;
            }
        }
        if (!CardOutOfTheList(this.H)) {
            this.HeartList = CardsSorting(this.HeartList);
            for (i = 0; i < this.HeartList.size(); i++) {
                all[index] = this.HeartList.get(i);
                index++;
            }
        }
        List<GameCard> notBurned = new ArrayList();
        i = 0;
        while (i < index) {
            if (!all[i].IsBurned && all[i].CardNum < 12) {
                notBurned.add(all[i]);
            }
            i++;
        }
        if (notBurned.size() <= 0) {
            return null;
        }
        GameCard[] m = new GameCard[notBurned.size()];
        for (i = 0; i < m.length; i++) {
            m[i] = notBurned.get(i);
        }
        return m;
    }

    public GameCard OneRandomCard() {
        GameCard[] all = CardsNotBurnedAndNotOut();
        if (all == null) {
            all = CardsNotBurned();
        }
        if (all != null) {
            Random random = new Random();
            if (all.length > 0) {
                return all[random.nextInt(all.length)];
            }
        }
        return null;
    }

    public List<GameCard> CardNotBurnedOfTheSameType(char type) {
        List<GameCard> tmpList = null;
        List<GameCard> returnedList = new ArrayList();
        if (type == 'd') {
            tmpList = this.DiamondList;
        }
        if (type == 'c') {
            tmpList = this.ClubList;
        }
        if (type == 'h') {
            tmpList = this.HeartList;
        }
        if (type == 's') {
            tmpList = this.SpadeList;
        }
        if (tmpList != null) {
            for (int i = 0; i < tmpList.size(); i++) {
                if (!(tmpList.get(i)).IsBurned) {
                    returnedList.add(tmpList.get(i));
                }
            }
        }
        return returnedList;
    }

    public GameCard CardValidOfSameType(GameCard fc) {
        if (fc == null) {
            return null;
        }
        List<GameCard> sameTypeList = CardNotBurnedOfTheSameType(fc.CardType);
        Random random = new Random();
        if (sameTypeList.size() > 0) {

            return sameTypeList.get(random.nextInt(sameTypeList.size()));
        }

        GameCard[] all = CardsNotBurned();
        return all[random.nextInt(all.length)];
    }

    public boolean CardValid(GameCard fc, GameCard tc) {
        if (tc.CardType == fc.CardType) {
            return true;
        }
        GameCard[] all = CardsNotBurned();
        for (GameCard card : all) {
            if (card.CardType == fc.CardType) {
                return false;
            }
        }
        return true;
    }
}