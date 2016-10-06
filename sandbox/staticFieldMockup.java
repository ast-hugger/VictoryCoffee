class StaticFieldExample {

    public static String FOO;
    public static String BAR = "bar";

    public String test() {
	FOO = "Hello";
	return BAR;
    }
}
