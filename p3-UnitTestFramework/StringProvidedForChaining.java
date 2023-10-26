public class StringProvidedForChaining {
    String s;
    public StringProvidedForChaining(String s)
    {
        this.s = s;
    }
    
    public StringProvidedForChaining isNotNull()
    {
        if (this.s == null)
        {
            throw new IllegalArgumentException("Error: Provided object is null.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining isNull()
    {
        if (this.s != null)
        {
            throw new IllegalArgumentException("Error: Provided object is not null.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining isEqualTo(Object s2)
    {
        if (!this.s.equals(s2))
        {
            throw new IllegalArgumentException("Error: Objects aren't equal.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining isNotEqualTo(Object s2)
    {
        if (this.s.equals(s2))
        {
            throw new IllegalArgumentException("Error: Objects are equal.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining isInstanceOf(Class<?> c)
    {   
        if (!c.isInstance(this.s))
        {
            throw new IllegalArgumentException("Error: No instace of given class.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining startsWith(String s2)
    {
        if (!this.s.startsWith(s2))
        {
            throw new IllegalArgumentException("Error: String {" + s + "} does not start with {" + s2 + "}.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining isEmpty()
    {
        if (!this.s.isEmpty())
        {
            throw new IllegalArgumentException("Error: String {" + s + "} is not empty.");
        }
        else
        {
            return this;
        }
    }

    public StringProvidedForChaining contains(String s2)
    {
        if (!this.s.contains(s2))
        {
            throw new IllegalArgumentException("Error: String {" + s + "} does not contain {" + s2 + "}.");
        }
        else
        {
            return this;
        }
    }
}
