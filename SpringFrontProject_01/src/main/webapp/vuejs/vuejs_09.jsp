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
<body>
	<div class="container">
		<div class="row">
			<input type=text size=20 class="input-sm" v-model="fd" ref="fd"> <%-- id가 아니라 ref로 제어를 한다. --%>
			<input type=button class="btn-sm btn-danger" value="검색" 
				@click="find()"
			>
		</div>
	</div>
	<script>
	/*
		new Vue({
			el (element):'제어하는 태그 영역', => #app
			data:{}
		})
	
		M  : 데이터 설정 => data()
		
		V  : v-if, v-model, v-on:click, v-for, v-show, ...
			 디렉티브
			 
		VM : 데이터를 변경하는 방식
			monted(){}, methods:{}, components:{}, filter:{}, ...
		
			=> input제어 ==> ref 속성을 이용한다..
			
		Connect Server, Read data
			=> axios.get()		=> @GetMapping		=> SELECT
			   axios.post()		=> @PostMapping		=> INSERT
			   =============================================== RestFul
			   axios.put()		=> @PutMapping		=> UPDATE
			   axios.delete()	=> @DeleteMapping	=> DELETE
			   
	*/
	// tag 자체를 제어하기 때문에 Ajax보다 간편하다.
		let app = Vue.createApp({
			data() {
				return {
					fd:''
				}
			},
			methods: {
				find() {
					/*
						id 값을 가지고 올 수 없기 때문에 ref값으로 가지고 온다.
						this.$refs.ref명.이벤트
										 ====== focus, value, ... => input tag 제어
						=> e.target.value ==> React 방식.
						=> $('#fd').val() => Jquery
							 =====
					 		 id 값
					 	submit버튼은 사용하지 않는다. => 자체에서 CRUD를 처리.
					*/
					let fds = this.$refs.fd.value
					if (fds === "") {
						alert("검색어를 입력하세요")
						this.$refs.fd.focus()
						return
					}
					alert("검색어 : " + this.fd)
				}
			}
		}).mount('.container')
	</script>
</body>
</html>