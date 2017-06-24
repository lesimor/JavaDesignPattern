package step1;
class Light {
	private static int ON = 0;	// 형광등이 켜진 상태..
	private static int OFF = 1; // 형광등이 꺼진 상태..
	private int state; // 형광등의 현재 상태.
	
	public Light(){
		state = OFF;
	}
	
	public void on_button_pushed(){
		if(state == ON){
			System.out.println("반응 없음");
		} else {
			System.out.println("Light on!");
			state = ON;
		}
	}
	
	public void off_button_pushed(){
		if(state == OFF){
			System.out.println("반응 없음.");
		} else {
			System.out.println("Light off");
			state = OFF;
		}
	}
}
public class StateStep1 {

	public static void main(String[] args) {
		Light light = new Light();
		light.off_button_pushed();
		light.on_button_pushed();
		light.off_button_pushed();
	}

}