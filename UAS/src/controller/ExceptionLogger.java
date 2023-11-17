package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLogger {
    public ExceptionLogger(String message) {


        try {
            File file = new File("exception.log");
            file.createNewFile();

            String content = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "]: " + message + "\r\n";

            BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
            out.write(content);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}