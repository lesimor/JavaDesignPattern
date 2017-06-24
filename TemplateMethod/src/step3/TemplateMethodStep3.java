// 해결책: 중복이 많은 메소드마저 추상클래스에 정의한다!!
package step3;

enum DoorStatus { CLOSED, OPENED }
enum MotorStatus { MOVING, STOPPED }
enum Direction { UP, DOWN }

class Door {
	private DoorStatus doorStatus;
	
	public Door(){
		doorStatus = DoorStatus.CLOSED;
	}
	
	public DoorStatus getDoorStatus(){
		return this.doorStatus;
	}
	
	public void close(){
		doorStatus = DoorStatus.CLOSED;
	}
	
	public void open(){
		doorStatus = DoorStatus.OPENED;
	}
}

abstract class Motor{
	protected Door door;
	private MotorStatus motorStatus;
	public Motor(Door door){
		door = door;
		motorStatus = motorStatus.STOPPED;
	}
	
	protected MotorStatus getMotorStatus(){
		return motorStatus;
	}
	
	protected void setMotorStatus(MotorStatus motorStatus){
		this.motorStatus = motorStatus;
	}
	
	// 문제점: 중복이 많은 메소드도 추상클래스에 정의한다!!
	public void move(Direction direction){
		MotorStatus motorStatus = getMotorStatus();
		if(motorStatus == MotorStatus.MOVING)
			return;
		
		DoorStatus doorStatus = door.getDoorStatus();
		if(doorStatus == DoorStatus.OPENED)
			door.close();
		
		moveMotor(direction);
		setMotorStatus(MotorStatus.MOVING);
	}
	
	protected abstract void moveMotor(Direction direction);
}

// 현대 모터.
class HyundaiMotor extends Motor{
	private Door door;
	private MotorStatus motorStatus;
	
	public HyundaiMotor(Door door){
		super(door);
	}
	
	protected void moveMotor(Direction direction){
		// Hyundai motor를 구동시킴.
	}
	
	
}

class LGMotor extends Motor{
	  private Door door;
	  private MotorStatus motorStatus;
	  
	  public LGMotor(Door door){
		 super(door);
	  }

	  protected void moveMotor(Direction direction){
	    // LG motor를 구동시킴.
	  }
}
public class TemplateMethodStep3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}