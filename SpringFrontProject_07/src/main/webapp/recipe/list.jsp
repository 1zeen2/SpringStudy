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
	   width: 960px
	}
	.nav-link {
	  cursor: pointer;
	}
	p {
	   white-space: nowrap;
	   overflow: hidden;
	   text-overflow: ellipsis;
	}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="page.js"></script>
<script src="image.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">레시피 목록</h1>
		<div class="row">
			<image-card></image-card>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<page-card></page-card>
		</div>
	</div>
	<script>
		let listApp = Vue.createApp({
			data() {
				return {
					list: [],
					curpage: 1,
					totalpage: 0,
					startPage: 0,
					endPage: 0
				}
			},
			mounted() {
				this.dataRecv()
			},
			methods: {
				prev() {
					this.curpage = this.startPage - 1
					this.dataRecv()
				},
				next() {
					this.curpage = this.endPage + 1
					this.dataRecv()
				},
				pageChange(page) {
					this.curpage = page
					this.dataRecv()
				},
				range(start, end) {
					let arr = []
					let len = end - start
					for (let i = 0; i <= len; i++) {
						arr[i] = start
						start++
					}
					return arr
				},
				dataRecv() {
					axios.get('../recipe/list_vue.do', {
						params: {
							page: this.curpage
						}
					}).then (response => {
						console.log(response.data)
						this.list = response.data.list
						this.curpage = response.data.curpage
						this.startpage = response.data.startpage
						this.totalPage = response.data.totalPage
						this.endPage = response.data.endPage
					}).catch (error => {
						console.log(error.response)
					})
				}
			},
			components: {
				'image-card': image_card,
				'page-card': page_card
			}
		}).mount('.container')
	</script>
</body>
</html>