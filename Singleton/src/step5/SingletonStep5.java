// p.202
// 정적 클래스를 싱글턴 패턴처럼 사용.
// 정적 클래스란 정적 메소드로만 이루어진 클래스를 의미.

// 객체를 전혀 생성하지 않고 메서드를 사용.
// 런타임이 아닌 컴파일 타임에 바인딩 되므로 성능 면에서 우수.
package step5;

class Printer {
	private static int counter = 0;
	public synchronized static void print(String str){
		counter++;
		System.out.println(str + counter);
	}
}

class UserThread extends Thread {
	public UserThread(String name){
		super(name);
	}
	
	public void run(){
		Printer.print(Thread.currentThread().getName() + " print using " + ".");
	}
}

public class SingletonStep5 {
	private static final int THREAD_NUM = 5;
	public static void main(String[] args) {
		UserThread[] users = new UserThread[THREAD_NUM];
		for(int i = 0 ; i < THREAD_NUM ; i++){
			users[i] = new UserThread((i + 1) + "번 스레드");
			users[i].start(); // 스레드 실행.
		}
	}

}