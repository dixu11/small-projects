package oop.very_old.football;

public class Pilka {
    private int licznikPrzechwycen = 0;

    public void zmianaWlasciciela() {
        licznikPrzechwycen++;
    }

    public Pilka() {
//        System.out.println("Powstala pilka");
    }

    public void podajStatus() {
        System.out.printf("Tyle razy pilka zmieniala wlasciciela: %d\n", licznikPrzechwycen);
    }
}
