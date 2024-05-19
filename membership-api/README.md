# 💳 멤버십 적립 서비스

<br>

## 실습 내용

- 주제는 멤버십 적립 서비스
- 지원중인 멤버십에는 네이버, 카카오, GS&POINT 3가지 멤버십이 있으며, 사용자는 원하는 멤버십을 등록할 수 있다.
- 포인트 적립 비율은 결제금액의 1%로 고정되며, 추후에 고정 금액(1000원)으로 확장하여 적립될 수 있어야 한다.
- 해당 실습에서는 위의 문제 설명과 아래의 요구사항을 만족하는 REST API를 자유롭게 정의하고, TDD 방식으로 구현하는 것

<br>

## 요구사항

- 멤버십 연결하기, 나의 멤버십 조회, 멤버십 연결끊기, 포인트 적립 API 를 구현한다.
- **사용자 식별값은 문자열 형태이며 "X-USER-ID" 라는 HTTP Header 로 전달되며**, 이 값은 포인트 적립할 때 바코드 대신 사용된다.
- Content-type 응답 형태는 application/json(JSON) 형식을 사용한다.
- 각 기능 및 제약사항에 대한 개발을 TDD, 단위테스트를 기반으로 진행해야 한다.

<br>

## API 명세서

| **API**               | **HTTP Method** | **URI**                                | **Request**               | **Response**                                                |
| --------------------- | --------------- | -------------------------------------- | ------------------------- | ----------------------------------------------------------- |
| 나의 멤버십 등록      | POST            | /api/v1/memberships                    | - 멤버십 이름<br>- 포인트 | - 멤버십 ID<br>- 멤버십 이름<br>- 포인트<br>- 가입 일시     |
| 나의 멤버십 전체 조회 | GET             | /api/v1/memberships                    | x                         | {멤버십 ID, 멤버십 이름, 포인트, 가입 일시}의 멤버십 리스트 |
| 나의 멤버십 상세 조회 | GET             | /api/v1/memberships/{membershipId}     | x                         | - 멤버십 ID<br>- 멤버십 이름<br>- 포인트<br>- 가입 일시     |
| 나의 멤버십 삭제      | DELETE          | /api/v1/memberships/{membershipId}     | x                         | X                                                           |
| 멤버십 포인트 적립    | POST            | /api/v1/memberships/{membershipId}/add | - 사용 금액               | X                                                           |

<br>

## 개발 과정

🔗 [커밋 로그](https://github.com/Jaster25/tdd-practice/commits/feature/membership/)

1. Member 도메인 - 멤버십 전체 조회
2. Member 도메인 - 멤버십 등록
3. Membership 도메인 - 포인트 조회
4. Member 도메인, Membership 도메인 연동 및 코드 리팩토링
5. Membership 도메인 - 포인트 추가
6. 멤버십 지원 회사 Enum 추가 및 코드 리팩토링
7. Member 도메인 - 멤버십 삭제
   1. 존재하지 않는 멤버십 삭제
8. JPA 연관관계 코드 추가 및 도메인 코드 소스 이동(test -> main)
9. Member 도메인 - 포인트 추가 놓친 예외 추가
   1. 추가하려는 포인트가 음수인 경우
10. Member 서비스 - 멤버 조회
    1. 존재하지 않는 멤버
11. Membership 서비스 - 멤버십 목록 조회
    1. 존재하지 않는 멤버
12. Membership 서비스 - 멤버십 상세 조회
    1. 존재하지 않는 멤버십
13. Membership 서비스 - 권한 검증
    1. 본인 멤버십이 아닌 경우
14. 코드 리팩토링
15. Membership 서비스 - 멤버십 등록
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십 이름
    3. 이미 존재하는 멤버십 중복 등록
16. Membership 서비스 - 멤버십 삭제
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십
    3. 본인 멤버십이 아닌 경우
17. Membership 서비스 - 멤버십 포인트 적립
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십
    3. 본인 멤버십이 아닌 경우
18. 서비스 코드 소스 이동(test -> main)
19. Membership 컨트롤러 - 멤버십 등록
    1. 존재하지 않는 멤버의 사용자 식별 값
    2. 존재하지 않는 멤버십
20. Membership 서비스 - 멤버십 등록 시 포인트 기입하도록 리팩토링
21. Membership 컨트롤러 - 멤버십 전체 조회
    1. 존재하지 않는 멤버
22. Membership 컨트롤러 - 멤버십 상세 조회
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십
23. Membership 컨트롤러 - 멤버식 삭제
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십
24. Membership 컨트롤러 - 멤버십 포인트 적립
    1. 존재하지 않는 멤버
    2. 존재하지 않는 멤버십
25. 컨트롤러 코드 소스 이동(test -> main)

<br>

## 느낀 점

- 테스트 코드 작성 단계에서 컴파일 에러 상태에서 마무리하는 것보다 return null, 예외 발생 등으로 처리하여 컴파일은 되도록 구현하고 넘어가는게 나은 것 같다.
- 도메인 계층부터 구현해봤는데 상위 계층에서 보니 필요없는 코드도 생기고 리팩토링도 빈번했다. 다음엔 컨트롤러 계층부터 구현해보며 차이점을 정리해봐야겠다.

<br>

## 궁금한 점

- 도메인 계층부터 DDD를 추구하며 구현을 했는데, JPA를 도입하며 연관관계 기능을 추가하는 과정에서 난처해진 코드가 많아지고 고민되는 부분이 많았다.
  - ex) Member 도메인의 멤버십 추가 메서드
    - 멤버 도메인에서 멤버십 엔티티를 생성하여 연관관계를 맺어 주는 것이 부자연스러워 보였다.(영속화하는 과정)
    - 서비스 계층으로 옮기기로 함
    - Member 도메인에서 멤버십 추가 기능이 없어지다 보니 멤버십 조회, 삭제 테스트가 불가하여 기존 테스트가 난처해졌다.
    - Member 도메인의 멤버십 등록 코드
    ```java
    public Membership registerMembership(Membership newMembership) {
        memberships.add(newMembership);
        return newMembership;
    }
    ```
- 반환 타입이 void인 메서드를 테스트 할 때는 행위에 포커스를 맞춘다.(내부 메서드가 실행 했는지, 상태가 바뀌었는지, ...) 하지만 아래와 같이 상태의 변화가 없는 단순 검증 로직이 성공하는 테스트 케이스는 then 영역에서 무엇을 테스트 해야 하는지 찾아봐야겠다.
    ```java
    public void verify(Member member, Membership membership) {
        if (!membership.getMember().equals(member)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
    ```

<br>

## 📚 References

- https://mangkyu.tistory.com/182
