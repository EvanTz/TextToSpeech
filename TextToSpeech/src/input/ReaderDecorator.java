package input;

import java.util.List;

public abstract class ReaderDecorator implements DocumentReader{

	public ReaderDecorator(DocumentReader doc) {
		// TODO Auto-generated constructor stub
	}
	
	public abstract List<String> read();

}
