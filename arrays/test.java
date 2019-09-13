package arrays;

public class test {

	public static void main(String args[]) {
		System.out.println("Hi");
		test t = new test();
		t.f1();
		int b = t.f2();
		System.out.println(b);
	}

	public void f1() {
		System.out.println("Function 1");
	}

	public int f2() {
		return 2;
	}

}
