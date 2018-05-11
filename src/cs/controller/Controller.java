package cs.controller;

import cs.model.Game;
import cs.model.Player;
import cs.model.View;
import cs.model.weapons.Weapon;

public class Controller {

    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        game.setPlayer(player);
    }

    public void setView(View view) {
        game.setView(view);
    }

    public void startGame() {
        game.start();
    }

    public void attack() {
        game.getPlayer().attack();
    }

    public void lookAtWeapon() {
        game.getPlayer().lookAtWeapon();
    }

    public void showInventory() {
        game.getPlayer().showInventory();
    }

    public Weapon getCurrentWeapon() {
        return game.getPlayer().getCurrentWeapon();
    }

    public void setCurrentWeapon(Integer weaponId) {
        game.getPlayer().setCurrentWeapon(weaponId);
    }

    public void takeFromInventory(Integer weaponId) {
        game.getPlayer().takeFromInventory(weaponId);
    }

    public void hideWeapon(Weapon weapon) {
        game.getPlayer().hideToInv(weapon);
    }

    public void swapWeapon(Integer weaponId) {
        game.getPlayer().swapWeapon(weaponId);
    }

}

