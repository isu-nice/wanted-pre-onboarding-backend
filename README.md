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

## ERD



## 프로젝트 구조
```markdown
wanted.pre_onboarding
├── application
│   ├── controller
│   │   ├── ApplicationController              # 지원 관련 API 엔드포인트
│   ├── dto
│   │   ├── ApplicationDto                     # 지원 관련 DTO
│   ├── entity
│   │   ├── Application                        # 지원 엔티티
│   ├── repository
│   │   ├── ApplicationRepository              # 지원 관련 레포지토리
│   └── service
│       ├── ApplicationService                # 지원 관련 서비스
├── company
│   ├── entity
│   │   ├── Company                         # 회사 엔티티
│   ├── repository
│   │   ├── CompanyRepository               # 회사 레포지토리
│   └── service
│       ├── CompanyService                 # 회사 관련 서비스
├── jobPosting
│   ├── controller
│   │   ├── JobPostingController              # 채용공고 관련 API 엔드포인트
│   ├── dto
│   │   ├── JobPostingDetailsDto              # 채용공고 상세 DTO
│   │   ├── JobPostingPatchDto                # 채용공고 수정 DTO
│   │   ├── JobPostingPostDto                 # 채용공고 등록 DTO
│   │   ├── JobPostingsResponseDto            # 채용공고 목록 DTO
│   ├── entity
│   │   ├── JobPosting                        # 채용공고 엔티티
│   ├── mapper
│   │   ├── JobPostingMapper                  # 채용공고 관련 매퍼
│   ├── repository
│   │   ├── JobPostingRepository               # 채용공고 레포지토리
│   │   ├── JobPostingRepositoryCustom        # 커스텀 쿼리 메소드
│   │   ├── JobPostingRepositoryImpl          # 커스텀 쿼리 구현
│   └── service
│       ├── JobPostingService                 # 채용공고 관련 서비스
└── user
    ├── entity
    │   ├── User                             # 사용자 엔티티
    ├── repository
    │   ├── UserRepository                   # 사용자 레포지토리
    └── service
        ├── UserService                     # 사용자 관련 서비스
├── config
│   ├── QueryDSLConfig                       # QueryDSL 설정 클래스
├── exception
│   ├── advice
│   │   ├── JGlobalExceptionAdvice           # 전역 예외 처리 어드바이스
│   ├── util
│   │   ├── ErrorResponder
│   │   ├── ErrorResponse
│   │   ├── JsonUtil                          # JSON 유틸리티 클래스
│   ├──  BusinessLogicException               # 비즈니스 로직 예외 클래스
│   ├──  ExceptionCode                        # 예외 코드 

```


## API 문서
