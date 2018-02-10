package io.peter.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.peter.domain.ReplyVO;
import io.peter.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReplyDAOTest {
	
	@Inject
	private ReplyDAO dao;
	
	@Test
	public void testCreate() throws Exception{
		for(int i=0 ; i<10 ;i++){
			ReplyVO vo = new ReplyVO();
			vo.setBno(1);
			vo.setReplyer("최병준");
			vo.setReplytext("1번 게시물에 댓글달기 테스트 " + (i+1) + " 입니다.");
			dao.create(vo);	
		}
	}
	
	@Test
	public void testList() throws Exception{
		List<ReplyVO> list = dao.list(1);
		for(ReplyVO vo : list){
			System.out.println(vo.toString());
		}
	}
	
	@Test
	public void testUpdate() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setReplytext("rno 7번 댓글 업데이트 테스트");
		vo.setRno(7);
		dao.update(vo);
	}
	
	@Test
	public void testDelete() throws Exception{
		Integer rno = 2;
		dao.delete(rno);
	}
}
