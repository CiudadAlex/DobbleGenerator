package org.leviatanplatform.dobble.engine.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * SortUtils.
 * 
 * @author Alejandro
 * 
 */
public final class SortUtils {

    private SortUtils() {
    }

    /**
     * Sorts the collection.
     * 
     * @param <T>
     *            type
     * @param list
     *            list
     * @param arraySortCriteria
     *            arraySortCriteria
     */
    @SafeVarargs
    public static <T> void sort(final List<T> list, final SortCriteria<T, ?>... arraySortCriteria) {

        final Comparator<T> comparator = new Comparator<T>() {

            @Override
            public int compare(final T o1, final T o2) {

                for (final SortCriteria<T, ?> sortCriteria : arraySortCriteria) {

                    final int comparison = compareForSort(o1, o2, sortCriteria);

                    if (comparison != 0) {
                        return comparison;
                    }
                }

                return 0;
            }

        };

        Collections.sort(list, comparator);
    }

    private static <T, C extends Comparable<C>> int compareForSort(final T o1, final T o2, final SortCriteria<T, C> sortCriteria) {

        final Function<T, C> comparableExtractor = sortCriteria.getComparableExtractor();
        final boolean asc = sortCriteria.isAsc();
        final boolean isNullBiggest = sortCriteria.isNullBiggest();

        final C c1 = comparableExtractor.apply(o1);
        final C c2 = comparableExtractor.apply(o2);

        final int comparation;

        if (c1 == null) {
            comparation = isNullBiggest ? 1 : -1;
        } else if (c2 == null) {
            comparation = isNullBiggest ? -1 : 1;
        } else {
            comparation = c1.compareTo(c2);
        }

        final int comparationWithAsc = asc ? comparation : -comparation;

        return comparationWithAsc;
    }

    /**
     * Sorts the collection.
     * 
     * @param <T>
     *            type
     * @param <C>
     *            Comparable
     * @param list
     *            list
     * @param arraySortCriteria
     *            arraySortCriteria
     * @return the new sorted list
     */
    @SafeVarargs
    public static <T, C extends Comparable<C>> List<T> sortInOtherList(final List<T> list, final SortCriteria<T, C>... arraySortCriteria) {

        final List<T> listNew = new ArrayList<T>();
        listNew.addAll(list);

        sort(listNew, arraySortCriteria);

        return listNew;
    }
}
