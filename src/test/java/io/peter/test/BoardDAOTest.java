package io.peter.test;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.peter.domain.BoardVO;
import io.peter.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 테스트 순서 설정
public class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void a_testCreate() throws Exception{
		BoardVO board = new BoardVO();
		board.setTitle("제목입니다.");
		board.setContent("내용입니다.");
		board.setWriter("작성자입니다.");
		dao.create(board);
	}
	
	@Test
	public void b_testRead() throws Exception{
		logger.info(dao.read(1).toString());
	}
	
	@Test
	public void c_testUpdate() throws Exception{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 제목입니다.");
		board.setContent("수정된 내용입니다.");
		dao.update(board);
	}
	
	@Test
	public void d_testDelete() throws Exception{
		dao.delete(1);
	}
	
	@Test
	public void e_testListPage() throws Exception{
		dao.listPage(1);
		dao.listPage(2);
		dao.listPage(3);
	}
	
}
