package cs.gui;

import cs.controller.Controller;
import cs.model.Game;
import cs.model.Player;
import cs.model.View;

public class GUIMain {

    public static void main(String[] args) {

        Game game = new Game();
        Player player = new Player("Player");
        View guiView = new GUIView();
        Controller controller = new Controller();

        guiView.setController(controller);
        controller.setGame(game);
        controller.setPlayer(player);
        controller.setView(guiView);

        controller.startGame();

    }

}
