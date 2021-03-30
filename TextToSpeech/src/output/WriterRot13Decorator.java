package output;

import java.util.ArrayList;
import java.util.List;

public class WriterRot13Decorator extends WriterDecorator {
	private List<String> contents;
	private List<String> output;
	
	public WriterRot13Decorator(List<String> contents) {
		this.contents = contents;
		this.output = new ArrayList<>();
	}
	
	@Override
	public void write() {
		output.clear();
		String tempString = "";
		for(String line: contents) {
			tempString = "";
			for( char c : line.toCharArray()) {
				if(Character.isLowerCase(c)) {
					if('z' - c >= 13) tempString += (char) (c + 13);
					else tempString += (char) ('a' + (13 - ('z' - c + 1)));
 				}
				else if(Character.isUpperCase(c)) {
					if('Z' - c >= 13) tempString += (char) (c + 13);
					else tempString += (char) ('A' + (13 - ('Z' - c + 1)));
				}
				else {
					tempString += c;
				}
			}
			output.add(tempString);
		
		}
	}
	
	public List<String> getOutput(){
		return this.output;
	}

}
