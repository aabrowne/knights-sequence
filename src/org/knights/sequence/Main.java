package org.knights.sequence;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * Main
 * Created by aabrowne on 8/2/2016.
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
        if (type == 1)
            System.out.println("Valid type: " + type);
        else if (type == 2)
            System.out.println("Valid type: " + type);
        else {
            System.err.println("Invalid type: " + type);
            return 1;
        }
        return getPaths(setValidMoves(new Keypad(), new HashMap<>(), type), 1, type, sequence);
    }

    private static Map<Object, Long> setValidMoves(Keypad keypad, Map<Object, Long> paths, Integer type) {
        for (Key key : keypad.getValidMoves()) {
            if (type == 1)
                paths.put(new Path(key, key.isVowel() ? 1:0), 1L);
            else
                paths.put(new HashPath(key, key.isVowel() ? 1:0), 1L);
        }
        return paths;
    }

    private static long getPaths(Map <Object, Long> paths , Integer length, Integer type, Integer sequence) {// without hashing, this runs out of memory at input of 10-13 (tested with 512m-2g heaps)
        long duration;
        long startTime = System.currentTimeMillis();// start time for process
        if (length >= sequence) {
            long numberOfPaths = 0;
            for (Long pathCount : paths.values()) {
                numberOfPaths += pathCount;
            }
            duration = System.currentTimeMillis() - startTime;// end time for process
            System.out.println("getPathsHashCode took " + new BigDecimal(duration).divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP) + " seconds to complete");
            return numberOfPaths;
        }
        long localDuration = System.currentTimeMillis();
        HashMap<Object, Long> memoization = new HashMap<>();// Use memoization
        for (Map.Entry<Object, Long> path : paths.entrySet()) {
            HashPath    pathWithHashCode = null;
            Path        pathWithoutHashCode = null;
            if (type == 1)
                pathWithoutHashCode = (Path) path.getKey();
            else
                pathWithHashCode = (HashPath) path.getKey();

            Long pathCount = path.getValue();

            Set<Key> possibleMoveSet;
            if (Objects.nonNull(pathWithoutHashCode))
                possibleMoveSet = pathWithoutHashCode.getKey().getMovesSet();
            else
                possibleMoveSet = pathWithHashCode.getKey().getMovesSet();

            for (Key key : possibleMoveSet) {
                Integer vowelCount;
                if (type == 1)
                    vowelCount = pathWithoutHashCode.getVowelCount();
                else
                    vowelCount = pathWithHashCode.getVowelCount();

                if (key.isVowel()){
                    if (vowelCount >= 2){// maxVowelCount = 2
                        continue;
                    }
                    vowelCount++;
                }
                if (type == 1)// Use memoization
                    memoization.merge(new Path(key, vowelCount), pathCount, Long::sum);
                else
                    memoization.merge(new HashPath(key, vowelCount), pathCount, Long::sum);
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
        return getPaths(memoization, length, type, sequence);
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
        System.out.println("#############################################");
        System.out.println("Bucket entry clash print: ");
        printClashes(bucketClashes);
        try {
            hashClashes = MapClashInspector.getHashClashDistribution(map);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
        assert hashClashes != null;
        System.out.println("---------------------------------------------");
        System.out.println("Hash entry clash print: ");
        printClashes(hashClashes);
        System.out.println("#############################################");
    }
}
