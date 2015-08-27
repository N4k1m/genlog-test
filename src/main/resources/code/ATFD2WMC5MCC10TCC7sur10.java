// START : ATFD = 0

public class ATFD2TCC4sur10
{    
    public void m1(Avion avion) throws Exception
    {
        avion.ouvrirPorte(); // Ne fait pas augmenter ATFD (pas un accesseur)


        Voiture maVoiture = new Voiture();
        a = maVoiture.getPassagers(); // ATFD = 1 (ajout de la classe Voiture)

        switch(a)
        {
            case 1:
                System.out.println("Voiture de sport");
                break;
            case 4:
                System.out.println("Voiture familiale");
                break;
            default:
                System.out.println("Voiture type inconnu");
        }

    	m2();
    }
    
    public void m2()
    {
        this.monCamion.stop();       // Ne fait pas augmenter ATFD (pas un accesseur)
        this.monCamion.poids++;      // ATFD = 2 (ajout de la classe Camion)
        this.monCamion.getPoids();   // Ne fait pas augmenter ATFD (même si accesseur)

        a = 1;
        b = 2;
    }

    public void m3()
    {
        System.out.println("Poids = " + this.monCamion.poids); // Ne fait pas augmenter ATFD
        System.out.get();   // Ne fait pas augmenter ATFD
        a = 2;
        b = 1;
    }

    public void m4()
    {
        InnerClass inner = new InnerClass();
        inner.innerVar++; // Ne fait pas augmenter ATFD car inner class

        if (c == null)
            c = new Bateau();
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
    }

    private int a;
    private int b;
    private Bateau c;
    private Camion monCamion;
}