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

    public boolean error(String text){
        String errorText = getCurrentTimeStamp() + ": -- ERROR -- " + text + ".";
        return write(errorText);
    }

    public boolean log(String text, long timeMS) {
        String textTolog = getCurrentTimeStamp() + ": " + text + " The function took " + timeMS + "ms to execute.";
        return write(textTolog);
    }

    public boolean logAndPrint(String text, long timeMS) {
        System.out.println(text);
        return log(text, timeMS);
    }

    private String getCurrentTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // +1 in month because calendar.month starts from 0
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return day + "-" + month + "-" + year + "T" + doubleDigits(hour) + ":" + doubleDigits(minute) + ":"
                + doubleDigits(second);
    }

    private String doubleDigits(int number) {
        if (Math.abs(number) < 10) {
            return "0" + number;
        }
        return "" + number;
    }
}
