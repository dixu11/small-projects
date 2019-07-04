package structural.old_projects;// porada: zacznij od stworzenia szkieletu aplikacji - nazw metod głównych i metod mniejszych
// będziesz potrzebował 2 tablic wielowymiarowych
// zaawanswane - dodaj żeby bomby rozkładały się równomiernie po całej planszy
// zaawansowane - dodaj większe plansze
// dodaj wybór trudności

/*Początkujący – plansza 8×8 pól, 10 min, ryzyko trafienia na minę: 15,625%*/

/*Możliwa maksymalna liczba min zależna jest od rozmiarów planszy. Dla planszy o rozmiarach A×B maksymalna liczba wynosi
        A×B/3, czyli np. na planszy o rozmiarach 12×16 pól może być najwyżej 12×16/3=64 miny.*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeperUndone {
    private static int[][] computer;
    private static char[][] player;

    public static void main(String[] args) {
        while(true){
            System.out.println("Napisz wspolrzedne: ");
            Scanner scanner = new Scanner(System.in);
            int[] effect = readInput(askUser());
            System.out.println( Arrays.toString(effect));

        }





        /*prepareGameObjects(8, 8);
        generateComputerBoard();
        generatePlayerBoard();
        printBoard(computer);
        System.out.println("------");
        printBoard(player);*/

    }

    public static void prepareGameObjects(int boardY, int boardX) {
        computer = new int[boardY][boardX];
        player = new char[boardY][boardX];
    }

    public static void generateComputerBoard() {
        addBombs(10);
        addNumbers();
    }

    public static void generatePlayerBoard() {
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player[i].length; j++) {
                player[i][j] = '#';
            }
        }
    }

    public static void addBombs(int bombs) {
        int bombsPlaced = 0;
        Random random = new Random();
        while (bombsPlaced < bombs) {
            for (int i = 0; i < computer.length; i++) {
                if (bombsPlaced >= bombs) {
                    break;
                }
                for (int j = 0; j < computer[i].length; j++) {
                    if (computer[i][j] != 9 &&
                            bombsPlaced < bombs &&
                            random.nextInt(100) <= 1) {
                        computer[i][j] = 9;
                        bombsPlaced++;
                    }
                }
            }
        }
    }

    public static void addNumbers() {
        for (int i = 0; i < computer.length; i++) {
            for (int j = 0; j < computer[i].length; j++) {
                if (computer[i][j] != 9) {
                    computer[i][j] = countNeighbours(i, j);
                }
            }
        }
    }

    public static int countNeighbours(int y, int x) {
        return countConnectedNeighbours(y, x) + countDiagonalNeighbours(y, x);
    }

    public static int countConnectedNeighbours(int y, int x) {
        int neighbours = 0;
        // sąsiad po lewej: x-1  :   y
        if (x - 1 >= 0 && computer[y][x - 1] == 9) {
            neighbours++;
        }
        // sąsiad po prawej: x+1  :   y
        if (x + 1 < computer[y].length && computer[y][x + 1] == 9) {
            neighbours++;
        }
        // sąsiad na gorze: x  :   y-1
        if (y - 1 >= 0 && computer[y - 1][x] == 9) {
            neighbours++;
        }
        // sąsiad na dole: x  :   y+1
        if (y + 1 < computer[y].length && computer[y + 1][x] == 9) {
            neighbours++;
        }
        return neighbours;
    }

    public static int countDiagonalNeighbours(int y, int x) {
        int neighbours = 0;
        // sąsiad po lewej na gorze: x-1  :   y-1
        if (x - 1 >= 0 && y - 1 >= 0 && computer[y - 1][x - 1] == 9) {
            neighbours++;
        }
        // sąsiad po prawej na gorze: x+1  :   y-1
        if (x + 1 < computer[y].length && y - 1 >= 0 && computer[y - 1][x + 1] == 9) {
            neighbours++;
        }
        // sąsiad po prawej dole: x+1  :   y+1
        if (x + 1 < computer[y].length && y + 1 < computer[y].length && computer[y + 1][x+1] == 9) {
            neighbours++;
        }
        // sąsiad po lewej na dole: x-1  :   y+1
        if (x - 1 >= 0 && y + 1 < computer[y].length && computer[y + 1][x - 1] == 9) {
            neighbours++;
        }
        return neighbours;
    }


    public static void printNumbersRowLine(int howMany) {
        System.out.print("  ");
        for (int i = 0; i < howMany; i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();
    }


    public static void printBoard(int[][] board) {
        char a = 'A';
        printNumbersRowLine(board[0].length);
        for (int[] axisY : board) {
            System.out.print(a + "|");
            a++;
            for (int X : axisY) {
                System.out.print(X + " ");
            }
            System.out.println();
        }
    }

    public static void printBoard(char[][] board) {
        char a = 'A';
        printNumbersRowLine(board[0].length);
        for (char[] axisY : board) {
            System.out.print(a + " ");
            a++;
            for (char X : axisY) {
                System.out.print(X + " ");
            }
            System.out.println();
        }
    }


    public static String askUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public static int[] readInput(String line) {
        int[] yx = new int[2];
        char y = 'x';
        char x = 'x';
        if (line.length() == 2 && Character.isLetter(line.charAt(0)) && Character.isDigit(line.charAt(1))) {
            y = line.charAt(0);
            x = line.charAt(1);
        }

        yx[0] = convertLetterToInt(y);
        yx[1] = Character.getNumericValue(x);
        if (yx[1] > 9) {
            yx[1] = -1;
        }
        return yx;
    }

    public static int convertLetterToInt(char letter) {
        letter = Character.toLowerCase(letter);
        char checked = 'a';
        int counter = 1;
        while (letter != checked && checked != 'z') {
            counter++;
            checked++;
        }
        if (checked == 'z') {
            return -1;
        }
        return counter;
    }

    public static boolean isInputCorrect(int[] yx) {
        if (yx[0] != -1 && yx[1] != -1) {
            return true;
        } else {
            return false;
        }
    }


    public static void executeUserInput() {

    }


}
