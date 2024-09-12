package com.sist.dao;

import com.sist.mapper.*;
import com.sist.vo.*;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	// @Autowired는 객체의 주소 값만 주입이 가능하다.
	// (일반 변수는 사용이 불가능함.)
	@Autowired
	private EmpMapper mapper; // Spring => MyBatis에서 구현 => 구현한 클래스의 주소를 가지고 온다.
	// 값을 넣을 때 사용하는 방식.
	// DB에서 값을 채워넣기 때문에 거의 사용하지 않는다.
//	@Value("홍길동")
//	private String name = "";
//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno, "
//			+ "FROM emp")
//	public List<EmpVO> empListData();
	public List<EmpVO> empListData() {
		return mapper.empListData();
	}

//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno, "
//			+ "FROM emp "
//			+ "WHERE empno=${empno}")
//	public EmpVO empDetailData();
	public EmpVO empDetailData() {
		return mapper.empDetailData();
	}

//	@Select("SELECT DISTINCT ename "
//			+ "FROM emp")	
//	public List<String> empEnameList();
	public List<String> empEnameList() {
		return mapper.empEnameList();
	}
	
//	@Select({"<script>"
//			   + "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno "
//			   + "FROM emp "
//			   + "<trim prefix=\"WHERE ename IN(\" suffix=\")\" suffixOverrides=\")\" "
//			   + "prefixOverrides=\"(\"> "
//			   + "<forEach collection=\"names\" item=\"name\" open=\"(\" close=\")\" "
//			   + "separator=\",\"> "
//			   + "#{name} "
//			   + "</forEach>"
//			   + "</trim>"
//			   + "</script>"})
//	public List<EmpVO> enameFindData(Map map);
	public List<EmpVO> empNameFindData(Map map) {
		return mapper.empNameFindData(map);
	}
	
//	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno "
//	+ "FROM emp "
//	+ "WHERE ${fd} LIKE '%'||#{ss}||'%'")
//public List<EmpVO> empFindData(Map map);
	public List<EmpVO> empFindData(Map map) {
		return mapper.empFindData(map);
	}
}
