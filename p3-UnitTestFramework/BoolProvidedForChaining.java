public class BoolProvidedForChaining {
    boolean b;
    public BoolProvidedForChaining(boolean b)
    {
        this.b = b;
    }

    public BoolProvidedForChaining isEqualTo(boolean b2)
    {

        if (this.b != b2)
        {
            throw new IllegalArgumentException("Error: Compared bools aren't equal.");
        }
        else
        {
            return this;
        }
    }

    public BoolProvidedForChaining isTrue()
    {
        if (!this.b)
        {
            throw new IllegalArgumentException("Error: Bool is not True.");
        }
        else
        {
            return this;
        }
    }

    public BoolProvidedForChaining isFalse()
    {
        if (this.b)
        {
            throw new IllegalArgumentException("Error: Bool is not False.");
        }
        else
        {
            return this;
        }
    }
}
