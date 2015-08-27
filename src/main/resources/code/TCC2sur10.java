class TCC2sur10
{
    private int x, y;
    
    public void methodA()
    {
        methodB();
    }
    
    public void methodB()
    {
        x = 1;
    }
    
    public void methodC()
    {
        y = 1;
    }
    
    public void methodD()
    {
        y = 2;

        methodE();
    }

    public int methodE()
    {
        // No variables access
    }
}