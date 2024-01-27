# 상속 (Inheritance)
* 공통된 부분이 있으면 상위 클래스를 상속하여 구현
    * 단, 'is-a' 관계가 성립할 때에만 상속 관계 맺기

## OOP
* 만들고자 하는 완성 객체를 모델링하고 각각의 객체들을 설계하여 조립하는 방식으로 프로그램 개발하는 기법

## OOP의 특징
* 캡슐화 (Encapsulation)
* 상속 (Inheritance)
* 다형성 (Polymorphism)

## Java의 상속
* 부모 클래스는 일반 클래스를 만드는 방법으로 작성
* 자식 클래스 선언 시, `extends` 키워드를 이용하여 부모 클래스를 상속 받기
* 자식 클래스에서 부모 클래스의 것까지 "확장"하여 사용할 수 있다는 의미

```java
public class Child extends Parent { }
```

## 상속 클래스 객체 생성
* 부모 클래스 객체 생성 : 일반 클래스 객체 생성과 다르지 않음
* 자식 클래스 객체 생성
    * 자식 클래스 이름으로 객체 생성하면 부모 객체 먼저 생성되고 자식 객체 생성
    * 따로 부모 클래스 객체 생성하지 않아도 부모 멤버 사용 가능
```java
Person person = new Person();
Student student = new Student();
Teacher teacher = new Teacher();
``` 

## 상속에서의 생성자
* 생성자는 객체 생성 시, 자신의 클래스에 대한 객체만 생성
* 자식 클래스에서는 부모의 필드나 멤버 메소드를 사용해야 하기 때문에 **부모의 생성자를 호출하여 부모의 객체를 만들어 주어야** 함
    * 자식 클래스 생성자의 첫 번째 줄에 `super` 키워드를 이용하며 부모 생성자 호출
    * 명시적으로 부모 생성자 호출하지 않는 경우 자동으로 `super();` 추가됨
    * 생성자 없으면 컴파일러가 기본 생성자 만들어줌
    * `super` 키워드 이용하여 생성자 명시적으로 호출할 때 반드시 **생성자 첫 번째 줄**에 작성되어야 한다.
    * `super()`와 `this()`는 같이 호출하지 못한다.
```java
public class Person {
    private String name;
    private int age;
    // public Person() {
    // }
    // 기본(빈) 생성자 없는 경우 에러 발생
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```
```java
public class Student extends Person {
    private String major;
    public Student(String major) {
        super();    // 자동 추가, 부모 클래스의 Person의 파라미터 없는 생성자 실행
        this.major = major;
    }
    public Student(String name, int age) {
        super(name, age);
    }
}
```

# 오버라이딩 (Overriding)
자식 클래스에서 부모 클래스로부터 상속 받은 메소드를 재정의

## 오버라이딩 조건
* 메소드 이름이 같아야 함
* 메소드 파라미터 개수와 데이터 타입이 같아야 함
* 메소드 **리턴 타입과는 상관 없음**
* 접근제어자는 부모의 것과 같거나 더 넓은 범위여야 함

## 오버라이딩 할 수 없는 경우
* 부모 클래스 메소드의 접근 제어자가 `private`로 선언되어 있는 경우
    * `private` 키워드는 다른 클래스에서의 접근을 막는다.
* 부모 클래스의 메소드가 `final` 키워드로 선언되어 있는 경우

## `final` 키워드
* `final` 클래스
    * 자식 클래스를 만들 수 없음
    ```java
    public final class Example {
        // ...implementation
    }
    ```
* `final` 메소드
    * 자식 클래스에서 오버라이딩 할 수 없음
    ```java
    public class Parent {
        public final void parentMethod() {
            // ...implementation
        }
    }
    //////
    public class Child extends Parent {
        public void parentMethod() {
            // ...new implementation
        }   // ERROR: cannot override `final` method
    }
    ```
* `final` 변수
    * 값을 변경할 수 없는 상수
    ```java
    final int age = 25;
    // ...
    age = 25;   // ERROR: cannot change value of a final variable
    ```

> ### 어노테이션 (Annotation)
> * 컴파일러가 검증해야 하는 부가 정보 추가
> * 필요한 데이터를 쉽게 활용할 수 있도록 소스 코드에 메타 데이터 작성
> * `@`로 시작하며 일종의 주석
> 
> ### `@Override`
> * 해당 메소드가 부모 클래스 메소드를 오버라이딩한다는 것을 나타냄
> * `@Override` 어노테이션을 사용한 메소드가 오버라이딩 된 메소드가 아닐 경우 컴파일러는 에러를 뱉음

## 부모 클래스 메소드도 쓰고 싶을 때
* 오버라이딩으로 메소드를 재정의할 때 완전히 다른 메소드가 아닌 부모의 메소드 내용도 실행하고 싶을 때 `super` 키워드 이용
```java
public class Child extends Parent {
    public void do() {
        super.do();
        System.out.println("I'm a child.");
    }
}
```

## `Object` Class
* Java의 최상위 부모 클래스
* `extends` 키워드로 다른 클래스를 상속하지 않은 클래스는 암시적으로 `Object` 클래스를 상속
* 필드가 없고 메소드만으로 구성
* `toString()`과 같은 유용한 메소드 제공
    * 객체의 정보를 `String`으로 제공
    * 모든 클래스에서는 `toString()` 메소드를 오버라이딩하여 객체에 대한 정보를 문자열로 변환 가능
    ```java
    public class Example {
        // fields
        private String name;
    
        // methods
        // ...

        public String toString() {
            return "이름: " + name;
        }
    }
    ```
    ```java
    public class ExampleTest {
        public static void main(String[] args) {
            Example ex = new Example("name!");

            System.out.println(ex); // 객체명을 작성하면 `객체명.toString()`이 호출된다.
        }
    }
    ```

## 추상 클래스
* 클래스들의 공통적인 특성을 추출하여 선언한 클래스
* 객체를 직접 생성(Instantiate)할 수 없다
    * `new` 연산자 사용 불가
    * 상속받은 자식 클래스만 객체 생성 가능
    * 참조 변수 타입으론 사용 가능
    ```java
    // `Appliance` is an abstract class
    Appliance app = new Radio();
    ```

## 추상 클래스의 용도
* 클래스들의 필드와 메소드명 통일(일관된 interface 제공)
* 꼭 필요한 기능 구현 강제
* 개발 규격 통일 -> 효율성 증대

## 추상 클래스 선언 방법
* `abstract` 키워드 사용
```java
public abstract class Example {
    // ...implementation
}
```

## 추상 메소드 활용
```java
public abstract void test();
// no body, ends with semicolon
```
* 추상 메소드는 `{ }`(메소드 body)가 없는 메소드로, 반드시 `;`로 끝나야 함
* 추상 클래스를 상속받은 자식 클래스는 추상 메소드를 오버라이딩하여 실행 내용(body)를 완성해야 한다(구현체).  
(미 작성 시에 컴파일 에러 발생)
* 추상 메소드는 추상 클래스에만 선언 가능
* 일반 필드, 생성자, 메소드 선언 가능




