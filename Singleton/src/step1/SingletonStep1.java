package step1;

class User {
	private String name;
	
	public User(String name){
		this.name = name;
	}
	
	public void print(){
		Printer printer = Printer.getPrinter();
		printer.print(this.name + "(이)가 [" + printer.toString() + "]번 프린터를 사용하고 있습니다.");
	}
}

class Printer {
	private static Printer printer = null;
	
	// 외부에서 생성자를 호출할 수 없도록 private으로 선언.
	private Printer() { }
	
	// 인스턴스는 하나 만들어야하므로 외부에 제공해줄 메소드가 필요.
	// 정적 메소드로 선언
	// 정적 메소드는 정적 변수만 사용할 수 있다.
	// 인스턴스에 속하는 게 아닌 클래스에 속하는 것.
	public static Printer getPrinter(){
		// 기존에 프린터 객체가 생성이 안되어 있는 경우에 한해서만 객체 생성.
		if(printer == null){
			printer = new Printer();
		} 
		return printer;
	}
	
	public void print(String text){
		System.out.println(text);
	}
}

public class SingletonStep1 {
	private static final int User_num = 5;
	
	public static void main(String[] args) {
		User[] users = new User[User_num];
		for(int i = 0 ; i < User_num ; i++){
			users[i] = new User((i+1) + "번째 사람");	// User 인스턴스 생성.
			users[i].print();
		}
		// 프린터 인스턴스가 새로 생성되지 않고 모든 사람이 하나의 프린터 객체만 사용.
	}

}