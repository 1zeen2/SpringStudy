<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link href='https://cdn.jsdelivr.net/npm/@fullcalendar/icalendar@5.11.3/main.css' rel='stylesheet'/>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style type="text/css">
	.container{
	   margin-top: 50px
	}
	.row{
	   margin: 0px auto;
	   width: 1100px;
	}
	.nav-link {
		cursor:pointer;
	}
	p {
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<table class="table">
			<tr>
				<td class="text-center" width=30% height="500">
					<table class="table">
						<caption><h3>맛집 정보</h3></caption>
						<tr>
							<td class="text-center" colspan="2">
								<button class="btn btn-xs btn-success" @click="typeChange('한식')">한식</button>
								<button class="btn btn-xs btn-danger"@click="typeChange('중식')">중식</button>
								<button class="btn btn-xs btn-info" @click="typeChange('양식')">양식</button>
								<button class="btn btn-xs btn-primary" @click="typeChange('일식')">일식</button>
								<button class="btn btn-xs btn-warning"@click="typeChange('분식')">분식</button>
							</td>
						</tr>  
						<tr v-for="vo in food_list"">
							<td class="text-center" width=30%>
								<img :src="'http://www.menupan.com'+vo.poster" style="width:300px">
							</td>
							<td width=70%>{{vo.name}}</td>
						</tr> 
				<td class="text-center" width=45% height="500">
					<table class="table">
						<caption><h3>예약 정보</h3></caption>
					</table>
				</td>
				<td class="text-center"	width=25% rowspan="2">
					<table class="table" v-show=isDay>
						<caption><h3>예약일 정보</h3></caption>
					</table>
				</td>
			</tr>
			<tr>
				<td class="text-center" width=40% height="200">
					<table class="table">
						<caption><h3>시간 정보</h3></caption>
					</table>
				</td>  
				<td class="text-center" width=30% height="200">
					<table class="table" v-show="ispwe">
						<caption><h3>인원 정보</h3></caption>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
	let rApp = Vue.createApp({
		data() {
			return {
					isDay:		false,
					isTime:		false,
					isPeople:	false,
					food_list:	[],
					type:'한식'
			}
		},
		mounted() {
			// 시작과 동시에 변수 초기화
			let date = new Date()
			let year = date.getFullYear()
			let month = ("0" + (1 + date.getMonth())).slice(-2)
			let day = ("0" + date.getDate()).slice(-2)
			document.addEventListener('DOMContentLoaded', function() {
				let calendarEl = document.getElementById('calendar')
				let calendar = new FullCalendar.Calendar(calendarEl, {
					initialView: 'dayGridMonth',
					height: 450,
					headerToolbar: {
						left: 'prev, next today',
						center: 'title'
					},
					validRange: {
						start: year + "-" + month + "-" + day
					}
					themeSystem: 'bootstrap',
						editable : true,
						dropable : true,
						dateClick: ((info) => {
							alert('Click date : ' + info.dateStr)
						})
						 
				})
				calendar.render()
			})
			this.dataRecv()
		},
		methods: {
			typeChange(type) {
				this.type = type
				this.dataRecv()
			},
			// 사용자 정의 메서드
			dataRecv() {
				axios.get('../food/type_vue.do', {
					params: {
						type: this.type	
					}
				}).then(response => {
					console.log(response.data)
					this.food_list = response.data
				}).catch(error => {
					console.log(error.response)
				})
			}
		},
		updated() {
			
		},
		components: {
			// 등록
		}
	}).mount('.container')
</script>
</body>
</html>