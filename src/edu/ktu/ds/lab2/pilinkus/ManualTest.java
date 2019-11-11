package edu.ktu.ds.lab2.pilinkus;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Ks;

import java.util.Locale;

public class ManualTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US);
        executeTest();
    }

    public static void executeTest() throws CloneNotSupportedException {
        BstSet<Integer> set = new BstSet<>();
        BstSet<Integer> set2 = new BstSet<>();
        set.add(6);
        set.add(10);
        set.add(8);
        set.add(4);
        set.add(11);
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(5);

        set2.add(1);
        set2.add(11);
        set2.add(6);
        Ks.oun("1asis medis");
        Ks.oun(set.toVisualizedString(""));
        Ks.oun("subSet(6, false, 3, true)");
        Ks.oun(set.subSet(6, false, 3, true).toVisualizedString(""));
//        Ks.oun("");
//        Ks.oun(set.subSet(6, 11).toVisualizedString(""));
//        Ks.oun("headSet(6)");
//        Ks.oun(set.headSet(6).toVisualizedString(""));
//        Ks.oun("2asis medis");
//        Ks.out(set2.toVisualizedString(""));
//        Ks.oun(set.removeAll(set2));
//        Ks.oun("1asis medis po removeAll");
//        Ks.oun(set.toVisualizedString(""));
//        Ks.oun("");
//        Ks.oun(set.removeAll(set2));
//        Ks.oun("");
//        Ks.oun(set.toVisualizedString(""));
        Ks.oun("");
        Ks.oun(set.last());
    }
}
