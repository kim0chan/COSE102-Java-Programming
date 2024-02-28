# Design Patterns by GoF


## MVC Pattern
* Model-View-Controller
* Domain model과 Controller logic을 UI(View)로부터 분리(decouple)시키는 것

### Model
* Data access operation과 같은 business logic
* Data manipulation에 대한 business rule을 정의함

### View
* 유저에게 보이는 view 영역
* Controller로부터 받은 data를 결과로 보여주고 model을 UI로 변형시키기도 함

### Controller
* 뷰로부터 받아온 요청을 처리하는 역할
* Model의 도움으로 데이터를 처리한 후 view로 결과를 쏴줌
* View와 model의 중재자 역할


## MVP Pattern
* Model-View-Presenter
* MVC와 유사하지만 controller가 presenter로 대체됨.

### Model
* 모델

### View
* 뷰

### Presenter
* View 대신 모든 UI Event를 처리함
* Model의 도움을 받아 data를 처리하고 view로 결과를 내보냄
* 💥 View-Controller와 다르게 View-Presenter는 서로 완전히 decouple되며 인터페이스를 통해 통신함
* 또한 controller처럼 요청 트래픽을 관리하지 않음

### MVP 패턴의 Key Points
1. 유저는 view와 상호작용
1. View-presenter는 일대일의 대응 관계를 가짐
1. View는 presenter로의 reference를 가지지만 view는 model에 reference를 가지지 않음.
1. View와 presenter 사이 양방향 통신을 제공


## MVVM Pattern
* Model-View-View Model
* View와 view model 사이에 양방향 data-binding을 지원함
  * view model의 상태 변화가 view로 자동으로 propagation되는 것이 가능해짐
* 보통 view model의 변화를 model로 notify하기 위해 observer patern을 사용홤

### Model
* 모델

### View
* 뷰

### View Model
* View의 상태를 유지시키기 위한 methods, commands, properties를 노출시키는 역할을 함.
* Model을 조작하여 view에 결과를 나타냄
* View 자체에 이벤트를 작동시킴

### MVVM 패턴의 Key Points
1. 유저는 view와 상호작용
1. View와 view model 사이에 다대일의 관계가 존재
  * 여러 view가 하나의 view model에 매핑될 수 있음
1. View는 view model에 reference를 가지지만 view model은 view에 대한 정보를 가지지 않음
1. View와 view model 사이에 양방향 data binding을 지원


## Creational Patterns
### Abstract Factory Pattern
* 상세화된 서브클래스를 정의하지 않고 서로 관련성이 있거나 독립적인 여러 객체의 군을 생성하기 위한 인터페이스 제공

### 예제
```java
// Abstract Factory Interface
public interface GUIFactory {
  Button createButton();
  Label createLabel();
}

// Concrete Factory Class for MacOS
public class MacOSGUIFactory implements GUIFactory {
  public Button createButton() {
    return new MacOSButton();
  }
  public Label createLabel() {
    return new MacOSLabel();
  }
}

public interface Button {
  void click();
}

public interface Label {
  void setText(String text);
}

public class MacOSButton implements Button {
  public void click() {
    // MacOS Clicking
  }
}

public class MacOSLabel implements Label {
  public void setText(String text) {
    // MacOS Text Setting
  }
}

public class MacOSClient {
  public static void main(String[] args) {
    GUIFactory factory = new MacOSFactory();
    Button button = factory.createButton();
    Label label = factory.createLabel();
    // Utilize button and label objects here
  }
}
```
* 클래스 분리
* 제품군 대체 용이
* 제품 사이의 일관성 UP
* 새로운 제품 제공 어려움

### Factory Method Pattern
* 객체를 생성하기 위해 인터페이스를 정의하지만 어떤 클래스의 인스턴스를 생성할지는 서브클래스가 결정

### 예제
```java
public abstract class Room {
  abstract void connect(Room room);
}

public class MagicRoom extends Room {
  public void connect(Room room) {}
}

public class OrdinaryRoom extends Room {
  public void connect(Room room) {}
}

public abstract class MazeGame {
  private final List<Room> rooms = new ArrayList<>();

  public MazeGame() {
    Room room1 = makeRoom();
    Room room2 = makeRoom();
    room1.connect(room2);
    rooms.add(room1);
    rooms.add(room2);
  }

  abstract protected Room makeRoom();
}

public class MagicMazeGame extends MazeGame {
  @Override
  protected MagicRoom makeRoom() {
    return new MagicRoom();
  }
}

public class OrdinaryMazeGame extends MazeGame {
  @Override
  protected OrdinaryRoom makeRoom() {
    return new OrdinaryRoom();
  }
}

MazeGame ordinaryGame = new OrdinaryMazeGame();
MazeGame magicGame = new MagicMazeGame();
```
* 서브클래스에 대한 훅(hook) 메서드를 제공
* 병렬적인 클래스 계통을 연결


