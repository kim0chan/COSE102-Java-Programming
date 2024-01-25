# 컬렉션
다수 객체를 저장, 관리할 수 있음

## 배열 vs 컬렉션
. | 배열 | 컬렉션
---|---|---
장점|모든 데이터 저장 가능, 사용 편리|자동 크기 조절, 명시적 이름의 메소드 사용
단점|처음 지정한 크기에서 공간 크기 변경 불가 | 사용 방법 다소 복잡

## 컬렉션 프레임워크
배열의 문제점을 해소하고 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 `java.util` 패키지에 컬렉션과 관련된 인터페이스와 클래스들이 포함되어 있다.  
이들을 총칭해 **컬렉션 프레임워크**라고 부른다.

`List`, `Set`, `Map` 등의 인터페이스를 통해 다양한 컬렉션 클래스를 이용할 수 있다.
* Collection
    * List
        * `ArrayList`
        * `Vector`
        * `LinkedList`
    * Set
        * `HashSet`
        * `TreeSet`
* Map
    * `HashMap`
    * `HashTable`
    * `TreeMap`
    * `Properties`

## 인터페이스 분류
* Collection
    * `List`
        * 순서 유지하며 저장
        * 중복 저장 가능
        * `ArrayList`, `Vector`, `LinkedList`
    * `Set`
        * 순서 유지하지 않고 저장
        * 중복 저장 불가
        * `HashSet`, `TreeSet`
* `Map`
    * (Key, Value) 쌍으로 저장
    * Key는 중복 저장 불가
    * Value는 중복 저장 가능
    * `HashMap`, `HashTable`, `TreeMap`, `Properties`

## `ArrayList`
* 데이터를 순차 처리하는 구조, 인덱스로 관리
* 객체 자체를 저장하지 않고 객체의 주소를 참조
* 객체가 아닌 `null`을 담을 수 있음
* 데이터 중복 허용(동일 주소 참조)
```java
import java.util.ArrayList;
```

### List 인터페이스의 메소드
기능 | 메소드 | 설명
---|---|---
객체 추가 | `add(Object obj)` | 객체를 맨 끝에 추가
객체 추가 | `add(int index, Object obj)` | 해당 인덱스에 객체 추가
객체 추가 | `set(int index, Object obj)` | 해당 인덱스의 객체를 주어진 객체로 바꿈
객체 검색 | `contains(Object obj)` | 해당 객체가 저장되어 있는지 여부를 반환
객체 검색 | `get(int index)` | 해당 인덱스에 저장된 객체 반환
객체 검색 | `isEmpty()` | 컬렉션이 비어있는지 조회
객체 검색 | `size()` | 저장된 객체 수 반환
객체 삭제 | `clear()` | 저장된 모든 객체 삭제
객체 삭제 | `remove(int index)` | 해당 인덱스에 저장된 객체 삭제
객체 삭제 | `remove(Object obj)` | 해당 객체 삭제

## `ArrayList`란?
* List 인터페이스의 구현 클래스
* `ArrayList`에 객체를 추가하는 순서대로 인덱스 번호 매겨짐(0부터)
* 배열과 유사한 구조
* `ArrayList<데이터타입> 변수명 = new ArrayList<데이터타입>();`
    * 괄호에 `int`값 넣으면 그 만큼의 메모리를 할당하며 초기화할 수 있음
        * ❓ 써봤는데 의미가 있나 싶긴 함
    * 뒤의 Generics는 생략할 수 있다.
* `toString()` 메소드가 오버라이드 되어 있어 리스트를 출력하면 내부 원소들을 예쁘게 확인할 수 있음

## `LinkedList`란?
* List의 구현 클래스, 사용 방법은 똑같지만(인터페이스) 내부 구조가 완전히 다름
* `ArrayList`는 데이터를 인덱스로 관리하지만 `LinkedList`는 인접 참조를 링크해서 체인처럼 관리
* 데이터의 빈번한 추가/삭제가 일어나는 경우 대부분은 `ArrayList`보다 성능이 좋음

## `Map`
* Key 객체와 Value 객체로 구성된 객체를 저장하는 구조
* 객체 자체를 저장하지 않고 객체의 주소를 참조
* Key는 중복 저장 불가, Value는 중복 저장 가능
    * 기존 저장된 Key와 동일한 Key로 저장하면 새로운 값으로 대체됨

## Map 인터페이스의 메소드
기능 | 메소드 | 설명
---|---|---
객체 추가|`put(Object key, Object value)`|주어진 키와 값을 Map에 추가
객체 검색|`containsKey(Object key)`|주어진 키가 있는지 여부 반환
객체 검색|`containsValue(Object value)`|주어진 값이 있는지 여부 반환
객체 검색|`get(Object key)`|주어진 키에 해당하는 값 반환
객체 검색|`isEmpty()`|컬렉션이 비어있는지 여부 반환
객체 검색|`keySet()`|모든 키를 Set 객체 담아서 리턴
객체 검색|`size()`|저장된 키 총 개수 리턴
객체 삭제|`clear()`|Map의 모든 객체 삭제
객체 삭제|`remove(Object key)`|해당 키와 일치하는 객체 삭제

## `HashMap`이란?
* `Map` 인터페이스를 구현한 대표적인 Map 컬렉션
* 데이터 저장의 순서 보장하지 않음
* Key에 대한 Hash 값을 저장하고 조회하기 때문에 많은 양의 데이터를 다룰 때 성능이 뛰어남
* `HashMap<키_데이터타입, 값_데이터타입> 변수명 = new HashMap<키_데이터타입, 값_데이터타입>();`
* `keySet()`과 `for-each`문을 이용하여 데이터 접근 가능
    ```java
    for(String key, map.keySet()) {
        System.out.println(map.get(key));
    }
    ```


## MEMO
* `list.remove(value)`
    * 중복된 값 있으면 어떻게 됨?