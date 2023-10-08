package org.leviatanplatform.dobble.engine.util;

import java.util.function.Function;

/**
 * SortCriteria.
 * 
 * @author Alejandro
 * 
 */
public class SortCriteria<T, C extends Comparable<C>> {

    private final Function<T, C> comparableExtractor;
    private final boolean asc;
    private final boolean nullBiggest;

    /**
     * Constructor for SortCriteria. Ascending and null biggest.
     * 
     * @param comparableExtractor
     *            comparableExtractor
     */
    public SortCriteria(final Function<T, C> comparableExtractor) {
        this(comparableExtractor, true, true);
    }

    /**
     * Constructor for SortCriteria.
     * 
     * @param comparableExtractor
     *            comparableExtractor
     * @param asc
     *            asc
     * @param nullBiggest
     *            nullBiggest
     */
    public SortCriteria(final Function<T, C> comparableExtractor, final boolean asc, final boolean nullBiggest) {
        this.comparableExtractor = comparableExtractor;
        this.asc = asc;
        this.nullBiggest = nullBiggest;
    }

    public Function<T, C> getComparableExtractor() {
        return this.comparableExtractor;
    }

    public boolean isAsc() {
        return this.asc;
    }

    public boolean isNullBiggest() {
        return this.nullBiggest;
    }

    /**
     * Creates a new SortCriteria.
     * 
     * @param <T>
     *            type
     * @param <C>
     *            Comparable
     * @param comparableExtractor
     *            comparableExtractor
     * @param asc
     *            asc
     * @param nullBiggest
     *            nullBiggest
     * @return a new SortCriteria
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T, C extends Comparable<C>> SortCriteria<T, ? extends Comparable<?>> newInstance(
            final Function<T, ? extends Comparable<?>> comparableExtractor, final boolean asc, final boolean nullBiggest) {

        return new SortCriteria(comparableExtractor, asc, nullBiggest);
    }

}
