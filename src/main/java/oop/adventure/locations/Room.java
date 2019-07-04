package oop.adventure.locations;

import oop.adventure.objects.GameObject;
import oop.adventure.objects.room.content.Door;
import oop.adventure.objects.room.content.Key;
import oop.adventure.objects.room.content.Window;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<GameObject> gameObjects = new ArrayList<>();
    private Door door;

    public Room() {
        door = new Door("door");
        gameObjects.add(door);
        gameObjects.add(new Window("okno"));
        gameObjects.add(new Key("kluczyk"));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Door getDoor() {
        return door;
    }
}
