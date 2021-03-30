package output;

import java.util.ArrayList;
import java.util.List;

public class WriterAtbashDecorator extends WriterDecorator{
	private List<String> contents;
	private List<String> output;
	
	public WriterAtbashDecorator(List<String> contents) {
		this.contents = contents;
		this.output = new ArrayList<>();
	}

	@Override
	public void write() {
		// encode and decode are the same
		output.clear();
		String tempString = "";
		for(String line: contents) {
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
			output.add(tempString);
		
		}
	}
	
	public List<String> getOutput(){
		return this.output;
	}

}
