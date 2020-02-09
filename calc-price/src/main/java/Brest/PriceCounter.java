package Brest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PriceCounter {

    private static ArrayList<Double> getCoefficient(int inputCounter) throws IOException {

        FileReader coefficientFileReader = new FileReader("src/main/resources/coefficients");

        BufferedReader reader = new BufferedReader(coefficientFileReader);

        ArrayList<Double> coefficientList = new ArrayList<>();

        String coefficientLine = reader.readLine();;

        if (inputCounter == 3) {
            coefficientLine = reader.readLine();
        }

        for (String value : coefficientLine.split("-")) {
            coefficientList.add(Double.parseDouble(value));
        }

        return coefficientList;
    }

    private static BigDecimal countPriceDistance(
            Double kilometerPrice,
            ArrayList<Double> coefficientArray,
            Double quantityKilometers)
    {

        BigDecimal pricePerKm = new BigDecimal(kilometerPrice);
        if (quantityKilometers < 1000){
            pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(2));
        } else if (quantityKilometers > 1000 && quantityKilometers < 10_000 ){
            pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(3));
        } else if (quantityKilometers > 10_000){
            pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(4));
        }

        return pricePerKm;
    }

    private static BigDecimal countPriceWeight(
            Double kilogramPrice,
            ArrayList<Double> coefficientArray,
            Double quantityKilogram)
    {
        BigDecimal pricePerKg = new BigDecimal(kilogramPrice);
        if (quantityKilogram < 300){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(2));
        } else if (quantityKilogram > 300 && quantityKilogram < 2_000 ){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(3));
        } else if (quantityKilogram > 2_000){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(4));
        }

        return pricePerKg;
    }


    static BigDecimal coefficientCalculation(int inputCounter, String inputValue, double quantitativeValue)
            throws IOException
    {
        BigDecimal pricePerUnit = new BigDecimal(0);
        switch (inputCounter) {
            case 1:
                System.out.println(getCoefficient(inputCounter));
                pricePerUnit = countPriceDistance(Double.parseDouble(inputValue),
                        getCoefficient(inputCounter),
                        quantitativeValue);
                break;

            case 3:
                System.out.println(getCoefficient(inputCounter));
                pricePerUnit = countPriceWeight(Double.parseDouble(inputValue),
                        getCoefficient(inputCounter),
                        quantitativeValue);
                break;
        }
        return pricePerUnit;
    }
}
