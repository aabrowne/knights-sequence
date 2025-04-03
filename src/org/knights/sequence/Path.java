package org.knights.sequence;

/**
 *
 * Path
 * Created by aabrowne on 8/2/2016.
 *
 */
class Path implements Comparable<Character> {
    final private Key key;
    final private Integer vowelCount;

    Path(Key key, Integer vowelCount) {
        this.key = key;
        this.vowelCount = vowelCount;
    }

    Key getKey() {
        return key;
    }

    int getVowelCount() {
        return vowelCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Path)){
            return false;
        }
        else {
            Path other = (Path) o;
            return this.key.equals(other.key) && this.vowelCount.equals(other.vowelCount);
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
