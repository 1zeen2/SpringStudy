package com.sist.vo;

import lombok.Data;

/*
	 이름                                      널?      유형
	 ----------------------------------------- -------- ----------------------------
	 FNO                                                NUMBER(38)
	 NAME                                               VARCHAR2(4000)
	 TYPE                                               VARCHAR2(4000)
	 PHONE                                              VARCHAR2(4000)
	 ADDRESS                                            VARCHAR2(4000)
	 SCORE                                              NUMBER(38,1)
	 THEME                                              VARCHAR2(4000)
	 POSTER                                             VARCHAR2(4000)
	 IMAGES                                             VARCHAR2(4000)
	 TIME                                               VARCHAR2(4000)
	 PARKING                                            VARCHAR2(4000)
	 CONTENT                                            VARCHAR2(4000)
	 RDAYS                                              VARCHAR2(4000)
	 JJIMCOUNT                                          NUMBER(38)
	 LIKECOUNT                                          NUMBER(38)
	 HIT                                                NUMBER(38)
 */

@Data
public class FoodVO {
	private int fno, jjimcount, likecount, hit;
	private String name, type, phone, address, theme, poster, images, time, parking, content, rdays;
	private double score;
}
