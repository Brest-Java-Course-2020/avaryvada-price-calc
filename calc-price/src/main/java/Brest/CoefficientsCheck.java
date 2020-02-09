package Brest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class CoefficientsCheck {

    static void coefficientsEdit() {
        Scanner scanner = new Scanner(System.in);
        String inputValue;

        System.out.println("Welcome in price calculation program!" +
                " You can set coefficients(press E) or start to calculating now(press any key)!");

        inputValue = scanner.next();

        if(inputValue.equalsIgnoreCase("E")){

            System.out.println("Please set coefficients for less 1000, 1000-10.000 amd more than 10.000km "+
                    "and separate it with (-):");
            String distanceCoefficients = scanner.next();

            System.out.println("Please set coefficients for less 300, 300-2.000 amd more than 2.000kg "+
                    "and separate it with (-):");

            String weightCoefficients = scanner.next();

            File coefFile = new File("src/main/resources/coefficients");
            FileWriter writeCoef = null;

            try {
                writeCoef = new FileWriter(coefFile);
                writeCoef.write(distanceCoefficients + "\n" + weightCoefficients);

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    writeCoef.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
