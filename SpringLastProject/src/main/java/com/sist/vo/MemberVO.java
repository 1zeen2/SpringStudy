package com.sist.vo;

import java.util.*;

import lombok.Data;

/*
	 이름                                      널?      유형
	 ----------------------------------------- -------- ----------------------------
	 USERID                                    NOT NULL VARCHAR2(20)
	 USERNAME                                  NOT NULL VARCHAR2(50)
	 USERPWD                                   NOT NULL VARCHAR2(20)
	 ENABLED                                            NUMBER(1)
	 SEX                                                VARCHAR2(6)
	 BIRTHDAY                                  NOT NULL VARCHAR2(20)
	 EMAIL                                              VARCHAR2(100)
	 POST                                      NOT NULL VARCHAR2(10)
	 ADDR1                                     NOT NULL VARCHAR2(500)
	 ADDR2                                              VARCHAR2(500)
	 PHONE                                              VARCHAR2(20)
	 CONTENT                                            CLOB
	 REGDATE                                            DATE
	 MODIFYDATE                                         DATE
	 LASTLOGIN                                          DATE
 */
/*
 * 	보안 : userName, userPwd
 * 		  ========= id 
 */

@Data
public class MemberVO {
	private int enabled;
	private String userId, userPwd, sex, post, addr1, addr2, email, phone, content;
	private Date regdate, modifydate, lastlogin;
	// 정규화
	private String msg, authority;
}
