<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
    <table class="table">
      <tr>
        <td class="text-center" colspan="3">
          <img src="${vo.poster }" style="width: 600px;height: 600px">
        </td>
      </tr>
      <tr>
        <td class="text-center" colspan="3"><h3>${vo.name }</h3></td>
      </tr>
      <tr>
        <td class="text-center" colspan="3">${vo.content }</td>
        <td class="text-center"></td>
      </tr>
      <tr>
        <td class="text-center">업종 : ${type}</td>
        <td class="text-center">주소 : ${vo.address}</td>
        <td class="text-center">평점 : ${vo.score}</td>
      </tr>
      <tr>
        <td class="text-center">뭐로할까</td>
        <td class="text-center">뭐로할까2</td>
        <td class="text-center">뭐로할까3</td>
      </tr>
    </table>
    <h3>[레시피 작성자]</h3>
    <table class="table">
      <tr>
       <td class="text-right">
         <input type=button class="btn-sm btn-warning" value="좋아요">
         <input type=button class="btn-sm btn-success" value="찜하기">
         <a href="../main/main.do" class="btn-sm btn-info">목록</a>
       </td>
      </tr>
    </table>
  </div>
</body>
</html>