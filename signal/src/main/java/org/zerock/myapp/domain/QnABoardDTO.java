package org.zerock.myapp.domain;



import lombok.Data;

// 결론 : VO패턴과 거의 차이가 없음. 단지 DTO패턴 객체는 수정이 가능하다(Mutable)
@Data			// DTO 클래스 선언 용도
public class QnABoardDTO {
	private Integer postno;
	private String title;
	private String content;
	private String nickname;
	

} // end class
