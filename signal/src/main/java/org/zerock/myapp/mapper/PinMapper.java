package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.PinDTO;

public interface PinMapper {
	
	
	// 이 클래스 안써도됨 usersMapper에 합쳐놨음  
	
	
	public abstract Integer insert(PinDTO pin) throws Exception;
	
	public abstract Integer delete(Integer pinNo);
	
//	찜 수정 
//	public Integer modify(PinDTO pin);
	
//	찜 목록
	public abstract List<PinDTO> selectList(String nickName);
	
	public abstract PinDTO select(Integer postNo, String nickName);
	
//	회원정보와 게시글 정보를 넘겨서 해당 row가 있는지 확인
	public abstract Integer checkPin(PinDTO pin);
	
} // end interface
