

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        BigDecimal[] enterValues = new BigDecimal[4];
     Scanner scanner = new Scanner(System.in);
     String inputValue;

     int i = 0;
     do{
         invitationToEnterValue(i);
         inputValue = scanner.next();

         if(!isExitValue(inputValue)){
             if(isDoubleValue(inputValue)){
                 System.out.println(i);
                 if(i == 1 || i == 3) {
                     enterValues[i] = (coefficientCalculation(i, inputValue, enterValues[i]));
                 } else {
                     enterValues[i] = new BigDecimal(inputValue);
                 }

                 i++;
             }
         }
         if(i == 4){
             BigDecimal calcResult =
                     enterValues[0].multiply(enterValues[1])
                             .add(enterValues[2].multiply(enterValues[3]));

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
    { System.out.println("NEN");

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

    private static BigDecimal countPriceWeight(
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


    private static BigDecimal coefficientCalculation(int inputCounter, String inputValue, BigDecimal quantitativeValue)
            throws FileNotFoundException
    {
        BigDecimal pricePerUnit = new BigDecimal(0);
        switch (inputCounter) {
            case 1:
                //передаём в калькулятор массив коэфов для инпуткоунтера и значение для километрожа
                //inputvalue значение цены, потом массив, потом количество километров(а потом кг)
                System.out.println((Double.parseDouble(inputValue)));
                System.out.println(getCoefficient(inputCounter));
                System.out.println( quantitativeValue.doubleValue());
                pricePerUnit = countPriceDistance(Double.parseDouble(inputValue),
                        getCoefficient(inputCounter),
                        quantitativeValue.doubleValue());
                break;

            case 3:
                System.out.println(getCoefficient(inputCounter));
                pricePerUnit = countPriceWeight(Double.parseDouble(inputValue),
                        getCoefficient(inputCounter),
                        quantitativeValue.doubleValue());
                break;
        }
        return pricePerUnit;
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
