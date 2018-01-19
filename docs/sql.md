# 자주쓰는 SQL문 정리(mysql)
## 1. 원하는 갯수 만큼만 데이터 가져오기.
```
select * from tbl_board order by bno desc limit 0, 5;
```
bno로 내림차순 정렬한 데이터 record중 index 0 번 부터 5개를 가져온다<br>
그 다음 5개를 가져오고 싶다면, 아래와 같이 쿼리하면 된다.
```
select * from tbl_board order by bno desc limit 5, 5;
```

## 2. 테이블 내 모든 데이터 삭제하기
```
delete from tbl_board;
```

## 3. auto_increment를 1로 초기화 하기.
```
ALTER TABLE tbl_board auto_increment = 1;
```

## 4. test용 데이터 자가복제해서 넣기
```
insert into tbl_board (title, content, writer) (select title, content, writer from tbl_board);
```
위와 같이 쿼리하면 tbl_board 테이블에 있던 모든 데이터를 가져와서 복제해서 insert하기 떄문에 총 저장된 데이터의 수가 2배가 된다.

## 5. 테이블에 저장된 데이터 갯수 확인하기.
```
select count(*) from tbl_board;
```
