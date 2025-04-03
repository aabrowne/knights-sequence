package org.knights.sequence;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * Main
 * Created by BrowneA on 8/2/2016.
 *
 */
public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss:SS z", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("CST"));
        long duration;
        long startTime = System.currentTimeMillis();// start time for process
        System.out.println("starting at: " + sdf.format(startTime));
        for (Integer n = 0; n <= new Integer(args[1]); n++) {
            long validSequences = getSequence(n, new Integer(args[0]));
            System.out.println("for n=" + n + ", number of valid " + n + "-key sequences is: " + validSequences);
        }
        duration = System.currentTimeMillis() - startTime;// end time for process
        System.out.println("Took " + new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP) + " seconds to complete");
        System.out.println("done at: " + sdf.format(System.currentTimeMillis()));
    }

    private static long getSequence(Integer sequence, Integer type) {
        if (sequence <= 0){
            return 1;
        }
        Keypad keypad = new Keypad();
        switch(type) {
            case 1: {
                Map<Path, Long> paths = new HashMap<>();
                for (Key key : keypad.getKeysHashSet()) {
                    int vowelCount;
                    if (key.isVowel()) {
                        vowelCount = 1;
                    }
                    else {
                        vowelCount = 0;
                    }
                    Path pathWithHashCode = new Path(key, vowelCount);
                    paths.put(pathWithHashCode, 1L);
                }
                return getPathWithoutHashCode(paths, 1, sequence);
            }
            case 2: {
                Map<HashPath, Long> paths = new HashMap<>();
                for (Key key : keypad.getKeysHashSet()) {
                    int vowelCount;
                    if (key.isVowel()) {
                        vowelCount = 1;
                    }
                    else {
                        vowelCount = 0;
                    }
                    HashPath hashPath = new HashPath(key, vowelCount);
                    paths.put(hashPath, 1L);
                }
                return getPathWithHashCode(paths, 1, sequence);
            }
            default: {
                return 1;
            }
        }
    }

    private static long getPathWithoutHashCode(Map <Path, Long> paths , int length, int sequence) {// without hashing, this runs out of memory at input of 10-13 (tested with 512m-2g heaps)
        long duration;
        long startTime = System.currentTimeMillis();// start time for process
        if (length >= sequence){
            long numberOfPaths = 0;
            for (Long pathCount : paths.values()) {
                numberOfPaths += pathCount;
            }
            duration = System.currentTimeMillis() - startTime;// end time for process
            System.out.println("getPathWithoutHashCode took " + new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP) + " seconds to complete");
            return numberOfPaths;
        }
        long localDuration = System.currentTimeMillis();
        HashMap<Path, Long> memoization = new HashMap<>();// use memoization
        for (Map.Entry<Path, Long> path : paths.entrySet()) {
            Path pathWithoutHashCode = path.getKey();
            Long pathCount = path.getValue();
            Set<Key> possibleMoveSet = path.getKey().getKey().getMovesSet();
            for (Key key : possibleMoveSet) {
                int vowelCount = pathWithoutHashCode.getVowelCount();
                if (key.isVowel()){
                    if (vowelCount >= 2){// maxVowelCount = 2
                        continue;
                    }
                    vowelCount++;
                }
                Path newPathWithoutHashCode = new Path(key, vowelCount);
                // save newPath in the memoization map
                memoization.merge(newPathWithoutHashCode, pathCount, Long::sum);
            }
        }
        duration = System.currentTimeMillis() - localDuration;// end time for process
        BigDecimal interval = new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_DOWN);
        if (interval.compareTo(new BigDecimal(1)) > 0) {
            System.out.println("iteration took " + interval + " seconds to complete");
        }
        length++;
        if (length >= sequence) {
            printMap(memoization);
        }
        return getPathWithoutHashCode(memoization, length, sequence);
    }

    private static long getPathWithHashCode(Map <HashPath, Long> paths , int length, int sequence) {
        long duration;
        long startTime = System.currentTimeMillis();// start time for process
        if (length >= sequence){
            long numberOfPaths = 0;
            for (Long pathCount : paths.values()) {
                numberOfPaths += pathCount;
            }
            duration = System.currentTimeMillis() - startTime;// end time for process
            System.out.println("getPathWithHashCode took " + new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP) + " seconds to complete");
            return numberOfPaths;
        }
        long localDuration = System.currentTimeMillis();
        HashMap<HashPath, Long> memoization = new HashMap<>();// use memoization with hash
        for (Map.Entry<HashPath, Long> counts : paths.entrySet()) {
            HashPath hashPath = counts.getKey();
            Long pathCount = counts.getValue();
            for (Key key : counts.getKey().getKey().getMovesSet()) {
                int vowelCount = hashPath.getVowelCount();
                if (key.isVowel()){
                    if (vowelCount >= 2){// maxVowelCount = 2
                        continue;
                    }
                    vowelCount++;
                }
                HashPath newHashPath = new HashPath(key, vowelCount);
                // use memoization
                memoization.merge(newHashPath, pathCount, Long::sum);
            }
        }
        duration = System.currentTimeMillis() - localDuration;// end time for process
        BigDecimal interval = new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_DOWN);
        if (interval.compareTo(new BigDecimal(1)) > 0) {
            System.out.println("iteration took " + interval + " seconds to complete");
        }
        length++;
        if (length >= sequence) {
            printMap(memoization);
        }
        return getPathWithHashCode(memoization, length, sequence);
    }

    private static void printClashes(Map<Integer, Integer> clashes) {
        for (Map.Entry<Integer, Integer> e : clashes.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    private static void printMap(HashMap<?,?> map) {
        Map<Integer, Integer> bucketClashes = null;
        Map<Integer, Integer> hashClashes = null;
        try {
            bucketClashes = MapClashInspector.getBucketClashDistribution(map);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        assert bucketClashes != null;
        System.out.println("Bucket entry clash print: ");
        printClashes(bucketClashes);
        try {
            hashClashes = MapClashInspector.getHashClashDistribution(map);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        assert hashClashes != null;
        System.out.println("Hash entry clash print: ");
        printClashes(hashClashes);
    }
}
