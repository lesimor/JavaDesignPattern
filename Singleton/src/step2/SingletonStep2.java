// 다중 스레드로 인한 싱글턴 객체 여러개 생성되는 문제.
package step2;

class UserThread extends Thread {
	public UserThread(String name){
		super(name);
	}
	
	public void run(){
		Printer printer = Printer.getPrinter();
		printer.print(Thread.currentThread().getName() + "(이)가 [" + printer.toString() + "]번 프린터를 사용하고 있습니다.");
	}
}

class Printer {
	private static Printer printer = null;
	
	// 외부에서 생성자를 호출할 수 없도록 private으로 선언.
	private Printer() { }
	
	public static Printer getPrinter(){
		if(printer == null){
			// 객체가 중복되어 생성되는 경우를 만들기 위해 일부러 지연 발생.
			try {
				Thread.sleep(1);
			} catch(InterruptedException e) { }
			printer = new Printer();
		} 
		return printer;
	}
	
	public void print(String text){
		System.out.println(text);
	}
}

public class SingletonStep2 {
	private static final int User_num = 5;
	
	public static void main(String[] args) {
		UserThread[] users = new UserThread[User_num];
		for(int i = 0 ; i < User_num ; i++){
			users[i] = new UserThread((i+1) + "번째 사람");	// User 인스턴스 생성.
			users[i].start();
		}
		// 프린터 인스턴스가 여러개 생성되는 문제 발생.
	}
}