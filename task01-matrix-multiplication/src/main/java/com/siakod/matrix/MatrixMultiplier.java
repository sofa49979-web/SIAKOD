package com.siakod.matrix;

public final class MatrixMultiplier {

    private MatrixMultiplier() {
    }

    public static double[][] multiplyPlain(double[][] a, double[][] b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }
    /**
     * Блочное умножение матриц.
     * <p>
     * Матрицы разрезаются на блоки b x b. Формула та же, что и в обычном умножении,
     * но элементами выступают блоки: C(I,J) = sum_K A(I,K) * B(K,J).
     * <p>
     * На практике это шесть вложенных циклов: три внешних по блокам (I, J, K),
     * три внутренних обычным образом перемножают два блока b x b и прибавляют
     * результат к блоку (I, J).
     * <p>
     * Асимптотика: O(n^3) по времени — операций столько же, сколько у обычного.
     * Меняется только порядок обращений к памяти: блок b x b помещается в кэш,
     * и каждый его элемент успевает поучаствовать в b умножениях, не вылетая из кэша.
     *
     * @param a         первая матрица (n x n)
     * @param b         вторая матрица (n x n)
     * @param blockSize размер блока по стороне (n должно быть кратно blockSize)
     * @return результат умножения a * b
     */
    public static double[][] multiplyBlocked(double[][] a, double[][] b, int blockSize) {
        int n = a.length;
        double[][] c = new double[n][n];

        // Внешние три цикла — по блокам.
        for (int iBlock = 0; iBlock < n; iBlock += blockSize) {
            for (int jBlock = 0; jBlock < n; jBlock += blockSize) {
                for (int kBlock = 0; kBlock < n; kBlock += blockSize) {

                    // Внутренние три цикла — обычное умножение двух блоков b x b.
                    for (int i = iBlock; i < iBlock + blockSize; i++) {
                        for (int j = jBlock; j < jBlock + blockSize; j++) {
                            double sum = 0.0;
                            for (int k = kBlock; k < kBlock + blockSize; k++) {
                                sum += a[i][k] * b[k][j];
                            }
                            c[i][j] += sum;
                        }
                    }
                }
            }
        }
        return c;
    }
}
