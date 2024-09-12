package com.sist.temp;

import javax.naming.Name;

public class MainClass {
	private SawonVO vo;
	
	
	public static void main(String[] args) {
		SawonVO sawon = new SawonVO();
		sawon.setName("홍길동");
		sawon.setDept("개발부");
		
		System.out.printf(sawon.getName());
		System.out.printf("\n" + sawon.getDept());
	}
}
