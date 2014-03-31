package model;

public class Duplet<A,B> {
	public A a;
	public B b;
	
	public Duplet(A a , B b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString() {
		return "[" + a + "," + b + "]";
	}
}
