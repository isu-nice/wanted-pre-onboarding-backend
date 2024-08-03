# 프리온보딩 백엔드 인턴십 선발과제

## 개요
이 프로젝트는 회사의 채용 웹 서비스를 위한 API 서버입니다.   
회사는 채용공고를 생성하고, 사용자는 채용공고에 지원할 수 있습니다.   
이 시스템은 Java와 Spring Framework를 사용하여 구현되었습니다.


## 기능
- 채용공고 관리
  - [x] 채용공고 등록
    - 회사 정보 존재 여부 검증
  - [x] 채용공고 수정
    - 채용공고 존재 여부 검증
    - 채용보상금 검증 (10만원 이상)
  - [x] 채용공고 삭제
  - [x] 채용공고 목록 조회
  - [x] 채용공고 검색
  - [x] 채용 상세 페이지 조회
- 사용자 지원 관리
  - [x] 채용공고에 지원
- 오류 응답
  - [x] ErrorResponse 클래스를 추가하여 오류 응답을 보다 세부적으로 관리
  - [x] 필드 유효성 검증 및 제약 조건 위반 에러를 포함한 응답 정보를 생성하는 기능
  - [x] BindingResult와 ConstraintViolation을 처리하여, 발생한 오류에 대한 상세 정보를 포함하는 JSON 응답을 생성

## 기술 스택
- Java 17
- Spring Boot 3.2.8
- Spring Data JPA
- QueryDSL
- MySQL

## 프로젝트 구조
```markdown
wanted.pre_onboarding
├── application
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
├── jobPosting
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── mapper
│   ├── repository
│   └── service
└── user
    ├── controller
    ├── dto
    ├── entity
    ├── mapper
    ├── repository
    └── service
```

## API 문서
