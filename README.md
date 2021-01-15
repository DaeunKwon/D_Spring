# SpringMall

- 이름: SpringMall (쇼핑몰 게시판)
-	기간: 2020.03 ~ 2020.05
-	사용한 언어/기술: Spring, JAVA, Oracle Database
-	개요:
```
 1. Spring framework에서 Tomcat Server를 설치하고 Oracle DB와 연동하여 웹 페이지 구축
 2. 상품 목록 페이지가 페이징 처리되어 있고 사용자가 원하는 상품을 찾을 수 있는 쇼핑몰 게시판
 3. 사용자는 상품 등록, 수정, 삭제할 수 있으며 상품 목록을 볼 수 있고 상품 상세 페이지에 댓글을 작성할 수 있다.
 ```
  
-	목표 :
```
 1. 사용자는 페이징 처리된 상품 목록을 볼 수 있다
 2. 사용자는 상품 목록에서 원하는 상품을 검색할 수 있다
 3. 사용자는 상품 상세 페이지를 볼 수 있다
  3.1. 사용자는 상품 상세 페이지에 댓글을 작성할 수 있다
  3.2. 사용자는 상품 상세 페이지에 작성한 댓글을 수정하거나 삭제할 수 있다
 4. 사용자는 상품을 등록할 수 있다
  4.1. 사용자는 상품 등록시 이미지 파일을 첨부할 수 있다
 5. 사용자는 상품을 수정할 수 있다
  5.1. 사용자는 상품 수정 시 이미지 파일을 수정하거나 삭제할 수 있다
 6. 사용자는 상품을 삭제할 수 있다
 ```

- 내용 :
```
 * 개발 환경 세팅
  1. STS에 Tomcat server 설치
  2. Oracle Database 설치하고 유저 생성, 권한 부여
  3. HTML5, Bootstrap4 이용하여 프론트엔드 세팅
  
 * DB 설계
  1. 상품 테이블
   상품 번호 > contraints로 기본키 설정 (기본키 테이블 생성), index 생성 (자동 증가)
   상품명
   작성자
   상품 내용
   상품 작성일
   상품 최종 수정일
   조회수
   이미지파일
   +댓글개수
  2. 댓글 테이블
   댓글 번호 > contraints로 기본키 설정 (기본키 테이블 생성), index 생성 (자동 증가)
   상품 번호 (외래키-상품 테이블 참조)
   댓글 내용
   작성자
   댓글 등록 날짜
   댓글 수정 날짜
   
 * Back

 * Front
  - 홈 페이지
   메인 화면의 사진을 누르면 상품 목록 페이지로 이동
  
  - 상품 목록 페이지
   1. 페이징 처리된 상품 목록(상품 번호, 이름, 작성자, 조회수, 작성 날짜 포함)을 볼 수 있음 (페이징 처리 과정에서 DB index 사용)
   2. [Write] 버튼을 누르면 상품 등록 가능
   3. 검색 범위(제목/작성자/내용/제목+작성자/제목+내용/작성자+내용 중 택일) 설정하여 원하는 상품 검색 가능
  
  - 상품 등록 페이지
   1. 상품명, 작성자, 내용 작성, 이미지 파일 첨부 가능
   2. [Submit] 버튼을 누르면 DB에 상품 저장하고 상품 목록 페이지로 이동
  
  - 상품 상세 설명 페이지
   1. 상품 번호, 상품명, 작성자, 조회수, 처음 작성 날짜, 최근 작성(수정) 날짜, 상품 내용, 이미지 파일 확인
   2. 댓글 작성, 수정, 삭제 가능
   3. [목록] 버튼 누르면 해당 상품이 포함된 페이지 목록으로 이동
   4. [수정] 버튼 누르면 상품 수정 페이지로 이동
   5. [삭제] 버튼 누르면 상품 삭제 처리
  
  - 상품 수정 페이지
   1. 상품 번호, 작성자, 최근 작성 날짜를 제외한 내용을 수정할 수 있음
   2. 첨부했던 이미지 파일을 삭제할 수 있음
   3. [submit] 버튼 누르면 수정된 내용이 적용된 상품 상세 내용 페이지로 이동
```
