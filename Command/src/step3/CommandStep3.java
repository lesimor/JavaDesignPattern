/* Step1 문제점 2에 대한 해결책:
 *  -> Lamp 클래스와 Alarm 클래스 인스턴스를 Button 인스턴스가 모두 갖고 있도록 한다
 *  -> Mode 변수를 이용해 모드를 변경하고 이에 따라 다른 동작이 실행되도록 한다.
 * 문제점:
 * 	-> 기능을 변경하기 위해 Button 클래스를 수정.
 *  -> 핵심적인 문제는 Button클래스를 수정했다는 거...
 *  
 * */
package step3;

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

enum Mode {LAMP, ALARM};

class Button {
	private Lamp theLamp;	// 램프 변수 생성.
	private Alarm theAlarm;	// 알람 변수 생성.
	private Mode theMode;
	
	public Button(Lamp thelamp, Alarm theAlarm){ 	// 생성자 변경 -> OCP 위배.
		this.theAlarm = theAlarm;  
		this.theLamp = thelamp;
	}
	
	public void setMode(Mode mode){
		this.theMode = mode;
	}
	
	public void pressed(){	
		switch (theMode) {
		case LAMP:	// 램프 모드인 경우 램프를 켠다.
			theLamp.turnOn();
			break;
		case ALARM:	// 알람 모드인 경우 알람을 울리게 한다.
			theAlarm.start();
			break;
		default:
			break;
		}
	}
}

public class CommandStep3 {
	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Alarm alarm = new Alarm();
		Button button = new Button(lamp, alarm);
		
		button.setMode(Mode.LAMP);	// 램프 모드로 변경.
		button.pressed();			// 램프가 켜진다.
		
		button.setMode(Mode.ALARM);	// 알람 모드로 변경
		button.pressed(); 			// 알람 모드를 설정했으므로 알람이 울린다.
	}

}