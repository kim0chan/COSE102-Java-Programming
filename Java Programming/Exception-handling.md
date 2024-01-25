# 예외 처리

## 예외 발생하는 프로그램의 실행 순서
1. 예외 상황 발생
2. 에러 정보를 가지고 있는 `Exception` 객체 생성됨
3. `Exception` 객체가 소멸되지 않으면 프로그램이 **비정상적으로 종료**됨

## 예외 클래스
* Checked Exception
    * 컴파일 시 예외 처리를 해 주었는지 체크하는 Exception
* Unchecked Exception
    * 프로그램 실행 중 발생하는 Exception
> Java Exception Inheritance Hierarchy 참고

### 자주 발생하는 Exception
* Unchecked Exception
    * 컴파일러가 체크하지 않기 때문에 개발자의 경험으로 예외처리 코드 작성
    * 예외 발생 시 프로그램이 비정상 종료
    * `ArithmeticException`
        * '0'으로 나누는 산술 연산
    * `NumberFormatException`
        * 숫자로 변경될 수 없는 문자열을 숫자로 변경하려고 시도하는 경우
        * `Integer.parseInt()`
    * `NullPointerException`
        * 참조 데이터형 변수 값이 `null`일 때 객체에 접근
    * `ArrayIndexOutOfBoundsException`
        * 배열 범위 벗어난 인덱스에 접근
    * `ClassCastException`
        * 객체 형변환 시, 잘못된 클래스로 변환할 때
            * `instanceof`를 이용해 형변환이 가능한지 확인하는 것이 필요함

## 예외 처리 방법
### try-catch-finally
```java
// 문법
try {
    // ... Exception이 발생할 가능성이 있는 구문
} catch (ExceptionClassType Variable_Name) {
    // ... Exception 발생 시 수행할 구문
} finally {
    // Exception 발생 유무에 관계 없이 반드시 수행해야 하는 문장
}
```
* `try`
    * `Exception`이 발생할 가능성이 있는 코드를 `try`로 감싸기
    * `try` 블록 안에 있는 문장들은 `Exception`이 발생하지 않는 한 모두 정상 수행됨
    * 💥 `Exception`이 발생하면 발생 즉시 `Exception` 객체를 `catch` 블록으로 전달
        * 예외 발생 지점 이후 작성된 구문은 실행되지 않음
* `catch`
    * `Exception` 객체를 받아서 예외 상황 발생 시 수행할 문장들을 작성함
* `finally`
    * `try` 블록 안 코드의 `Exception` 발생 유무와 상관 없이 반드시 수행해야 하는 문장
    * `catch` 블록 모두 끝난 후 `finally` 블록 작성
    * 보통 파일 입출력 할 때 많이 사용함(`close` 해주려고)

### 여러 Exception이 발생하는 경우
```java
// 문법
try {
    // ... Exception 발생 가능성 있는 구문
} catch (ExceptionClassType_1 Variable_Name) {
    // ... Exception 1 발생 시 수행할 구문
} catch (ExceptionClassType_2 Variable_Name) {
    // ... Exception 2 발생 시 수행할 구문
}
```
* `try` 블록 안 코드에서 여러 `Exception` 발생할 가능성 있을 땐 `catch` 블록을 여러 개 작성
    * `Exception` 발생하면 위에서부터 차례대로 검사하면서 알맞은 `catch` 블록을 찾아 수행
    * 하위 클래스의 `Exception`부터 상위 클래스의 `Exception` 순서로 작성
    * 제일 마지막에는 최상위 `Exception` 클래스를 작성하여 모든 예외사항 처리할 수 있음

### `throws`
* 처리하기 힘든 `Exception`을 상위 메소드로 던짐
* 여러 개의 작업을 모아 예외 처리를 해야 하는 경우 사용
```java
// 문법
public Return_Type Method_Name (Param_1/*, ...*/) throws Exception_Type_1, Exception_Type_2/*, ...*/ {
    // ... method implementation
}
```
* `try-catch` 구문에서 `catch`를 여러 번 사용할 수 있듯이, `throws`도 여러 개의 `Exception`을 던질 수 있다.
* `main` method에서 `throw`하면 상위 메소드가 없기 때문에 Exception 발생 시 프로그램이 비정상 종료된다.

### 강제로 Exception 발생시키기
* 조건문 등을 이용하여 예외 상황을 발생시킬 수 있다.
* 강제로 `Exception`을 발생시켜 예외 상황을 만들 수 있다.
```java
// 문법
throw new Exception("Exception 발생 메시지");
throw Exception_Object;
```
```java
////// 강제 발생 두 가지 예시
// 1
Exception exception = new Exception();
throw exception;

// 2
throw new Exception("예외 발생");
```
```java
// 예제
try {
    boolean isInvalid = false;
    // ...
    if(isInvalid)   throw new Exception("예외 발생");
    // ...
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```
* 보통 `catch` 구문에서 상위 메소드로 넘겨준다?
```java
try {
    // ...
} catch (Exception e) {
    throw e;
}
```

## 사용자 정의 예외
* 기본 `Exception` 클래스로는 프로그램의 다양한 논리적 예외를 표현할 수 없다.
* 기존에 정의된 예외 클래스를 상속받아 새로운 예외 클래스를 정의한다.
* 사용자 정의 예외 클래스는 `Checked Exception` 또는 `Unchecked Exception` 두 가지 종류로 정의할 수 있다.
    * Checked Exception
        * `Exception` 클래스를 상속 받아 정의
    * Unchecked Exception
        * `RuntimeException` 클래스를 상속 받아 정의
* 사용자 정의 예외 클래스 이름은 `Exception`으로 끝나는 것이 좋다.
* 사용자 정의 예외 클래스는 필드, 생성자, 메소드를 포함할 수 있으나 **대부분 생성자만을 포함**한다.
    * 주로 두 개의 생성자 선언
        * 기본 생성자
        * `String` 타입 매개변수 갖는 생성자
    * `String` 타입 매개 변수에 예외 발생 원인을 알려주는 메시지를 담아 전달

### 사용자 정의 예외 클래스 선언
```java
Class TimeInputException extends Exception {
    public TimeInputException() {
        // 기본 생성자
    }

    public TimeInputException(String message) {
        super(message);
    }
}
```
```java
public static void main(String[] args) {
    try {
        int time = readTime();
        System.out.println(time);
    } catch (TimeInputException e) {
        System.out.println(e.getMessage());
    }
    // main 메소드에서 Exception을 처리해 주지 않는다면, Exception이 main 메소드를 호출한 jvm에게까지 넘어가게 되고 프로그램이 비정상 종료된다.
}

public static int readTime() throws TimeInputException {
    Scanner scanner = new Scanner(System.in);
    int time = scanner.nextInt();

    if(time < 0) {
        TimeInputException e = new TimeInputException("시간이 음수로 입력되었습니다.");
        throw e;
    }
    
    return time;
}
```