package oop.adventure;

import oop.adventure.locations.Room;
import oop.adventure.objects.GameObject;
import oop.adventure.persons.Player;

import java.util.Scanner;

public class Simulation {
    private Room room = new Room();
    private Player player = new Player();

    public Simulation() {
        System.out.println("Jesteś w małym pokoju. Widzisz zamknięte drzwi i okno.\n" +
                "Przed Tobą znajduje się duży stół na którym znajduje się\n" +
                "tylko jedna rzecz, mały złoty kluczyk.");
    }

    void startGame() {
        do {
            makeDecision();
        } while (!room.getDoor().isOpen());
        showEnding();
    }

    void makeDecision() {
        boolean isInputIncorrect;
        do {
            showOptions();
            isInputIncorrect = executeDecision(reciveDecision());
        } while (isInputIncorrect);
    }

    private void showOptions() {
        System.out.println("\nNapisz, co chcesz zrobić. Dostępne komendy:");
        for (GameObject gameObject : room.getGameObjects()) {
            System.out.println(gameObject + " - " + gameObject.whatToDoOnIt());
        }

    }

    private String reciveDecision() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private Boolean executeDecision(String decision) {
        boolean isDecisionIncorrect = true;
        for (GameObject gameObject : room.getGameObjects()) {
            if (decision.equals(gameObject.toString())) {
                gameObject.useObject(player,room);
                isDecisionIncorrect = false;
                return isDecisionIncorrect;
            }
        }
        System.out.printf("\"%s\" - co?\n", decision);
        System.out.println("Nie znajduje takiego przedmiotu...");
        return isDecisionIncorrect;
    }

    private void showEnding() {
        System.out.println("\n*Koniec wersji demo*");
    }
}
