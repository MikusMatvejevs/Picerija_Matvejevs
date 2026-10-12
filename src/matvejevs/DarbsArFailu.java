package matvejevs;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class DarbsArFailu {
    public boolean saglabat(String failaNosaukums, String dati) {
        try {
            PrintWriter rakstitajs = new PrintWriter(failaNosaukums, "UTF-8");
            rakstitajs.print(dati);
            rakstitajs.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String nolasit(String failaNosaukums) {
        String dati = "";
        try {
            File fails = new File(failaNosaukums);
            if (!fails.exists()) return "";
            Scanner lasitajs = new Scanner(fails, "UTF-8");
            while (lasitajs.hasNextLine()) {
                dati += lasitajs.nextLine() + "\n";
            }
            lasitajs.close();
        } catch (Exception e) {
            System.out.println("Neizdevās nolasīt failu!");
        }
        return dati;
    }
}