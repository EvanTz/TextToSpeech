package output;

import java.util.List;

public abstract class WriterDecorator implements DocumentWriter{

	public WriterDecorator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public abstract void write();
	
}
