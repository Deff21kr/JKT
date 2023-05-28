package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.DAOException;

public interface PinMapper {
	
	public Integer insert(PinDTO pin) throws Exception;
	
	public Integer delete(Integer pinNo);
	
//	찜 수정 
//	public Integer modify(PinDTO pin);
	
//	찜 목록
	public List<PinDTO> select(String nickName);
	
//	회원정보와 게시글 정보를 넘겨서 해당 row가 있는지 확인
	public PinDTO checkPin(PinDTO pin);
	
} // end interface
