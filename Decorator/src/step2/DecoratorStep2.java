package step2;

abstract class Display{
	public abstract void draw();
}

class RoadDisplay extends Display {

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("기본 도로 표시.");
	}
	
}

abstract class DisplayDecorator extends Display {
	private Display decoratedDisplay;
	
	public DisplayDecorator(Display decoratedDisplay){
		this.decoratedDisplay = decoratedDisplay;
	}
	
	public void draw(){
		decoratedDisplay.draw();
	}
}

class LaneDecorator extends DisplayDecorator {
	public LaneDecorator(Display decoratedDisplay){
		super(decoratedDisplay);
	}
	
	public void draw(){
		super.draw();	// 설정된 기존 표시 기능을 수행.
		drawLane();		// 추가적으로 교통량 표시.
	}
	
	private void drawLane(){
		System.out.println("\t차선 표시.");
	}
}

class TrafficDecorator extends DisplayDecorator {
	public TrafficDecorator(Display decoratedDisplay){
		super(decoratedDisplay);
	}
	
	public void draw(){
		super.draw();	// 설정된 기존 표시 기능을 수행.
		drawTraffic();	// 추가적으로 교통량 표시.
	}
	
	private void drawTraffic(){
		System.out.println("\t교통량 표시.");
	}
}

public class DecoratorStep2 {
	
	public static void main(String[] args) {
		Display road = new RoadDisplay();
		road.draw();
		
		Display roadWithLane=  new LaneDecorator(new RoadDisplay());
		roadWithLane.draw();
		
		
		// 각각의 기능을 연쇄적으로 호출하여 요소를 조립할 수 있게 되었다.
		Display roadWithLaneTraffic = new TrafficDecorator(new LaneDecorator(new RoadDisplay()));
		roadWithLaneTraffic.draw();
	}

}