package step1;

abstract class Robot {
	private String name;
	public Robot(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract void attack();
	public abstract void move();
}

class TaekwonV extends Robot {

	public TaekwonV(String name) {
		super(name);
	}

	@Override
	public void attack() {
		System.out.println("저는 미사일을 이용해 공격합니다.");
	}

	@Override
	public void move() {
		System.out.println("저는 걸어다닙니다.");
	}
	
}

class Atom extends Robot {

	public Atom(String name) {
		super(name);
	}

	//	문제점1
	//	기존 로봇의 공격 또는 이동 방법을 수정하려면 해당 로봇의 메소드를 수정해야 한다.
	@Override
	public void attack() {
		System.out.println("저는 강력한 펀치를 할 수 있어요.");
	}

	@Override
	public void move() {
		System.out.println("전 날아다닐 수 있어요.");
		//	System.out.println("날개가 고장나서 이제 걷기밖에 못해요.");  -> 기존의 코드를 수정해야함..
	}
	
}

// 문제점2 
// 새로운 로봇을 만들어 기존의 공격 또는 이동 방법을 추가하거나 수정하려면?
// 예를들어 태권브이의 미사일 공격 기능을 사용하려면?

public class StrategyStep1 {

	public static void main(String[] args) {
		Robot taekonV = new TaekwonV("TaekwonV");
		Robot atom = new Atom("Atom");
		
		System.out.println("My name is " + taekonV.getName());
		taekonV.move();
		taekonV.attack();
		
		System.out.println("");
		
		System.out.println("My name is " + atom.getName());
		atom.move();
		atom.attack();
	}

}