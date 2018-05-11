package cs.model;

public class Game {

    private Player player;
    private View view;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setView(View view) {
        this.view = view;
        this.player.register(view);
    }

    public void start() {
        view.init();
    }
}
