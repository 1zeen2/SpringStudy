package com.sist.vo;

import lombok.Data;

/*
	 이름                                      널?      유형
	 ----------------------------------------- -------- ----------------------------
	 NO                                        NOT NULL NUMBER
	 TITLE                                     NOT NULL VARCHAR2(4000)
	 POSTER                                    NOT NULL VARCHAR2(4000)
	 CHEF                                      NOT NULL VARCHAR2(4000)
	 LINK                                               VARCHAR2(4000)
	 HIT                                                NUMBER
 */

@Data
public class RecipeVO {
	private int no, hit;
	private String title, poster, chef, content;
}
