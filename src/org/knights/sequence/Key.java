package org.knights.sequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Key
 * Created by aabrowne on 8/2/2016.
 *
 */
class Key implements Comparable<Character> {
    final private Character key;
    final private Boolean isVowel;
    private Set<Key> movesSet;

    Key(Character key, Boolean isVowel) {
        this.key = key;
        this.isVowel = isVowel;
    }

    Key setValidMovesHashSet(Key [] moves) {
        this.movesSet = new HashSet<>(Arrays.asList(moves));
        return this;
    }

    Set<Key> getMovesSet() {
        return movesSet;
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
            return this.key.equals(other.key) && this.isVowel == other.isVowel && this.movesSet.equals(other.movesSet);
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

