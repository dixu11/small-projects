package oop.very_old.football;

public class Bramkarz extends Pilkarz {
    private double skutecznosc;

    public Bramkarz(String druzyna, double skutecznosc) {
        super(druzyna);
        this.skutecznosc = skutecznosc;
    }

    public double getSkutecznosc() {
        return skutecznosc;
    }

    public void obronStrzal(Pilka pilka) {
        if (czyMaPilke()) {
            System.out.println("Bramkarz nie może bronić ponieważ z jakiegoś powodu już ma piłkę. Coś jest nie tak.");
        } else {
            System.out.println("Bramkarz rzuca się w stronę piłki aby obronić strzał...");
            if (Math.random() > skutecznosc) {
                System.out.println("Bramkarz nie złapał piłki, GOOOOL!!");
            } else {
                System.out.println("Bramkarz obronił strzał!");
            }
        }
    }
}
