public class IntProvidedForChaining {
    int i;
    public IntProvidedForChaining(int i)
    {
        this.i = i;
    }

    public IntProvidedForChaining isEqualTo(int i2)
    {

        if (this.i != i2)
        {
            throw new IllegalArgumentException("Error: {" + this.i + "} is not euqal to {" + i2 + "}.");
        }
        else
        {
            return this;
        }
    }

    public IntProvidedForChaining isLessThan(int i2)
    {
        if (this.i >= i2)
        {
            throw new IllegalArgumentException("Error: {" + this.i + "} is not less than {" + i2 + "}.");
        }
        else
        {
            return this;
        }
    }

    public IntProvidedForChaining isGreaterThan(int i2)
    {
        if (this.i <= i2)
        {
            throw new IllegalArgumentException("Error: {" + this.i + "} is not greater than {" + i2 + "}.");
        }
        else
        {
            return this;
        }
    }
}
