package com.kcg;

/**
 *
 * HashPath
 *
 * Created by BrowneA on 8/7/2016.
 *
 */
class HashPath implements Comparable {
    final private Key key;
    private Integer vowelCount;

    HashPath(Key key, Integer vowelCount) {
        this.key = key;
        this.vowelCount = vowelCount;
    }

    Key getKey() {
        return key;
    }

    Integer getVowelCount() {
        return vowelCount;
    }

    @Override
    public int hashCode() {
        Integer result = 17;
        Integer keyHash;
        if (key == null) {
            keyHash = 0;
        }
        else {
            keyHash = key.hashCode();
        }
        result = 37 * result + keyHash;
        result = 37 * result + vowelCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof HashPath)){
            return false;
        }
        else {
            HashPath other = (HashPath) obj;
            return !(key != null && !key.equals(other.key)) && vowelCount.equals(other.vowelCount);
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof HashPath)){
            return -1;
        }
        else {
            return this.key.compareTo(o);
        }
    }
}
