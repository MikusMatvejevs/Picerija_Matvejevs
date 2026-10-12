package matvejevs;

public class CenasAprekins {
    double mazasPicasCena = 6.00;
    double videjasPicasCena = 9.00;
    double lielasPicasCena = 12.00;
    double piegadesCena = 3.00;

    public double picasCena(int izmers, double piedevuCena, double mercuCena) {
        double cena = mazasPicasCena;
        if (izmers == 2) cena = videjasPicasCena;
        if (izmers == 3) cena = lielasPicasCena;
        return cena + piedevuCena + mercuCena;
    }

    public double kopsumma(double picasCena, boolean uzVietas) {
        if (uzVietas) return picasCena;
        return picasCena + piegadesCena;
    }

    public double piedevasCena(String nosaukums) {
        if (nosaukums.equals("Siers")) return 1.50;
        if (nosaukums.equals("Šķiņķis")) return 2.00;
        if (nosaukums.equals("Sēnes")) return 1.00;
        return 0;
    }

    public double mercesCena(String nosaukums) {
        if (nosaukums.equals("Ķiploku")) return 0.80;
        if (nosaukums.equals("BBQ")) return 0.90;
        return 0;
    }
}

