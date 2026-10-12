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
    
	void izveidotPautijumu(){
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
	}
	
	void labotPasutijumu() {
		
	}
	
	void apskatitPasutijumu() {
		
	}
	
	void nodotPasutijumu() {
		
	}
	
	boolean virknesParbaude(String teksts) {
		return false;
	}
	
	boolean talrunaParbaude(String talrunis) {
		return false;
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
