package oop.very_old.football;

public class Druzyna {
    private Pilkarz[] pilkarze;
    private String nazwaDruzyny;
    private Bramkarz bramkarz;
    private int iloscPilkarzy;

    public Druzyna(int iloscPilkarzy, String nazwaDruzyny, boolean czyBramkarz) {
        this.nazwaDruzyny = nazwaDruzyny;
        Pilkarz[] druzyna = new Pilkarz[iloscPilkarzy];
        for (int i = 0; i < druzyna.length; i++) {
            druzyna[i] = new Pilkarz(nazwaDruzyny);
        }
        this.pilkarze = druzyna;
        if (czyBramkarz) {
            bramkarz = new Bramkarz(nazwaDruzyny, 0.7);
        }
        this.iloscPilkarzy = iloscPilkarzy;
    }

    public int getIloscPilkarzy() {
        return iloscPilkarzy;
    }

    public Pilkarz[] getPilkarze() {
        return pilkarze;
    }

    public Bramkarz getBramkarz() {
        return bramkarz;
    }
}
