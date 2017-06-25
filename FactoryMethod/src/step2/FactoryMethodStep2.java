package step2;

import java.util.ArrayList;
import java.util.List;

enum Direction { UP, DOWN }
enum SchedulingStrategyID { RESPONSE_TIME, THROUGHPUT, DYNAMIC }

// 스케줄러 팩토리 클래스.
class SchedulerFactory {
	public static ElevatorScheduler getScheduler(SchedulingStrategyID strategyID){
		// 각 객체를 매번 새로 생성하는 것보다 각 스케줄러를 싱글턴 패턴으로 생성하면 더 좋다.
		ElevatorScheduler scheduler = null;
		switch (strategyID){
		case RESPONSE_TIME:
			scheduler = new ResponseTimeScheduler();
			break;
		case THROUGHPUT:
			scheduler = new ThroughputScheduler();
			break;
		case DYNAMIC:
			int hour = 11; // 임의로 현재 시간을 가져온다.
			if(hour < 12)
				scheduler = new ResponseTimeScheduler();
			else
				scheduler = new ThroughputScheduler();
			break;
		}
		return scheduler;
	}
}

class ElevatorManager{
	private List<ElevatorController> controllers;
//	private ThroughputScheduler scheduler;  
	private SchedulingStrategyID strategyID; // 스케줄러를 직접 갖고 있는 대신에 StrategyID만으로 스케줄러를 제어.
	
	public ElevatorManager(int controllerCount, SchedulingStrategyID strategyID){
		controllers = new ArrayList<ElevatorController>(controllerCount);
		for(int i = 0 ; i < controllerCount ; i++){
			ElevatorController controller = new ElevatorController(i);
			controllers.add(controller);
		}
		// scheduler = new ThroughputScheduler();
		this.strategyID = strategyID;  // 스케줄링 전략을 설정함.
	}
	
	public void setStrategyID(SchedulingStrategyID strategyID){
		this.strategyID = strategyID;
	}
	
	void requestElevator(int destination, Direction direction){
		// 엘리베이터를 선택.
		// int selectedElevator = scheduler.selectElevator(this, destination, direction);
		
		// 선택된 엘리베이터를 이동시킴.
		// controllers.get(selectedElevator).gotoFloor(destination);
		
		// 주어진 전략 ID에 해당되는 ElevatorScheduler를 사용.
		// 해결: 이제 전략을 변경하려면 SchedulerFactory메서드만 변경하면 된다.
		ElevatorScheduler scheduler = SchedulerFactory.getScheduler(strategyID);
		System.out.println(scheduler);
		int selectedElevator = scheduler.selectElevator(this, destination, direction);
		controllers.get(selectedElevator).gotoFloor(destination);
		
		
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

interface ElevatorScheduler {
	public int selectElevator(ElevatorManager manager, int destination, Direction direction);
}

class ThroughputScheduler implements ElevatorScheduler{
	public int selectElevator(ElevatorManager manager, int destination, Direction direction){
		// 처리량 기준으로 엘리베이터를 선택하는 로직 구현.
		return 0;
	}
}

class ResponseTimeScheduler implements ElevatorScheduler{
	public int selectElevator(ElevatorManager manager, int destination, Direction direction){
		// 응답시 기준으로 엘리베이터를 선택하는 로직 구현.
		return 0;
	}
}

public class FactoryMethodStep2 {
	
	public static void main(String[] args) {
		// response time 기준으로 elevator manager생성.
		ElevatorManager emWithResponseTimeScheduler = new ElevatorManager(2, SchedulingStrategyID.RESPONSE_TIME);
		emWithResponseTimeScheduler.requestElevator(10, Direction.UP);
		
		// 처리량 기준으로 elevator manager 생성.
		ElevatorManager emWithThroughputScheduler = new ElevatorManager(2, SchedulingStrategyID.THROUGHPUT);
		emWithThroughputScheduler.requestElevator(10, Direction.UP);
		
		

	}

}
