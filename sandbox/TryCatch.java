class TryCatch {

    static final class MyException extends RuntimeException {

	int value;

	MyException(int value) {
	    this.value = value;
	}
    }
    
    public int test() {
	try {
	    dostuff();
	    return 0;
	} catch (MyException e) {
	    return e.value;
	}
    }

    private void dostuff() {
	throw new MyException(42);
    }
}
