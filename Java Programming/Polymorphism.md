# 다형성 (Polymorphism)
* 하나의 타입으로 여러 객체를 생성하여 다양한 기능을 이용할 수 있도록 하는 특성
* 부모의 타입으로 다양한 형태의 자식 객체를 생성, 참조할 수 있는 것
```java
Person person = new Person();
// 부모 타입으로 자식 클래스 객체 생성
// 배열로 관리하며 중복 메소드 호출하는 등의 방법을 사용할 수 있음
Person student = new Student();
Person teacher = new Teacher();
```

## 동적 바인딩
런타임에 참조 데이터 타입이 아닌 실제 객체 타입의 메소드를 실행
* 자식 클래스에서 부모 클래스의 메소드를 오버라이딩 했다면?
* 부모 클래스 타입으로 자식 클래스 객체를 만들고 오버라이딩 메소드를 실행하면 **실제 객체의 메소드가 실행됨**
* 컴파일할 때 호출하는 메소드와 실행할 때 호출하는 메소드가 유동적으로 적용되는 것
    * ⚠ 자식 클래스에만 있는 메소드를 호출하면 어떻게 될까?
        * 에러 발생함
        * 기본 데이터 타입(`int`, `double` , ...)이 아닌 참조 데이터 타입은 객체가 저장된 메모리의 주소 값을 담게 됨
        * 컴파일 타임엔 참조 데이터 타입에 따라 메소드가 결정되지만 실제 런타임에는 객체에 따라서 실행될 메소드가 결정이 됨
        * 따라서 컴파일 시에 해당 메소드가 부모 클래스에 저장되어 있지 않기 때문에 컴파일 에러가 발생함
    * 자식 클래스에만 있는 멤버를 사용하고 싶으면 어떻게 하나?
        * 다운캐스팅(부모를 자식클래스 타입으로 캐스팅)
        ```java
        Person student = new Student();
        ((Student) student).study();
        ```

## `instanceof` 연산을 통한 객체 판별
* `A instanceof B`의 의미
    * A가 B의 자식이거나 같은 class type이면 `true`
    * A가 B의 부모이면 `false`
    * A의 참조 데이터 타입과 B의 부모가 동일하면 `false`
    * 부모/자식 관계에 속하지 않는 것끼리 `instanceof`하면 컴파일 에러
    * `true`는 검사한 타입으로 형변환을 해도 아무 문제가 없다는 뜻이고 `false`는 형변환이 불가능하거나 문제가 생긴다는 것을 뜻함
        * 부모-자식 관계이거나 형제 관계가 아니면 `false`
    * 주로 조건문에 사용

## 인터페이스
인터페이스는 구현되지 않은 메소드로 구성되어 있다.
* 추상클래스와 어떻게 다를까?
    * 추상클래스는 일부가 구현되지 않은 추상 메소드로 구성되어 있고 인터페이스는 구현되지 않은 메소드로만 구성
        * 추상클래스는 미완성 설계도라 한다면 인터페이스는 기본 설계도 (❓)
    * 추상클래스는 여러 클래스의 구현 방식을 가이드, 공통 부분과 다른 부분이 있음. 하지만 다중 상속 되지 않음
    * 인터페이스는 중복 메소드가 있다고 하더라도 다중 구현 사용 가능. 즉 여러 인터페이스를 한 클래스에서 사용 가능

### 인터페이스 전체 구조
* 인터페이스 내부는 다음과 같은 구조로 되어 있다.
```java
public interface INTERFACE_NAME {   // 인터페이스 선언
    public final static Data_Type CONST_NAME = CONST_VALUE;     // 상수 선언
    public Return_Type METHOD_NAME (PARAM_1, PARAM_2);      //메소드 선언
}
```
* 인터페이스 내부에 선언하는 변수는 무조건 `public final static`이다.
    * `final static` 키워드가 붙는 변수는 상수이다.
    * 상수는 값을 변경할 수 없으므로 선언 시에 값을 대입해 주자.
* 인터페이스 내부에 선언하는 메소드는 body가 없는 추상 메소드이다.

