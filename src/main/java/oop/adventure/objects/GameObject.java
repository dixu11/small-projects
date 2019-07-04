package oop.adventure.objects;

import oop.adventure.locations.Room;
import oop.adventure.persons.Player;

public abstract class GameObject {
    protected String name;

    public GameObject(String name) {
        this.name = name;
    }

    public abstract void useObject(Player player, Room room);

    public abstract String whatToDoOnIt();
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}


