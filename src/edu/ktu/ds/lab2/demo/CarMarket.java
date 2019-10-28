package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Set;

public class CarMarket {

    public static Set<String> duplicateCarMakes(Car[] cars) {
        Set<Car> uni = new BstSet<>(Car.byMake);
        Set<String> duplicates = new BstSet<>();
        for (Car car : cars) {
            int sizeBefore = uni.size();
            uni.add(car);

            if (sizeBefore == uni.size()) {
                duplicates.add(car.getMake());
            }
        }
        return duplicates;
    }

    public static Set<String> uniqueCarModels(Car[] cars) {
        Set<String> uniqueModels = new BstSet<>();
        for (Car car : cars) {
            uniqueModels.add(car.getModel());
        }
        return uniqueModels;
    }
}
