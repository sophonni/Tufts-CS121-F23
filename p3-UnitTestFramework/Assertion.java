public class Assertion {
    /* You'll need to change the return type of the assertThat methods */
    static ObjectProvidedForChaining assertThat(Object o)
    {
        return new ObjectProvidedForChaining(o);
    }
    static StringProvidedForChaining assertThat(String s) {
        return new StringProvidedForChaining(s);
    }
    static Object assertThat(boolean b) {
	throw new UnsupportedOperationException();
    }
    static Object assertThat(int i) {
	throw new UnsupportedOperationException();
    }
}