# 자바 디자인 패턴

### 1. 객체지향 원리

- **추상화**

  > 어떤 영역에서 필요로 하는 속성(변수)이나 행동(메소드)을 추출하는 작업

  - **단일 클래스 설계 시 변수와 메소드를 정의**하는 과정
  - **인간이 사물을 인식하는 방법과 유사**

- **캡슐화**~~(밑줄쫙돼지꼬리땡땡)~~

  > **높은 응집도**와 **낮은 결합도**를 유지하기 위한 객체지향 원리

  - ~~(개발자의 숙명인)~~**요구사항 변경에 의한 코드 수정을 최소화**
  - **응집도(↑): 가족 구성원(클래스 내부)** 간 얼마나 화목한지?
  - **결합도(↓): 이웃(클래스-클래스)** 간 얼마나 사이가 좋은지?
  - ~~팔은 안으로 굽는다~~

- **일반화**~~(추상화랑 헷갈림ㅠㅠ)~~

  > 여러 개체들이 가진 **공통된 특성**을 부각시켜 하나의 개념이나 법칙으로 성립시키는 과정

  - **공통된 특성은 부모 클래스로** 모으고 자식 클래스의 특수한 기능은 각각 따로 정의(**상속**)
  - **위의 추상화는 단일 클래스에 관한 것**이라면 **<u>일반화는 클래스 간 관계 설정에서의 추상화</u>**

- **다형성**

  > 서로 다른 클래스의 객체가 **같은 메시지**를 받았을 때 **각자의 방식으로 동작**하는 능력

  - (동물에게 인사를 건넨다) 강아지: 멍멍! / 고양이: 야옹~ / 소: 음메~ 



### 2. SOLID 원칙

- **단일 책임** 원칙(**S**ingle Responsibllity Principle)

  > 소프트웨어의 설계 부품(클래스, 함수 등)은 **단 하나의 책임**만을 가져야 한다.

- **개방-폐쇄** 원칙(**O**pen-Closed Principle)

  > **기존의 코드를 변경하지 않고**(Closed) **기능을 수정하거나 추가**할 수 있도록(Open) 설계해야 한다.(~~젤로 중요~~)

- **리스코프 치환 원칙**(**L**iskov Substitution Principle)

  > 자식 클래스는 부모 클래스에서 가능한 행위를 수행할 수 있어야 한다.(~~아들아 내가 그렇게 가르쳤니?~~)

- **인터페이스 분리 원칙**(**I**nterface Segregation Principle)

  > 하나의 일반적인 인터페이스보다는, 여러개의 구체적인 인터페이스가 낫다.(~~뼈와 살을 분리~~)

- **의존 역전 원칙**(**D**ependency Inversion Principle)

  > 의존 관계를 맺을 때, 변화하기 쉬운 것 보단 변화하기 어려운 것에 의존해야 한다.



### 3. 디자인 패턴의 분류

- 생성 패턴
  1. [추상 팩토리(Abstract Factory)](https://sourcemaking.com/design_patterns/abstract_factory)
  2. 빌더(Builder)
  3. 팩토리 메서드(Factory Method)
  4. 프로토 타입(Prototype)
  5. [**싱글턴(Singleton**)](https://github.com/KangByungWook/JavaDesignPattern/tree/master/Singleton/src)
- 구조 패턴
  1. 어댑터(Adapter)
  2. 브리지(Bridge)
  3. 컴퍼지트(Composite)
  4. 데커레이터(Decorator)
  5. 퍼사드(Facade)
  6. 플라이웨이트(Flyweight)
  7. 프록시(Proxy)
- 행위 패턴
  1. 책임 연쇄(Chain of Responsibility)
  2. [**커맨드(Command)**](https://github.com/KangByungWook/JavaDesignPattern/tree/master/Command/src)
  3. 인터프리터(Interpreter)
  4. 이터레이터(Iterator)
  5. 미디에이터(Mediator)
  6. 메멘토(Memento)
  7. [**옵서버(Observer)**](https://github.com/KangByungWook/JavaDesignPattern/tree/master/Observer/src)
  8. [**스테이트(State)**](https://github.com/KangByungWook/JavaDesignPattern/tree/master/State/src)
  9. [**스트래티지(Strategy)**](https://github.com/KangByungWook/JavaDesignPattern/tree/master/Strategy/src)
  10. 템플릿 메서드(Template Method)
  11. 비지터(Visitor)