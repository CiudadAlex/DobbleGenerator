package org.leviatanplatform.dobble.engine;

import org.junit.jupiter.api.Test;


class MatrixCombinatorTest {

    @Test
    public void testGetHorizontal() {

        MatrixCombinator<Integer> matrixCombinator = new MatrixCombinator<>(MatrixCombinator.buildMatrixOfNumbers(3, 4));
        System.out.println(matrixCombinator.getHorizontal());
        System.out.println(matrixCombinator.getVertical());
        System.out.println(matrixCombinator.getDiagonal1());
        System.out.println(matrixCombinator.getDiagonal2());
    }


}