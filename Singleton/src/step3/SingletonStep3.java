// 초기화 시 바로 객체 생성하여 다중 스레드로 인한 문제 해결.
package step3;

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
	// 다중 스레드로 인한 문제를 해결하는 방법
	// 1. 초기화 시 바로 객체 생성.
	private static Printer printer = new Printer();
	
	// 외부에서 생성자를 호출할 수 없도록 private으로 선언.
	private Printer() { }
	
	public static Printer getPrinter(){ 
		return printer;
	}
	
	public void print(String text){
		System.out.println(text);
	}
}

public class SingletonStep3 {
	private static final int User_num = 5;
	
	public static void main(String[] args) {
		UserThread[] users = new UserThread[User_num];
		for(int i = 0 ; i < User_num ; i++){
			users[i] = new UserThread((i+1) + "번째 사람");	// User 인스턴스 생성.
			users[i].start();
		}
		// 프린터 인스턴스가 여러개 생성되는 문제 해.
	}
}