package oop.very_old.football;

public class Pilkarz {
    private static int nastepnyNumer = 1;

    private int numer;
    private String druzyna;
    private Pilka pilka = null;

    public Pilkarz(String druzyna) {
        numer = nastepnyNumer;
        nastepnyNumer++;
        this.druzyna = druzyna;
//        System.out.println("Tworze pilkarza: " + numer);
    }

 /*   public void okiwaj(Pilkarz przeciwnik) {

    }*/

    public void podajPilke(Pilkarz pilkarz) {
      pilkarz.odbierzPilke(oddajPilke());
        System.out.printf("\n%s podaje piłkę do %s\n", this,pilkarz);
    }

    public void strzelNaBramkePrzeciwnika(Bramkarz bramkarz) {
        if (czyMaPilke()) {
            System.out.printf("\n%s kieruje się w stronę bramki...\n",this);
            bramkarz.obronStrzal(pilka);
            pilka.zmianaWlasciciela();
            pilka = null;
        } else {
            System.out.printf("%s nie moze strzelic na bramke bo nie ma pilki!", this);
        }
    }


    public boolean czyMaPilke() {
        if (pilka != null) {
            return true;
        } else {
            return false;
        }
    }

    public void odbierzPilke(Pilka pilka) {
        if (czyMaPilke()) {
            System.out.printf("%s nie moze mieć 2 piłek, ma już jedną!\n", this);
        } else {
            this.pilka = pilka;
        }

    }

    public Pilka oddajPilke() {
        if (czyMaPilke()) {
            Pilka temp = pilka;
            pilka = null;
            temp.zmianaWlasciciela();
            return temp;
        } else {
            System.out.printf("%s nie ma pilki!\n", this);
            return null;
        }
    }

    @Override
    public String toString() {
        return "Pilkarz " +
                "numer " + numer;
    }
}
