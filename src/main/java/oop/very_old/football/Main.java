package oop.very_old.football;

public class Main {
    public static void main(String[] args) {
        ParametryStartowe parametryStartowe = new ParametryStartowe();
        Symulacja symulacja = new Symulacja(2);
        symulacja.przygotujDruzyne(parametryStartowe,1);
        symulacja.przygotujDruzyne(parametryStartowe, 2);
        symulacja.dajPilkeLosowemu(1);
        symulacja.rozgrzewka(20, 20,1);

    }
}
