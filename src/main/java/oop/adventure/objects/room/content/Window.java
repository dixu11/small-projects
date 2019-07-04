package oop.adventure.objects.room.content;

import oop.adventure.locations.Room;
import oop.adventure.objects.GameObject;
import oop.adventure.persons.Player;

public class Window extends GameObject {
   private boolean isOpen;
   private String windowView;

    public Window(String name) {
        super(name);
        windowView = "\nWidzisz za oknem wielką otwartą przestrzeń pełną budynków i parków.\n" +
                "Spoglądasz na to wszystko z bardzo wysoka... Musisz znajdować się na \n" +
                "co najmniej dziesiątym piętrze.";
    }

    @Override
    public void useObject(Player player, Room room) {
        if (isOpen) {
            System.out.println("Bez trudu zamykasz okno.\n" +
                    "Hałasy z dworu natychmiast ucichły. Tak jest dużo lepiej.");
            isOpen = false;
        } else {
            System.out.println("Podchodzisz do okna i je otwierasz.\n" + windowView);
            isOpen = true;
        }
    }

    @Override
   public String whatToDoOnIt() {
        if (isOpen) {
            return "zamknij okno";
        } else {
            return "otworz i wyjżyj";
        }
    }
}
