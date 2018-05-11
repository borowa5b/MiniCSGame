package cs.model;

import cs.model.weapons.Ak47;
import cs.model.weapons.Glock;
import cs.model.weapons.Weapon;

import java.util.*;
import java.util.logging.Logger;

public class Inventory implements Subject {

    private Map<Integer, Weapon> weapons = new HashMap<>();
    private ArrayList<Observer> observers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger("cs.model.Inventory");

    /*
      Initializes inventory
     */
    {
        Weapon glock = new Glock();
        weapons.put(glock.getID(), glock);

        Weapon ak = new Ak47();
        weapons.put(ak.getID(), ak);
    }

    /**
     * Adds to observers list
     * @param observer observe object
     */
    @Override
    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Removes from observers list
     * @param observer observer object
     */
    @Override
    public void deleteObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    /**
     * Adds weapon to inventory
     * @param weapon weapon object
     */
    public void add(Weapon weapon) {
        weapons.put(weapon.getID(), weapon);
    }

    /**
     * Removes weapon from inventory
     * @param weaponId weapon identifier
     */
    public void delete(int weaponId) {
        logger.info("Otrzymane ID: " + weaponId);
        weapons.remove(weaponId);
    }

    /**
     * Returns weapons collection
     * @return weapons map
     */
    public Map<Integer, Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Notifies observers about displaying inventory content
     */
    public void show() {
        for (Observer observer : observers) {
            observer.onShowInventory(this);
        }
    }

    /**
     * Notifies observers about taking weapon from inventory
     * @param weaponId weapon identifier
     */
    public void take(Integer weaponId) {
        for (Observer observer : observers) {
            observer.onTakeFromInventory(this, weaponId);
        }
    }

    /**
     * Notifies observers about hiding weapon to inventory
     * @param weapon weapon object
     */
    public void hide(Weapon weapon) {
        for (Observer observer : observers) {
            observer.onHideToInventory(this, weapon);
        }
    }

    /**
     * Notifies observers about swapping weapon
     * @param weaponId weapon identifier
     */
    public void swap(Integer weaponId) {
        for (Observer observer : observers) {
            observer.onSwap(weaponId);
        }
    }

}
