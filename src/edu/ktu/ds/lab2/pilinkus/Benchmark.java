package edu.ktu.ds.lab2.pilinkus;

import edu.ktu.ds.lab2.utils.AvlSet;
import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Ks;

import java.util.*;

public class Benchmark {


    Random rg = new Random();
    int[] counts = {2_000, 4_000, 8_000, 16_000, 32_000, 64_000, 128_000, 256_000};
    int[] xIntNumbers;
    int[] yIntNumbers;
    BstSet<Integer> fullSetCont = new BstSet<Integer>();
    BstSet<Integer> halfSetCont = new BstSet<Integer>();
    BstSet<Integer> fullSetRem = new BstSet<Integer>();
    BstSet<Integer> fullSetRem2 = new BstSet<Integer>();
    BstSet<Integer> fullSetSubSet = new BstSet<Integer>();
    TreeSet<Integer> treeSet;
    HashSet<Integer> hashSet;
    BstSet<Integer> bstSet;
    AvlSet<Integer> avlSet;

    void generateNumbers(int count) {
        xIntNumbers = new int[count];
        yIntNumbers = new int[count];
        rg.setSeed(2017);
        for(int i = 0; i < count; i++){
            xIntNumbers[i] = rg.nextInt(200);
            yIntNumbers[i] = rg.nextInt(200);
        }
        Collections.shuffle(Arrays.asList(xIntNumbers, yIntNumbers));
    }

    void testTreeVsHashAdd(int elementCount){
        System.gc();
        System.gc();
        System.gc();
        long memTotal = Runtime.getRuntime().totalMemory();
        treeSet = new TreeSet<Integer>();
        hashSet = new HashSet<Integer>();
        bstSet = new BstSet<Integer>();
        avlSet = new AvlSet<Integer>();
        long t0 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            treeSet.add(rg.nextInt(200));
        }
        long t1 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            hashSet.add(rg.nextInt(200));
        }
        long t2 = System.nanoTime();
        for (int i=0;i<elementCount;i++){
            bstSet.add(rg.nextInt(200));
        }
        long t3 = System.nanoTime();
        for (int i=0;i<elementCount;i++){
            avlSet.add(rg.nextInt(200));
        }
        long t4 = System.nanoTime();
        long memFree = Runtime.getRuntime().freeMemory();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7d\n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9, (t4 - t3) / 1e9, memTotal - memFree);
    }

    void testTreeVsHashContains(int elementCount){
        System.gc();
        System.gc();
        System.gc();
        long memTotal = Runtime.getRuntime().totalMemory();
        treeSet = new TreeSet<Integer>();
        hashSet = new HashSet<Integer>();
        bstSet = new BstSet<Integer>();
        avlSet = new AvlSet<Integer>();
        for(int i = 0; i < elementCount; i++){
            treeSet.add(rg.nextInt(200));
        }
        for(int i = 0; i < elementCount; i++){
            hashSet.add(rg.nextInt(200));
        }
        for(int i = 0; i < elementCount; i++){
            bstSet.add(rg.nextInt(200));
        }
        for(int i = 0; i < elementCount; i++){
            avlSet.add(rg.nextInt(200));
        }
        long t0 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            treeSet.contains(rg.nextInt(200));
        }
        long t1 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            hashSet.contains(rg.nextInt(200));
        }
        long t2 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            bstSet.contains(rg.nextInt(200));
        }
        long t3 = System.nanoTime();
        for(int i = 0; i < elementCount; i++){
            avlSet.contains(rg.nextInt(200));
        }
        long t4 = System.nanoTime();
        long memFree = Runtime.getRuntime().freeMemory();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7d\n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9, (t4 - t3) / 1e9, memTotal - memFree);
    }

    void execute() {
        Ks.oun("TreeSet vs HashSet add");
        Ks.oun("1 - TreeSet");
        Ks.oun("2 - HashSet");
        Ks.oun("3 - BstSet");
        Ks.oun("4 - AvlSet");
        Ks.oun("5 - Sunaudota atminties");
        Ks.ouf("%6d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5);
        for (int n : counts) {
            testTreeVsHashAdd(n);
        }
        Ks.oun("TreeSet vs HashSet contains");
        Ks.oun("1 - TreeSet");
        Ks.oun("2 - HashSet");
        Ks.oun("3 - BstSet");
        Ks.oun("4 - AvlSet");
        Ks.oun("5 - Sunaudota atminties");
        Ks.ouf("%6d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5);
        for (int n : counts) {
            testTreeVsHashContains(n);
        }
        Ks.oun("Greitaveikos tyrimas baigtas");
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("LT"));
        new Benchmark().execute();
    }
}
