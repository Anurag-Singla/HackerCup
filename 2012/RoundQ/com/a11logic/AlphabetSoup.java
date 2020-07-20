package com.a11logic;

import java.io.*;

/**
 * @author Anurag Singla
 *
 */
public class AlphabetSoup {
	
	
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
				String[] tokens = line.split(" ");
				inputs[i] = new Input(line);
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
		//find the minimum number of units available of a character that is needed
		int MathWords = Integer.MAX_VALUE;
		MathWords = Math.min(MathWords, input.H);
		MathWords = Math.min(MathWords, input.A);
		MathWords = Math.min(MathWords, input.C);
		MathWords = Math.min(MathWords, input.K);
		MathWords = Math.min(MathWords, input.E);
		MathWords = Math.min(MathWords, input.R);
		MathWords = Math.min(MathWords, input.U);
		MathWords = Math.min(MathWords, input.P);

		out.println("Case #" + caseNum + ": " + MathWords);

	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlphabetSoup problem1 = new AlphabetSoup();
		Input[] inputs = problem1.readInputs();
		problem1.processInputs(inputs);

	}
	
	private static class Input{
		String s;
		int H = 0;
		int A = 0;
		int C = 0;
		int K = 0;
		int E = 0;
		int R = 0;
		int U = 0;
		int P = 0;
		public Input(String s) {
			this.s = s;
			calculateStatistics();
		}
		
		private void calculateStatistics() { 
			for(int i = 0; i < s.length(); i++) {
				switch(s.charAt(i)) {
				case 'H':
					H++;
					break;
				case 'A':
					A++;
					break;
				case 'C':
					C++;
					break;
				case 'K':
					K++;
					break;
				case 'E':
					E++;
					break;
				case 'R':
					R++;
					break;
				case 'U':
					U++;
					break;
				case 'P':
					P++;
					break;
				default:
					break;				
				}
			}
			
			//we need 2 C's in HACKERCUP
			//divide C's by 2
			C = C/2;
		}
		
	}
	

}
