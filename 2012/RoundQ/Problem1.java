package round.qualifying;

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
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * @author Anurag Singla
 * compiled using JDK 1.7.0_02
 *
 */
public class Problem1 {
	
	
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
				System.out.println(line);
				String[] tokens = line.split(" ");
				System.out.println(tokens.length);
				System.out.println(Arrays.toString(tokens));	
				int w = Integer.parseInt(tokens[0]);
				int h = Integer.parseInt(tokens[1]);
				String[] s = Arrays.copyOfRange(tokens, 2, tokens.length);
				System.out.println(Arrays.toString(s));
				inputs[i] = new Input(w, h, s);
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
		int maxAreaPerCharacter = (input.w * input.h) / input.numCharactersWithoutSpaces;
		int maxFontSize = (int) Math.sqrt(maxAreaPerCharacter);
		int maxWidth = input.w / input.maxWordLength;
		maxFontSize = Math.min(maxFontSize, maxWidth);
		int i = maxFontSize;
		while( i > 0) {
			//fit the characters
			boolean charactersFit = testCharactersFit(input, i);
			if(charactersFit)
				break;
			i--;
		}
		

		out.println("Case #" + caseNum + ": " + i);

	}
	
	private boolean testCharactersFit(Input input, int fontSize) {
		int maxCharactersPerLine = input.w / fontSize;
		int maxLines = input.h /fontSize;
		int rowNum = -1;
		int i = 0;
		int spaceRemainingInTheRow = maxCharactersPerLine;
		while (i < input.s.length && rowNum < maxLines) {
			rowNum++;
			spaceRemainingInTheRow = maxCharactersPerLine;
			spaceRemainingInTheRow -= input.s[i].length();
			i++;
			if(i == input.s.length)
				break;
			while(spaceRemainingInTheRow >= input.s[i].length() + 1) {
				spaceRemainingInTheRow -= (input.s[i].length() + 1);
				i++;
				if(i == input.s.length)
					break;
			}
			
		}
		
		if(rowNum < maxLines)
			return true;
		else			
			return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem1 problem1 = new Problem1();
		Input[] inputs = problem1.readInputs();
		problem1.processInputs(inputs);

	}
	
	private static class Input{
		int w;
		int h;
		String[] s;
		int numCharactersWithoutSpaces = 0;
		int maxWordLength = 0;
		public Input(int w, int h, String[] s) {
			this.w = w;
			this.h = h;
			this.s = s;
			calculateStatistics();
		}
		
		private void calculateStatistics() { 
			for(int i = 0; i < s.length; i++) {
				numCharactersWithoutSpaces += s[i].length();
				if(s[i].length() > maxWordLength) 
					maxWordLength = s[i].length();
			}
		}
		
	}
	

}
