package edu.ktu.ds.lab2.pilinkus;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Ks;
import edu.ktu.ds.lab2.utils.ParsableBstSet;
import edu.ktu.ds.lab2.utils.ParsableSortedSet;

import java.util.Locale;

public class HouseTest {

    static House[] houses;
    static ParsableSortedSet<House> hSeries = new ParsableBstSet<>(House::new, House.byPrice);

    private static void run() {
        House h1  = new House(
                "Kaunas",
                "Berzu g. 1",
                2014,
                250000.00,
                150.75);
        House h2  = new House(
                "Kaunas",
                "Rutu g. 1",
                2012,
                150000.00,
                200.50);
        House h3  = new House(
                "Kaunas",
                "Plytu g. 1",
                2018,
                550000.00,
                300.00);
        House h4  = new House(
                "Vilnius",
                "Rytu g. 1",
                2000,
                50000.00,
                125.20);
        House h5  = new House(
                "Vilnius",
                "Metu g. 1",
                2015,
                700000.00,
                512.00);
        House h6  = new House(
                "Kaunas",
                "Giliu g. 1",
                2015,
                600000.00,
                412.00);
        House[] housesArray = {h3, h1, h2, h4, h5};
        ParsableSortedSet<House> houseSet = new ParsableBstSet<>(House::new, House.byArea);
        for (House h : housesArray) {
            houseSet.add(h);
            Ks.oun("Aibė papildoma: " + h + ". Jos dydis: " + houseSet.size());
        }
        Ks.oun("");
        Ks.oun(houseSet.toVisualizedString(""));
        Ks.oun("");
        Ks.oun(houseSet.contains(h5));
        Ks.oun(houseSet.contains(h6));
        Ks.oun("Removing");
        Ks.oun(h2.toString());
        Ks.oun("");
        houseSet.remove(h2);
        Ks.oun(houseSet.toVisualizedString(""));


    }

    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        run();
        Ks.oun("House test done");
    }
}
