package commands;

import java.awt.event.ActionListener;

public class CommandsFactory {
	
	public CommandsFactory() {

	}
	public ActionListener createCommand(String str) {
		
		if(str == "OpenDocument") {
			return new OpenDocument();
		}
		else if (str == "SaveDocument") {
			return new SaveDocument();
		}
		else if (str == "DocumentToSpeech"){
			return new DocumentToSpeech();
		}
		else {
			// extra commands
			return null;
		}
		
	}
	
}
