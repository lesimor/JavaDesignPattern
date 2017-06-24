/* Step1 문제점 1에 대한 해결책:
 * -> 버튼과 연결할 새로운 클래스 추가.
 * -> 버튼 클래스 수정.
 * 문제점:
 * -> 기능을 변경하기 위해 기존의 Button클래스를 변경하는 것은 Open Close 원칙 위배.
 * */
package step2;

class Lamp {
	public void turnOn(){
		System.out.println("Lamp on!");
	}
}

//알람 클래스 추가.
class Alarm{
	public void start(){
		System.out.println("Alarming...");
	}
}

class Button {
//	private Lamp theLamp;
	private Alarm theAlarm;
	
	public Button(Alarm theAlarm){ // 인자 변경. -> OCP 위배.
		this.theAlarm = theAlarm;  // 변경.	   -> OCP 위배.
	}
	
	public void pressed(){
		theAlarm.start();			// 메소드 변경.
	}
}

public class CommandStep2 {

	public static void main(String[] args) {
		Alarm alarm = new Alarm();
		alarm.start();
//		Button lampButton = new Button(alarm);
//		lampButton.pressed();

	}

}