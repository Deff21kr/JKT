package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.exception.DAOException;

public interface GroupBoardMapper {
	
	// 게시판 C/R/U/D/LIST에 필요한 메소드
	
	// 1. LIST - 게시판 목록조회
	@Select("""
			SELECT 
			/*+ index_desc(tbl_groupboard) */ *
			FROM TBL_GROUPBOARD
			OFFSET (#{currPage} -1) * #{amount} ROWS
			FETCH NEXT #{amount} ROWS ONLY
			""")
	public abstract List<GroupBoardVO> selectList(GroupBoardCriteria cri);
	
	// 2. 조건검색조회
	public abstract List<GroupBoardVO> selectSearchList(GroupBoardCriteria cri);
	
	
	// 3. Create(Insert) - 새로운 게시물 등록
	public abstract Integer insert(GroupBoardDTO dto);
	
	// 4. 특정 게시물 상세조회
	public abstract GroupBoardVO select(Integer postno);
   
	// 5. 특정 게시물 삭제
	public abstract Integer delete(Integer postno) throws DAOException;
	
	// 6. 특정 게시물 업데이트(갱신)
	public abstract Integer update(GroupBoardDTO dto);

	// 7. 총 게시물 갯수 반환
	@Select("SELECT count(postNo) FROM TBL_GROUPBOARD WHERE postno > 0")
	public abstract Integer getTotalAmount();

	// 8. 검색 조회된 게시물 갯수 반환
	public abstract Integer getSearchTotalAmount(GroupBoardCriteria cri);
	
	// 9. 조회수 올리기
	public abstract Integer plusReadcnt (Integer postNo);
	
} // end interface
