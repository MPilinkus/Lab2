package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

/*
 * Aibės testavimas be Gui
 *
 */
public class ManualTest {

    static Car[] cars;
    static ParsableSortedSet<Car> cSeries = new ParsableBstSet<>(Car::new, Car.byPrice);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        executeTest();
    }

    public static void executeTest() throws CloneNotSupportedException {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car.Builder()
                .make("Renault")
                .model("Megane")
                .year(2001)
                .mileage(20000)
                .price(3500)
                .build();
        Car c3 = new Car.Builder().buildRandom();
        Car c4 = new Car("Renault Laguna 2001 115900 700");
        Car c5 = new Car("Renault Megane 1946 365100 9500");
        Car c6 = new Car("Honda   Civic  2001  36400 80.3");
        Car c7 = new Car("Renault Laguna 2001 115900 7500");
        Car c8 = new Car("Renault Megane 1946 365100 950");
        Car c9 = new Car("Honda   Civic  2007  36400 850.3");

        Car[] carsArray = {c9, c7, c8, c5, c1, c6};

        Ks.oun("Auto Aibė:");
        ParsableSortedSet<Car> carsSet = new ParsableBstSet<>(Car::new);

        for (Car c : carsArray) {
            carsSet.add(c);
            Ks.oun("Aibė papildoma: " + c + ". Jos dydis: " + carsSet.size());
        }
        Ks.oun("");
        Ks.oun(carsSet.toVisualizedString(""));

        ParsableSortedSet<Car> carsSetCopy = (ParsableSortedSet<Car>) carsSet.clone();

        carsSetCopy.add(c2);
        carsSetCopy.add(c3);
        carsSetCopy.add(c4);
        Ks.oun("Papildyta autoaibės kopija:");
        Ks.oun(carsSetCopy.toVisualizedString(""));

        c9.setMileage(10000);

        Ks.oun("Originalas:");
        Ks.ounn(carsSet.toVisualizedString(""));

        Ks.oun("Ar elementai egzistuoja aibėje?");
        for (Car c : carsArray) {
            Ks.oun(c + ": " + carsSet.contains(c));
        }
        Ks.oun(c2 + ": " + carsSet.contains(c2));
        Ks.oun(c3 + ": " + carsSet.contains(c3));
        Ks.oun(c4 + ": " + carsSet.contains(c4));
        Ks.oun("");

        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
        for (Car c : carsArray) {
            Ks.oun(c + ": " + carsSetCopy.contains(c));
        }
        Ks.oun(c2 + ": " + carsSetCopy.contains(c2));
        Ks.oun(c3 + ": " + carsSetCopy.contains(c3));
        Ks.oun(c4 + ": " + carsSetCopy.contains(c4));
        Ks.oun("");

        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + carsSetCopy.size());
        for (Car c : new Car[]{c2, c1, c9, c8, c5, c3, c4, c2, c7, c6, c7, c9}) {
            carsSetCopy.remove(c);
            Ks.oun("Iš autoaibės kopijos pašalinama: " + c + ". Jos dydis: " + carsSetCopy.size());
        }
        Ks.oun("");

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Car c : carsSet) {
            Ks.oun(c);
        }
        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        ParsableSortedSet<Car> carsSetAvl = new ParsableAvlSet<>(Car::new);
        for (Car c : carsArray) {
            carsSetAvl.add(c);
        }
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Ks.oun("Automobilių aibė su iteratoriumi:");
        Ks.oun("");
        for (Car c : carsSetAvl) {
            Ks.oun(c);
        }

        Ks.oun("");
        Ks.oun("Automobilių aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = carsSetAvl.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Automobilių aibės toString() metodas:");
        Ks.ounn(carsSetAvl);

        // Išvalome ir suformuojame aibes skaitydami iš failo
        carsSet.clear();
        carsSetAvl.clear();

        Ks.oun("");
        Ks.oun("Automobilių aibė DP-medyje:");
        carsSet.load("data\\ban.txt");
        Ks.ounn(carsSet.toVisualizedString(""));
        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");

        Ks.oun("");
        Ks.oun("Automobilių aibė AVL-medyje:");
        carsSetAvl.load("data\\ban.txt");
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Set<String> carsSet4 = CarMarket.duplicateCarMakes(carsArray);
        Ks.oun("Pasikartojančios automobilių markės:\n" + carsSet4.toString());

        Set<String> carsSet5 = CarMarket.uniqueCarModels(carsArray);
        Ks.oun("Unikalūs automobilių modeliai:\n" + carsSet5.toString());
    }

    static ParsableSortedSet generateSet(int kiekis, int generN) {
        cars = new Car[generN];
        for (int i = 0; i < generN; i++) {
            cars[i] = new Car.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(cars));

        cSeries.clear();
        Arrays.stream(cars).limit(kiekis).forEach(cSeries::add);
        return cSeries;
    }
}
