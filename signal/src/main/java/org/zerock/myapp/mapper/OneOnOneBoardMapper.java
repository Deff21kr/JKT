package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;

public interface OneOnOneBoardMapper {
	
	// 게시글 목록 조회
//	@Select(" SELECT  /*+ index_desc(tbl_oneononeinquiry) */  * FROM tbl_oneononeinquiry "
//			+ " ORDER BY repRoot desc, repStep asc"
//			+ "	OFFSET ( #{currPage} - 1 ) * #{amount} ROWS"
//			+ "	FETCH NEXT #{amount} ROWS ONLY"
//			)
	@Select("SELECT *\r\n"
			+ "FROM tbl_oneononeinquiry\r\n"
			+ "ORDER BY repRoot DESC, repStep ASC\r\n"
			+ "	OFFSET ( #{currPage} - 1 ) * #{amount} ROWS"
			+ "	FETCH NEXT #{amount} ROWS ONLY"
			+ "")
	public abstract List<OneOnOneBoardVO> selectList(Criteria cri);
	
	// 게시글 등록
	public abstract Integer insert(OneOnOneBoardDTO dto);
	
	// 특정 게시글 상세조회
	public abstract OneOnOneBoardVO select(Integer postNo);
	
	// 특정 게시물 삭제
	public abstract Integer delete(Integer postNo);

	// 특정 게시물 업데이트
	public abstract Integer update(OneOnOneBoardDTO dto);
	
	// 요청시점에 총 게시물 개수 반환
	@Select("SELECT count(postNo) FROM tbl_oneononeinquiry WHERE postNo > 0")
	public abstract Integer getTotalAmount();
	
	// 답변글의 기존 repStep 1 증가
	public abstract Integer increase(OneOnOneBoardDTO dto);
	
	// 답변글 달기
	public abstract Integer response(OneOnOneBoardDTO dto);
	
}
