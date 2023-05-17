package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.domain.QnACommentDTO;

public interface QnACommentMapper {

	// 댓글 조회
	@Select("""
			SELECT * FROM TBL_QNACOMMENT WHERE postNo = #{postNo} ORDER BY
			MODFIYDATE DESC, REGIDATE DESC
				""")
	public abstract List<QnACommentVO> selectList(Integer postNo);
	
	// 댓글 페이징만을 위해 만들어봄
//	@Select("""
//	SELECT 
//	/*+ index_desc(tbl_qnacomment) */ *
//	FROM TBL_QNACOMMENT
//	OFFSET (#{currPage} -1) * #{amount} ROWS
//	FETCH NEXT #{amount} ROWS ONLY
//	""")
//	public abstract List<QnACommentVO> pageList(Criteria cri);
	
	// 댓글 상세조회 업데이트 할때 필요함...
	public abstract QnACommentVO select(Integer commentNo);

	// 댓글 작성
	public abstract Integer insert(QnACommentDTO dto);

	// 댓글 삭제
	public abstract Integer delete(Integer commentNo);

	// 댓글 수정
	public abstract Integer update(QnACommentDTO dto);
	
//	// 총 게시물 갯수 반환
//	@Select("SELECT count(commentNo) FROM TBL_QNACOMMENT WHERE commentNo > 0 AND postNo = #{postNo}")
//	public abstract Integer getTotalAmount();

} // end interface
