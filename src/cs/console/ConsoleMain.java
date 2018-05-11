package cs.console;

import cs.controller.Controller;
import cs.model.Game;
import cs.model.Player;
import cs.model.View;

public class ConsoleMain {

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player("Player");
        View consoleView = new ConsoleView();
        Controller controller = new Controller();

        consoleView.setController(controller);
        controller.setGame(game);
        controller.setPlayer(player);
        controller.setView(consoleView);

        controller.startGame();

    }

}
