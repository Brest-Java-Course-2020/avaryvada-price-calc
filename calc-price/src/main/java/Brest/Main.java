package Brest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

     Double[] enterValues = new Double[4];

     Scanner scanner = new Scanner(System.in);
     String inputValue;
     int i = 0;
     do{
         if(i == 0){
             System.out.println(" enter distance or q for exit: ");
         } else if(i == 1){
             System.out.println("Please, enter price per km or q for exit: ");
         } else if(i == 2){
             System.out.println("Please, enter weight km or q for exit: ");
         } else if(i == 3){
             System.out.println("Please, enter price per kg or q for exit: ");
         }

         inputValue = scanner.next();
         if(!isExitValue(inputValue)){
             if(isDoubleValue(inputValue)){
                 enterValues[i] = Double.parseDouble(inputValue);
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
