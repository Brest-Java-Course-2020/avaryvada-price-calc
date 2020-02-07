package Brest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("hello");
     coefficientСalculation(0, "33");


     Double[] enterValues = new Double[4];
     Scanner scanner = new Scanner(System.in);
     String inputValue;

     int i = 0;
     do{
         invitationToEnterValue(i);
         inputValue = scanner.next();

         if(!isExitValue(inputValue)){
             if(isDoubleValue(inputValue)){

//                 enterValues[i] = new BigDecimal(coefficientСalculation(i, inputValue));
                 i++;
             }
         }
         if(i == 4){
             Double calcResult = enterValues[0] * enterValues[1] + enterValues[2] * enterValues[3];
             System.out.println("Price: $"+calcResult);
             i = 0;
         }
     } while (!isExitValue(inputValue));
        System.out.println("Finish");
    }

    private static ArrayList<Double> getCoefficient(int inputCounter) throws FileNotFoundException {

        FileReader coefficientFileReader = new FileReader("calc-price/src/main/java/Brest/coefficients");
        Scanner scan = new Scanner(coefficientFileReader);

        int i = 0;
        ArrayList<Double> coefficientList = new ArrayList<>();
        while (scan.hasNextLine()) {
            if(i == inputCounter) {

                for (String value : scan.nextLine().split("-")) {
                    coefficientList.add(Double.parseDouble(value));
                }

            } else {
                i++;
            }
        }
        return coefficientList;
    }

//считает цену за километр
    private static BigDecimal countPriceDistance(
            Double kilometerPrice,
            ArrayList<Double> coefficientArray,
            Double quantityKilometers)
    {
        BigDecimal pricePerKm = new BigDecimal(kilometerPrice);
            if (quantityKilometers < 1000){
                pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(1));
            } else if (quantityKilometers > 1000 && quantityKilometers < 10_000 ){
                pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(2));
            } else if (quantityKilometers > 10_000){
                pricePerKm = new BigDecimal(kilometerPrice * coefficientArray.get(3));
            }

        return pricePerKm;
    }

    private static BigDecimal countValueKilogram(
            Double kilogramPrice,
            ArrayList<Double> coefficientArray,
            Double quantityKilogram)
    {
        BigDecimal pricePerKg = new BigDecimal(kilogramPrice);
        if (quantityKilogram < 300){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(1));
        } else if (quantityKilogram > 300 && quantityKilogram < 2_000 ){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(2));
        } else if (quantityKilogram > 2_000){
            pricePerKg = new BigDecimal(kilogramPrice * coefficientArray.get(3));
        }

        return pricePerKg;
    }

    private static void coefficientСalculation(int inputCounter, String inputValue, Double quantitativeValue)
            throws FileNotFoundException
    {

        switch (inputCounter) {
            case 1:
                //передаём в калькулятор массив коэфов для инпуткоунтера и значение для километрожа
                //inputvalue значение цены, потом массив, потом количество километров(а потом кг)
                System.out.println(getCoefficient(inputCounter));
                countPriceDistance(Double.parseDouble(inputValue), getCoefficient(inputCounter), quantitativeValue);
                break;

            case 3:
                System.out.println(getCoefficient(inputCounter));
                countPriceDistance(Double.parseDouble(inputValue), getCoefficient(inputCounter), quantitativeValue);
                break;

        }
    }



    private static void invitationToEnterValue(int inputCounter) {

        switch (inputCounter) {
            case 0:
                System.out.println("Please enter distance or Q for exit: ");
                break;
            case 1:
                System.out.println("Please, enter price or Q for exit: ");
                break;
            case 2:
                System.out.println("Please enter weight or Q for exit: ");
                break;
            case 3:
                System.out.println("Please, enter price per kg or Q for exit: ");
                break;
        }
    }






    private static boolean isExitValue(String value){
        return  value.equalsIgnoreCase("Q");
    }

    private static boolean isDoubleValue(String value){
        boolean checkResult = true;
        try {
            double enteredValue = Double.parseDouble(value);
            checkResult = enteredValue >=0;
        } catch(NumberFormatException ex) {
            checkResult = false;
        }
        return checkResult;
    }
}
