# 스트래티지 패턴

> 객체의 행위를 인터페이스화하고 ~~(변태같이)~~ 자잘하게 쪼개서 구현(implements)하는 패턴

![행위들 추상화하여 인터페이스로 쪼개고 해당 인터페이스는 구체적 행위로 구현](https://ws1.sinaimg.cn/large/006tNc79gy1fgasaa0wnoj30qo0k0tb5.jpg)*행위를 추상화하여 인터페이스로 쪼개고 해당 인터페이스는 구체적 행위로 구현*(출처: JAVA객체지향 디자인 패턴-한빛미디어)

### 1. 특징

- 구체적 행위가 **인터페이스에 의해 캡슐화**


- 객체는 행위는 **인터페이스에 의존**하지 구체적인 클래스에 의존하지 않는다
  - **의존 역전 원칙**: 의존 관계를 맺을 때에는 **변화하기 어려운 것에 의존**하라.
- 기능의 추가가 **기존의 코드에 영향을 미치지 않는다**.
  - **폐쇄-개방 원칙 만족** : 새로운 기능의 추가가 기존의 코드에 영향을 미치지 않는다



### 2. 적용해보기

- 목표

  - 로봇의 **기능을 마음대로 추가, 수정**할 수 있게 만들어보자!!

- 주의사항
  - 기능의 추가, 수정이 해당 기능을 참조하는 클래스에 영향을 미치면 안된다.

  #### Step 1. 바람직하지 않은 예

  ![](https://ws2.sinaimg.cn/large/006tNc79gy1fgbbsnksixj307g08swem.jpg)

  - 구조

    - 로봇의 공통적인 특성을 모아 로봇(Robot) 추상 클래스를 만든다.(추상화)
    - 태권브이(TaekwonV)와 아톰(Atom)은  로봇(Robot)을 상속받고 추상메소드를 구현한다.
    - 태권브이와 아톰의 attack()과 move()는 서로 다르다.

  - [StrategyStep1.java](https://github.com/KangByungWook/JavaDesignPattern/blob/master/Strategy/src/step1/StrategyStep1.java)

    ```java
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
    // 예를들어 마징가 제트를 만들어 태권브이의 미사일 공격 기능을 사용할 수 있도록 만드려면?

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
    ```

  - 문제점

    - 기존 로봇의 공격 또는 이동 방식을 수정하려면 Atom 또는 TaekwonV 클래스를 수정해야 한다.
      - **폐쇄-개방 원칙 위배**
    - 기능 공유가 불가능하다
      - 예를 들어 새로 만들 마징가Z 클래스에서 미사일 공격 기능을 넣으려면 새롭게 구현해야 한다(코드 중복 발생)

  ​

  #### Step2. 올바른 예(~~아주 칭찬해~~)

  ![](https://ws1.sinaimg.cn/large/006tNc79gy1fgbcproun2j30pl08wq3s.jpg)

  - 구조

    - 각각의 행위를 **인터페이스에 위임**
    - **세부적인 행위는 인터페이스를 implements하는 방식**으로 구현되어 있다.
    - **Robot은 세부 클래스가 아닌 인터페이스에 의존**하므로 세부 클래스의 변경이  Robot클래스에 영향을 미치지 않는다.(**의존 역전 원칙**)

  - [StrategyStep2.java](https://github.com/KangByungWook/JavaDesignPattern/blob/master/Strategy/src/step2/StrategyStep2.java)

    ```java
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
    ```

  - 기존의 문제점과 개선 사항

    - 기존 로봇의 공격 또는 이동 방식을 수정하려면 Atom 또는 TaekwonV 클래스를 수정해야 한다.
      - 해결: 공격 또는 이동 방식을 수정하고자 하는 경우 해당 strategy 인스턴스를 생성하여 바꿔주기만 하면 된다
    - 기능 공유가 불가능하다
      - 해결: 같은 strategy 인스턴스를 서로 다른 로봇들이 공유할 수 있게 되었다.