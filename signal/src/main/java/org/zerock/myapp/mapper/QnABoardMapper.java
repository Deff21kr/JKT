package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.DAOException;

public interface QnABoardMapper {
	
	// 게시판 C/R/U/D/LIST에 필요한 메소드
	
	// 1. LIST - 게시판 목록조회
	@Select("""
			SELECT 
			/*+ index_desc(tbl_qnaboard) */ *
			FROM TBL_QNABOARD
			OFFSET (#{currPage} -1) * #{amount} ROWS
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<QnABoardVO> selectList(Criteria cri);
	
	
	// 2. Create(Insert) - 새로운 게시물 등록
	public abstract Integer insert(QnABoardDTO dto);
	
	// 3. 특정 게시물 상세조회
	public abstract QnABoardVO select(Integer postNo);
   
	// 4. 특정 게시물 삭제
	public abstract Integer delete(Integer postNo) throws DAOException;
	
	// 5. 특정 게시물 업데이트(갱신)
	public abstract Integer update(QnABoardDTO dto);

	// 6. 총 게시물 갯수 반환
	@Select("SELECT count(postNo) FROM TBL_QNABOARD WHERE postNo > 0")
	public abstract Integer getTotalAmount();
	
	// 7. 조회수 올리기
	public Integer updateReadcnt(Integer postNo);
	
} // end interface
