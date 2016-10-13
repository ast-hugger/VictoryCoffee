public class SampleVarargs {
    public SampleVarargs(Object receiver, Object... args) {
	int argc = args.length;
    }

    public static void main() {
	Object r = Integer.valueOf(42);
	Object a1 = Integer.valueOf("one");
	Object a2 = Integer.valueOf("two");
	SampleVarargs s = new SampleVarargs(r, a1, a2);
    }
}
    
