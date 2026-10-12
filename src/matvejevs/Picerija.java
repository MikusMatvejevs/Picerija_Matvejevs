package matvejevs;

import javax.swing.JOptionPane;

public class Picerija {
	
	static String pasutijumi = "";
	static String failaNosaukums = "pasutijumi.txt";
	static int numurs;
    static String klientaVards;
    static String talrunis;
    static String adrese;
    static int izmers;
    static String piedevas;
    static String merces;
    static boolean uzVietas;
    static boolean nodots;
    static double piedevuCena;
    static double mercuCena;
    static double picasCena;
    static double kopsumma;
	
 	// Uzkodas un dzērieni (papildinajums no uzdevuma, bet nebija UML)
    static String uzkoda;
    static String dzeriens;

    static CenasAprekins cenas = new CenasAprekins();
    static DarbsArFailu fails = new DarbsArFailu();
    
    public static void izveidotPasutijumu(){
		numurs = 1;
        String[] rindas = pasutijumi.split("\n");
        for (String rinda : rindas) {
            String[] dati = rinda.split(";", -1);
            if (dati.length == 15) {
                try {
                    int ieprieksejaisNr = Integer.parseInt(dati[0]);
                    if (ieprieksejaisNr >= numurs) numurs = ieprieksejaisNr + 1;
                } catch (NumberFormatException e) {
                    // Izlaiž nepareizu rindu.
                }
            }
        }

        if (ievaditDatus()) {
            nodots = false;
            ierakstitPasutijumu();
            JOptionPane.showMessageDialog(null, "Izveidots pasūtījums Nr. " + numurs
                    + "\nKopā: " + String.format("%.2f", kopsumma) + " EUR");
        }
    }
	
	public static void labotPasutijumu(int nr) {
		if (!atrastPasutijumu(nr)) {
            JOptionPane.showMessageDialog(null, "Pasūtījums nav atrasts!");
            return;
        }
        if (nodots) {
            JOptionPane.showMessageDialog(null, "Nodotu pasūtījumu nevar labot!");
            return;
        }
        if (ievaditDatus()) {
            ierakstitPasutijumu();
            JOptionPane.showMessageDialog(null, "Pasūtījums izlabots!");
        }
    }
	
	public static void apskatitPasutijumus(boolean raditNodotos) {
		 String teksts = "";
	     String[] rindas = pasutijumi.split("\n");
	        for (String rinda : rindas) {
	            String[] d = rinda.split(";", -1);
	            if (d.length == 15 && Boolean.parseBoolean(d[8]) == raditNodotos) {
	                teksts += "Nr. " + d[0] + " | " + d[1] + " | " + d[2] + "\n";
	                teksts += "Adrese: " + d[3] + "\n";
	                teksts += "Picas izmērs: " + d[4] + ", piedevas: " + d[5]
	                        + ", mērce: " + d[6] + "\n";
	                teksts += "Uzkoda: " + d[13] + ", dzēriens: " + d[14] + "\n";
	                teksts += "Uz vietas: " + (Boolean.parseBoolean(d[7]) ? "Jā" : "Nē") + "\n";
	                teksts += "Cena: " + d[12] + " EUR\n\n";
	            }
	        }
	        if (teksts.isEmpty()) teksts = "Pasūtījumu nav.";
	        JOptionPane.showMessageDialog(null, teksts);
	}
	
	 public static void nodotPasutijumu(int nr) {
		if (!atrastPasutijumu(nr)) {
            JOptionPane.showMessageDialog(null, "Pasūtījums nav atrasts!");
            return;
        }
        if (nodots) {
            JOptionPane.showMessageDialog(null, "Pasūtījums jau ir nodots!");
            return;
        }
        nodots = true;
        ierakstitPasutijumu();
        JOptionPane.showMessageDialog(null, "Pasūtījums nodots klientam!");
	}
	
	public static boolean virknesParbaude(String teksts) {
        return teksts != null && !teksts.trim().isEmpty();
    }
	
	 public static boolean talrunaParbaude(String talrunis) {
        if (talrunis == null || talrunis.length() != 8) return false;
        for (int i = 0; i < talrunis.length(); i++) {
            if (!Character.isDigit(talrunis.charAt(i))) return false;
        }
        return true;
    }
	