### Builder Pattern
...


## Structural Patterns
### Adapter Pattern
* 클래스의 인터페이스를 사용자가 기대하는 인터페이스 형태로 적응(변환)시킨다.
* 서로 일치하지 않는 인터페이스를 갖는 클래스들을 함께 동작시킨다.
### 예제
```cpp
class Shape {
public:
  Shape();
  virtual void BoundingBox(
    Point& bottomLeft, Point& topRight
  ) const;
  virtual Manipulator* CreateManipulator() const;
};

class TextView {
public:
  TextView();
  void GetOrigin(Coord& x, Coord& y) const;
  void GetExtent(Coord& w, Coord& h) const;
  virtual bool IsEmpty() const;
};

class TextShape: public Shape {
public:
  TextShape(TextView*);

  virtual void BoundingBox(
    Point& bottomLeft, Point& topRight
  ) const;
  virtual bool IsEmpty() const;
  virtual Manipulator* CreateManipulator() const;

private:
  TextView* _text;
};

TextShape::TextShape(TextView* t) {
  _text = t;
}

void TextShape::BoundingBox(
  Point& bottomLeft, Point& topRight
) const {
  Coord bottom, left, width, height;

  _text->GetOrigin(bottom, left);
  _text->GetExtent(width, height);

  bottomLeft = Point(bottom, left);
  topRight = Point(bottom + height, left + width);
}

bool TextShape::IsEmpty() const {
  return _text->IsEmpty();
}
```

### Composite Pattern
* 부분과 전체의 계층을 표현하기 위해 객체들을 모아 트리 구조로 구성
* 이용자가 개별 객체와 복합 객체를 동일하게 다룰 수 있다
### 예제
```java
interface Component {
  void draw();
}

class Leaf implements Component {
  private String name;

  public Leaf(String name) {
    this.name = name;
  }

  public void draw() {
    System.out.println("Draw Leaf " + name);
  }
}

class Composite implements Component {
  private List<Component> components = new ArrayList<>();
  private String name;

  public Composite(String name) {
    this.name = name;
  }

  public void add(Component component) {
    components.add(component);
  }

  public void draw() {
    System.out.println("Draw Composite " + name);
    for (Component component : components) {
      component.draw();
    }
  }
}

public class CompositePatternExample {
  public static void main(String[] args) {
    Component leaf1 = new Leaf("1");
    Component leaf2 = new Leaf("2");

    Composite composite1 = new Composite("C1");
    composite1.add(leaf1);
    composite1.add(leaf2);

    Component leaf3 = new Leaf("3");
    Component leaf4 = new Leaf("4");

    Composite composite2 = new Composite("C2");
    composite2.add(leaf3);
    composite2.add(leaf4);
    composite2.add(leaf1);

    composite2.draw();
  }
}
```
* 기본 객체와 복합 객체로 구성된 하나의 일관된 클래스 계통 정의
  * 런타임 시 기본 객체와 복합 객체를 구분하지 않고 일관되게 프로그래밍 가능
* `switch` - `case` 문을 쓸 필요가 없어 사용자의 코드가 단순해짐
* 새로운 종류의 구성요소를 쉽게 추가할 수 있음(html dom tree를 연상)
* 설계가 지나치게 범용적임

### Decorator Pattern
* 객체에 동적으로 새로운 책임을 추가
* 서브클래스 생성하는 것보다 융통성 있는 방법 제공
  * 많은 수의 확장이 가능할 때 상속으로 해결하려면 클래수 수가 폭발적으로 많아지게 됨
