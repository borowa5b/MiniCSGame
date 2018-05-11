package cs.model.weapons;

import cs.model.Observer;
import cs.model.Subject;

import java.util.ArrayList;
import java.util.List;

public interface Weapon extends Subject {

    List<Observer> observers = new ArrayList<>();

    void attack();
    void lookAt();
    int getID();

    /**
     * Adds to observers list
     * @param observer observe object
     */
    default void addObserver(Observer observer) {
        if(observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Removes from observers list
     * @param observer observer object
     */
    default void deleteObserver(Observer observer) {
        if(observer != null) {
            observers.remove(observer);
        }
    }

    /**
     * Creates new weapon object
     * @param weaponId weapon identifier
     * @return weapon object
     */
    static Weapon createWeaponObject(Integer weaponId) {
        switch (weaponId) {
            case 0:
                return new Knife();
            case 1:
                return new Glock();
            case 2:
                return new Ak47();
        }
        return null;
    }

}
