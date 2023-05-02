package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewDTO;
import org.zerock.myapp.domain.ReviewVO;

public interface ReviewMapper {
	
	// 게시판 목록 조회 
		@Select("""
				SELECT /*+ index_desc(tbl_reviewboard) */ *
				FROM tbl_reviewboard
				OFFSET ( #{currPage} - 1) * #{amount} ROWS
				FETCH NEXT #{amount} ROWS ONLY
				""") // 스키마 테이블 명 변경 필요하다잉 ex:) Review_board 
	public abstract List<ReviewVO> selectList(Criteria cri);
		
	// 게시물 등록 
	public abstract Integer insert(ReviewDTO dto);

	// 게시물 조회 
	public abstract ReviewVO select(Integer postno);

	// 게시물 삭제 
	public abstract Integer delete(Integer postno);

	// 게시물 업데이트(갱신)
	public abstract Integer update(ReviewDTO dto);

	// 총 게시물 갯수 반환 
	@Select("SELECT count(postno) FROM tbl_reviewboard WHERE postno > 0")
	public abstract Integer getTotalAmount();


}
