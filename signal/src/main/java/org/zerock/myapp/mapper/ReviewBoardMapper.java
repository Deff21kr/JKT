package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;

public interface ReviewBoardMapper {
	
	// 게시판 목록 조회 
		@Select("""
				SELECT /*+ index_desc(tbl_reviewboard) */ *
				FROM tbl_reviewboard
				OFFSET (#{currPage} -1) * #{amount} ROWS
				FETCH NEXT #{amount} ROWS ONLY
				""") // 스키마 테이블 명 변경 필요하다잉 ex:) Review_board 
	public abstract List<ReviewBoardVO> selectList(Criteria cri);
		
	// 게시물 등록 
	public abstract Integer insert(ReviewBoardDTO dto);

	// 게시물 조회 
	public abstract ReviewBoardVO select(Integer postno);

	// 게시물 삭제 
	public abstract Integer delete(Integer postno);

	// 게시물 업데이트(갱신)
	public abstract Integer update(ReviewBoardDTO dto);

	// 총 게시물 갯수 반환 
	@Select("SELECT count(postno) FROM tbl_reviewboard WHERE postno > 0")
	public abstract Integer getTotalAmount();


}
