package org.zerock.myapp.domain;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
public class CommentPageDTO {

	private CommentCriteria commentCri;
	
	private int commentTotalAmount;

	
	private int startCommentPage;			// 한 페이지당 페이지목록의 시작번호
	private int endCommentPage;			// 한 패이지당 페이지목록의 끝번호
	private int realCommentEndPage;
	
	private int commentOffSet;
	
	private boolean commentPrev;			// 다음 페이지목록의 존재여부
	private boolean commentNext;			// 이전 페이지목록의 존재여부
	
	
	
	public CommentPageDTO(int totalAmount, CommentCriteria cri) {
		this.commentCri = cri;
		
		this.commentTotalAmount = totalAmount;
		//----------------------------------------------------------//
		//--Step.0 : 페이징 처리를 위한 공통변수 생성하기
		//----------------------------------------------------------//
		int commentPage = cri.getCommentCurrPage();
		
		int commentAmount = cri.getCommentAmount();
		
		int commentPagesPerPage = cri.getCommentPagesPerPage();

		//----------------------------------------------------------//
		//--Step.1 : 현재 페이지에서 보여줄 페이지목록의 끝페이지번호 구하기
		//----------------------------------------------------------//
		// (공식) 끝페이지번호 = (int) Math.ceil( (double) 현재페이지번호 / 페이지목록길이 ) x 페이지목록길이
		//----------------------------------------------------------//
		this.endCommentPage = (int) Math.ceil( (commentPage * 1.0) / commentPagesPerPage ) * commentPagesPerPage;

		//----------------------------------------------------------//
		//--Step.3 : 현재 페이지의 페이지번호목록의 시작번호 구하기
		//----------------------------------------------------------//
		// (공식) 시작페이지번호 = 끝페이지번호 - ( 페이지목록길이 - 1 )
		//----------------------------------------------------------//
		this.startCommentPage = this.endCommentPage - ( commentPagesPerPage - 1 );

		//----------------------------------------------------------//
		//--Step.4 : 총페이지수 구하기
		//----------------------------------------------------------//
		// (공식) 총페이지수 = (int) Math.ceil( (double) 모든행의개수 / 한페이지당행의수 )
		//----------------------------------------------------------//
		this.realCommentEndPage = (int) Math.ceil( (commentTotalAmount * 1.0) / commentAmount );
		
		
		if(this.realCommentEndPage < this.endCommentPage) {
			this.endCommentPage = this.realCommentEndPage;
		} // if

		//----------------------------------------------------------//
		//--Step.5 : 이전 페이지번호목록으로 이동가능여부(prev) 구하기
		//----------------------------------------------------------//
		// (공식) 이전페이지목록이동가능여부 = 시작페이지번호 > 1
		//----------------------------------------------------------//
		this.commentPrev = this.startCommentPage > 1;

		//----------------------------------------------------------//
		//--Step.6 : 다음 페이지번호목록으로 이동가능여부(next) 구하기
		//----------------------------------------------------------//
		// (공식) 다음페이지목록이동가능여부 = 끝페이지번호 < 총페이지수
		//----------------------------------------------------------//
		this.commentNext = this.endCommentPage < realCommentEndPage;
		//----------------------------------------------------------//
		//--Step.7 : 현재 페이지에 표시할 목록의 시작 offset 구하기
		//----------------------------------------------------------//
		// (공식) 시작 offset = (현재페이지번호 - 1) x 한페이지당행의수
		//----------------------------------------------------------//
		this.commentOffSet = (commentPage - 1) * commentAmount;
	} // constructor
	

} // end class