	public static boolean atrastPasutijumu(int nr) {
		String[] rindas = pasutijumi.split("\n");
        for (String rinda : rindas) {
            String[] d = rinda.split(";", -1);
            if (d.length == 15 && d[0].equals("" + nr)) {
                try {
                    numurs = Integer.parseInt(d[0]);
                    klientaVards = d[1];
                    talrunis = d[2];
                    adrese = d[3];
                    izmers = Integer.parseInt(d[4]);
                    piedevas = d[5];
                    merces = d[6];
                    uzVietas = Boolean.parseBoolean(d[7]);
                    nodots = Boolean.parseBoolean(d[8]);
                    piedevuCena = Double.parseDouble(d[9]);
                    mercuCena = Double.parseDouble(d[10]);
                    picasCena = Double.parseDouble(d[11]);
                    kopsumma = Double.parseDouble(d[12]);
                    uzkoda = d[13];
                    dzeriens = d[14];
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }
	
	public static void ierakstitPasutijumu(){
		String jaunaRinda = numurs + ";" + klientaVards + ";" + talrunis + ";"
                + adrese + ";" + izmers + ";" + piedevas + ";" + merces + ";"
                + uzVietas + ";" + nodots + ";" + piedevuCena + ";"
                + mercuCena + ";" + picasCena + ";" + kopsumma + ";"
                + uzkoda + ";" + dzeriens;

        String jauniePasutijumi = "";
        boolean atrasts = false;
        String[] rindas = pasutijumi.split("\n");

        for (String rinda : rindas) {
            if (rinda.isEmpty()) continue;
            String[] d = rinda.split(";", -1);
            if (d.length == 15 && d[0].equals("" + numurs)) {
                rinda = jaunaRinda;
                atrasts = true;
            }
            jauniePasutijumi += rinda + "\n";
        }
        if (!atrasts) jauniePasutijumi += jaunaRinda + "\n";

        pasutijumi = jauniePasutijumi;
        if (!fails.saglabat(failaNosaukums, pasutijumi)) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failu!");
        }
    }
	
	// Viena datu ievades metode gan jauna pasūtījuma veidošanai, gan labošanai.
    public static boolean ievaditDatus() {
        String teksts = JOptionPane.showInputDialog("Klienta vārds:");
        if (teksts == null) return false;
        while (!virknesParbaude(teksts)) {
            teksts = JOptionPane.showInputDialog("Vārds nedrīkst būt tukšs. Ievadi vārdu:");
            if (teksts == null) return false;
        }
        klientaVards = teksts.trim().replace(';', ',').replace('\n', ' ');

        teksts = JOptionPane.showInputDialog("Tālruņa numurs (8 cipari):");
        if (teksts == null) return false;
        while (!talrunaParbaude(teksts)) {
            teksts = JOptionPane.showInputDialog("Nepareizs tālrunis! Ievadi 8 ciparus:");
            if (teksts == null) return false;
        }
        talrunis = teksts;

        String[] sanemsana = {"Uz vietas", "Piegāde"};
        int atbilde = JOptionPane.showOptionDialog(null, "Kā klients saņems pasūtījumu?",
                "Saņemšana", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, sanemsana, sanemsana[0]);
        if (atbilde == -1) return false;
        uzVietas = atbilde == 0;

        adrese = "-";
        if (!uzVietas) {
            teksts = JOptionPane.showInputDialog("Piegādes adrese:");
            if (teksts == null) return false;
            while (!virknesParbaude(teksts)) {
                teksts = JOptionPane.showInputDialog("Ievadi piegādes adresi:");
                if (teksts == null) return false;
            }
            adrese = teksts.trim().replace(';', ',').replace('\n', ' ');
        }

        String[] izmeri = {"Mazā", "Vidējā", "Lielā"};
        teksts = (String) JOptionPane.showInputDialog(null, "Izvēlies izmēru:", "Pica",
                JOptionPane.QUESTION_MESSAGE, null, izmeri, izmeri[0]);
        if (teksts == null) return false;
        izmers = 1;
        if (teksts.equals("Vidējā")) izmers = 2;
        if (teksts.equals("Lielā")) izmers = 3;

        String[] piedevuVarianti = {"Bez piedevām", "Siers", "Šķiņķis", "Sēnes"};
        piedevas = (String) JOptionPane.showInputDialog(null, "Izvēlies piedevas:", "Piedevas",
                JOptionPane.QUESTION_MESSAGE, null, piedevuVarianti, piedevuVarianti[0]);
        if (piedevas == null) return false;

        String[] mercuVarianti = {"Bez mērces", "Ķiploku", "BBQ"};
        merces = (String) JOptionPane.showInputDialog(null, "Izvēlies mērci:", "Mērces",
                JOptionPane.QUESTION_MESSAGE, null, mercuVarianti, mercuVarianti[0]);
        if (merces == null) return false;

        String[] uzkodas = {"Nav", "Frī kartupeļi", "Vistas nageti"};
        uzkoda = (String) JOptionPane.showInputDialog(null, "Izvēlies uzkodu:", "Uzkodas",
                JOptionPane.QUESTION_MESSAGE, null, uzkodas, uzkodas[0]);
        if (uzkoda == null) return false;

        String[] dzerieni = {"Nav", "Ūdens", "Kola", "Sula"};
        dzeriens = (String) JOptionPane.showInputDialog(null, "Izvēlies dzērienu:", "Dzērieni",
                JOptionPane.QUESTION_MESSAGE, null, dzerieni, dzerieni[0]);
        if (dzeriens == null) return false;

        piedevuCena = cenas.piedevasCena(piedevas);
        mercuCena = cenas.mercesCena(merces);
        picasCena = cenas.picasCena(izmers, piedevuCena, mercuCena);
        kopsumma = cenas.kopsumma(picasCena, uzVietas);

        if (uzkoda.equals("Frī kartupeļi")) kopsumma += 3.50;
        if (uzkoda.equals("Vistas nageti")) kopsumma += 4.50;
        if (dzeriens.equals("Ūdens")) kopsumma += 1.00;
        if (dzeriens.equals("Kola")) kopsumma += 2.00;
        if (dzeriens.equals("Sula")) kopsumma += 2.50;

        return true;
        
    }
	
	public static void main(String[] args) {
		pasutijumi = fails.nolasit(failaNosaukums);
        String[] pogas = {"Jauns", "Labot", "Aktīvie", "Nodot", "Nodotie", "Iziet"};

        while (true) {
            int izvele = JOptionPane.showOptionDialog(null, "Izvēlies darbību:", "Picērija",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, pogas, pogas[0]);

            if (izvele == 0) izveidotPasutijumu();
            if (izvele == 2) apskatitPasutijumus(false);
            if (izvele == 4) apskatitPasutijumus(true);
            if (izvele == 5 || izvele == -1) break;

            if (izvele == 1 || izvele == 3) {
                String teksts = JOptionPane.showInputDialog("Pasūtījuma numurs:");
                if (teksts != null) {
                    try {
                        int nr = Integer.parseInt(teksts.trim());
                        if (izvele == 1) labotPasutijumu(nr);
                        if (izvele == 3) nodotPasutijumu(nr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ievadi derīgu numuru!");
                    }
                }
            }
        }
    }
}
