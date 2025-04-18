## Maple 정보찾기 앱
메이플 캐릭터의 정보 검색을 할 수 있는 앱입니다.

## 개발환경
Kotlin : 1.8.10  
Java : 17  
gradle : 8.4.0  
AGP : 8.3.2  
IDE : Android Studio Ladybug (2024.2.1 Patch 2)  

## Module  
본 프로젝트는 multi-module 구조이며 각 feature마다 모듈의 형태로 구성되어있습니다.  
uncle bob의 클린아키텍쳐를 차용하였습니다.  
## 모듈구조
📦build-logic  
📦app  
📦feature  
 ┣ 📂character-info  
 ┣ 📂dojang-record  
 ┣ 📂item  
 ┣ 📂main  
📦core  
 ┣ 📂data  
 ┣ 📂designsystem  
 ┣ 📂domain  
 ┣ 📂model  
 ┣ 📂ui  
 ┗ 📂util  

## 주의사항
현재 해당 프로젝트에는 API KEY가 담겨있지않습니다.  
만약 테스트를 해보고 싶으시다면 secrets.defaults.properties 값을 변경하시면 됩니다.  

## 기술스택
- network
  - Retrofit2, kotlinx.serialization, Corouitne
- ui
  - Jetpack Compose, ViewModel, Coroutine Flows  
- DI
  - Hilt  
- test
  - Junit4, mockito-kotlin, Espresso  

## 기술정보
- **compose 도입**  
  앱의 시작부터 compose를 사용하여 제작했습니다.  
  xml -> compose로 전환한 앱이 궁금하시다면 NowInJururu나 NewSimpleMemoApp을 참고해주시면 감사하겠습니다.

- **kotlinx.serialization 도입**  
  json의 특정 파라미터를 무시하고 kotlin과의 호환성을 높이기 위해서 moshi -> serialization으로 변경하였습니다.
  
- **multi-module + build-logic 도입**  
  앱의 모듈간 의존성을 **직관적으로** 보고 분리하고 **빌드 시간을 감소**시키기 위해 multi-module으로 분리했습니다.  
  그리고 모듈 분리로 인한 **의존성 설정 증가**를 방지하기 위해 build-logic을 사용했습니다.  
  **의존성 버전**과 **의존성 관리**를 위해 version-catalog를 도입했습니다.
  
- **custom exception 도입**  
  retrofit2.httpException을 presentation module에서도 사용하기 위해  
  data module에서 httpException을 분리해 custom exception을 만들어 UI에서 오류를 표시할 수 있게 하였습니다.  
  
## 프로젝트 칸반  
지속적으로 기능을 추가하고 오류를 수정하고있습니다.  
https://github.com/users/hegunhee/projects/6  

## issue(트러블 슈팅)  
- **Json 직렬화/역직렬화 라이브러리를 moshi에서 serialization으로 변경하게 된 계기**  
Json객체의 비슷한 속성에 대한 **클래스 선언 중복**을 줄이기 위해  
Json의 특정 속성을 무시할 수 있는것에 대해 찾던중 serialization의 Json Builder값을 수정해 특정 값을 무시할 수 있다는걸 찾고  
능력치 클래스를 하나만 만들어 **보일러 플레이트 코드를 줄이고** 매퍼 함수가 줄어들기때문에 **유지보수에 용이**해졌습니다.  
코틀린 호환성과 default value를 사용할 수 있어 not-null한 코드를 작성할 수 있게 되었습니다.  
https://hegunhee.tistory.com/33  

- **custom exception 적용**  
유저에게 에러가 발생했을때 에러 메시지 화면을 보여주고싶었지만  
retrofit2.httpException 타입으로 내려오게 되고 presentation module의 경우 retrofit2 의존성이 존재하지않아  
해당 exception을 처리할 수 없었습니다.  
data module에서 httpException을 파싱해 model module에서 exception class를 작성해 presentation module에서도  
해당 에러를 파싱해 유저에게 적합한 에러 화면을 보여줄 수 있었습니다.  
https://hegunhee.tistory.com/34  

## 앱 화면  
| 캐릭터 정보 검색창 (빈칸) | 캐릭터 정보검색창 (에러) | 캐릭터 정보 |
| ------------------- | ------------------ | -------- |
| ![캐릭터 정보검색창 (빈칸)](https://github.com/user-attachments/assets/e0a9a9c0-6ebf-4a4b-a9bd-0c949dd3afad) | ![캐릭터 정보검색창 (에러)](https://github.com/user-attachments/assets/10d7482e-9015-4be2-b2a1-ae2058e326c2) | ![캐릭터 정보](https://github.com/user-attachments/assets/7b605d62-fb93-4103-a5f6-6a1a86961135) |  
| 캐릭터 장비 정보 | 캐릭터 장비 상세 정보 | drawerLayout |
| ![캐릭터 장비 정보](https://github.com/user-attachments/assets/95d4ec32-2ad7-4382-a07f-12dd1b5f3d66) | ![캐릭터 장비 상세 정보](https://github.com/user-attachments/assets/e83737be-70a6-442e-8970-9168cf1a54b2) | ![drawerLayout](https://github.com/user-attachments/assets/afcec261-f6d5-4635-99c6-78cdb5e0a1b2) | 
| 캐릭터 캐시 장비 정보 |
| ![캐릭터 캐시 장비 정보](https://github.com/user-attachments/assets/75e3f7db-31ae-4b0b-8a13-d8bfa962ff24) |
