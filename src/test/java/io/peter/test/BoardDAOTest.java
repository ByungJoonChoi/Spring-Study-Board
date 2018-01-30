package io.peter.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.peter.domain.BoardVO;
import io.peter.domain.Criteria;
import io.peter.domain.SearchCriteria;
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
		for(BoardVO board : dao.listPage(1)){
			logger.info(board.toString());
		}		
	}
	
	@Test
	public void f_testListCriteria() throws Exception{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		for(BoardVO board : dao.listCriteria(cri)){
			logger.info(board.toString());
		}		
	}
	
	@Test
	public void testURI() throws Exception{
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
		logger.info(uriComponents.toUriString());
	}
	
	@Test
	public void testURI2() throws Exception{
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testDynamic() throws Exception{
		
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("!");
		cri.setSearchType("tcw");
		
		logger.info("================================================");
		
		List<BoardVO> list = dao.listSearch(cri);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + ": " + boardVO.getTitle());
		}
		
		logger.info("================================================");
		logger.info("COUNT : " + dao.listSearchCount(cri));
	}
}
