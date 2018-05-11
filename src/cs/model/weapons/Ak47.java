package cs.model.weapons;

import cs.model.Observer;

public class Ak47 implements Weapon {

    private static final int ID = 2;

    @Override
    public void attack() {
        for (Observer observer : observers) {
            observer.onAttack();
        }
    }

    @Override
    public void lookAt() {
        for (Observer observer : observers) {
            observer.onLook();
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Ak47";
    }

}