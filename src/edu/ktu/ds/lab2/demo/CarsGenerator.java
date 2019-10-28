package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.gui.ValidationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CarsGenerator {

    private int startIndex = 0, lastIndex = 0;
    private boolean isStart = true;

    private Car[] cars;

    public Car[] generateShuffle(int setSize,
                                 double shuffleCoef) throws ValidationException {

        return generateShuffle(setSize, setSize, shuffleCoef);
    }

    /**
     * @param setSize
     * @param setTake
     * @param shuffleCoef
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws ValidationException
     */
    public Car[] generateShuffle(int setSize,
                                 int setTake,
                                 double shuffleCoef) throws ValidationException {

        Car[] cars = IntStream.range(0, setSize)
                .mapToObj(i -> new Car.Builder().buildRandom())
                .toArray(Car[]::new);
        return shuffle(cars, setTake, shuffleCoef);
    }

    public Car takeCar() throws ValidationException {
        if (lastIndex < startIndex) {
            throw new ValidationException(String.valueOf(lastIndex - startIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.
        isStart = !isStart;
        return cars[isStart ? startIndex++ : lastIndex--];
    }

    private Car[] shuffle(Car[] cars, int amountToReturn, double shuffleCoef) throws ValidationException {
        if (cars == null) {
            throw new IllegalArgumentException("Automobilių nėra (null)");
        }
        if (amountToReturn <= 0) {
            throw new ValidationException(String.valueOf(amountToReturn), 1);
        }
        if (cars.length < amountToReturn) {
            throw new ValidationException(cars.length + " >= " + amountToReturn, 2);
        }
        if ((shuffleCoef < 0) || (shuffleCoef > 1)) {
            throw new ValidationException(String.valueOf(shuffleCoef), 3);
        }

        int amountToLeave = cars.length - amountToReturn;
        int startIndex = (int) (amountToLeave * shuffleCoef / 2);

        Car[] takeToReturn = Arrays.copyOfRange(cars, startIndex, startIndex + amountToReturn);
        Car[] takeToLeave = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(cars, 0, startIndex)),
                        Arrays.stream(Arrays.copyOfRange(cars, startIndex + amountToReturn, cars.length)))
                .toArray(Car[]::new);

        Collections.shuffle(Arrays.asList(takeToReturn)
                .subList(0, (int) (takeToReturn.length * shuffleCoef)));
        Collections.shuffle(Arrays.asList(takeToLeave)
                .subList(0, (int) (takeToLeave.length * shuffleCoef)));

        this.startIndex = 0;
        this.lastIndex = takeToLeave.length - 1;
        this.cars = takeToLeave;
        return takeToReturn;
    }
}
