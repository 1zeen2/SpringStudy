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
        margin-top: 50px;
    }
    .row {
       margin: 0px auto;
       width: 500px;
    }
    .movieTr:hover {
        cursor: pointer
    }
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<%--
	v-for="변수 in 배열" => for-each만 존재 => 어디부터 ~ 어디까지는 문법에 존재하지 않는다.
	
 --%>
<body>
	<div class="container">
		<div class="row">
			Start:v<inpyt type=text size=5 class="inpyt-sm"></inpyt>msp;
			&nbsp;
			Start:<inpyt type=text size=5 class="inpyt-sm"> </inpyt>msp;
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li><a href="#">&laquo</a></li>
					<li v-for="i in range(1, 10)"><a href="#">{{i}}</a></li>
					<li><a href="#">&raquo</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data() {
				return {
					startPage:0,
					endPage:0
				}
			},
			methods: {
			// 페이지 나눠서 반복문 수행
				range(start, end) {
					let arr=[]
					let len=end-start
					for (let i = 0; i <= len; i++) {
						arr[i] = start
						start++
					}
					return arr
				}
			}
		}).mount(".container")
	</script>
</body>
</html>