package it.network;

import java.util.Scanner;

public class EvidencePojistnychUdalosti {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SpravaPojistenych sprava = new SpravaPojistenych(); // Instance správy pojištěných
        int volba;

        do {
            try {
                System.out.println("\n--- Evidence pojištěných ---");
                System.out.println("1. Přidat pojištěného");
                System.out.println("2. Zobrazit všechny pojištěné");
                System.out.println("3. Vyhledat pojištěného");
                System.out.println("4. Smazat pojištěného podle ID");
                System.out.println("5. Ukončit");
                System.out.print("Zvolte akci: ");
                volba = Integer.parseInt(scanner.nextLine());

                switch (volba) {
                    case 1:
                        try {
                            System.out.print("Zadejte jméno: ");
                            String jmeno = scanner.nextLine();
                            System.out.print("Zadejte příjmení: ");
                            String prijmeni = scanner.nextLine();
                            System.out.print("Zadejte věk: ");
                            int vek = Integer.parseInt(scanner.nextLine());
                            System.out.print("Zadejte telefon: ");
                            String telefon = scanner.nextLine();

                            // Volání metody pro přidání pojištěného
                            sprava.pridatPojisteneho(jmeno, prijmeni, vek, telefon);
                            System.out.println("Pojištěný byl úspěšně přidán.");
                        } catch (NumberFormatException e) {
                            System.out.println("Chyba: Zadejte platné číslo pro věk.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Chyba: " + e.getMessage());
                        }
                        break;
                    case 2:
                        sprava.zobrazitVsechny();
                        break;
                    case 3:
                        System.out.print("Zadejte jméno: ");
                        String hledaneJmeno = scanner.nextLine();
                        System.out.print("Zadejte příjmení: ");
                        String hledanePrijmeni = scanner.nextLine();
                        sprava.vyhledatPojisteneho(hledaneJmeno, hledanePrijmeni);
                        break;
                    case 4:
                        try {
                            System.out.print("Zadejte ID pojištěného: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            sprava.smazatPojisteneho(id);
                        } catch (NumberFormatException e) {
                            System.out.println("Chyba: Zadejte platné číslo pro ID.");
                        }
                        break;
                    case 5:
                        System.out.println("Aplikace byla ukončena.");
                        break;
                    default:
                        System.out.println("Neplatná volba. Zkuste to znovu.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Chyba: Zadejte platné číslo akce.");
                volba = 0; // Resetování volby pro opakování cyklu
            }
        } while (volba != 5);
    }
}
