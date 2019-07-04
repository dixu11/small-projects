package structural.battle_ships;

import java.util.Random;
import java.util.Scanner;

public class Battleships {
// FAZA 1
// GRACZ 1 WYBIERA
// GRACZ 2 WYBIERA
// 4x1/ 3x2 / 2x3 / 1x4

//FAZA 2
//GRACZ 1 STRZELA
// TRAFIA? STRZELA JESZCZE RAZ
// ZATOPIL? POKAZUJA SIE PUDLA DOOKOLA STATKU - WYSWIETLENIE POZOSTALYCH STATKOW I CZY WYGRAL
// GRACZ 2 STRZELA
// PETLA

//FAZA 3 WYGRANA I PODSUMOWANIE - STATYSTYKI




    private static char[][] planszaPrywatnaG1;
    private static char[][] planszaPrywatnaG2;

    private static char[][] planszaPublicznaG1;
    private static char[][] planszaPublicznaG2;

    private static int[][][] statkiG1;
    private static int[][][] statkiG2;

    private static boolean czyGraSieToczy = true;
    // pozycje 0-3 -> 1-masztowiec
    // pozycje 4-6 -> 2-masztasztoweic
    // pozycje 7-8 -> 3-masztowiec
    // pozycja 9 -> 4-masztowiec

    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();


    public static void main(String[] args) {
        //FAZA1
        przygotujPlansze();
        przygotujMiejsceNaStatki();
        ustawWszystkieStatki(planszaPrywatnaG1, 1, statkiG1);
        ustawWszystkieStatki(planszaPrywatnaG2, 2, statkiG2);

        //FAZA 2 i 3
        while (czyGraSieToczy) {
            boolean maszStrzaGl = true;
            boolean maszStrzaG2 = true;
            boolean graZakonczona = false;
            while (maszStrzaGl) {
                System.out.println("Gracz pierwszy oddaje strzał!");
                maszStrzaGl = oddajStrzal(planszaPublicznaG1, statkiG2);
                System.out.println("czy pierwszy przegrał? " + czyGraczPrzegral(statkiG1) + " czy drugi przegral? " + czyGraczPrzegral(statkiG2));
                if (czyGraczPrzegral(statkiG2)) {
                    System.out.println("Gracz pierwszy wygrywa!");
                    graZakonczona = true;
                    break;
                }
            }
            if (graZakonczona) {
                break;
            }
            while (maszStrzaG2) {
                System.out.println("Gracz drugi oddaje strzał!");
                maszStrzaG2 = oddajStrzal(planszaPublicznaG2, statkiG1);
                System.out.println("czy pierwszy przegrał? " + czyGraczPrzegral(statkiG1) + " czy drugi przegral? " + czyGraczPrzegral(statkiG2));
                if (czyGraczPrzegral(statkiG1)) {
                    System.out.println("Gracz drugi wygrywa!");
                    graZakonczona = true;
                    break;
                }
            }
            if (graZakonczona) {
                break;
            }
        }
    }

    ////////////////////////////////////////////////METODY FAZY 1//////////////////////////////////////////////

    private static void przygotujPlansze() {
        przygotujPusteTablice();
        wstawPusteMiejsca(planszaPrywatnaG1);
        wstawPusteMiejsca(planszaPrywatnaG2);
        wstawPusteMiejsca(planszaPublicznaG1);
        wstawPusteMiejsca(planszaPublicznaG2);
        wstawWspolrzedne(planszaPrywatnaG1);
        wstawWspolrzedne(planszaPrywatnaG2);
        wstawWspolrzedne(planszaPublicznaG1);
        wstawWspolrzedne(planszaPublicznaG2);
    }

    private static void przygotujPusteTablice() {
        planszaPrywatnaG1 = new char[11][11];
        planszaPrywatnaG2 = new char[11][11];
        planszaPublicznaG1 = new char[11][11];
        planszaPublicznaG2 = new char[11][11];
    }

    private static void przygotujMiejsceNaStatki() {
        // 4x1/ 3x2 / 2x3 / 1x4
        statkiG1 = new int[10][][];
        statkiG2 = new int[10][][];
    }

    private static void wstawPusteMiejsca(char[][] tablicaGracza) {
        for (int j = 0; j < tablicaGracza.length; j++) {
            for (int k = 0; k < tablicaGracza[j].length; k++) {
                tablicaGracza[j][k] = ' ';
//                tablicaGracza[j][k] = (char) (random.nextInt(10) + 65);
            }
        }
    }

