public class Assertion {
    /* You'll need to change the return type of the assertThat methods */
    static ObjectProvidedForChaining assertThat(Object o)
    {
        return new ObjectProvidedForChaining(o);
    }
    static StringProvidedForChaining assertThat(String s) {
        return new StringProvidedForChaining(s);
    }
    static BoolProvidedForChaining assertThat(boolean b) {
	return new BoolProvidedForChaining(b);
    }
    static IntProvidedForChaining assertThat(int i) {
	return new IntProvidedForChaining(i);
    }
}