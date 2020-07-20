package com.a11logic;

import java.text.DecimalFormat;
import java.util.*;

public class PegGame {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int i = 1; i <= numTests; i++) {
            Set<String> missingPegs = new HashSet<>();
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            int k = scanner.nextInt();
            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                missingPegs.add(row + ":" + col);
            }
            runTestCase(i, r, c, k, missingPegs);
        }
    }

    private void runTestCase(int caseNum, int r, int c, int k, Set<String> missingPegs) {
        double[][] plinko = null;
        double maxResult = 0.0d;
        int resultCol = k;
        for(int col = 0; col < c-1; col++) {
            plinko = new double[r][c - 1];
            plinko[0][col] = 1.0d;
            for (int i = 1; i < r; i++) {
                for (int j = 0; j < c - 1; j++) {
                    String leftPeg = i + ":" + j;
                    String rightPeg = i + ":" + (j + 1);
                    if (i % 2 != 0) { //odd row
                        //for odd  rows, there is one less column
                        if (j == c - 2) {
                            continue;
                        }
                        String topPeg = (i - 1) + ":" + (j + 1);
                        if (j == 0) { //left most
                            //if also right most
                            if(j == c -3) {
                                plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j]) +
                                        (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j + 1]);
                            } else {
                                plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j]) +
                                        (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j + 1] / 2);
                            }
                        } else if (j == c - 3) { //right most
                            plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j] / 2) +
                                    (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j + 1]);
                        } else {
                            plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j] / 2) +
                                    (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j + 1] / 2);
                        }
                        if (missingPegs.contains(topPeg)) {
                            plinko[i][j] += plinko[i - 2][j];
                        }
                    } else { //even row
                        String topPeg = (i - 1) + ":" + j;
                        if (j == 0) { //left most
                            plinko[i][j] = (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j] / 2);
                        } else if (j == c - 2) { //right most
                            plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j - 1] / 2);
                        } else {
                            plinko[i][j] = (missingPegs.contains(leftPeg) ? 0 : plinko[i - 1][j - 1] / 2) +
                                    (missingPegs.contains(rightPeg) ? 0 : plinko[i - 1][j] / 2);
                        }
                        if (missingPegs.contains(topPeg)) {
                            plinko[i][j] += plinko[i - 2][j];
                        }
                    }
                }
            }
            if(plinko[r-1][k] > maxResult) {
                maxResult = plinko[r - 1][k];
                resultCol = col;
            }
        }

        String result = String.format("%.6f", maxResult);
        System.out.println("Case #" + caseNum + ": " + resultCol + " " + result);
    }

    public static void main(String[] args) {
        PegGame s = new PegGame();
        s.runTests();
    }
}
