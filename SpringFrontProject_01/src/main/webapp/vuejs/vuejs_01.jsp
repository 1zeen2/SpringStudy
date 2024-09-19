<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Vue.js
	  = 웹 화면을 출력하기 위한 JavaScript library = framework
	  = Component 기반 (Java처럼 class 별로 나뉘어져 있는 것 => 기능 별 처리가 가능하다.)
	  	=> .vue, cdn 방식을 이용
	  			 === Ajax처럼 JSP 안에서 처리.
	  	=> 단독으로 처리 / JSP나 HTML 안에서 출력이 가능하다.
	  					  ================================ CDN  
	  = 라우터 : 화면 변경, 상태 관리
	  					   ======== 데이터를 저장해서 관리한다. (vuex) => store
	  = 배우기 쉽다.
	  = 성능이 좋다. / 속도가 빠르다.
	  = 라이브러리가 가볍다.
	  
	  = 	앵귤러JS / ReactJS
		  	  |			  |=> 가상 돔
		  +	양방향 통신	
		 ============================
		 두 개의 장점을 이용해서 만든 라이브러리.
		 	
	  	<input type=text id=id>
	  	<div id="print"></div>
	  	let id=$('#id').val()	=> 값을 읽고
	  	$('#print').text(id)	=> 넣어준다.
	  	======================= 단방향 (값을 읽고 넣어준다.)
	  	
	  	StringBuffer / String => 실행 속도에 차이가 있음.
	  	============
	  	가상 돔과 같은 실행 방식이다.
	  	
	  	mount : 가상 돔에 저장
	  	
	Vue.js
	*** 1. 생명 주기
			=> callback ==> 자동 호출
			=> beforeCreate()	=> Vue 객체 생성 전
			=> created()		=> Vue 객체 생성
			=> beforeMount()	=> 가상 돔에 태그를 올리기 전
		*** => mounted()		=> 가상 돔에 태그를 전송 ==> $(function())
				window.onload=function(){}
				componentDiMount() / useEffect() <=========== React에서 사용하는 함수
					=> server에서 출력에 필요한 데이터를 읽어온다.
			=> beforeUpdate()	=> 데이터 수정 전
		*** => updated()		=> 데이터 수정 완료
				Component 제작
				========= 재사용 == Modal
			=> beforeDestroy()	=> 화면 변경하기 전
			=> destroyed()		=> 화면 변경.
				$.ajax({
				})
				let app.Vue.createApp({
					data(){} => parameter ==> 화면에 출력할 데이터 모음
					mounted(){} => parameter의 초기화 ==> 서버에서 데이터 읽기
					methods:{
						사용자 정의 함수
					}
					components:등록
				}).mount(app)
				
	*** 2. 디렉티브	: 태그를 제어
			v-for : 태그 안에 속성으로 들어가있다.
				<tr v-for="">
					=
					=
				</tr>
			v-if ~ v-else
			====   ======
			출력	   출력 X
			v-if ~ v-elseif ~ v-else
			
			v-text / v-html
			text() / html()
			
			v-on:click	==> @click
			v-on:change	==> @change
			======================= 이벤트 처리 형식이 바뀜
			
			v-model / v-bind
			=======	  ======
			양방향	  이미지
			
		3. 서버 연동
		
	*** 4. 출력 형식
			{{출력 데이터}} => {react}
			==============
				Django
				
				${}				${}
				=> EL / JSTL은 사용하지 않는다.
				
	*** 5. 양방향 통신
	
		6. 화면 변경
		
 --%>
 <%-- HTML은 tree 형태로 저장이 된다. --%>
 <%-- 
	 가상 돔은 diffing을 통해 기존과 달라진 부분 (추가된 부분)만 RealDOM에 추가한다. 
	 	=> 새로 HTML을 전체 로드하지 않아 속도가 빨라짐
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.contianer {
	margin-top: 50px;
}
.row {
   margin: 0px auto;
   width: 500px
}
</style>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->
<!-- <script type="text/javascript"> -->
<!-- $(function() { -->
<!-- 	$('#msg').keyup(function() { -->
<!-- 		let msg=$('#msg').val() -->
<!-- 		$('#print').text(msg) -->
<!-- 	}) -->
<!-- }) -->
<!-- </script> -->
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type=text size=30 class="input-sm" v-model="msg">
		</div>
		<div class="row">{{msg}}</div>
	</div>
	<script>
		let app=Vue.createApp({
	// 출력에 필요한 변수 설정
	/*
		정수 (숫자)
		no:0
		문자, 문자열
		msg:''
		객체 (VO)
		board:{}
		목록 (List)
		board_list:[]
		논리형
		bCheck:false
	*/
			data() {
				return {
					msg:''
				}
			},
			beforeCreate(){
				console.log("beforeCreate() Call....")
			},
			created() {
				console.log("created() Call....")
			},
			beforeMount() {
				console.log("beforeMount() Call....")
			},
			mounted() {
				console.log("***mounted() Call... => 화면이 완료:onload()")
			},
			beforeUpdate() {
				console.log("beforeUpdate() Call....")
			},
			updated() {
				console.log("updated() Call... => data에 설정된 변수 값이 변경이 된 경우")
			},
			beforeDestroy() {
				console.log("beforeDestroy() Call....")
			},
			destroyed() {
				console.log("destroyed() Call....")
			}
			// 제어 영역을 지정
		}).mount('.container')
	</script>
</body>
</html>