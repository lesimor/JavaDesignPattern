/* 해결책 -> 상태 클래스를 싱글턴 패턴으로 디자인.
 * 각각의 상태 클래스는 하나씩밖에 생성되지 않는다.
 * 
 * 스테이트 패턴은 상태에 따라 동일한 작업이 다른 방식으로 실행될 때 해당 상태가 작업을 수행하도록 위임하는 디자인 패턴.
 * */
package step4;

interface State {
	public void on_button_pushed(Light light);
	public void off_button_pushed(Light light);
}

class ON implements State {
	private static ON on = new ON();	// 외부에서 참조할 수 있도록 정적 변수로 선언.(싱글턴)
										// 바로 assign하면 객체 참조와 동시에 생성. -> null 체크 할 필요 없음..
	private ON(){}	// 생성자를 private으로 선언하여 외부에서 객체를 생성하지 못하도록(싱글턴)
	
	public static ON getInstance(){
		return on;
	}
	
	@Override
	public void on_button_pushed(Light light) {
		System.out.println("취침등 모드로 전환");
		// 문제점: 상태를 전환할 때마다 새로운 객체를 생성하서 메모리가 낭비.
		light.setState(SLEEPING.getInstance());
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("꺼짐!!");
		// 문제점: 상태를 전환할 때마다 새로운 객체를 생성하서 메모리가 낭비.
		light.setState(OFF.getInstance());
		
	}
	
}

class OFF implements State {
	public static OFF off = new OFF();
	private OFF(){ }
	
	public static OFF getInstance(){
		return off;
	}
	
	@Override
	public void on_button_pushed(Light light) {
		System.out.println("켜짐!!");
		light.setState(ON.getInstance());
		
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("반응 없음");
	}
	
}

class SLEEPING implements State {
	public static SLEEPING sleeping = new SLEEPING();
	private SLEEPING(){ }
	
	public static SLEEPING getInstance(){
		return sleeping;
	}
	@Override
	public void on_button_pushed(Light light) {
		System.out.println("켜짐!");
		light.setState(ON.getInstance());
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("꺼짐!");
		light.setState(OFF.getInstance());
	}
	
}
class Light {
	
	/* 구체적인 클래스를 참조하지 않는다.
	 * 이는 상태가 새로운 상태로 교체되더라도 Light 클래스는 전혀 영향을 받지 않는다는 의미
	 * */
	private State state;
	public Light(){
		state = OFF.getInstance();
	}
	
	public void setState(State state){	
		this.state = state;
	}
	
	public void on_button_pushed(){
		state.on_button_pushed(this);
	}
	
	public void off_button_pushed(){
		state.off_button_pushed(this);
	}
	
}
public class StateStep4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Light light = new Light();
		light.on_button_pushed();
		light.on_button_pushed();
		light.on_button_pushed();
		light.on_button_pushed();
		light.on_button_pushed();
		light.off_button_pushed();
	}

}