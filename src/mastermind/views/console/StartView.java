package mastermind.views.console;

import mastermind.controllers.StartController;
import mastermind.views.MessageView;
import santaTecla.utils.WithConsoleView;

class StartView extends WithConsoleView {

    public void writeGameIntro() {
        this.console.writeln(MessageView.TITLE.getMessage());
    }

}
