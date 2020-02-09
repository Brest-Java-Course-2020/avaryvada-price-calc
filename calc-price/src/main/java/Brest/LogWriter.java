package Brest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class LogWriter {

    static void writeLog(BigDecimal finalPrice, BigDecimal[] enteredValues) {

        File logFile = new File("src/main/output/log");
        FileWriter writeLog = null;
        try {
            writeLog = new FileWriter(logFile);
            writeLog.write("Date: " + new Date() + "\nInputValues:\n"+
            "Distance: " + enteredValues[0] + " km.\nPrice per kilometer: " + enteredValues[1] +
            "$\nWidth: " + enteredValues[2] + " kg.\nPrice per kilogram: " + enteredValues[3] +
            "$\nTotal price: " + finalPrice + "\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writeLog.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
