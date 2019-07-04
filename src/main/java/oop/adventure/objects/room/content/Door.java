package oop.adventure.objects.room.content;

import oop.adventure.locations.Room;
import oop.adventure.objects.GameObject;
import oop.adventure.persons.Player;

public class Door extends GameObject {
    private boolean isOpen;

    private String whatBehind;

    public Door(String name) {
        super(name);
        whatBehind = "\nOtwierasz je bez problemu i gdy już miałeś przekroczyć próg\n" +
                "podskakujesz spanikowany i Twoje serce przyspiesza.\n" +
                "Przez ułamek sekundy widziałeś skuloną postać,\n" +
                "która wyczuwając Twoją obecność natychmiast\n" +
                "odwraca się  i..";
    }

    @Override
    public void useObject(Player player, Room room) {
        if (!isOpen && player.getKey() != null) {
            isOpen = true;
            System.out.printf("Podchodzisz do drzwi pewnym krokiem i wyjmujesz zabrany %s.\n", player.getKey());
            System.out.println(whatBehind);
        } else if (isOpen) {
            System.out.println(whatBehind);
        } else {
            System.out.println("Podchodzisz do drzwi i naciskasz klamkę.\n" +
                    "Niestety ani drgną, potrzebujesz klucza.");
        }
    }

    @Override
    public String whatToDoOnIt() {
        return "otwórz";
    }

    public boolean isOpen() {
        return isOpen;
    }
}
