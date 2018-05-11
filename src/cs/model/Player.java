package cs.model;

import cs.model.weapons.Knife;
import cs.model.weapons.Weapon;

import java.util.logging.Logger;

public class Player {

    private static final Logger logger = Logger.getLogger("cs.model.Player");
    private final String name;
    private Inventory inventory = new Inventory();
    private Weapon currentWeapon = new Knife();

    public Player(String name) {
        this.name = name;
    }

    public void register(View v) {
        currentWeapon.addObserver(v);
        inventory.addObserver(v);
    }

    public String getName() {
        return name;
    }

    public void setCurrentWeapon(Integer weaponId) {
        logger.info("Otrzymane ID: " + weaponId);
        currentWeapon = Weapon.createWeaponObject(weaponId);
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    @Override
    public String toString() {
        return "Player: " + name;
    }

    public void attack() {
        currentWeapon.attack();
    }

    public void lookAtWeapon() {
        currentWeapon.lookAt();
    }

    public void showInventory() {
        inventory.show();
    }

    public void takeFromInventory(Integer weaponId) {
        inventory.take(weaponId);
    }

    public void hideToInv(Weapon weapon) {
        inventory.hide(weapon);
    }

    public void swapWeapon(Integer weaponId) {
        inventory.swap(weaponId);
    }
}
