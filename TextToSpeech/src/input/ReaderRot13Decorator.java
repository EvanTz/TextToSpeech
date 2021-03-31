package input;

import java.util.ArrayList;
import java.util.List;

public class ReaderRot13Decorator extends ReaderDecorator{
	private DocumentReader doc;
	private List<String> input;
	private List<String> output;
	
	public ReaderRot13Decorator(DocumentReader doc) {
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
					if(c - 'a'>= 13) tempString += (char) (c - 13);
					else tempString += (char) ('z' - (13 - (c - 'a' + 1)));
 				}
				else if(Character.isUpperCase(c)) {
					if(c - 'A' >= 13) tempString += (char) (c - 13);
					else tempString += (char) ('Z' - (13 - (c - 'A' + 1)));
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
