package com.siakod.matrix;

import java.util.Random;

/**
 * Генератор случайных матриц.
 * <p>
 * Использует фиксированный seed для воспроизводимости замеров:
 * при повторном запуске с тем же seed матрицы будут идентичны.
 */
public final class MatrixGenerator {

    private MatrixGenerator() {
    }

    /**
     * Создаёт квадратную матрицу n x n, заполненную случайными double в диапазоне [0, 1).
     *
     * @param n    размер стороны матрицы
     * @param seed зерно генератора (для воспроизводимости)
     * @return матрица n x n
     */
    public static double[][] randomMatrix(int n, long seed) {
        Random random = new Random(seed);
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }
}
