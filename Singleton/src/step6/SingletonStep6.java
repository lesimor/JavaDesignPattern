package step6;
// p.204
// Printer를 인터페이스로 만들고 싶다면?
// 즉 Printer 클래스를 실제 구현하는 클래스를 싱글턴 패턴으로 이용하려면?
// but 정적 메서드는 인터페이스에서 사용할 수 없다.

// 아래와 같은 코드 불가능.
// interface Printer {
// 	public static void pirnt(String str);
// }
// class RealPrinter315 implements Printer {
// 	public synchronized static void print(String str){
// 		
// 	}
// }

class UsePrinter {
	public void doSomething(Printer printer){
		
	}
}

interface Printer {
	public void print(String str);
}

class RealPrinter315 implements Printer {
	private static Printer printer = null;
	private RealPrinter315() { }
	
	// 싱글턴 패턴을 사용.
	public static Printer getPrinter(){
		if(printer == null){
			printer = new RealPrinter315();
		}
		return printer;
	}
	
	@Override
	public void print(String str) {
		// 실제 프린터 하드웨어를 조작하는 코드.
		
	}

	
}

// 테스트용 가짜 프린터..
class FakePrinter implements Printer {
	private String str;

	@Override
	public void print(String str) {
		this.str = str;
	}
	
	public String get() {
		return str;
	}

}

public class SingletonStep6 {

}