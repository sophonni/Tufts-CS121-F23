public class ObjectProvidedForChaining {
    Object o;
    public ObjectProvidedForChaining(Object o)
    {
        this.o = o;
    }
    
    public ObjectProvidedForChaining isNotNull()
    {
        if (this.o == null)
        {
            throw new IllegalArgumentException("Error: Provided object is null.");
        }
        else
        {
            return this;
        }
    }

    public ObjectProvidedForChaining isNull()
    {
        if (this.o != null)
        {
            throw new IllegalArgumentException("Error: Provided object is not null.");
        }
        else
        {
            return this;
        }
    }

    //TODO: ask if this should be a physical or structural equality comparison
    public ObjectProvidedForChaining isEqualTo(Object o2)
    {
        if (!this.o.equals(o2))
        {
            throw new IllegalArgumentException("Error: Objects aren't equal.");
        }
        else
        {
            return this;
        }
    }

    public ObjectProvidedForChaining isNotEqualTo(Object o2)
    {
        if (this.o.equals(o2))
        {
            throw new IllegalArgumentException("Error: Objects are equal.");
        }
        else
        {
            return this;
        }
    }

    public ObjectProvidedForChaining isInstanceOf(Class<?> c)
    {   
        if (!c.isInstance(this.o))
        {
            throw new IllegalArgumentException("Error: No instace of given class.");
        }
        else
        {
            return this;
        }
    }
    
}
