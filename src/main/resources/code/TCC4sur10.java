class TCC4sur10
{
    private int x, y;
    
    public void methodA(Avion test)
    {
        test.decolle();
        this.methodB();
    }
    
    public void methodB()
    {
        this.x = 1;
    }
    
    public void methodC()
    {
        x = 2;
        y = 1;
    }
    
    public void methodD()
    {
        y = 2;

        methodE();
    }

    public void methodE()
    {
        // No variables access
    }
}