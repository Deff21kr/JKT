package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.domain.ReviewCommentDTO;
import org.zerock.myapp.domain.ReviewCommentVO;
import org.zerock.myapp.domain.CommentCriteria;
import org.zerock.myapp.domain.QnACommentDTO;

public interface ReviewCommentMapper {

	// 댓글 조회
//	@Select("""
//			SELECT * FROM TBL_QNACOMMENT WHERE postNo = #{postNo} ORDER BY
//			MODFIYDATE DESC, REGIDATE DESC
//				""")
//	public abstract List<QnACommentVO> selectList(Integer postNo);
	
	// 댓글 페이징만을 위해 만들어봄
	@Select("""
	SELECT 
	/*+ index_desc(tbl_reviewcomment) */ *
	FROM TBL_REVIEWCOMMENT
	WHERE POSTNO = #{postNo}
	OFFSET (#{commentCri.commentCurrPage} -1) * #{commentCri.commentAmount} ROWS
	FETCH NEXT #{commentCri.commentAmount} ROWS ONLY
	""")
	public abstract List<ReviewCommentVO> selectList(@Param("commentCri") CommentCriteria cri,@Param("postNo") Integer postNo);
	
	// 댓글 상세조회 업데이트 할때 필요함...
	public abstract ReviewCommentVO select(Integer commentNo);

	// 댓글 작성
	public abstract Integer insert(ReviewCommentDTO dto);

	// 댓글 삭제
	public abstract Integer delete(Integer commentNo);

	// 댓글 수정
	public abstract Integer update(ReviewCommentDTO dto);
	
	// 총 게시물 갯수 반환
	@Select("SELECT count(commentNo) FROM TBL_REVIEWCOMMENT WHERE commentNo > 0 AND postNo = #{postNo}")
	public abstract Integer getCommentTotalAmount(Integer postNo );

} // end interface
