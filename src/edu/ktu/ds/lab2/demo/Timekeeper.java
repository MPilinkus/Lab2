package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.Ks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author eimutis
 */
public class Timekeeper {

    private int[] tyrimoImtis;
    private long startTime;
    private final long startTimeTot;
    private double sumTime;
    private int tyrimoInd;
    private int kiekioInd;
    private int tyrimųN;
    private final int tyrimųNmax = 30;
    private final int kiekioN;
    private double[][] laikai;
    private String tyrimųEilutė;
    private final String kiekioFormatas = "%8d(%2d) ";
    private String header = "  kiekis(*k) ";

    private final BlockingQueue<String> resultsLogger;
    private final Semaphore semaphore;

    /**
     * For console benchmark
     * @param kiekiai
     */
    public Timekeeper(int[] kiekiai) {
        this(kiekiai, null, null);
    }

    /**
     * For Gui benchmark
     *
     * @param kiekiai
     * @param resultsLogger
     * @param semaphore
     */
    public Timekeeper(int[] kiekiai, BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        this.tyrimoImtis = kiekiai;
        this.resultsLogger = resultsLogger;
        this.semaphore = semaphore;
        kiekioN = tyrimoImtis.length;
        laikai = new double[kiekioN][tyrimųNmax];
        startTimeTot = System.nanoTime();
    }

    public void start() throws InterruptedException {
        tyrimoInd = 0;
        if (kiekioInd >= kiekioN) {
            logResult("Duomenų kiekis keičiamas daugiau kartų nei buvo planuota");
            // System.exit(0);        
        }
        tyrimųEilutė = String.format(kiekioFormatas, tyrimoImtis[kiekioInd],
                tyrimoImtis[kiekioInd] / tyrimoImtis[0]);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void startAfterPause() {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void finish(String vardas) throws InterruptedException {
        double executionTime = (System.nanoTime() - startTime) / 1e9;
        sumTime += executionTime;
        if (startTime == 0) {
            logResult("Metodas finish panaudotas be start metodo !!!\n");
            //   System.exit(0);
        }
        if (kiekioInd == 0) {
            header += String.format("%9s ", vardas);
        }
        if (tyrimoInd >= tyrimųNmax) {
            logResult("Jau atlikta daugiau tyrimų nei numatyta  !!!\n");
            //  System.exit(0);
        }
        laikai[kiekioInd][tyrimoInd++] = executionTime;
        tyrimųEilutė += String.format("%9.4f ", executionTime);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime = System.nanoTime();
    }

    public void seriesFinish() throws InterruptedException {
        if (kiekioInd == 0) {
            logResult(header + "\n");
        }
        logResult(tyrimųEilutė + "\n");
        kiekioInd++;
        tyrimųN = tyrimoInd;
        if (kiekioInd == kiekioN) {
            summary();
        }
    }

    private void summary() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        double totTime = (System.nanoTime() - startTimeTot) / 1e9;
        sb.append(String.format("       Bendras tyrimo laikas %8.3f sekundžių", totTime)).append("\n");
        sb.append(String.format("    Išmatuotas tyrimo laikas %8.3f sekundžių", sumTime)).append("\n");
        sb.append(String.format("    t.y. %5.1f%% sudaro pagalbiniai darbai",
                (totTime - sumTime) / totTime * 100)).append("\n");
        sb.append("\n");
        sb.append("Normalizuota (santykinė) laikų lentelė\n");
        sb.append(header).append("\n");
        double d1 = laikai[0][0];
        for (int i = 0; i < kiekioN; i++) {
            tyrimųEilutė = String.format(kiekioFormatas, tyrimoImtis[i],
                    tyrimoImtis[i] / tyrimoImtis[0]);
            for (int j = 0; j < tyrimųN; j++) {
                tyrimųEilutė += String.format("%9.2f ", laikai[i][j] / d1);
            }
            sb.append(tyrimųEilutė).append("\n");
        }
        sb.append("\n");
        logResult(sb.toString());
    }

    public void logResult(String result) throws InterruptedException {
        if (resultsLogger != null && semaphore != null) {
            resultsLogger.put(result);
            semaphore.acquire();
        } else {
            Ks.out(result);
        }
    }
}
