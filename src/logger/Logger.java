package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Logger {
    private File file;

    public Logger() {
        this("C:\\Users\\nicolas.valentine\\Documents\\Java\\FileIOProject\\src\\resources");
    }

    public Logger(String folderPath) {
        try {
            file = new File(folderPath + "\\logger.txt");
            // creates logger.txt file if not already in folder
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("IOerror " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public boolean write(String text) {
        try {
            FileWriter fw = new FileWriter(file, true);
            // Writes to logger.txt file. Adds line change to end
            fw.write(text + "\n");
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("IOerror " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }

    // method to log errors
    public boolean error(String text) {
        String errorText = getCurrentTimeStamp() + ": -- ERROR -- " + text + ".";
        return write(errorText);
    }

    // method to log to logger.txt
    public boolean log(String text, long timeMS, boolean print) {
        String textTolog = getCurrentTimeStamp() + ": " + text + " The function took " + timeMS + "ms to execute.";
        // log can be printed if necessary
        if (print) {
            System.out.println(textTolog);
        }
        return write(textTolog);
    }

    public boolean log(String text, long timeMS) {
        return log(text, timeMS, false);
    }

    public boolean logAndPrint(String text, long timeMS) {
        return log(text, timeMS, true);
    }

    private String getCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // +1 in month because calendar.month starts from 0
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return day + "-" + month + "-" + year + "T" + doubleDigits(hour) + ":" + doubleDigits(minute) + ":"
                + doubleDigits(second);
    }

    // returns numbers as double digit string
    private String doubleDigits(int number) {
        if (Math.abs(number) < 10) {
            if (number < 0) {
                // numbers from -9...-1
                return "-0" + Math.abs(number);
            }
            //numbers from 0...9
            return "0" + number;
        }
        // these numbers are already double digit
        return "" + number;
    }
}
