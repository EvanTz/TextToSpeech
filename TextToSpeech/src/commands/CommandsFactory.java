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
		else if (str == "StartRecording"){
			return new StartRecording();
		}
		else if (str == "EndRecording"){
			return new EndRecording();
		}
		else if (str == "ReplayCommand"){
			return new ReplayCommand();
		}
		else {
			// future extra commands
			return null;
		}
		
	}
	
}
