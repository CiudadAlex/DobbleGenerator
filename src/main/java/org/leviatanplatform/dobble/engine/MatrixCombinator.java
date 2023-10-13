package org.leviatanplatform.dobble.engine;

import java.util.ArrayList;
import java.util.List;

public class MatrixCombinator<T> {

    private final T[][] matrix;
    private final int dim;

    public MatrixCombinator(T[][] matrix) {

        this.matrix = matrix;
        this.dim = matrix.length;

        for (T[] row : matrix) {

            if (row.length != dim) {
                throw new IllegalArgumentException("Not corresponding dimensions: " + dim + " != " + row.length);
            }
        }
    }

    public List<List<T>> getHorizontal() {

        List<List<T>> result = buildNullResult();

        for (int r = 0; r < dim; r++) {

            for (int c = 0; c < dim; c++) {
                result.get(r).set(c, matrix[r][c]);
            }
        }

        return result;
    }

    public List<List<T>> getVertical() {

        List<List<T>> result = buildNullResult();

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                result.get(c).set(r, matrix[r][c]);
            }
        }

        return result;
    }

    public List<List<T>> getDiagonal1() {

        List<List<T>> result = buildNullResult();

        for (int i = 0; i < dim; i++) {
            for (int k = 0; k < dim; k++) {
                result.get(i).set(k, matrix[(k + i) % dim][k]);
            }
        }

        return result;
    }

    public List<List<T>> getDiagonal2() {

        List<List<T>> result = buildNullResult();

        for (int i = 0; i < dim; i++) {
            for (int k = 0; k < dim; k++) {
                result.get(i).set(k, matrix[(dim - 1 - k + i) % dim][k]);
            }
        }

        return result;
    }

    private List<List<T>> buildNullResult() {

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < dim; i++) {

            List<T> list = new ArrayList<>();
            result.add(list);

            for (int j = 0; j < dim; j++) {
                list.add(null);
            }
        }

        return result;
    }

    public static Integer[][] buildMatrixOfNumbers(int dim, int start) {

        Integer[][] matrix = new Integer[dim][dim];
        int index = start;

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
                matrix[r][c] = index++;
            }
        }

        return matrix;
    }
}
