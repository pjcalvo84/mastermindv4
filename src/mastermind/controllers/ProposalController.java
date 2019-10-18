package mastermind.controllers;

import java.util.ArrayList;
import java.util.List;

import mastermind.models.Combination;
import mastermind.models.Session;
import mastermind.types.Color;
import mastermind.types.Error;
import mastermind.views.console.ErrorView;
import mastermind.views.MessageView;
import mastermind.views.console.ColorView;
import mastermind.views.console.GameView;
import mastermind.views.console.ProposedCombinationView;

public class ProposalController extends Controller {

    private final ProposedCombinationView proposedCombinationView;

    public ProposalController(Session session) {
        super(session);
        this.proposedCombinationView = new ProposedCombinationView();
    }
    @Override
    public void control(){
        Error error;
        do {
            String characters = this.proposedCombinationView.read();
            List<Color> colors = new ArrayList<Color>();
            for (int i=0; i<characters.length(); i++) {
                colors.add(ColorView.getInstance(characters.charAt(i)));
            }
            error = this.addProposedCombination(colors);
            if (error != null) {
                new ErrorView(error).writeln();
            }
        } while (error != null);
        this.proposedCombinationView.writeln();
        new GameView().writeGame(this.session);
        this.checkGame();
    }

    private void checkGame(){
        if (this.session.isWinner()) {
            this.proposedCombinationView.writeln(MessageView.WINNER.getMessage());
            this.session.next();
        } else if (this.session.isLooser()) {
            this.proposedCombinationView.writeln(MessageView.LOOSER.getMessage());
            this.session.next();
        }
    }


    private Error addProposedCombination(List<Color> colors) {
        Error error = null;
        if (colors.size() != Combination.getWidth()) {
            error = Error.WRONG_LENGTH;
        } else {
            for (int i = 0; i < colors.size(); i++) {
                if (colors.get(i) == null) {
                    error = Error.WRONG_CHARACTERS;
                } else {
                    for (int j = i+1; j < colors.size(); j++) {
                        if (colors.get(i) == colors.get(j)) {
                            error = Error.DUPLICATED;
                        }
                    }
                }
            }
        }
        if (error == null){
            this.session.addProposedCombination(colors);
        }
        return error;
    }
}
