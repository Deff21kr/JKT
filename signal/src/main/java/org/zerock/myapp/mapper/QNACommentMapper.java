package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;

public interface QnACommentMapper {

	// 댓글 조회
	@Select("""
			SELECT * FROM TBL_QNACOMMENT WHERE postNo = #{postNo} ORDER BY
			MODFIYDATE DESC, REGIDATE DESC
				""")
	public abstract List<QnACommentVO> selectList(Integer postNo);

	// 댓글 상세조회 업데이트 할때 필요함...
	public abstract QnACommentVO select(Integer commentNo);

	// 댓글 작성
	public abstract Integer insert(QnACommentDTO dto);

	// 댓글 삭제
	public abstract Integer delete(Integer commentNo);

	// 댓글 수정
	public abstract Integer update(QnACommentDTO dto);

} // end interface
