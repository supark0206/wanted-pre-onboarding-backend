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
    //성공
      "id": 2,
      "message": "채용공고를 등록하였습니다."
  }
  ```

- **과제 2. 채용공고를 수정합니다. [PUT]/api/recruitment/{recruitId}**
  - @PathVariable을 통해서 채용공고 아이디 recruitId를 받아옵니다
  - RecruitmentRequest를 통해 수정할 데이터를 받아서 수정합니다.
 
- **과제 3. 채용공고를 삭제합니다. [DELETE]/api/recruitment/{recruitId}/{companyId}**
  - @PathVariable을 통해서 채용공고 아이디 recruitId, 회사아이디 companyId를 받아옵니다
  - 해당 리소스가 있는지 확인한 이후 삭제합니다
 
- **과제 4. 채용공고 목록을 가져옵니다. [GET]/api/recruitment**
  - @PathVariable을 통해서 채용공고 아이디 recruitId, 회사아이디 companyId를 받아옵니다
  - 해당 리소스가 있는지 확인한 이후 삭제합니다
