package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testDecoratorDecoders {

	public testDecoratorDecoders() {
		// Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
		System.out.println("Reader Atbash decorator test(decoder):");
		
		List<String> inputAtbash = (Arrays.asList("Olivn","rkhfn","wloli","hrg"));
		List<String> output = new ArrayList<>();
		String tempString = "";
		
		for(String line: inputAtbash) {
			tempString = "";
			for( char c : line.toCharArray()) {
				if(Character.isLowerCase(c)) {
					tempString += (char) ('z' + ('a' - c));
 				}
				else if(Character.isUpperCase(c)) {
					tempString += (char) ('Z' + ('A' - c));
				}
				else {
					tempString += c;
				}
			}
			System.out.println("Input: "+line+" Output: "+tempString);
			output.add(tempString);
		}
		
		
		
		
		System.out.println("\nReader Rot13 decorator test(decoder):");
		
		List<String> inputRot13 = (Arrays.asList("Yberz","vcfhz","qbybe","fvg"));
		output = new ArrayList<>();
		
		for(String line: inputRot13) {
			tempString = "";
			for( char c : line.toCharArray()) {
				if(Character.isLowerCase(c)) {
					if(c - 'a'>= 13) {
						tempString += (char) (c - 13);
					}
					else {
						tempString += (char) ('z' - (13 - (c - 'a' + 1)));
					}
 				}
				else if(Character.isUpperCase(c)) {
					if(c - 'A' >= 13) {
						tempString += (char) (c - 13);
					}
					else {
						tempString += (char) ('Z' - (13 - (c - 'A' + 1)));
					}
				}
				else {
					tempString += c;
				}
			}
			System.out.println("Input: "+line+" Output: "+tempString);
			output.add(tempString);
		
		}
	}
}
