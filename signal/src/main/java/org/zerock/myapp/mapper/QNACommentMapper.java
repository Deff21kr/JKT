package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QNACommentDTO;
import org.zerock.myapp.domain.QNACommentVO;

public interface QNACommentMapper {
	
	// 댓글 조회
	@Select("""
			SELECT *
			FROM tbl_QNAComment WHERE postno = #{postno}
			""")
	public abstract List<QNACommentVO> selectList(Integer postno);
	
	// 댓글 상세조회 업데이트 할때 필요함...
	public abstract QNACommentVO select(Integer commentno);
		
	// 댓글 작성
	public abstract Integer insert(QNACommentDTO dto);
	
	// 댓글 삭제
	public abstract Integer deletecomment(Integer commentno);
	
	// 댓글 수정
	public abstract Integer update(QNACommentDTO dto);
	
	
}
