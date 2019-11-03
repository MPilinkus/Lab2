package edu.ktu.ds.lab2.pilinkus;

import edu.ktu.ds.lab2.utils.Ks;
import edu.ktu.ds.lab2.utils.Parsable;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class House implements Parsable<House> {
    private String city;
    private String address;
    private int year;
    private double price;
    private double area;
    private static final String idCode = "HS";
    private static int serNr = 100;
    private final String houseRegNr;

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public double getArea() {
        return area;
    }

    public String getHouseRegNr() { return houseRegNr; }

    public House() {
        this.houseRegNr = idCode + (serNr++);
    }

    public House(String city, String address, int year, double price, double area) {
        this.houseRegNr = idCode + (serNr++);
        this.city = city;
        this.address = address;
        this.year = year;
        this.price = price;
        this.area = area;
    }

    public House(String data) {
        this.houseRegNr = idCode + (serNr++);
        parse(data);
    }

    @Override
    public final void parse(String data) {
        try {
            Scanner ed = new Scanner(data);
            city = ed.next();
            address = ed.next();
            year = ed.nextInt();
            price = ed.nextDouble();
            area = ed.nextDouble();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie namą -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie namą -> " + data);
        }
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %4d %9.1f %8.1f",
                city, address, year, price, area);
    }

    @Override
    public int compareTo(House otherHouse) {
        return getHouseRegNr().compareTo(otherHouse.getHouseRegNr());
    }
}
