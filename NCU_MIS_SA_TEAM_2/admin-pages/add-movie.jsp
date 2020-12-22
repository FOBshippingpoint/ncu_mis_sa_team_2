<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<html>

	<head>
		<%@ include file="../theater-header.jsp" %>
			<title>線上電影訂票系統</title>
			<h1>新增電影</h1>

			<!-- JQUERY，轉成JSP後要刪除 -->
			<script src="https://code.jquery.com/jquery-3.5.1.min.js"
				integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>


			<script type="text/javascript">
				Date.prototype.toDateInputValue = (function () {
					var local = new Date(this);
					local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
					return local.toJSON().slice(0, 10);
				});

				$(document).ready(function () {

					$('#start-date,	#end-date').val(new Date().toDateInputValue());
				});

				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();

						reader.onload = function (e) {
							$('#cover-preview').attr('src', e.target.result);
						};

						reader.readAsDataURL(input.files[0]);
					}
				}
			</script>

			<style>
				.my-row {
					display: flex;
					flex-direction: row;
				}

				.my-column {
					display: flex;
					flex-direction: column;
					margin-right: 50px;
				}

				.my-column * {
					margin-top: 10px;
				}

				label {
					display: block;
					float: left;
				}

				body {
					background: linear-gradient(to top, #595959, 20%, #80ffff);
				}

				h1 {
					text-align: center;
					color: white;
					background-color: #EE82EE;
					padding: 5px;
					border: 1px solid gray;
					box-shadow: 2px 2px 2px 1px gray;
					text-shadow: 2px 2px 1px gray;
					border-radius: 50px;
				}
			</style>

	</head>

	<body>
		<form action="/NCU_MIS_SA/api/add-movie.do" method="post" enctype="multipart/form-data">
			<div class="my-row">
				<div class="my-column">
					<label for="title">電影名稱：</label>
					<input type="text" id="title" name="title">
					<label for="introduction">電影簡介：</label>
					<textarea id="introduction" name="introduction" rows="10" cols="50"></textarea>
					<label for="length">電影長度（分鐘）：</label>
					<input type="number" min="0" step="1" id="length" name="length">
				</div>
				<div class="my-column">
					<label for="version">上映版本：</label>
					<select name="version" id="version">
						<option value="數位">數位</option>
						<option value="IMAX">IMAX</option>
						<option value="3D">3D</option>
					</select>
					<label for="start-date">上映日期（含）：</label>
					<input type="date" id="start-date" name="start-date">
					<label for="end-date">下檔日期（含）：</label>
					<input type="date" id="end-date" name="end-date">
					<label for="hall">上映影廳</label>
					<select name="hall" id="hall">
						<!-- JSP要處理 -->
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>
				<div class="my-column">
					<label for="cover">電影封面</label>
					<input type="file" id="cover" name="cover" accept="image/*" onchange="readURL(this);">
					<img src="" alt="預覽" id="cover-preview">
				</div>
			</div>
			<p>
				<input type="submit" value="送出" id="submit-button">
			</p>
		</form>
	</body>

	</html>