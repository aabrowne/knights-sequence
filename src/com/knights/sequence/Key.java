package com.knights.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Key
 * Created by BrowneA on 8/2/2016.
 *
 */
class Key implements Comparable<Character> {
    final private Character key;
    final private Boolean isVowel;
    private ArrayList<Key> movesList;
    private Set<Key> movesSet;

    Key(Character key, Boolean isVowel) {
        this.key = key;
        this.isVowel = isVowel;
    }

    Key setValidMovesArrayList(Key [] moves) {
        this.movesList = new ArrayList<>(Arrays.asList(moves));
        return this;
    }

    Key setValidMovesHashSet(Key [] moves) {
        this.movesSet = new HashSet<>(Arrays.asList(moves));
        return this;
    }

    Set<Key> getMovesSet() {
        return movesSet;
    }

//    ArrayList<Key> getMovesList() {
//        return movesList;
//    }

    @SuppressWarnings("unused")
    char getKey() {
        return key;
    }

    Boolean isVowel() {
        return isVowel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Key)){
            return false;
        }
        else {
            Key other = (Key) o;
            return this.key.equals(other.key) && this.isVowel == other.isVowel && this.movesList.equals(other.movesList) && this.movesSet.equals(other.movesSet);
        }
    }

    @Override
    public int compareTo(Character key) {
        if (key == null){
            return -1;
        }
        else {
            return this.key.compareTo(key);
        }
    }
}

