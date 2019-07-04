package oop.adventure.objects.room.content;

import oop.adventure.locations.Room;
import oop.adventure.objects.GameObject;
import oop.adventure.persons.Player;

public class Key extends GameObject {
    public Key(String name) {
        super(name);
    }

    @Override
   public void useObject(Player player, Room room) {
        if (room.getGameObjects().contains(this)) {
            System.out.println("Podchodzisz do biurka i zabierasz mały " + this + ".\n" +
                    "Chowasz go do kieszeni, zastanawiając się,\n" +
                    "do czego może Ci się przydać ten przedmiot.");
            player.setKey(this);
            room.getGameObjects().remove(this);
        }
    }

    @Override
    public String whatToDoOnIt() {
        return "zabierz";
    }
}
