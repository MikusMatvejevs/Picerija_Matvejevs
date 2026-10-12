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
	
	public static void labotPasutijumu() {
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
	
	public static void apskatitPasutijumus() {
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
	
	 public static void nodotPasutijumu() {
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
	
	boolean atrastPasutijumu(int nr) {
		return false;
	}
	
	void ierakstitPasutijumu(){
		
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
