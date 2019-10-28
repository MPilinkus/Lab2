package edu.ktu.ds.lab2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

public class ParsableAvlSet<E extends Parsable<E>> extends AvlSet<E>
        implements ParsableSortedSet<E> {

    private final Function<String, E> createFunction; // funkcija bazinio objekto kūrimui

    /**
     * Konstruktorius su funkcija bazinio objekto kūrimui
     *
     * @param createFunction
     */
    public ParsableAvlSet(Function<String, E> createFunction) {
        super();
        this.createFunction = createFunction;
    }

    /**
     * Konstruktorius su funkcija bazinio objekto kūrimui ir komparatoriumi
     *
     * @param createFunction
     * @param c
     */
    public ParsableAvlSet(Function<String, E> createFunction, Comparator<? super E> c) {
        super(c);
        this.createFunction = createFunction;
    }

    /**
     * Sukuria elementą iš String eilutės ir įdeda jį į pabaigą
     *
     * @param dataString
     */
    @Override
    public void add(String dataString) {
        add(createElement(dataString));
    }

    /**
     * Suformuoja sąrašą iš filePath failo
     *
     * @param filePath
     */
    @Override
    public void load(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return;
        }

        clear();
        try (BufferedReader fReader = Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset())) {
            fReader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(this::add);
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + filePath + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + filePath + " skaitymo klaida");
        }
    }

    protected E createElement(String data) {
        return Optional.ofNullable(createFunction)
                .map(f -> f.apply(data))
                .orElseThrow(() -> new IllegalStateException("Nenustatyta aibės elementų kūrimo funkcija"));
    }
}
