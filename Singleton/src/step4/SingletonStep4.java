// 메서드 동기화를 이용한 다중 스레드 문제 해결방법.
package step4;

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
	
	// 메서드 동기화
	// 해당 메서드는 한번에 한 스레드밖에 접근할 수 없다.
	public synchronized static Printer getPrinter(){
		if(printer == null){
			// 객체가 중복되어 생성되는 경우를 만들기 위해 일부러 지연 발생.
			// 동기화했기 때문에 이 메서드를 실행하고 있는 스레드는 메서드가 끝나기 전까지
			// 다른 스레드의 간섭을 막을 수 있다.
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

public class SingletonStep4 {
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