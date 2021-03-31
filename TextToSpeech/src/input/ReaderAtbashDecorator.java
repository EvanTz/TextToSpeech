package input;

import java.util.ArrayList;
import java.util.List;

public class ReaderAtbashDecorator extends ReaderDecorator{
	private DocumentReader doc;
	private List<String> input;
	private List<String> output;

	public ReaderAtbashDecorator(DocumentReader doc) {
		super(doc);
		this.doc = doc;
		output = new ArrayList<String>();
	}

	@Override
	public List<String> read() {
		output.clear();
		input = doc.read();
		String tempString = "";
	
		for(String line: input) {
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
		return output;
	}

}
