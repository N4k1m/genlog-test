// START : ATFD = 0

public class ATFD3FullVisitor
{
    public void m1(Avion avion)
    {
        avion.ouvrirPorte(); // Ne fait pas augmenter ATFD (pas un accesseur)


        Voiture maVoiture;
        a = maVoiture.getPassagers(); // ATFD = 1 (ajout de la classe Voiture)

    	m2();
    }

    public void m2()
    {
        monCamion.stop();       // Ne fait pas augmenter ATFD (pas un accesseur)
        monCamion.poids++;      // ATFD = 2 (ajout de la classe Camion)
        monCamion.getPoids();   // Ne fait pas augmenter ATFD (même si accesseur)

        a = 1;
    }

    public void m3()
    {
        System.out.println("Poids = " + monCamion.poids); // Ne fait pas augmenter ATFD
        System.out.get();   // Ne fait pas augmenter ATFD
        a = 2;
        b = 1;
    }

    public void m4()
    {
        InnerClass inner = new InnerClass();
        inner.innerVar++; // Ne fait pas augmenter ATFD car inner class
    }

    public void m5(Camion c)
    {
        m4();

        /* "Camion c" masque la variable membre "Bateau c"
         * Comme la classe Camion a déjà été ajoutée, ATFD n'augmente pas
         */
        c.getPoids();

        b = 2;
    }

    public class InnerClass
    {
        public int innerVar;

        /* Ici rien ne masque la variable membre "Bateau c"
         * ATFD = 3 (Ajout de la classe Bateau)
         */
        int poids = c.getPoids();
    }

    private int a;
    private int b;
    private Bateau c;
    private Camion monCamion;
}