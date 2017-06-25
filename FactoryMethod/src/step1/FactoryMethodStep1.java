/* 상황
 * ElevatorManager를 통해 여러 대의 엘리베이터를 관리하고
 * ThroughputScheduler를 통해 처리량을 최대화하는 전략을 사용하고자 한다.
 * */
package step1;

import java.util.ArrayList;
import java.util.List;

enum Direction { UP, DOWN }

class ElevatorManager{
	private List<ElevatorController> controllers;
	private ThroughputScheduler scheduler;
	
	// 주어진 수만큼의 EleavtorController를 생성.
	public ElevatorManager(int controllerCount){
		controllers = new ArrayList<ElevatorController>(controllerCount);
		for(int i = 0 ; i < controllerCount ; i++){
			ElevatorController controller = new ElevatorController(i);
			controllers.add(controller);
		}
		scheduler = new ThroughputScheduler();
	}
	
	void requestElevator(int destination, Direction direction){
		// 엘리베이터를 선택.
		int selectedElevator = scheduler.selectElevator(this, destination, direction);
		
		// 선택된 엘리베이터를 이동시킴.
		controllers.get(selectedElevator).gotoFloor(destination);
		
		// 문제점 1.
		// 다른 스케줄링 전략을 쓰고자 한다면?
		// -> 별도의 스케줄러 클래스를 정의한 후 인스턴스를 생성하여 사용.
		// 문제점 2.
		// 프로그램 실행 중에 스케줄링을 변경하는 동적 스케줄링 전략을 사용하고자 한다면?
		// 스트래티지 패턴을 이용하여 scheduler를 구현하여 scheduler를 언제든 바꿀 수 있도록 설계.
		// -> 그러나 requesetElevator내에서 로직을 따로 구현해야 한다.
		// -> OCP 원칙 위배.(가장 큰 문제)
		
	}
}

class ElevatorController {
	private int id;
	private int curFloor;
	
	public ElevatorController(int id){
		this.id = id;
		curFloor = 1;
	}
	
	public void gotoFloor(int destination){
		System.out.println("Elevator [" + id + "] Floor: " + curFloor);
		
		// 현재 층 갱신, 목적지 층으로 이동함
		curFloor = destination;
		System.out.println(" ==> " + curFloor);
	}
	
}


class ThroughputScheduler {
	public int selectElevator(ElevatorManager manager, int destination, Direction direction){
		// 임의로 선택.
		return 0;
	}
}
public class FactoryMethodStep1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
