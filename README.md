# TDD 실습
> **Test-Driven Development**

<br>

## 학습 목표
- 선 테스트 후 개발, 아래 TDD 개발 순서를 최대한 지키기
- 모든 과정을 커밋하고 기록하기

### TDD 개발 순서
1. 테스트 코드 작성
2. 기능 구현
3. (리팩토링)

<br>

## 목차
1. 암호 검사기
2. 멤버십 적립 서비스

<br>

## 1. 암호 검사기
> **password-checker 프로젝트**
> 
> 간단한 암호 검사기를 구현해보자.

### 요구사항
- 다음 규칙을 모두 충족하면 매우 강함이다.
    - 길이가 8글자 이상
    - 0부터 9 사이의 숫자를 포함
    - 대문자 포함
- 2개의 규칙을 충족하면 보통이다.
- 1개 이하의 규칙을 충족하면 약함이다.

### 개발 과정
[🔗 TIL - TDD 소개 및 시연](https://github.com/Jaster25/TIL/blob/main/TDD/TDD%EC%86%8C%EA%B0%9C%EB%B0%8F%EC%8B%9C%EC%97%B0.md)

[📚 Ref: 유튜브 - 세미나 공유 - TDD 소개 및 시연 by 최범균](https://www.youtube.com/watch?v=6Vt-wKPBbuc&list=PLwouWTPuIjUj_QqgXlFsqjUwyC0-5dZ_q&index=5)


<br>

## 2. 멤버십 적립 서비스
> **membership-api 프로젝트**
> 
> 네이버, 카카오, GS&POINT 3가지 멤버십을 지원하며 멤버십 등록, 조회, 삭제, 포인트 적립 API이 제공되는 서비스를 구현해보자.

[🔗 개발 과정](https://github.com/Jaster25/tdd-practice/tree/main/membership-api/)

[🔗 커밋 로그](https://github.com/Jaster25/tdd-practice/commits/feature/membership/)

[📚 Ref: 블로그 - TDD 실습 by 망나니개발자](https://mangkyu.tistory.com/182)
