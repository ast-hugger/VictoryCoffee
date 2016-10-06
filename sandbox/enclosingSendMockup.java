class Sample {

	public static class Obj {
	}

	public static class SubObj extends Obj {
		public Obj[] enclosingObjects;
	}

	public Obj test(Obj obj) {
	    // SubObj sub = (SubObj) obj;
	    return ((SubObj) obj).enclosingObjects[0];
	}

}
