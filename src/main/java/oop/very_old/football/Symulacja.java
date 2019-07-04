package oop.very_old.football;

import java.util.Random;

public class Symulacja {

    private Druzyna[] druzyny;
    Random random = new Random();

    public Symulacja(int iloscDruzyn) {
        druzyny = new Druzyna[iloscDruzyn];
    }

    public void przygotujDruzyne(ParametryStartowe parametryStartowe, int nrDruzyny) {
        druzyny[nrDruzyny - 1] =
                new Druzyna(parametryStartowe.getIloscPilkarzy()[nrDruzyny - 1],
                        parametryStartowe.getNazwaDruzyny()[nrDruzyny - 1],
                        parametryStartowe.getCzyBramkarz()[nrDruzyny - 1]);
    }


    public void rozgrzewka(int iloscPodan, int iloscStrzalowNaBramke, int ktoraDruzyna) {
        int wykonanePodania = 0;
        int wykonaneStrzaly = 0;
        for (int i = 0; i < iloscPodan + iloscStrzalowNaBramke; i++) {
            if (Math.random() > 0.5 && wykonaneStrzaly < iloscStrzalowNaBramke) {
                strzalNaBramke(ktoraDruzyna);
                wykonaneStrzaly++;
            } else if (wykonanePodania< iloscPodan) {
                losowePodanie(ktoraDruzyna);
                wykonanePodania++;
            }
        }

    }

    void strzalNaBramke(int ktoraDruzynaStrzela) {
        int losowo = Integer.MIN_VALUE;
        for (int i = 0; i < druzyny[ktoraDruzynaStrzela-1].getPilkarze().length; i++) {
            if ( druzyny[ktoraDruzynaStrzela - 1].getPilkarze()[i].czyMaPilke()) {
                druzyny[ktoraDruzynaStrzela - 1].getPilkarze()[i]
                        .strzelNaBramkePrzeciwnika(druzyny[ktoraDruzynaStrzela].getBramkarz());
                dajPilkeLosowemu(1);
                break;
            }
        }
    }

    void losowePodanie(int ktoraDruzyna) {
        int losowo = Integer.MIN_VALUE;
        for (int i = 0; i < druzyny[ktoraDruzyna-1].getPilkarze().length; i++) {
            if ( druzyny[ktoraDruzyna - 1].getPilkarze()[i].czyMaPilke()) {
                do {
                    losowo = random.nextInt(druzyny[ktoraDruzyna-1].getIloscPilkarzy());
//                    System.out.println("Losowa liczba: " + losowo);
                } while(losowo == i);
                druzyny[ktoraDruzyna - 1].getPilkarze()[i].podajPilke(druzyny[ktoraDruzyna - 1].getPilkarze()[losowo]);
                break;
            }
        }
    }

    void dajPilkeLosowemu(int ktoraDruzyna) { // potem to zadanie moze przejac sedzia
        Pilka pilka = new Pilka();
        int losowo = random.nextInt(druzyny[ktoraDruzyna-1].getIloscPilkarzy());
        druzyny[ktoraDruzyna-1].getPilkarze()[losowo].odbierzPilke(pilka);
//        System.out.println("Losowa liczba: " + losowo);
        System.out.printf("Sedzia rzuca pilke do %s\n",druzyny[ktoraDruzyna-1].getPilkarze()[losowo] );
    }
}

