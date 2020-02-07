package Brest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

     Double[] enterValues = new Double[4];
     Scanner scanner = new Scanner(System.in);
     String inputValue;

     int i = 0;
     do{
         invitationToEnterValue(i);
         inputValue = scanner.next();

         if(!isExitValue(inputValue)){
             if(isDoubleValue(inputValue)){

                 enterValues[i] = new BigDecimal(coefficientСalculation(i, inputValue));
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

    private static String getCoefficient(int inputCounter) throws FileNotFoundException {

        FileReader coefficientFileReader = new FileReader("coefficients.txt");
        Scanner scan = new Scanner(coefficientFileReader);

        int i = 0;

        while (scan.hasNextLine()) {
            if(i == inputCounter) {
                return scan.nextLine();
            } else {
                i++;
            }
        }
        return "wrong string";
    }


    private static void coefficientСalculation(int inputCounter, int inputValue){

        switch (inputCounter) {
            case 0:

                String resultStr = str.substring(str.indexOf('.') + 1, str.indexOf(':'));
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
