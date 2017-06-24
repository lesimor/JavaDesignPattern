/* 해결책
 * : Button 클래스의 pressed 메서드를 직접 구현하는 대신
 * : 버튼을 눌렀을 때 실행될 기능을 Button 클래스 외부에서 제공받아 캡슐화하여 pressed 메서드에서 호출.
 * : 즉 Button 클래스의 메소드를 수정하지 않고도 다양한 기능을 수행할 수 있어야 함.
 * */
package step4;

interface Command {
	public abstract void execute();
}

// 모두 Command 클래스를 사용함
// 인터페이스를 구현한 클래스를 사용하지 않는다
// DIP 원칙 적용 -> 구체적인 클래스보다 추상적인 클래스에 의존한다.
class Button {
	private Command theCommand;
	
	public Button(Command theCommand){
		setCommand(theCommand);
	}
	
	public void setCommand(Command newCommand){
		this.theCommand = newCommand;
	}
	
	public void pressed(){
		theCommand.execute();
	}
}

class Lamp {
	public void turnOn(){
		System.out.println("Lamp On");
	}
}

class LampOnCommand implements Command {
	private Lamp theLamp;
	
	public LampOnCommand(Lamp theLamp) {
		this.theLamp = theLamp;
	}
	
	@Override
	public void execute() {
		theLamp.turnOn();
	}
}

class Alarm {
	public void start(){
		System.out.println("Alarming...");
	}
}

class AlarmOnCommand implements Command {
	private Alarm theAlarm;
	
	public AlarmOnCommand(Alarm theAlarm){
		this.theAlarm = theAlarm;
	}

	@Override
	public void execute() {
		theAlarm.start();
	}
}

public class CommandStep4 {

	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Command lampOnCommand = new LampOnCommand(lamp);
		
		Button button1 = new Button(lampOnCommand);	// 램프를 켜는 커맨드를 설정함.
		button1.pressed();	// 버튼을 누르면 램프 기능이 실행됨.
		
		Alarm alarm = new Alarm();
		Command alarmOnCommand = new AlarmOnCommand(alarm);
		
		Button button2 = new Button(alarmOnCommand);	// 알람을 울리는 커맨드로 설정함.
		button2.pressed(); 								// 버튼이 눌리면 알람을 울리는 기능이 실행됨.
		
		button2.setCommand(lampOnCommand);				// 램프 커맨드를 설정.
		button2.pressed();								// 이제는 램프기능이 실행됨.
	}

}