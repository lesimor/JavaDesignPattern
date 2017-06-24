/* 눌리면 특정 기능을 수행하는 버튼
 * 문제점: 
 * 1. 누군가가 버튼을 눌렀을 떄 램프가 켜지는 대신 다른 기능을 실행하게 하려면?
 * 2. 버튼을 처음 눌렀을 떄는 램프를 켜고, 두번째 눌렀을 때는 알람을 동작하게 하려면?
 * */
package step1;

class Lamp {
	public void turnOn(){
		System.out.println("Lamp on!");
	}
}

class Button {
	private Lamp theLamp;
	
	public Button(Lamp theLamp){
		this.theLamp = theLamp;
	}
	
	public void pressed(){
		theLamp.turnOn();
	}
}

public class CommandStep1 {

	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Button lampButton = new Button(lamp);
		lampButton.pressed();

	}

}