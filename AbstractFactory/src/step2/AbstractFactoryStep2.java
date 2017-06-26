package step2;

enum DoorStatus { CLOSED, OPENED }
enum MotorStatus { MOVING, STOPPED }
enum Direction { UP, DOWN }
enum VendorID { LG, HYUNDAI, SAMSUNG }

abstract class ElevatorFactory {
	public abstract Motor createMotor();
	public abstract Door createDoor(); 
}

// LG 부품을 생성하는 팩토리.
class LGElevatorFactory extends ElevatorFactory {

	@Override
	public Motor createMotor() {
		// TODO Auto-generated method stub
		return new LGMotor();
	}

	@Override
	public Door createDoor() {
		// TODO Auto-generated method stub
		return new LGDoor();
	}
	
}

// Hyundai 부품을 생성하는 팩토리.
class HyundaiElevatorFactory extends ElevatorFactory {

	@Override
	public Motor createMotor() {
		// TODO Auto-generated method stub
		return new HyundaiMotor();
	}

	@Override
	public Door createDoor() {
		// TODO Auto-generated method stub
		return new HyundaiDoor();
	}
	
}

// Samsung 부품을 생성하는 팩토리
class SamsungElevatorFactory extends ElevatorFactory {

	@Override
	public Motor createMotor() {
		// TODO Auto-generated method stub
		return new SamsungMotor();
	}

	@Override
	public Door createDoor() {
		// TODO Auto-generated method stub
		return new SamsungDoor();
	}
	
}
abstract class Door {
	private DoorStatus doorStatus;
	
	public Door(){
		doorStatus = DoorStatus.CLOSED;
	}
	
	public DoorStatus getDoorStatus(){
		return doorStatus;
	}
	
	public void close(){ // 템플릿 메서드.
		if(doorStatus == DoorStatus.CLOSED) // 이미 문이 열려 있으면 아무 동작을 하지 않음.
			return;
		
		doClose(); // 실제로 문을 닫는 동작을 수행. 하위 클래스에서 오버라이드될 것임.
		doorStatus = DoorStatus.CLOSED;
	}
	protected abstract void doClose();
	
	public void open(){ // 템플릿 메서드
		if(doorStatus == DoorStatus.OPENED) // 이미 문이 열려 있으면 아무 동작을 하지 않음.
			return;
		
		doOpen();
		doorStatus = DoorStatus.OPENED; // 문의 상태를 열림으로 기록.
		
	}
	protected abstract void doOpen();
}

class LGDoor extends Door {

	@Override
	protected void doClose() {
		System.out.println("LG Door is closed");
	}

	@Override
	protected void doOpen() {
		// TODO Auto-generated method stub
		System.out.println("LG Door is opened");
	}
	
}

class HyundaiDoor extends Door {

	@Override
	protected void doClose() {
		System.out.println("Hyundai Door is closed");
	}

	@Override
	protected void doOpen() {
		System.out.println("Hyundai Door is opened");
	}
	
}

// 삼성 모터 추가.
class SamsungDoor extends Door {

	@Override
	protected void doClose() {
		// TODO Auto-generated method stub
		System.out.println("Samsung Door is closed");
	}

	@Override
	protected void doOpen() {
		// TODO Auto-generated method stub
		System.out.println("Samsung Door is opened");
	}
	
}

class DoorFactory { // 팩토리 메서드 패턴을 사용.
	public static Door createDoor(VendorID vendorID){
		Door door = null;
		switch (vendorID){
		case LG:
			door = new LGDoor();
			break;
		case HYUNDAI:
			door = new HyundaiDoor();
			break;
		}
		return door;
	}
	
}

//템플릿메서드 패턴을 적용한 모터 추상 클래스.
abstract class Motor{
	protected Door door;
	private MotorStatus motorStatus;
	
	public Motor(){
		motorStatus = motorStatus.STOPPED;
	}
	
	public Motor(Door door){
		door = door;
		motorStatus = motorStatus.STOPPED;
	}
	
	public void setDoor(Door door){
		this.door = door;
	}
	
	protected MotorStatus getMotorStatus(){
		return motorStatus;
	}
	
	protected void setMotorStatus(MotorStatus motorStatus){
		this.motorStatus = motorStatus;
	}
	
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

//현대 모터.
class HyundaiMotor extends Motor{

	protected void moveMotor(Direction direction){
		// Hyundai motor를 구동시킴.
		System.out.println("Move Hyundai motor");
	}
	
	
}

class LGMotor extends Motor{
	
	protected void moveMotor(Direction direction){
	  // LG motor를 구동시킴.
		System.out.println("Move LG motor");
	}
}

class SamsungMotor extends Motor {

	@Override
	protected void moveMotor(Direction direction) {
		System.out.println("Move Samsung Motor");
	}
	
}

public class AbstractFactoryStep2 {

	public static void main(String[] args) {
		ElevatorFactory factory = null;
		String vendorName = "lg";
		if(vendorName.equalsIgnoreCase("LG"))
			factory = new LGElevatorFactory();
		else if(vendorName.equalsIgnoreCase("Hyundai"))
			factory = new HyundaiElevatorFactory();
		else
			factory = new SamsungElevatorFactory();
		
		Door door = factory.createDoor();
		Motor motor = factory.createMotor();
		motor.setDoor(door);
		
		door.open();
		motor.move(Direction.UP);
	}

}
