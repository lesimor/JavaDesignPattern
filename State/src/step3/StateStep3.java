/* 해결책 -> 변하는 부분을 찾아서 캡슐화.
 * 상태가 얼마든지 변화, 추가될 수 있으므로 상태 클래스를 추가한다.
 * 상태 변화에 따른 변화를 상태 클래스 메소드에 위임.
 * */
package step3;
interface State {
	public void on_button_pushed(Light light);
	public void off_button_pushed(Light light);
}

class ON implements State {

	@Override
	public void on_button_pushed(Light light) {
		System.out.println("취침등 모드로 전환");
		// 문제점: 상태를 전환할 때마다 새로운 객체를 생성하서 메모리가 낭비.
		light.setState(new SLEEPING());
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("꺼짐!!");
		// 문제점: 상태를 전환할 때마다 새로운 객체를 생성하서 메모리가 낭비.
		light.setState(new OFF());
		
	}
	
}

class OFF implements State {

	@Override
	public void on_button_pushed(Light light) {
		System.out.println("켜짐!!");
		light.setState(new ON());
		
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("반응 없음");
	}
	
}

class SLEEPING implements State {

	@Override
	public void on_button_pushed(Light light) {
		System.out.println("켜짐!");
		light.setState(new ON());
	}

	@Override
	public void off_button_pushed(Light light) {
		System.out.println("꺼짐!");
		light.setState(new OFF());
	}
	
}
class Light {
	
	/* 구체적인 클래스를 참조하지 않는다.
	 * 이는 상태가 새로운 상태로 교체되더라도 Light 클래스는 전혀 영향을 받지 않는다는 의미
	 * */
	private State state;
	public Light(){
		state = new OFF();
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
public class StateStep3 {

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