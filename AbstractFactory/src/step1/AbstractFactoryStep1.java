/* 상황: 엘리베이터 부품업체 변경하기
 * 기본적인 골격은 TemplateMethod 패턴을 따른다.
 * 모터와 같이 door 또한 각 제조사별로 기능이 비슷할 것이기 때문에 템플릿 메소드로 구현.
 * */
package step1;

enum DoorStatus { CLOSED, OPENED }
enum MotorStatus { MOVING, STOPPED }
enum Direction { UP, DOWN }

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

// 팩토리 메서드 패턴을 적용하여 모터클래스 생성.
enum VendorID { LG, HYUNDAI }

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

// 템플릿메서드 패턴을 적용한 모터 추상 클래스.
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

// 현대 모터.
class HyundaiMotor extends Motor{
	private Door door;
	private MotorStatus motorStatus;
	
	public HyundaiMotor(){
		super();
	}
	
	public HyundaiMotor(Door door){
		super(door);
	}
	
	protected void moveMotor(Direction direction){
		// Hyundai motor를 구동시킴.
		System.out.println("Move Hyundai motor");
	}
	
	
}

class LGMotor extends Motor{
	private Door door;
	private MotorStatus motorStatus;
	 
	public LGMotor(){
	  super();
	  
	}
	
	public LGMotor(Door door){
	  super(door);
	}
	
	protected void moveMotor(Direction direction){
	  // LG motor를 구동시킴.
		System.out.println("Move LG motor");
	}
}

class MotorFactory {
	public static Motor createMotor(VendorID vendorID){
		Motor motor = null;
		switch(vendorID){
		case LG:
			motor = new LGMotor();
			break;
		case HYUNDAI:
			motor = new HyundaiMotor();
			break;
		// 문제점 2
		// 새로운 제조 업체의 부품을 지원해야 하는 경우.
		//case SAMSUNG:
		//	motor = new SamsungMotor();
		//	break;
		}
		return motor;
	}
}

public class AbstractFactoryStep1 {

	public static void main(String[] args) {
		// LG의 부품을 사용해야 하는 경우.
		Door lgDoor = DoorFactory.createDoor(VendorID.LG);
		Motor lgMotor = MotorFactory.createMotor(VendorID.LG);
		lgMotor.setDoor(lgDoor);
		// 현대의 부품을 사용해야 하는 경우
		Door hyundaiDoor = DoorFactory.createDoor(VendorID.HYUNDAI);
		Motor hyundaiMotor = MotorFactory.createMotor(VendorID.HYUNDAI);
		hyundaiMotor.setDoor(hyundaiDoor);
		
		// 문제점1 
		// 다른 제조 업체의 부품을 사용해야 하는 경우.
		// 세 종류의 램프, 두 종류의 센서, 스피커, 두 종류의 버튼 등 총 10개의 부품을 사용해야 한다면?
		// ArrivalSensorFactory, DirectionLampFactory, WeightSensorFactory, SpeakFactory 등등
		// 각각의 부품마다 팩토리 메서드 클래스를 다 만들어줘야 한다.
		 
		
		// 문제점 요약
		// 기존의 팩토리 메서드 패턴을 이용한 객체 생성은, 관련 있는 여러 개의 객체를 일관성 있는 방식으로 생성하는 경우에
		// 많은 코드 변경이 발생한다.
		lgDoor.open();
		lgMotor.move(Direction.UP);
		
	}

}
