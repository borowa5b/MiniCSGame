package cs.model.weapons;

import cs.model.Observer;

public class Glock implements Weapon {

    private static final int ID = 1;

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
        return "Glock";
    }

}