    private static void wstawWspolrzedne(char[][] planszaPrywatnaG1) {
        // wspolrzedne 0-9
        for (int i = 1; i < planszaPrywatnaG1.length; i++) {
            planszaPrywatnaG1[i][0] = Character.forDigit(i - 1, 10);
        }

        // wspolrzedne A-J
        int characterIndex = 65;
        for (int i = 1; i < planszaPrywatnaG1[0].length; i++) {
            planszaPrywatnaG1[0][i] = (char) characterIndex;
            characterIndex++;
        }
    }


    private static void ustawWszystkieStatki(char[][] plansza, int gracz, int[][][] statkiGracza) {
        ustawStatki(plansza, 1, 4, statkiGracza);
        ustawStatki(plansza, 2, 3, statkiGracza);
        ustawStatki(plansza, 3, 2, statkiGracza);
        ustawStatki(plansza, 4, 1, statkiGracza);
        wyswietlPlansze(plansza);

    }

    private static void ustawStatki(char[][] plansza, int wielkoscStatku, int iloscStatkow, int[][][] statkiGracza) {
        String bladWprowadzaniaMasztu = "Niedozwolone pole na statek";
        String bladKrzywegoStatku = "Statek musi być w pionie lub poziomie";
        for (int aktualnyStatek = 1; aktualnyStatek <= iloscStatkow; aktualnyStatek++) { // pętla po każdym statku
            int[][] lokalizacjaStatku = new int[wielkoscStatku][2];
            boolean statekNiepoprawnieWprowadzony = true;
            while (statekNiepoprawnieWprowadzony) {
                for (int czescStatku = 1; czescStatku <= wielkoscStatku; czescStatku++) { // ustawiam poszczególne części statku
                    System.out.printf("Ustawiasz %d statek wielkości %d\n", aktualnyStatek, wielkoscStatku);
                    System.out.printf("Wprowadź %d element statku:\n", czescStatku);
                    boolean maszNiepoprawnieWprowadzony = true;
                    wyswietlPlansze(plansza);
                    while (maszNiepoprawnieWprowadzony) {
                        int[] wspolrzedne = wprowadzWspolrzedne();
                        char pole = dajPole(plansza, wspolrzedne);
                        if (czyPoleJestWolne(pole) && czyPoleJestObok(wspolrzedne, lokalizacjaStatku)) {
                            zmienPole(plansza, wspolrzedne, 'O');
                            lokalizacjaStatku[czescStatku - 1] = wspolrzedne;
                            maszNiepoprawnieWprowadzony = false;
                        } else {
                            System.out.println(bladWprowadzaniaMasztu);
                        }
                    }
                }
                if (czyStatekJestProsty(lokalizacjaStatku)) {
                    statekNiepoprawnieWprowadzony = false;
                } else {
                    System.out.println(bladKrzywegoStatku);
                    usunStatekZPlanszy(plansza, lokalizacjaStatku);
                }
            }
            zapiszStatek(lokalizacjaStatku, statkiGracza);
            System.out.printf("Statek %d wprowadzony poprawnie\n\n", aktualnyStatek);
        }
    }

    private static boolean czyStatekJestProsty(int[][] lokalizacjaStatku) {
        boolean wszystieIdentyczneX = true;
        boolean wszystieIdentyczneY = true;
        int x = lokalizacjaStatku[0][0];
        int y = lokalizacjaStatku[0][1];
        if (lokalizacjaStatku.length > 1) {
            for (int i = 1; i < lokalizacjaStatku.length; i++) {
                if (x != lokalizacjaStatku[i][0]) {
                    wszystieIdentyczneX = false;
                }
                if (y != lokalizacjaStatku[i][1]) {
                    wszystieIdentyczneY = false;
                }
            }
        }
        if (wszystieIdentyczneX || wszystieIdentyczneY) {
            return true;
        } else {
            return false;
        }
    }

    private static void usunStatekZPlanszy(char[][] plansza, int[][] lokalizacjaStatku) {
        for (int i = 0; i < lokalizacjaStatku.length; i++) {
            int x = lokalizacjaStatku[i][0];
            int y = lokalizacjaStatku[i][1];
            int[] wspolrzedne = {x, y};
            zmienPole(plansza, wspolrzedne, ' ');
        }
    }


