package edu.ktu.ds.lab2.utils;

public interface ParsableSortedSet<E> extends SortedSet<E> {

    void add(String dataString);

    void load(String fName);

    Object clone() throws CloneNotSupportedException;
}