## 예제
```java
interface Pizza {
  public String getDescription()
  public double getCost();
}

class Margherita implements Pizza {
  @Override
  public String getDescription() {
    return "Margherita Pizza";
  }

  @Override
  public double getCost() {
    return 5.99;
  }
}

abstract class ToppingDecorator implements Pizza {
  protected Pizza pizza;

  public ToppingDecorator(Pizza pizza) {
    this.pizza = pizza;
  }

  public String getDescription() {
    return pizza.getDescription();
  }

  public double getCost() {
    return pizza.getCost();
  }
}

class Cheese extends ToppingDecorator {
  public Cheese(Pizza pizza) {
    super(pizza);
  }

  public String getDescription() {
    return pizza.getDescription() + " with cheese";
  }

  public double getCost() {
    return pizza.getCost() + 1.25;
  }
}

public class PizzaDecoratorExample {
  public static void main(String[] args) {
    Pizza margherita = new Margherita();
    System.out.println(margherita.getDescription() + " - $" + margherita.getCost());

    Pizza cheeseMargherita = new Cheese(margherita);
    System.out.println(cheeseMargherita.getDescription() + " - $" + cheeseMargherita.getCost());
  }
}
```
* 설계의 융통성 UP
  * 객체에 새로운 행동 효과적으로 추가 가능
  * 상속과 달리 새로운 책임을 추가/삭제하는 것이 런타임에 가능해짐
* 클래스 계통의 상부 클래스에 많은 기능이 누적되는 상황을 피할 수 있음
  * 개발 시 현재 사용되지 않는 기능까지 개발하기 위해 시간과 노력을 투자할 필요 X다
* 인터페이스 일치시키기
  * `Decorator` 객체의 인터페이스는 자신이 장식하는 `Component`의 인터페이스를 만족해야 하므로  
  `ConcreteDecorator` 클래스는 반드시 **동일한 부모 클래스를 상속**해야 한다
* `Component` 클래스는 가볍게 유지
  * 연산만 정의하고 변수는 정의하지 말자 - 서브클래스에 부담이 된다


### Facade Pattern
* 한 서브시스템 내 인터페이스 집합에 대한 획일화된 하나의 인터페이스를 제공
* 서브시스템을 사용하기 쉽도록 상위 수준의 인터페이스 정의
### 예제
```java
class CarEngine {
  public void start() {
    System.out.println("Engine started");
  }

  public void stop() {
    System.out.println("Engine stopped");
  }
}

class CarLights {
  public void turnOn() {
    System.out.println("Lights turned on");
  }

  public void turnOff() {
    System.out.println("Lights turned off");
  }
}

class CarMusicSystem {
  public void playMusic() {
    System.out.println("Music playing");
  }

  public void stopMusic() {
    System.out.println("Music stopped");
  }
}
```
```java
public class CarFacade {
  private CarEngine engine;
  private CarLights lights;
  private CarMusicSystem musicSystem;

  public CarFacade() {
    engine = new CarEngine();
    lights = new CarLights();
    musicSystem = new CarMusicSystem();
  }

  public void startCar() {
    engine.start();
    lights.turnOn();
    musicSystem.playMusic();
  }

  public void stopCar() {
    engine.stop();
    lights.turnOff();
    musicSystem.stopMusic();
  }
}

public class CarFacadeExample {
  public static void main(String[] args) {
    CarFacade car = new CarFacade();

    carFacade.startCar();
    carFacade.stopCar();
  }
}
```
* 서브시스템의 구성요소 보호
* 서브시스템과 코드 간의 결합도를 약하게 만듬

### Proxy Pattern
* 다른 객체에 대한 접근을 제어하는 대리자(Surrogate) 또는 자리표시자(Placeholder) 역할을 하는 객체


## Behavioral Patterns
### Chain of Responsibility Pattern
* 메시지를 보내는 객체와 받아서 처리하는 객체들 간의 결합도를 없애기 위한 패턴
* 하나의 요청에 대한 처리가 한 객체에서만 되지 않고 여러 객체에게 처리 기회를 주는 것
### 예제
```java
public abstract class Handler {
  protected Handler successor;

  public void setSuccessor(Handler successor) {
    this.successor = successor
  }

  public abstract String handleRequest(Request request);
}

public class Request {
  private String type;
  private String content;

  public Request(String type, String content) {
    this.type = type;
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public String getContent() {
    return content;
  }
}
```
```java
public class ConcreteHandler1 extends Handler {
  @Override
  public String handleRequest(Request request) {
    if (request.getType().equals("type1")) {
      return "Handler 1 handling request: " + request.getContent();
    } else if (successor != null) {
      return successor.handleRequest(request);
    } else {
      return "No handler found for request: " + request.getType();
    }
  }
}

public class ConcreteHandler2 extends Handler {
  @Override
  public String handleRequest(Request request) {
    if (request.getType().equals("type2")) {
      return "Handler 2 handling reqeust: " + request.getContent();
    } else if (successor != null) {
      return successor.handleRequest(request);
    } else {
      return "No handler found for request: " + request.getType();
    }
  }
}
```

### Command Pattern
* 요청 자체를 캡슐화


