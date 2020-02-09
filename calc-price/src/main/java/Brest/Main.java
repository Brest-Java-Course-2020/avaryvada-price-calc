package Brest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BigDecimal[] enterValues = new BigDecimal[4];
        Scanner scanner = new Scanner(System.in);
        String inputValue;

        int i = 0;
        do {
            invitationToEnterValue(i);
            inputValue = scanner.next();
            if (!isExitValue(inputValue)) {
                if (isDoubleValue(inputValue)) {
                    if (i == 1 || i == 3) {
                        enterValues[i] = (PriceCounter.coefficientCalculation(i, inputValue, enterValues[i - 1].doubleValue()));
                    } else {
                        enterValues[i] = new BigDecimal(inputValue);
                    }
                    i++;
                }
            }
            if (i == 4) {
                BigDecimal calcResult =
                        enterValues[0].multiply(enterValues[1])
                                .add(enterValues[2].multiply(enterValues[3]));

                System.out.println("Price: $" + calcResult);
                LogWriter.writeLog(calcResult, enterValues);
                i = 0;
            }
        } while (!isExitValue(inputValue));
        System.out.println("Finish");
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

    private static boolean isExitValue(String value) {
        return value.equalsIgnoreCase("Q");
    }

    private static boolean isDoubleValue(String value) {
        boolean checkResult = true;
        try {
            double enteredValue = Double.parseDouble(value);
            checkResult = enteredValue >= 0;
        } catch (NumberFormatException ex) {
            checkResult = false;
        }
        return checkResult;
    }
}
