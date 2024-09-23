<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container {
		margin-top: 50px
	}
	
	.row {
		margin: 0px auto;
		width: 800px;
	}
</style>
<!-- 
	ES5 => type="text/javascript"
	ES6 => type="text/babel" 또는 생략.
 -->
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">자유 게시판(Vue.js)</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<!-- BoardController에 있는 insert.do가 화면을 바꿔줌 -->
						<a href="insert.do" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">내용</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<tr v-for="vo in board_list">
					<td width=10% class="text-center">{{vo.no}}</td>
					<td width=45%>
						<a :href="'detail.do?no='+vo.no">{{vo.subject}}</a>
						&nbsp;
						<sup v-if="today===vo.dbday"><img src="new.gif"></sup>
					</td>
					<td width=15% class="text-center">{{vo.name}}</td>
					<td width=20% class="text-center">{{vo.dbday}}</td>
					<td width=10% class="text-center">{{vo.hit}}</td>
				</tr>
				<tr>
					<td colspan="5" class="text-center">
						<input type=button class="btn btn-sm btn-danger" value="이전" @click="prev()">
							{{curpage}} page / {{totalpage}} pages
						<input type=button class="btn btn-sm btn-success" value="다음" @click="next()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
	/* 
		VO		=> {}
		list	=> []
		String	=> '', "" 둘 다 가능.
		int		=> 0
		double	=> 0.0
		boolean	=> false
	*/
		let boardApp=Vue.createApp({
			// 데이터 관리 => Model
			data() {
					// 멤버 변수 설정 = 서버에서 전송한 데이터와 일치해야 함
					// list(list), curpage(int), totalpage(int), count(int) 4개의 값이 넘어와있음.
					// 초기 값을 꼭 설정해 주어야 데이터 타입을 인식할 수 있음.
				return {
					board_list:[],
					curpage:1,
					totalpage:0,
					count:0
				}
			},
			// onload => 브라우저가 실행이 되기 전에 => server에서 데이터를 받는다.
			// Model에 설정된 데이터를 수정, 변경 => ViewModel
			// M V VM => Model / View / ViewModel
			mounted() {
				// 서버에서 전송한 데이터 읽기 => 파라미터에 저장.
				// RestController에 @GetMapping이면 get, @PostMapping이면 post 사용.
				this.dataRecv()
			},
			// 사용자 정의 메서드
			methods: {
				// 파라미터 변경
				// 공통으로 사용되는 부분
				dataRecv() {
					axios.get('http://localhost:8080/web/board/list_vue.do', {
						params: {
							page:this.curpage
						}
					}).then(response => {
						console.log(response.data)
						this.board_list = response.data.list
						this.curpage = response.data.curpage
						this.totalpage = response.data.totalpage
						this.count = response.data.count
						this.today = response.data.today
					})
				},
				prev() {
					this.curpage=this.curpage>1?this.curpage-1:this.curpgae
					this.dataRecv()
				},
				next() {
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpgae
					this.dataRecv()
				}
			}
		}).mount('.container')
	</script>
</body>
</html>