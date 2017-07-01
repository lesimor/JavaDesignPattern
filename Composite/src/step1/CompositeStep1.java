/* #상황
 * -컴퓨터와 그것을 구성하는 본체, 키보드, 모니터 클래스를 생성.
 * */
package step1;

class Keyboard {
	private int price;
	private int power;
	
	public Keyboard(int power, int price){
		this.power = power;
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
	
	public int getPower(){
		return power;
	}
}

class Body {
	private int price;
	private int power;
	
	public Body(int power, int price){
		this.power = power;
		this.price = price;
	}
	
	int getPrice(){
		return price;
	}
	
	int getPower(){
		return power;
	}
}

class Monitor {
	private int price;
	private int power;
	
	public Monitor(int power, int price){
		this.power = power;
		this.price = price;
	}
	
	int getPrice(){
		return price;
	}
	
	int getPower(){
		return power;
	}
}

class Computer{
	private Body body;
	private Keyboard keyboard;
	private Monitor monitor;
	
	public void addBody(Body body){
		this.body = body;
	}
	
	public void addKeyboard(Keyboard keyboard){
		this.keyboard = keyboard;
	}
	
	public void addMonitor(Monitor monitor){
		this.monitor = monitor;
	}
	
	public int getPrice(){
		int bodyPrice = body.getPrice();
		int monitorPrice = monitor.getPrice();
		int keyboardPrice = keyboard.getPrice();
		
		// 문제점
		// 컴퓨터 클래스의 부품으로 스피커를 추가한다면?
		// 다른 부품과 마찬가지로 스피커 객체를 생성하고 리턴값에 스피커의 가격을 붙여준다.
		// -> 새로운 부품이 추가될때마다 코드를 수정해야한다는 문제.
		
		return bodyPrice + monitorPrice + keyboardPrice;
	}
	
	public int getPower(){
		int bodyPower = body.getPower();
		int monitorPower = monitor.getPower();
		int keyboardPower = keyboard.getPower();
		
		return bodyPower + monitorPower + keyboardPower;
	}
	
}
public class CompositeStep1 {

	public static void main(String[] args) {
		// 컴퓨터의 부품으로 Body, Keyboard, Monitor 객체를 생성.
		Body body = new Body(100, 70);
		Keyboard keyboard = new Keyboard(5,2);
		Monitor monitor = new Monitor(20, 30);
		
		// Computer 객체를 생성하고 부품 객체들을 설정
		Computer computer = new Computer();
		computer.addBody(body);
		computer.addKeyboard(keyboard);
		computer.addMonitor(monitor);
		
		
		// 컴퓨터의 가격과 전력 소비량을 구함.
		int computerPrice = computer.getPrice();
		int computerPower = computer.getPower();
		System.out.println("Computer Power: " + computerPower + "W");
		System.out.println("Computer Price: " + computerPrice + "만 원");
		

	}

}
