package com.sist.mapper;

import com.sist.vo.*;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

public interface EmpMapper {
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno "
			+ "FROM emp")
	public List<EmpVO> empListData();
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno "
			+ "FROM emp "
			+ "WHERE empno=#{empno}")
	public EmpVO empDetailData();
	
	@Select("SELECT DISTINCT ename "
			+ "FROM emp")	
	public List<String> empEnameList();
	
	 /*@Select({"<script>"
    +"SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD DY')as dbday,"
    +"sal,deptno FROM emp "
    +"<trim prefix=\"WHERE ename IN(\" suffix=\")\" suffixOverrides=\")\" "
    +"prefixOverrides=\"(\">"
    +"<foreach collection=\"names\" item=\"name\" open=\"(\" close=\")\" separator=\",\">"
    +"#{name}"
    +"</foreach>"
    +"</trim>"
    +"</script>"})*/
	public List<EmpVO> empNameFindData(Map map);
	/*
	 * 	trim : 추가 / 삭제
	 * 			prefix : 접두어
	 * 			suffix : 접미어
	 * 	======================= 추가 기능
	 * 			suffixOverrides
	 * 			prefixOverrides
	 * 	======================= 삭제 기능
	 */
	
//	컬럼명은 $, 일반 데이터는 #이 앞에 붙는다.
//	${fd} => ename #{ss} => 'king'
	@Select({"<script>"
			+ "SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD') as dbday, sal, deptno "
			+ "FROM emp "
			+ "<if test=\"fd!='all'\">"
			+ "WHERE ${fd} LIKE '%'||#{ss}||'%'"
			+ "</if>"
			+ "</script>"})
	public List<EmpVO> empFindData(Map map);
			
}
