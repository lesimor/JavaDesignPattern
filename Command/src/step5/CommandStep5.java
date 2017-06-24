/* 검증
 * : 진짜 Button 클래스를 변경하지 않고도 새로운 기능을 추가할 수 있을까?
 * : -> 램프를 끄는 기능도 추가해보자.
 * */
package step5;

interface Command {
	public abstract void execute();
}

// 버튼 클래스는 step4와 동일.
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
	public void turnOff(){
		System.out.println("Lamp Off");
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

class LampOffCommand implements Command {
	private Lamp theLamp;
	
	public LampOffCommand(Lamp theLamp){
		this.theLamp = theLamp;
	}

	@Override
	public void execute() {
		theLamp.turnOff();
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

public class CommandStep5 {

	public static void main(String[] args) {
		Lamp lamp = new Lamp();
		Command lampOnCommand = new LampOnCommand(lamp);
		Command lampOffCommand = new LampOffCommand(lamp);
		
		Button button1 = new Button(lampOnCommand);		// 램프를 켜는 커맨드를 설정함.
		button1.pressed();								// 버튼을 누르면 램프가 켜짐.
		
		button1.setCommand(lampOffCommand);				// 램프를 끄는 커맨드를 설정함.
		button1.pressed();								// 버튼을 누르면 램프가 꺼짐.
	}

}