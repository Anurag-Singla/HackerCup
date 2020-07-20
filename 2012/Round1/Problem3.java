

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
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * @author Anurag Singla
 * compiled using JDK 1.7.0_02
 *
 */
public class Problem3 {
	
	private Input[] readInputs() {
		BufferedReader in = null;
		Input[] inputs = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			String line = null;
			line = in.readLine();
			int numCases = Integer.parseInt(line.trim());
			StringBuffer input = new StringBuffer();
			while((line = in.readLine()) != null) {
				input.append(line + " ");
			}
			String[] tokens = input.toString().trim().split("\\s");
			inputs = new Input[numCases];
			int tokenIndex = 0;
			for(int i = 0; i < numCases; i++) {
				
				int M = Integer.parseInt(tokens[tokenIndex++]);
				inputs[i] = new Input(M, tokens[tokenIndex++]);
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
		long numMessages = 0;
		ArrayList<Element> currentElements = new ArrayList<>();
		ArrayList<Element> currentElements_1 = new ArrayList<>();
		ArrayList<Element> currentElements_2 = new ArrayList<>();
		//keep up to 3 number sequences
		
		
		if(!input.inValidInput) {
			long f[] = new long[input.numElements];
			f[0] = 1;
			int combinedNumber2 = combineNumber(input.numbers[0], input.numbers[1]);
			if(combinedNumber2 <= input.M)
				f[1] = 2;
			else 
				f[1] = 1;
			//f[n] = f[n -1] * new options
			for(int i=2; i < input.numElements; i++) {
				long count1 = 1;
				long count2 = 0;
				if(input.M >= 10) {
					//combine current number with previous numbers
					count1 = f[i -1];
					int combinedNumber = combineNumber(input.numbers[i-1], input.numbers[i]);
					if(combinedNumber <= input.M)
						count1 = f[i -1]  + f[i -2];
					combinedNumber = combineNumber(input.numbers[i-2], combinedNumber);
					if(combinedNumber <= input.M)
						if( i > 2)
							count2 = f[i -3];
						else
							count2 = 1;
				}
				f[i] = (count1 + count2) % 4207849484L;
			}
			out.println("Case #" + caseNum + ": " + f[input.numElements -1] % 4207849484L);
		} else {	
			out.println("Case #" + caseNum + ": " + 0);
		}

	}
	
//	private ArrayList<Element> combineElements(ArrayList<Element> currentElements_1, int number, Input input) {
//		HashMap<Integer, Element> result = new HashMap<>();
//		for (Element element : currentElements_1) {
//			int combinedNumber = combineNumber(element.number, number);
//			if(combinedNumber <= input.M) {
//				Element newElement = new Element(element.cardinality, combinedNumber);
//				Element existingElement = result.get(Integer.valueOf(number));
//				if(existingElement != null)
//				result.add(new Element(element.cardinality, combinedNumber));
//				result.add(new Element(element.cardinality, number));
//			}
//		}
//	
//		return null;
//	}
	
	private int combineNumber(int number1, int number2) {
		int number = number1 * 10;
		int div = number2/10;
		while(div > 0) {
			number *= 10;
			div = div / 10;
		}
		return number + number2;
	}
	
	
	public class Sequence {
		int num1;
		int num2;
		int num3;
		public Sequence(int num1, int num2, int num3) {
			this.num1 = num1;
			this.num2 = num2;
			this.num3 = num3;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + num1;
			result = prime * result + num2;
			result = prime * result + num3;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Sequence other = (Sequence) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (num1 != other.num1)
				return false;
			if (num2 != other.num2)
				return false;
			if (num3 != other.num3)
				return false;
			return true;
		}
		private Problem3 getOuterType() {
			return Problem3.this;
		}
		
		
	}
	
	public class Element {
		int cardinality;
		int number;
		
		public Element (int cardinality, int number) {
			this.cardinality = cardinality;
			this.number = number;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem3 problem1 = new Problem3();
		Input[] inputs = problem1.readInputs();
		problem1.processInputs(inputs);

	}
	
	private static class Input {
		int M;
		String compressedStatus;
		int[] numbers = new int[1000];
		int numElements = 0;
		boolean inValidInput = false;
		
		public Input(int M, String compressedStatus) {
			this.M = M;
			this.compressedStatus = compressedStatus;
			calculate();
		}
		
		private void calculate() {
			//max length of encoded status = 1000;
			int j = 0;
			for(int i=0; i < compressedStatus.length(); i++, j++) {
				numbers[j] = compressedStatus.charAt(i) - '0';
				if(numbers[j] == 0) {
					if(j == 0) {
						inValidInput = true;
						return;
					}
						
					numbers[--j] *= 10;
				}
				if(numbers[j] < 1 || numbers[j] > M) {
					inValidInput = true;
					return;
				}
			}
			numElements = j;
		}
	}

}
