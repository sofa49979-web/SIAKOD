package com.siakod.matrix;

import org.junit.jupiter.api.Test;

class MatrixMultiplierTest {

    private static final double EPS = 1e-9;

    private static void assertMatrixEquals(double[][] expected, double[][] actual) {
        org.junit.jupiter.api.Assertions.assertEquals(
                expected.length, actual.length, "rows count mismatch");
        for (int i = 0; i < expected.length; i++) {
            org.junit.jupiter.api.Assertions.assertArrayEquals(
                    expected[i], actual[i], EPS,
                    "row " + i + " mismatch");
        }
    }

    @Test
    void multiplyPlain_2x2_returnsCorrectResult() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}, {7, 8}};
        double[][] expected = {{19, 22}, {43, 50}};
        assertMatrixEquals(expected, MatrixMultiplier.multiplyPlain(a, b));
    }

    @Test
    void multiplyPlain_3x3_identity_returnsSameMatrix() {
        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] identity = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        assertMatrixEquals(a, MatrixMultiplier.multiplyPlain(a, identity));
        assertMatrixEquals(a, MatrixMultiplier.multiplyPlain(identity, a));
    }

    @Test
    void multiplyPlain_1x1_returnsProduct() {
        double[][] a = {{7}};
        double[][] b = {{3}};
        double[][] expected = {{21}};
        assertMatrixEquals(expected, MatrixMultiplier.multiplyPlain(a, b));
    }

    @Test
    void multiplyPlain_byZeroMatrix_returnsZeroMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] zero = {{0, 0}, {0, 0}};
        double[][] expected = {{0, 0}, {0, 0}};
        assertMatrixEquals(expected, MatrixMultiplier.multiplyPlain(a, zero));
    }
    @Test
    void multiplyBlocked_4x4_block2_returnsSameAsPlain() {
        double[][] a = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        double[][] b = {
                {1, 0, 2, 0},
                {0, 1, 0, 2},
                {3, 0, 4, 0},
                {0, 3, 0, 4}
        };

        double[][] plain = MatrixMultiplier.multiplyPlain(a, b);
        double[][] blocked = MatrixMultiplier.multiplyBlocked(a, b, 2);

        assertMatrixEquals(plain, blocked);
    }

    @Test
    void multiplyBlocked_8x8_block4_returnsSameAsPlain() {
        double[][] a = new double[8][8];
        double[][] b = new double[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                a[i][j] = i + j;
                b[i][j] = i - j;
            }
        }

        double[][] plain = MatrixMultiplier.multiplyPlain(a, b);
        double[][] blocked = MatrixMultiplier.multiplyBlocked(a, b, 4);

        assertMatrixEquals(plain, blocked);
    }

    @Test
    void multiplyBlocked_blockEqualsSize_returnsSameAsPlain() {
        double[][] a = {
                {1, 2},
                {3, 4}
        };
        double[][] b = {
                {5, 6},
                {7, 8}
        };

        double[][] plain = MatrixMultiplier.multiplyPlain(a, b);
        double[][] blocked = MatrixMultiplier.multiplyBlocked(a, b, 2);

        assertMatrixEquals(plain, blocked);
    }

    @Test
    void multiplyBlocked_2x2_block1_returnsSameAsPlain() {
        double[][] a = {
                {1, 2},
                {3, 4}
        };
        double[][] b = {
                {5, 6},
                {7, 8}
        };

        double[][] plain = MatrixMultiplier.multiplyPlain(a, b);
        double[][] blocked = MatrixMultiplier.multiplyBlocked(a, b, 1);

        assertMatrixEquals(plain, blocked);
    }
}