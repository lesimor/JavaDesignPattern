/* p.222
- 문제점
-> step1에서 취침등(SLEEPING) 상태를 추가하고자 한다면??
-> 모든 메서드를 수정해야한다..(추가)로 표시된 부분. */
package step2;

class Light {
	private static int ON = 0;	// 형광등이 켜진 상태..
	private static int OFF = 1; // 형광등이 꺼진 상태..
	private static int SLEEPING = 2;	// 취침등 상태..
	private int state; // 형광등의 현재 상태.
	
	public Light(){
		state = OFF;
	}
	
	public void on_button_pushed(){				// On 버튼 클릭.
		if(state == ON){						// (추가)켜져 있는 경우 취침등 상태로 전
			System.out.println("취침등 상태.");
			state = SLEEPING;
		} else if(state == SLEEPING){			// (추가)취침등 모드인 경우 ON으로 전환.
			System.out.println("Light on!");
			state = ON;
		} else {								// 켜져 있는 경우는 그대로.
			System.out.println("Light on!");
			state = ON;
		}
	}
	
	public void off_button_pushed(){			// Off 버튼 클릭.
		if(state == OFF){						// 꺼져있는 경우 그대로.
			System.out.println("반응 없음.");
		} else if(state == SLEEPING) {			// (추가)취침모드인 경우 Off
			System.out.println("Light off");
		}else {									// 켜져 있는 경우 Off.
			System.out.println("Light off");
			state = OFF;
		}
	}
}
public class StateStep2 {

	public static void main(String[] args) {
		Light light = new Light();
		light.off_button_pushed();
		light.on_button_pushed();
		light.on_button_pushed();
		light.off_button_pushed();
	}

}