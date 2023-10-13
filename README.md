# 원티드 프리온보딩 백엔드 인턴십(10월) - 선발 과제
<br></br>
 

### 지원자의 성명

---
박정현

<br><br>


### 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)

---
1. 해당 repository clone
2. RecruitApplication 실행
3. http://localhost:8080 으로 접근

<br><br>

### 데이터베이스 테이블 구조

---
![image](https://github.com/supark0206/wanted-pre-onboarding-backend/assets/71696834/8ff63412-7777-4d49-8c89-cc09f7f4500a)


<br><br>

### 구현한 API의 동작을 촬영한 데모 영상 링크

---

<br><br>
### 구현 방법 및 이유에 대한 간략한 설명 / API문서

---
- **모델링**
  - User(사용자), Company(회사), Recruitment(채용공고), ApplyHistory(지원내역) 테이블을 만들었습니다.
  - @ManyToOne 관계이기 때문에 성능(이후 기능 추가가 되도 성능 리팩토링이 더 쉽다.)과 sql 추적을 위해 지연로딩을 사용하였습니다.

- **과제 1. 채용공고를 등록합니다. [POST]/api/recruitment**
  - RecruitmentRequest를 통해 받은 데이터로 채용공고를 등록합니다.
    
  - request
  ```json
  {
    "companyId":1, 
    "position":"원티드 프론트 개발자",
    "reward":2000,
    "content":"백엔드 개발자를 채용합니다. 자격요건은..",
    "skill":"C"
  }
  ```
  
  - response
  ```json 
  {
      "id": 2,
      "message": "채용공고를 등록하였습니다."
  }
  ```

- **과제 2. 채용공고를 수정합니다. [PUT]/api/recruitment/{recruitId}**
  - @PathVariable을 통해서 채용공고 아이디 recruitId를 받아옵니다
  - RecruitmentRequest를 통해 수정할 데이터를 받아서 수정합니다.
 
  - request
  ```json
  {
    "companyId":1, 
    "position":"백엔드 주니어 개발자 수정!",
    "reward":10,
    "content":"수정!",
    "skill":"수정!"
  }
  ```
  
  - response
  ```json 
  {
      "id": 1,
      "message": "채용공고를 수정하였습니다."
  }
  ```

 
- **과제 3. 채용공고를 삭제합니다. [DELETE]/api/recruitment/{recruitId}/{companyId}**
  - @PathVariable을 통해서 채용공고 아이디 recruitId, 회사아이디 companyId를 받아옵니다
  - 해당 리소스가 있는지 확인한 이후 삭제합니다
 
  - response
  ```json 
  {
      "id": 1,
      "message": "채용공고를 삭제하였습니다.."
  }
  ```
 
- **과제 4-1. 채용공고 목록을 가져옵니다. [GET]/api/recruitment?page=1&size=10**
  - JPA를 활용하여 페이징 처리한 채용공고 목록을 가져옵니다
  - page에 원하는 페이지와 size에 한페이지당 원하는 공고수를 입력받습니다.
  - page와 size에 맞도록 공고가 출력됩니다
  - ![image](https://github.com/supark0206/wanted-pre-onboarding-backend/assets/71696834/7fce944b-6ee4-4c48-bcd8-80ef6fa67f34)

   - response
  ```json 
  {
      "recruitmentResponseList": [
          {
              "id": 2,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "C",
              "reward": "2000"
          },
          {
              "id": 3,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "C",
              "reward": "2000"
          },
          {
              "id": 4,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "C",
              "reward": "2000"
          },
          {
              "id": 5,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "java",
              "reward": "2000"
          },
          {
              "id": 6,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "java",
              "reward": "2000"
          },
          {
              "id": 7,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "자바",
              "reward": "2000"
          },
          {
              "id": 8,
              "companyName": "회사명",
              "position": "원티드 백엔드 개발자",
              "skill": "자바",
              "reward": "2000"
          },
          {
              "id": 9,
              "companyName": "회사명",
              "position": "원티드 자바 개발자",
              "skill": "파이썬",
              "reward": "2000"
          },
          {
              "id": 10,
              "companyName": "회사명",
              "position": "원티드 자바 개발자",
              "skill": "파이썬",
              "reward": "2000"
          }
      ],
      "totalPages": 1
  }
  ```
   
- **과제 4-2. 채용검색 기능 구현. [GET]/api/recruitment/list?page=1&size=10&search=java**
  - JPA를 활용하여 페이징 처리한 채용공고 목록을 가져옵니다
  - page에 원하는 페이지와 size에 한페이지당 원하는 공고수를 입력받습니다.
  - 해당 리소스가 있는지 확인한 이후 삭제합니다.
  - jpql을 작성하여 or 을 활용하여 전체컬럼을 search값 하나로 조회하였습니다. 
  - ![image](https://github.com/supark0206/wanted-pre-onboarding-backend/assets/71696834/73e08dd9-60e2-4882-982f-c58ff62e7108)

  - response
  ```json 
  {
      "recruitmentResponseList": [
          {
              "id": 5,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "java",
              "reward": "2000"
          },
          {
              "id": 6,
              "companyName": "회사명",
              "position": "원티드 프론트 개발자",
              "skill": "java",
              "reward": "2000"
          }
      ],
      "totalPages": 1
  }
  ```
- **과제 5. 채용 상세 페이지를 가져옵니다. [GET]/api/recruitment/detail/{recruitId}**
  - @PathVariable을 통해서 채용공고 아이디 recruitId를 받아옵니다
  - recruitId로 해당 채용공고를 조회한후 Company 정보를 조회합니다
  - 해당 company로 정보로 해당 company가 올린 모든 채용정보 List를 조회합니다
  - 조회한 채용공고와 해당 채용공고Id를 제외한 company가 올렸던 다른 Id값들을 같이 출력합니다.
  
  - response
  ```json 
  {
      "id": 2,
      "companyName": "회사명",
      "position": "원티드 프론트 개발자",
      "skill": "C",
      "reward": "2000",
      "content": "백엔드 개발자를 채용합니다. 자격요건은..",
      "anotherList": [
          3,
          4,
          5,
          6,
          7,
          8,
          9,
          10
      ]
  }
  ```

- **과제 6. 사용자는 채용공고에 지원합니다(선택사항 및 가산점요소). [GET]/api/apply-history**
  - 사용자는 1회만 지원 가능합니다.
  - 저장된 채용공고 id와 사용자 id를 받아서 저장합니다
  - ApplyHistory 테이블은 User와 Recruitment 테이블을 각각 외래키로 가지고있습니다
  - 같은 곳에 같은 사용자가 지원하게되면 validDuplicateApply 로직을 거쳐서 중복체크를 진행합니다

  - request
  ```json 
  {
    "recruitmentId":2,
    "userId":1
  }
  ```
 
 - response
  ```json 
  {
      //지원 성공시
      "id": 1,
      "message": "채용공고 지원에 성공하였습니다."
  }
  ```

  ```json 
  {
    //중복 지원시
    "status": 409,
    "error": "CONFLICT",
    "code": "EXIST_APPLY_HISTORY",
    "message": "이미 지원한 공고입니다."
  }
  ```
