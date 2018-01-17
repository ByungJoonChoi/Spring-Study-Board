package io.peter.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.peter.domain.BoardVO;
import io.peter.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardServiceTest {
	
	@Inject
	private BoardService service;
	
	@Test
	public void testRegister() throws Exception{
		BoardVO board = new BoardVO();
		board.setTitle("제목입니다.");
		board.setWriter("작성자입니다.");
		board.setContent("내용입니다.");
		service.regist(board);
	}
	
	@Test
	public void testRead() throws Exception{
		
		System.out.println(service.read(1));
	}
}
