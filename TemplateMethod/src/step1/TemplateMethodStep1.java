/* 상황: door를 제어하는 모터 클래스를 설계하고자 한다. 
 * door를 움직이는 데에는 여러 회사의 모터가 사용될 수 있다.
 * */
package step1;

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

class HyundaiMotor{
	private Door door;
	private MotorStatus motorStatus;
	
	public HyundaiMotor(Door door){
		this.door = door;
		motorStatus = MotorStatus.STOPPED;	// 초기에는 멈춘 상태로 세팅.
	}
	
	private void moveHyundaiMotor(Direction direction){
		// Hyundai motor를 구동시킴.
	}
	
	public MotorStatus getMotorStatus(){
		return motorStatus;
	}
	
	private void setMotorStatus(MotorStatus motorStatus){
		this.motorStatus = motorStatus;
	}
	
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

// 문제점1. 현대 모터 이외의 다른 제조사의 모터 클래스를 정의하고자 한다면?
// 코드의 대부분을 제외하고 나머지 부분은 거의 현대 모터 클래스와 동일하다.
class LGMotor{
	  private Door door;
	  private MotorStatus motorStatus;
	  
	  public LGMotor(Door door){
	    this.door = door;
	    motorStatus = MotorStatus.STOPPED;  // 초기에는 멈춘 상태로 세팅.
	  }
	  
	  private void moveLGMotor(Direction direction){
	    // LG motor를 구동시킴.
		// 모터를 움직이는 코드만 다르다.
		// 나머지 코드는 현대와 동일.
	  }
	  
	  public MotorStatus getMotorStatus(){
	    return motorStatus;
	  }
	  
	  private void setMotorStatus(MotorStatus motorStatus){
	    this.motorStatus = motorStatus;
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


public class TemplateMethodStep1 {

	public static void main(String[] args) {
		Door door = new Door();
		HyundaiMotor hyundaiMotor = new HyundaiMotor(door);
		hyundaiMotor.move(Direction.UP);

	}

}