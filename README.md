## Maple 정보찾기 앱
메이플 캐릭터의 정보 검색을 할 수 있는 앱입니다.

## 개발환경
Kotlin : Kotlin 1.8
Java : Java 17
gradle : 8.0
IDE = Android Studio Giraffe (2022.3.1)  

## Module  
본 프로젝트는 multi-module 구조이며 각 feature마다 모듈의 형태로 구성되어있습니다.  
uncle bob의 클린아키텍쳐를 차용하였습니다.
![image](https://github.com/hegunhee/MapleFinder/assets/57277631/c04d6a51-89b3-4b3d-bb87-28ab0ec58903)

## 주의사항
현재 해당 프로젝트에는 API KEY가 담겨있지않습니다.
만약 테스트를 해보고 싶으시다면 secrets.defaults.properties 값을 변경하시면 됩니다.

## 기술스택
network - retrofit2, kotlinx.serialization, coroutine  
ui - compose, ViewModel, Flows  
DI - Hilt  
test - Junit  

## 기술정보
- **compose 도입**  
  앱의 시작부터 compose를 사용하여 제작했습니다. xml -> compose로 전환한 앱이 궁금하시다면 NowInJururu나 NewSimpleMemoApp을 참고해주시면 감사하겠습니다.
- **kotlinx.serialization 도입**  
  json의 특정 파라미터를 무시하고 kotlin과의 호환성을 높이기 위해서 moshi -> serialization으로 변경하였습니다.  
- **multi-module + build-logic 도입**  
  앱의 모듈간 의존성을 직관적으로 보고 분리하고 빌드 시간을 감소시키기 위해 multi-module으로 분리했습니다.  
  그리고 모듈 분리로 인한 의존성 설정 증가를 방지하기 위해 build-logic을 사용했습니다.  
  의존성 버전과 의존성 관리를 위해 version-catalog를 도입했습니다.  
- **custom exception 도입**  
  retrofit2.httpException을 presentation module에서도 사용하기 위해  
  data module에서 httpException을 분리해 custom exception을 만들어 UI에서 오류를 표시할 수 있게 하였습니다.  
  
## 진척도  
https://github.com/hegunhee/MapleFinder/issues/3  

## issue(트러블 슈팅)  
**Json 직렬화/역직렬화 라이브러리를 moshi에서 serialization으로 변경하게 된 계기**
비슷한 캐릭터 능력치 데이터에 대한 클래스 선언 중복을 줄이기 위해  
Json의 특정 key-value값을 무시할 수 있는것에 대해 찾던중 serialization의 Json Builder값을 수정해 특정 값을 무시할 수 있다는걸 찾고  
능력치 클래스를 하나만 만들어 보일러 플레이트 코드를 줄이고 매퍼 함수가 줄어들기때문에 유지보수에 용이해졌습니다.  
코틀린 호환성과 default value를 사용할 수 있어 not-null한 코드를 작성할 수 있게 되었습니다.  
https://hegunhee.tistory.com/33  
**custom exception 적용**  
유저에게 에러가 발생했을때 에러 메시지 화면을 보여주고싶었지만  
retrofit2.httpException 타입으로 내려오게 되고 presentation module의 경우 retrofit2 의존성이 존재하지않아  
해당 exception을 처리할 수 없었습니다.  
data module에서 httpException을 파싱해 model module에서 exception class를 작성해 presentation module에서도  
해당 에러를 파싱해 유저에게 적합한 에러 화면을 보여줄 수 있었습니다.  
https://hegunhee.tistory.com/34
