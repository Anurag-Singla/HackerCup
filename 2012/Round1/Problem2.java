

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * @author Anurag Singla
 * compiled using JDK 1.7.0_02
 *
 */
public class Problem2 {
	
	private Input[] readInputs() {
		BufferedReader in = null;
		Input[] inputs = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			String line = null;
			line = in.readLine();
			int numCases = Integer.parseInt(line.trim());
			inputs = new Input[numCases];
			for(int i = 0; i < numCases; i++) {
				line = in.readLine();	
				int N = Integer.parseInt(line);
				String debugSequence = in.readLine().trim();
				inputs[i] = new Input(N, debugSequence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			finally {
				try {
				if(in != null)
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return inputs;
	}
	
	public void processInputs(Input[] inputs) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("output")); 
			for(int i = 0; i < inputs.length; i++) {
				processInput(inputs[i], i+1, out);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			finally {
				try {
				if(out != null)
					out.flush();
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	public void processInput(Input input, int caseNum, PrintWriter out) throws IOException {
		
		LinkedList<Element> ordering = new LinkedList<>();
		
//		int numLevels = calculateLevels(input.N);
//		int[] numLHSElements = new int[numLevels];
//		int[] numRHSElements = new int[numLevels];
//		//elements compared
//		int n = input.N;
//		for(int i = 0; i < numLevels ; i++) {
//			numLHSElements[i] = n/2;
//			numRHSElements[i] = n - n/2;
//			n = n/2;
//		}
		
		Element[] sortedResults = merge_sort(input.elements, input);
		int[] originalArray = new int[sortedResults.length];
		for(int i = 0; i < originalArray.length; i++) {
			originalArray[sortedResults[i].index - 1] = i+1;
		}
		long checkSum = checkSum(originalArray);
		
		out.println("Case #" + caseNum + ": " + checkSum);

	}
	
	private long checkSum(int[] results) {
		    long checkSum = 1;
		    for( int i=0; i < results.length; i ++) {
		    	checkSum = (31 * checkSum + results[i]) % 1000003;
		    }
		    return checkSum;
	}
	
	Element[] merge_sort(Element[] arr, Input input) {
	    int n = arr.length;
	    if (n <= 1)
	        return arr;

	    // arr is indexed 0 through n-1, inclusive
	    int mid = n/2;
	    
	    Element[] first_half = merge_sort(Arrays.copyOfRange(arr, 0, mid), input);
	    Element[] second_half = merge_sort(Arrays.copyOfRange(arr, mid, n), input);
	    return merge(first_half, second_half, input);
	}

	Element[] merge(Element[] arr1, Element[] arr2, Input input) {
	    Element[] result = new Element[arr1.length + arr2.length];
	    int i=0;
	    int index1=0, index2 = 0;
	    while(index1 < arr1.length && index2 < arr2.length) {
	    	if(input.nextResult() == '1') {
//	    		arr1[index1] < arr2[index]
	    		result[i++] = arr1[index1++];
	    	} else {
	    		result[i++] = arr2[index2++];
	    	}
	    }
	    while(index1 < arr1.length)     
	    	result[i++] = arr1[index1++];
	    while(index2 < arr2.length)     
	    	result[i++] = arr2[index2++];
	    return result;
	}
	
	private int calculateLevels(int n) {
		int numLevels = 1;
		while(n > 1) {
			numLevels++;
			n = n/2;
		}
		return numLevels;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem2 problem1 = new Problem2();
		Input[] inputs = problem1.readInputs();
		problem1.processInputs(inputs);

	}
	
	private static class Input {
		int N;
		String debugSequence;
		int debugSequenceIndex = 0;
		Element[] elements;
		public Input(int N, String debugSequence) {
			this.N = N;
			this.debugSequence = debugSequence;
			calculate();
		}
		
		private void calculate() {
			elements = new Element[N];
			for(int i=0 ; i < N; i++) {
				elements[i] = new Element(i +1);
			}
		}
		
		public char nextResult(){
			return debugSequence.charAt(debugSequenceIndex++);
		}
			
	}
	
	private static class Element  {
		int index;
		public Element (int index) {
			this.index = index;
		}
		
		public String toString() {
			return "i=" + index;
		}
		
	}
	

}