### 인터페이스의 구현
* 인터페이스를 구현하는 클래스에서 `implements` 이용하여 구현
    * 인터페이스에 선언된 모든 추상 메소드를 override하여 구현해야 함.
    ```java
    // 문법
    public class 클래스명 implements 인터페이스명 {
        // 인터페이스에 선언된 메소드 구현
    }
    ```
    ```java
    // 예제
    public interface Flyable {
        public void fly();
    }

    public class Bird implements Flyable {
        public void fly() {
            // fly() 메소드 구현
        }
    }
    ```

### 인터페이스의 사용
* 인터페이스 타입의 변수를 선언하여 구현체를 대입할 수 있음
* 인터페이스 변수는 참조 타입이기 때문에 구현체가 대입되는 경우 구현체의 주소 값을 저장
```java
public class BirdTest {
    Flyable fly = new Bird();       // Case 1: 클래스의 필드로 선언

    BirdTest(Flyable fly) {         // Case 2: 생성자 또는 메소드의 매개 변수로 선언
        this.fly = fly;
    }
    public void methodA(Flyable fly) {
        // ...implementation
    }

    public void methodB() {         // Case 3: 생성자 또는 메소드의 로컬 변수로 선언
        Flyable fly = new Bird();
    }
}
```

### 클래스와 여러 개 인터페이스
* 클래스 상속 시 `extends` 키워드 뒤에 올 수 있는 부모 클래스는 단 하나
* 하지만 인터페이스는 한 번에 여러 개 구현할 수 있음
```java
public class ChildClass extends ParentClass implements InterfaceOne, Interfacetwo {
    // ...implementation
    // 인터페이스의 추상 메소드 전부 구현하기
}
```

### 인터페이스의 역할
* 인터페이스는 개발 코드와 객체가 서로 통신하는 접점 역할을 함
> 프로그램을 개발할 때 인터페이스를 사용해서 메소드를 호출하도록 했다면 구현 객체를 교체하기 용이해진다.  
메소드 호출부는 수정할 필요 없이 구현 객체를 교체하는 것만으로 실행 결과를 다르게 할 수 있다는 점. 이것이 인터페이스의 다형성이다.

## 자동 타입 변환 (Promotion)
* 프로그램 실행 도중 자동으로 일어나는 형변환
    * 구현 객체가 인터페이스 타입으로 변환되는 것 (인터페이스 변수 = new 구현객체;)
    * 인터페이스를 구현한 클래스를 상속받은 자식클래스는 인터페이스 타입으로 자동 형변환이 가능하다.

## 매개변수 다형성 예제
```java
public interface Vehicle {
    pubilc void drive();
}
```
```java
public class Motorcycle implements Vehicle {
    @Override
    public void drive() {
        System.out.println("부릉");
    }
}
```
```java
public class Bicycle implements Vehicle {
    @Override
    public void drive() {
        System.out.println("영차영차");
    }
}
```
```java
public class Driver {
    public void drive(Vehicle vehicle) {    // 매개변수 다형성
        vehicle.drive();
    }
}
```
```java
public class DriverTest {
    public static void main(String[] args) {
        Driver driver = new Driver();
        Motorcycle mc = new Motorcycle();
        Bicycle bc = new Bicycle();
        driver.drive(mc);   // 다형성
        driver.drive(bc);   // 다형성
    }
}
```

## MEMO
* 실수형 데이터를 문자열로
```java
double data = 5.2;
String dataToString = Double.toString(data);
```
* 👀 [Java의 상수에 대해](https://sslblog.tistory.com/23)

## TRIVIA
* `abstract` 키워드에 대해
    * 추상 클래스에서 추상 메소드에는 `abstract` 키워드를 붙여줘야 한다 (컴파일 에러)
    ```java
    public abstract class AbstractClass {
        public void methodA();  // Missing method body, or declare abstract

        public abstract void methodB();
    }
    ```
    * 인터페이스에선 `abstract` 키워드를 붙이지 않아도 된다
    ```java
    public interface _Interface {
        public abstract void methodA(); // Modifier 'abstract' is redundant for interface methods

        public void methodB();
    }
    ```