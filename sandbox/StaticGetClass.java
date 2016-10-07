class StaticGetClass {

    private static class Holder {
	private Class<?> value;
	Holder(Class<?> value) {
	    this.value = value;
	}
    }

    public static Holder HOLDER = new Holder(StaticGetClass.class);
    
}
