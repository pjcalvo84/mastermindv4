package mastermind.views.console;

import mastermind.controllers.Controller;
import mastermind.views.MessageView;
import santaTecla.utils.WithConsoleView;

public class SecretCombinationView extends WithConsoleView {

	public void writeln(int width) {
		for (int i = 0; i < width; i++) {
			this.console.write(MessageView.SECRET.getMessage());
		}
		this.console.writeln();
	}
	
}
