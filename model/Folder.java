package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Folder {
    private String fileName;

    public Folder(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            BufferedReader bR = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
            String line = bR.readLine();
            if(line != null){
                lines.add(line);
            }
            while(line != null) {
                line = bR.readLine();
                if(line != null) {
                    lines.add(line);
                }
            }
            bR.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public void saveAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : lines){
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
