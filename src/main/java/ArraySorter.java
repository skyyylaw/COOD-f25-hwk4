import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ArraySorter {

    /**
     * Sort an array in place and perform the same reordering of entries on
     * other arrays.  
     *
     * @param pattern Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param ascending whether the values should be sorted in ascending (increasing) order
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on the pattern array.
     * @return whether sorting was applied to the yList arrays
     */
    public static boolean sortInPlace(double[] pattern,
                                   boolean ascending,
                                   double[] ... yList) {

        // Consistency checks.
        if (pattern == null) {
            return false;
        }

        final int yListLen = yList.length;
        final int len = pattern.length;

        for (int j = 0; j < yListLen; j++) {
            final double[] y = yList[j];
            if (y == null) {
                return false;
            }
            if (y.length != len) {
                return false;
            }
        }

        // Associate each value "pattern[i]" with its index "i".
        final List<PairDoubleInteger> list = new ArrayList<PairDoubleInteger>(len);
        for (int i = 0; i < len; i++) {
            list.add(new PairDoubleInteger(pattern[i], i));
        }

        // Create comparators for increasing and decreasing orders.
        final Comparator<PairDoubleInteger> comp
                = ascending ?
                new Comparator<PairDoubleInteger>() {

                    public int compare(PairDoubleInteger o1,
                                       PairDoubleInteger o2) {
                        return Double.compare(o1.getKey(), o2.getKey());
                    }
                } : new Comparator<PairDoubleInteger>() {

            public int compare(PairDoubleInteger o1,
                               PairDoubleInteger o2) {
                return Double.compare(o2.getKey(), o1.getKey());
            }
        };

        // Sort.
        Collections.sort(list, comp);

        // Modify the original array so that its elements are in
        // the prescribed order.
        // Retrieve indices of original locations.
        final int[] indices = new int[len];
        for (int i = 0; i < len; i++) {
            final PairDoubleInteger e = list.get(i);
            pattern[i] = e.getKey();
            indices[i] = e.getValue();
        }

        // In each of the associated arrays, move the
        // elements to their new location.
        for (int j = 0; j < yListLen; j++) {
            // Input array will be modified in place.
            final double[] yInPlace = yList[j];
            final double[] yOrig = yInPlace.clone();

            for (int i = 0; i < len; i++) {
                yInPlace[i] = yOrig[indices[i]];
            }
        }
        return true;
    }

    /**
     * A helper data structure holding a double and an integer value.
     */
    public static class PairDoubleInteger {
        /** Key */
        private final double key;
        /** Value */
        private final int value;

        /**
         * @param key Key.
         * @param value Value.
         */
        PairDoubleInteger(double key, int value) {
            this.key = key;
            this.value = value;
        }

        /** @return the key. */
        public double getKey() {
            return key;
        }

        /** @return the value. */
        public int getValue() {
            return value;
        }
    }

}