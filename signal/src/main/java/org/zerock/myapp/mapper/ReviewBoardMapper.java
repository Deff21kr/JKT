package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.domain.ReviewBoardCriteria;

public interface ReviewBoardMapper {
	
	
//	@Select("SELECT  /*+ index_desc(tbl_board_review) */  * FROM tbl_board_review")
//	@Select("""
//			SELECT 
//			/*+ index_desc(tbl_board_review) */ *
//			FROM tbl_board_review
//			OFFSET (#{currPage} -1) * #{amount} ROWS
//			FETCH NEXT #{amount} ROWS ONLY
//			""")
	//1. 게시판 목록 조회
	public abstract List<ReviewBoardVO> selectList(ReviewBoardCriteria cri);
		
	//2. 게시물 등록 
	public abstract Integer insert(ReviewBoardDTO dto);

	//3. 게시물 상세 조회 
	public abstract ReviewBoardVO select(Integer postNo);

	//4. 게시물 삭제 
	public abstract Integer delete(Integer postNo);

	//5. 게시물 수정(갱신)
	public abstract Integer update(ReviewBoardDTO dto);

	//6. 총 게시물 갯수 반환 
	@Select("SELECT count(postNo) FROM tbl_board_review WHERE postno > 0")
	public abstract Integer getTotalAmount();
	
	// 7. 조회수 올리기
	public abstract Integer updateReadcnt(Integer postNo);


}//end class
