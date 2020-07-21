package com.a11logic;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Anurag Singla
 */
public class LeapFrogCh1 {

	private void runTests() {
		Scanner scanner = new Scanner(System.in);
		int numTests = scanner.nextInt();
		scanner.nextLine();
		for (int i = 1; i <= numTests; i++) {
			String testInput = scanner.nextLine();
			runTestCase(i, testInput);
		}
	}

	private void runTestCase(int caseNum, String input) {
		int strlen = input.length();
		int countB = 0;
		for (int i=1; i < strlen; i++){
			if(input.charAt(i) == 'B')
				countB++;
		}
		char result = 'N';
		if(countB >= strlen/2 && countB < strlen - 1)
			result = 'Y';
		System.out.println("Case #" + caseNum + ": " + result);
	}

	public static void main(String[] args) {
		LeapFrogCh1 lp = new LeapFrogCh1();
		lp.runTests();
	}

}