    private static void zapiszStatek(int[][] lokalizacjaStatku, int[][][] statkiGracza) {
        // pozycje 0-3 -> 1-masztowiec
        // pozycje 4-6 -> 2-masztasztoweic
        // pozycje 7-8 -> 3-masztowiec
        // pozycja 9 -> 4-masztowiec
        int iloscMasztow = lokalizacjaStatku.length;
        int indeksDlaNaszegoStatku = 0;
        switch (iloscMasztow) {
            case 2:
                indeksDlaNaszegoStatku = 4;
                break;
            case 3:
                indeksDlaNaszegoStatku = 7;
                break;
            case 4:
                indeksDlaNaszegoStatku = 9;
                break;
            default:
                indeksDlaNaszegoStatku = 0;
        }
        for (int i = indeksDlaNaszegoStatku; i < statkiGracza.length; i++) {
            if (statkiGracza[i] == null) {
                statkiGracza[i] = lokalizacjaStatku;
                break;
            }
        }
    }

    private static boolean czyPoleJestWolne(char pole) {
        if (pole == 'O') {
            return false;
        }
        return true;
    }

    private static boolean czyPoleJestObok(int[] wspolrzedne, int[][] lokalizacjaStatku) {
        int wybraneX = wspolrzedne[0];
        int wybraneY = wspolrzedne[1];
        boolean brakElementow = true;
        // tes dla sasiada na górze
        // X = 5 Y = 5 - moje wybrane
        //  5 == 4+1   - potrzebny warunek
        // X = 5 Y = 4 - tu musi byc sasiad
        //  5 == 5     - potrzebny warunek
        for (int i = 0; i < lokalizacjaStatku.length; i++) {
            int wczesniejszeX = lokalizacjaStatku[i][0];
            int wczesniejszeY = lokalizacjaStatku[i][1];
            if (wczesniejszeX > 0 || wczesniejszeY > 0) {
                brakElementow = false;
            }
            if (wybraneY == wczesniejszeY && wybraneX == wczesniejszeX + 1) {
                return true;
                // pole na lewo
            } else if (wybraneY == wczesniejszeY && wybraneX == wczesniejszeX - 1) {
                return true;
                // pole na prawo
            } else if (wybraneY == wczesniejszeY + 1 && wybraneX == wczesniejszeX) {
                return true;
                // pole na górze
            } else if (wybraneY == wczesniejszeY - 1 && wybraneX == wczesniejszeX) {
                return true;
                // pole na dole
            }
        }
        if (brakElementow) {
            return true;
        }
        return false;
    }

    private static int przetlumaczLitereNaCyfre(char litera) {
        switch (litera) {
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            case 'D':
                return 4;
            case 'E':
                return 5;
            case 'F':
                return 6;
            case 'G':
                return 7;
            case 'H':
                return 8;
            case 'I':
                return 9;
            case 'J':
                return 10;
            default:
                throw new IllegalStateException("Nie można przetłumaczyć tej litery na indeks tablicy");
        }
        // można też odczytać litere w formacie ASCI i odjąć odpowiednią sumę - inna metoda
    }

    private static void przywitaj(int gracz) {
        System.out.printf("Gracz %d rozpoczyna grę!\n", gracz);
    }

    // metoda do testów
    private static void wyswietlPozycjeWpisanychStatkow(int[][][] statkiGracza) {
        for (int i = 0; i < statkiGracza.length; i++) {
            System.out.println("Statek nr: " + (i + 1));
            if (statkiGracza[i] != null) {
                for (int j = 0; j < statkiGracza[i].length; j++) {
                    System.out.println("Maszt nr: " + (j + 1) + " wspołrzedne: ");
                    for (int k = 0; k < statkiGracza[i][j].length; k++) {
                        System.out.print(statkiGracza[i][j][k] + " ");
                    }
                }
            } else {
                System.out.println("Statek nr " + (i + 1) + " jeszcze nie istnieje");
            }
        }
    }

    ////////////////////////////////////////////////METODY FAZY 2//////////////////////////////////////////////

