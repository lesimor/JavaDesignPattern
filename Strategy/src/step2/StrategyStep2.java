package step2;
abstract class Robot {
	private String name;
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;
	
	public Robot(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void move(){
		movingStrategy.move();
	}
	
	public void attack(){
		attackStrategy.attack();
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy){
		this.movingStrategy = movingStrategy;
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy){
		this.attackStrategy = attackStrategy;
	}
}

class Atom extends Robot {

	public Atom(String name) {
		super(name);
	}
	
}

class TaekwonV extends Robot {

	public TaekwonV(String name) {
		super(name);
	}
	
}

interface MovingStrategy {
	public void move();
}

class FlyingStaregy implements MovingStrategy {

	@Override
	public void move() {
		System.out.println("I can fly");
	}
	
}

class WalkingStrategy implements MovingStrategy {

	@Override
	public void move() {
		System.out.println("I can only walk");
	}
	
}

interface AttackStrategy {
	public void attack();
}

class MissileStrategy implements AttackStrategy {
	public void attack(){
		System.out.println("I have Missile and can attack with it.");
	}
}

class PunchStrategy implements AttackStrategy {

	@Override
	public void attack() {
		System.out.println("I have strong punch and can attack with it");
		
	}
	
}
public class StrategyStep2 {

	public static void main(String[] args) {
		Robot taekwonV = new TaekwonV("TaekwonV");
		Robot atom = new Atom("Atom");
		
		// 개선 포인트
		// 클래스 메소드를 변경하지 않고도 객체의 동작을 변경할 수 있게 됨.
		taekwonV.setMovingStrategy(new WalkingStrategy());
		taekwonV.setAttackStrategy(new MissileStrategy());
		
		atom.setMovingStrategy(new FlyingStaregy());
		atom.setAttackStrategy(new PunchStrategy());
		
		System.out.println("My name is " + taekwonV.getName());
		taekwonV.move();
		taekwonV.attack();
		
		System.out.println("");
		
		System.out.println("My name is " + atom.getName());
		atom.move();
		atom.attack();
	}

}