# SpringBoard

- 이름: SpringBoard (스프링 게시판)
-	기간: 2020.05 ~
-	개요:
```
 1. Spring framework에서의 웹 페이지 개발
 2. 상품 목록 페이지가 페이징 처리되어 있고 사용자가 원하는 상품을 찾을 수 있는 게시판
 3. 사용자는 상품 등록, 수정, 삭제할 수 있으며 상품 목록을 볼 수 있고 상품 상세 페이지에 댓글을 작성할 수 있다
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
  - Front-End
   1. HTML5
   2. Bootstrap4
   3. jQuery3
  - Back-End (전자정부프레임워크)
   1. Spring 4.3 (STS 3.9)
   2. JDK 1.8
   3. Oracle 11g
   4. Mybatis
   5. Tomcat Server v8.5
  
 * DB 설계
  1. 상품 테이블(tbl_product)
   상품 번호(pno) > contraints로 기본키 설정 (기본키 테이블(tbl_product_pk) 생성, index 생성 (자동 증가)
   상품명(title)
   작성자(writer)
   상품 내용(content)
   상품 작성일(regd8)
   상품 최종 수정일(modd8)
   조회수(readcount)
   이미지파일(imgfile)
   +댓글 개수(commentcnt)
  2. 댓글 테이블(tbl_comment)
   댓글 번호(cno) > contraints로 기본키 설정 (기본키 테이블(tbl_comment_pk) 생성, index 생성 (자동 증가)
   상품 번호(pno) (외래키-상품 테이블 참조)
   댓글 내용(content)
   작성자(writer) (상품 작성자와 동일)
   댓글 등록 날짜(regd8)
   댓글 수정 날짜(modd8)
   
 * Back-End
  - 기본 원리
   1. client가 request 요청 > Controller로 request 전달 > Service interface로 전달 > ServiceImp class로 전달 (위임) > DAO 객체 통해 DB에 접근 > mapper.xml에 정의된 sql문을 통해 요청한 request의 결과값 반환
    > DAO > ServiceImp class > Controller > jsp 파일 > 요청한 request의 결과가 browser에 나타남
   2. servlet-context.xml에서 요청 들어온 request를 어떤 식으로 처리할지 명시 (ViewResolver가 /***.jsp/ 형태를 가진 파일을 browser에 띄워줌)
   
  - Controller
   1. ProductCtrl
    ~ 상품과 관련된 전반적 기능 컨트롤 (상품 리스트 출력, 등록, 수정, 삭제 등)
   2. CommentCtrl
    ~ 댓글과 관련된 전반적 기능 컨트롤
    ~ @RestController 사용
    
   - Domain
    1. ProductVO
     ~ 상품 테이블이 가지는 컬럼 정의
    2. Criteria
     ~ 페이징의 기준과 상품 검색 기준을 정해놓은 클래스
     ~ 한 페이지에 출력할 상품의 개수, 현재 페이지
     ~ 검색한 내용(keyword, DB에서 일치하는 상품 검색하는 데 필요)
     ~ 검색한 조건(type, 내용/상품명/작성자/내용+상품명/내용+작성자/상품명+작성자)
    3. PagingVO
     ~ 페이징에 필요한 자료 정의한 객체
     ~ 총 글의 개수, Criteria, 페이징 시작 번호, 끝 번호, 시작 버튼, 끝 버튼
    4. CommentVO
     ~ 댓글 테이블이 가지는 컬럼 정의
    5. CommentDTO
     ~ 댓글 페이징을 위해 필요한 자료 정의한 객체
     
   - Persistence
    1. ProductDAO(interface) > ProductDAOImp (위임)
    2. CommentDAO(interface) > CommentDAOImp (위임)
     ~ 상품/댓글과 관련된 기능을 처리하기 위한 함수를 정의하고 DB에 접근하는 객체
     **** interface > implements 형태로 구현하는 이유: 객체 간 의존도 낮추고 함수 미리 정의하고 구현한 형태이기 때문에 유지보수 쉬워짐, 구현 코드를 수정하지 않고 사용하는 객체 수정 가능
     
   - Service
    1. ProductService(interface) > ProductServiceImp
    2. CommentService(interface) > CommentServiceImp
     ~ 컨트롤러로부터 받은 request를 처리하는 객체
     ~ DAO에 접근
     
   - Util
    FileProcess
     ~ 파일 첨부 기능을 처리하기 위한 객체
     
   - Mapper
    1. ProductMapper
     ~ 상품과 관련된 자료를 DB에서 꺼내오기 위한 SQL 정의
     ~ 상품 검색 기능 (like 연산자 사용)
     ~ 페이징 (rownum, index 사용, 서브 쿼리문에 INDEX_DESC 기법으로 조회하기 위해 주석 추가)
    2. CommentMapper
     ~ 댓글과 관련된 자료를 DB에서 꺼내오기 위한 SQL 정의
   
 * Front-End
  - 홈 페이지
   메인 화면의 사진을 누르면 상품 목록 페이지로 이동
  
  - 상품 목록 페이지
   1. 페이징 처리된 상품 목록(상품 번호, 이름, 작성자, 조회수, 작성 날짜 포함)을 볼 수 있음
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