    private static boolean oddajStrzal(char[][] plansza, int[][][] statkiGracza) {
        wyswietlPlansze(plansza);
        // tak dlugo prosze o wprowadzenie wspolrzednych aż wpiszesz nie strzelane pole
        // nie można strzelać tam gdzie strzeliliśmy
        boolean wybralesNiewlasciwePole = true;
        int[] wspolrzedne = {0, 0};
        while (wybralesNiewlasciwePole) {
            wspolrzedne = wprowadzWspolrzedne();
            if (dajPole(plansza, wspolrzedne) == ' ') {
                wybralesNiewlasciwePole = false;
            }
        }
        boolean czyTrafilem = czyTrafilesWStatek(statkiGracza, wspolrzedne);
        if (czyTrafilem) {
            zmienPole(plansza, wspolrzedne, 'X');
            wyswietlPlansze(plansza);
            System.out.println("Statek trafiony!");
            usunWspolrzedneMasztu(statkiGracza, wspolrzedne);
            return true;
        } else {
            zmienPole(plansza, wspolrzedne, '-');
            wyswietlPlansze(plansza);
            System.out.println("Pudło!\n");
            System.out.println("Pora na kolejnego gracza...");
            return false;
        }

        // jeśli zatopiłeś ostatni statek to wygrałeś TODO
        // jeśli zatopiłeś statek to wyświetla że zatopiłeś statek TODO
    }

    private static boolean czyTrafilesWStatek(int[][][] statkiGracza, int[] wspolrzedne) {
        for (int[][] maszty : statkiGracza) {
            if (maszty != null) {
                for (int[] wspolrzedneMasztu : maszty) {
                    if (wspolrzedneMasztu[0] == wspolrzedne[0] && wspolrzedneMasztu[1] == wspolrzedne[1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // jesli zestrzelilismy statek ustawiamy na jego maszcie wspolrzedne 0 0 aby bylo wiadomo ze masz przy tym statku jest zestrzelony
    private static void usunWspolrzedneMasztu(int[][][] statkiGracza, int[] wspolrzedne) {
        for (int[][] maszty : statkiGracza) {
            if (maszty != null) {
                for (int[] wspolrzedneMasztu : maszty) {
                    if (wspolrzedneMasztu[0] == wspolrzedne[0] && wspolrzedneMasztu[1] == wspolrzedne[1]) {
                        wspolrzedneMasztu[0] = 0;
                        wspolrzedneMasztu[1] = 0;
                    }
                }
            }
        }
    }


    ////////////////////////////////////////////////METODY FAZY 3//////////////////////////////////////////////

    private static boolean czyGraczPrzegral(int[][][] statkiGracza) {
        for (int[][] maszty : statkiGracza) {
            if (maszty != null) {
                for (int[] wspolrzedneMasztu : maszty) {
                    if (wspolrzedneMasztu[0] != 0 && wspolrzedneMasztu[1] != 0) {
                        return false;
                    }
                }
            }
        }
        return true; // zwraca prawde jeśli wszystkie maszty wszystki statkow zdjete to znaczy maja wspolrzedne 0 0
    }


////////////////////////////////////////////////METODY UNIWERSALNE//////////////////////////////////////////////

    private static void wyswietlPlansze(char[][] plansza) {
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                System.out.print(plansza[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] wprowadzWspolrzedne() {
        char[] wprowadzoneXY = new char[2];
        boolean isInputIncorrect = true;
        while (isInputIncorrect) {
            String blad = "Współrzędne mają być w formacie: \"A1\" dla kolumny A i rzędu 1";
            System.out.println("Wprowadź współrzędne:");
            String wprowadzono = input.nextLine();
            if (wprowadzono.length() == 2) {
                char x = wprowadzono.charAt(0);
                char y = wprowadzono.charAt(1);
                if (x > 64 && x < 91 && y > 47 && y < 58) {
                    wprowadzoneXY[0] = x;
                    wprowadzoneXY[1] = y;
                    isInputIncorrect = false;
                } else {
                    System.out.println(blad);
                }
            } else {
                System.out.println(blad);
            }
        }
        return przetlumaczWspolrzedneNaIndeksy(wprowadzoneXY);
    }

    private static char dajPole(char[][] plansza, int[] wspolrzedneXY) {
        char znalezionePole = ' ';
        int x = wspolrzedneXY[0];
        int y = wspolrzedneXY[1];
        znalezionePole = plansza[y][x];
        return znalezionePole;
    }


    public static void zmienPole(char[][] plansza, int[] wspolrzedneXY, char nowePole) {
        int x = wspolrzedneXY[0];
        int y = wspolrzedneXY[1];
        plansza[y][x] = nowePole;
    }

    private static int[] przetlumaczWspolrzedneNaIndeksy(char[] wspolrzedneXY) {
        int x = przetlumaczLitereNaCyfre(wspolrzedneXY[0]);
        int y = Integer.parseInt(String.valueOf(wspolrzedneXY[1]));
        y += 1;
        int[] wspolrzedneJakoIndeksy = {x, y};
        return wspolrzedneJakoIndeksy;
    }
}


