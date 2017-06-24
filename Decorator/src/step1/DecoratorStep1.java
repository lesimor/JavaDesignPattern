/* 도로 표시 방법 조합하기.
 * 기본 도로 위에 추가적인 요소(신호등, 횡단보도 등)와 함께 표시하기.
 * -> 여러가지 조합할 수 있는 경우의 수가 많은 요소들을 조합하려고 하는 경우.
 * -> 기본 기능 + 여러 요소를 선택적으로 조합하고자 할 때.
 * */

package step1;

class RoadDisplay {
	public void draw(){
		System.out.println("기본 도로 표시.");
	}
}

class RoadDisplayWithLane extends RoadDisplay {
	public void draw(){
		super.draw();
		drawLane();
	}
	
	private void drawLane(){
		System.out.println("차선 표시.");
	}
}

//문제점 1
//또다른 도로 표시 기능을 추가로 구현하고 싶다면? 
//-> 아래 클래스와 같은 형제 클래스를 생성하여 다른 기능을 똑같이 추가로 만든다.
class RoadDisplayWithTraffic extends RoadDisplay {
	public void draw(){
		super.draw();
		drawTraffic();
	}
	
	private void drawTraffic(){
		System.out.println("교통량 표시.");
	}
}

//문제점 2
//여러 기능을 조합하고 싶다면?
//-> RoadDisplayWithLaneCrossing, RoadDisplayWithLaneTraffic 등등 조합 클래스를 만들면 된다
//-> 그러나 일일이 모든 경우의 수마다 클래스를 만드는 것은 다소 무리.
class RoadDisplayWithLaneTraffic extends RoadDisplay {
	public void draw(){
		super.draw();
		drawLane();
		drawTraffic();
	}
	
	private void drawLane(){
		System.out.println("차선 표시.");
	}
	
	private void drawTraffic(){
		System.out.println("교통량 표시.");
	}
	 
}

public class DecoratorStep1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("------차선만 표시한다.------");
		new RoadDisplayWithLane().draw();
		System.out.println("------------------------");
		
		System.out.println("------교통량만 표시한다.------");
		new RoadDisplayWithTraffic().draw();
		System.out.println("--------------------------");
		
		System.out.println("---차선과 교통량을 모두 표시한다.---");
		new RoadDisplayWithLaneTraffic().draw();
		System.out.println("----------------------------");
	
	}

}