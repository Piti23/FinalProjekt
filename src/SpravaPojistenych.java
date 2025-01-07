package it.network;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpravaPojistenych {
    private List<Pojisteny> pojisteni = new ArrayList<>();
    private final String SOUBOR = "pojisteni_data.txt";

    public SpravaPojistenych() {
        nactiZeSouboru();
    }

    public void pridatPojisteneho(String jmeno, String prijmeni, int vek, String telefon) {
        Pojisteny novyPojisteny = new Pojisteny(jmeno, prijmeni, vek, telefon);
        pojisteni.add(novyPojisteny);
        ulozDoSouboru();
    }

    public void zobrazitVsechny() {
        if (pojisteni.isEmpty()) {
            System.out.println("Seznam pojištěných je prázdný.");
        } else {
            pojisteni.forEach(System.out::println);
        }
    }

    public void vyhledatPojisteneho(String jmeno, String prijmeni) {
        String hledaneJmeno = Pojisteny.odstranitDiakritiku(jmeno.trim());
        String hledanePrijmeni = Pojisteny.odstranitDiakritiku(prijmeni.trim());
        boolean nalezen = false;

        for (Pojisteny pojisteny : pojisteni) {
            String ulozeneJmeno = Pojisteny.odstranitDiakritiku(pojisteny.getJmeno());
            String ulozenePrijmeni = Pojisteny.odstranitDiakritiku(pojisteny.getPrijmeni());

            if (ulozeneJmeno.equalsIgnoreCase(hledaneJmeno) && ulozenePrijmeni.equalsIgnoreCase(hledanePrijmeni)) {
                System.out.println(pojisteny);
                nalezen = true;
            }
        }

        if (!nalezen) {
            System.out.println("Pojištěný s tímto jménem nebyl nalezen.");
        }
    }

    public void smazatPojisteneho(int id) {
        if (pojisteni.removeIf(p -> p.getId() == id)) {
            ulozDoSouboru();
            System.out.println("Pojištěný byl úspěšně smazán.");
        } else {
            System.out.println("Pojištěný s tímto ID nebyl nalezen.");
        }
    }

    private void ulozDoSouboru() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SOUBOR))) {
            for (Pojisteny p : pojisteni) {
                writer.write(String.format("%d,%s,%s,%d,%s", p.getId(), p.getJmeno(), p.getPrijmeni(), p.getVek(), p.getTelefon()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Chyba při ukládání dat: " + e.getMessage());
        }
    }

    private void nactiZeSouboru() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SOUBOR))) {
            String radek;
            while ((radek = reader.readLine()) != null) {
                String[] data = radek.split(",");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String jmeno = data[1];
                    String prijmeni = data[2];
                    int vek = Integer.parseInt(data[3]);
                    String telefon = data[4];
                    pojisteni.add(new Pojisteny(jmeno, prijmeni, vek, telefon));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Soubor neexistuje, bude vytvořen nový.");
        } catch (IOException e) {
            System.out.println("Chyba při načítání dat: " + e.getMessage());
        }
    }
}
