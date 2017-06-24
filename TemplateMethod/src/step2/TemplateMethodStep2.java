// 해결책: motor를 추상클래스로 만들어 코드의 재사용성을 높이고 다른 부분만 따로 처리하면 되지 않을까?
// 각 클래스의 코드가 다소 간결해짐. -> 중복 제거.
// 문제점: 메소드 마저도 코드가 비슷하다....
package step2;

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
}

// 현대 모터.
class HyundaiMotor extends Motor{
	private Door door;
	private MotorStatus motorStatus;
	
	public HyundaiMotor(Door door){
		super(door);
	}
	
	private void moveHyundaiMotor(Direction direction){
		// Hyundai motor를 구동시킴.
	}
	
	// 문제점: 메소드 마저도 다른 클래스의 같은 메소드와 코드가 너무나도 비슷하다....
	public void move(Direction direction){
		MotorStatus motorStatus = getMotorStatus();
		if(motorStatus == MotorStatus.MOVING)
			return;
		
		DoorStatus doorStatus = door.getDoorStatus();
		if(doorStatus == DoorStatus.OPENED)
			door.close();
		
		moveHyundaiMotor(direction);
		setMotorStatus(MotorStatus.MOVING);
	}
}

class LGMotor extends Motor{
	  private Door door;
	  private MotorStatus motorStatus;
	  
	  public LGMotor(Door door){
		 super(door);
	  }
	  
	  private void moveLGMotor(Direction direction){
	    // LG motor를 구동시킴.
		// 모터를 움직이는 코드만 다르다.
		// 나머지 코드는 현대와 동일.
	  }
	  
	  public void move(Direction direction){
	    MotorStatus motorStatus = getMotorStatus();
	    if(motorStatus == MotorStatus.MOVING)
	      return;
	    
	    DoorStatus doorStatus = door.getDoorStatus();
	    if(doorStatus == DoorStatus.OPENED)
	      door.close();
	    
	    moveLGMotor(direction);				// 이 부분을 제외한 나머지는 현대와 동일.
	    setMotorStatus(MotorStatus.MOVING);
	  }
}
public class TemplateMethodStep2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	}

}