package com.a11logic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class StudiousStudent {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= numTests; i++) {
            String testInput = scanner.nextLine();
//            System.out.println(testInput);
            String[] params = testInput.split("\\s");
            int m = Integer.parseInt(params[0]);
            String[] words = Arrays.copyOfRange(params, 1, params.length);
            runTestCase(i, words);
        }
    }

    private void runTestCase(int caseNum, String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String result = "";
        for (String s: words) {
            result += s;
        }
        System.out.println("Case #" + caseNum + ": " + result);
    }

    public static void main(String[] args) {
        StudiousStudent s = new StudiousStudent();
        s.runTests();
    }
}
