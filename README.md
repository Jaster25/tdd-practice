# TDD 실습
> **Test Driven Development**

## 학습 목표
- 선 테스트 후 개발의 모든 과정을 커밋하며 습득하기

<br>

## 목차
1. 암호 검사기
2. 멤버십 적립 서비스

<br>

## 1. 암호 검사기
> **password-checker 프로젝트**
> 
> [유튜브 - 세미나 공유 - TDD 소개 및 시연 by 최범균](https://www.youtube.com/watch?v=6Vt-wKPBbuc&list=PLwouWTPuIjUj_QqgXlFsqjUwyC0-5dZ_q&index=5)

### 요구사항
- 다음 규칙을 모두 충족하면 매우 강함이다.
    - 길이가 8글자 이상
    - 0부터 9 사이의 숫자를 포함
    - 대문자 포함
- 2개의 규칙을 충족하면 보통이다.
- 1개 이하의 규칙을 충족하면 약함이다.

### 개발 과정
[🔗 TIL - TDD 소개 및 시연](https://github.com/Jaster25/TIL/blob/main/TDD/TDD%EC%86%8C%EA%B0%9C%EB%B0%8F%EC%8B%9C%EC%97%B0.md)

<br>

## 2. 멤버십 적립 서비스
> **membership-api 프로젝트**
> 
> [블로그 - TDD 실습 by 망나니개발자](https://mangkyu.tistory.com/182)

### 실습 내용
- 이번 연습 문제의 주제는 멤버십 적립 서비스입니다.
- 현재 지원중인 멤버십에는 네이버, 카카오, GS&POINT 3가지 멤버십이 있으며, 사용자는 원하는 멤버십을 등록할 수 있습니다.
- 포인트 적립비율은 결제금액의 1%로 고정되며, 추후에 고정 금액(1000원)으로 확장하여 적립될 수 있어야 합니다.
- 이번 연습문제에서는 위의 문제 설명과 아래의 요구사항을 만족하는 REST API를 자유롭게 정의하고, TDD 방식으로 구현하는 것입니다.

### 요구사항
- 멤버십 연결하기, 나의 멤버십 조회, 멤버십 연결끊기, 포인트 적립 API 를 구현합니다.
- **사용자 식별값은 문자열 형태이며 "X-USER-ID" 라는 HTTP Header 로 전달되며**, 이 값은 포인트 적립할 때 바코드 대신 사용됩니다.
- Content-type 응답 형태는 application/json(JSON) 형식을 사용합니다.
- 각 기능 및 제약사항에 대한 개발을 TDD, 단위테스트를 기반으로 진행해야 합니다.

### API 명세서
| **API**               | **HTTP Method** | **URI**                                | **Request**               | **Response**                                                |
|-----------------------|-----------------|----------------------------------------|---------------------------|-------------------------------------------------------------|
| 나의 멤버십 등록      | POST            | /api/v1/memberships                    | - 멤버십 이름<br>- 포인트 | - 멤버십 ID<br>- 멤버십 이름<br>- 포인트<br>- 가입 일시     |
| 나의 멤버십 전체 조회 | GET             | /api/v1/memberships                    | x                         | {멤버십 ID, 멤버십 이름, 포인트, 가입 일시}의 멤버십 리스트 |
| 나의 멤버십 상세 조회 | GET             | /api/v1/memberships/{membershipId}     | x                         | - 멤버십 ID<br>- 멤버십 이름<br>- 포인트<br>- 가입 일시     |
| 나의 멤버십 삭제      | DELETE          | /api/v1/memberships/{membershipId}     | x                         | X                                                           |
| 멤버십 포인트 적립    | POST            | /api/v1/memberships/{membershipId}/add | - 사용 금액               | X                                                           |



### 개발 과정

<br>

## 📚 References
- [유튜브 - 세미나 공유 - TDD 소개 및 시연 by 최범균](https://www.youtube.com/watch?v=6Vt-wKPBbuc&list=PLwouWTPuIjUj_QqgXlFsqjUwyC0-5dZ_q&index=5)
- [블로그 - TDD 실습 by 망나니개발자](https://mangkyu.tistory.com/182)
