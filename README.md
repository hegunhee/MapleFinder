## Maple 정보찾기 앱
메이플 캐릭터의 정보 검색을 할 수 있는 앱입니다.

## 개발환경
Kotlin : Kotlin 1.8
Java : Java 17
gradle : 8.0
IDE = Android Studio Giraffe (2022.3.1)

## 주의사항
현재 해당 프로젝트에는 API KEY가 담겨있지않습니다.
만약 테스트를 해보고 싶으시다면 secrets.defaults.properties 값을 변경하시면 됩니다.

## 기술스택
network - retrofit2, moshi, coroutine
ui - compose, ViewModel, Flows
DI - Hilt
test - Junit

## 기술정보
- 100% Compose로 작업했습니다.
- build-logic 모듈을 통해 멀티모듈에서도 의존성 관리를 보다 쉽게했습니다.

## 진척도  
https://github.com/hegunhee/MapleFinder/issues/3
