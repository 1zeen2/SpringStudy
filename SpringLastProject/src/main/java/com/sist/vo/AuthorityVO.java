package com.sist.vo;

import lombok.Data;

/*
	 이름                                      널?      유형
	 ----------------------------------------- -------- ----------------------------
	 USERID                                             VARCHAR2(20)
	 AUTHORITY                                 NOT NULL VARCHAR2(20)

 */

@Data
public class AuthorityVO {
	// Id 하나에 여러 개의 권한이 들어갈 수 있음. 
	// (유저이면서 관리자 같은 경우)
	private String userId, authority;
}
