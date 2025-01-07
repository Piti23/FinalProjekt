public class Pojisteny {
    private static int counter = 1; // Statický čítač pro generování unikátních ID
    private int id;
    private String jmeno;
    private String prijmeni;
    private int vek;
    private String telefon;

    public Pojisteny(String jmeno, String prijmeni, int vek, String telefon) {
        validateJmenoPrijmeni(jmeno, prijmeni);
        validateVek(vek); // Přidána validace věku
        validateTelefon(telefon);
        this.id = counter++;
        this.jmeno = jmeno.trim();
        this.prijmeni = prijmeni.trim();
        this.vek = vek;
        this.telefon = telefon.trim();
    }

    private void validateJmenoPrijmeni(String jmeno, String prijmeni) {
        if (jmeno == null || jmeno.trim().isEmpty() || prijmeni == null || prijmeni.trim().isEmpty()) {
            throw new IllegalArgumentException("Jméno a příjmení nesmí být prázdné.");
        }
    }

    private void validateVek(int vek) {
        if (vek < 1 || vek > 120) {
            throw new IllegalArgumentException("Neplatný věk uživatele. Věk musí být v rozmezí 1 až 120.");
        }
    }

    private void validateTelefon(String telefon) {
        if (telefon == null || !telefon.matches("\\d{9}")) {
            throw new IllegalArgumentException("Telefon musí obsahovat přesně 9 číslic bez předvolby a bez znaménka +.");
        }
    }

    public int getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getVek() {
        return vek;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, %s %s, Věk: %d, Telefon: %s", id, jmeno, prijmeni, vek, telefon);
    }
}
