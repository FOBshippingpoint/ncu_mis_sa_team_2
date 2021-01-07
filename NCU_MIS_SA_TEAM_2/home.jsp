<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<%@ include file = "theater-header.jsp"%> 
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC:wght@900&display=swap" rel="stylesheet">
<title>線上電影訂票系統</title>

<style>
	h1 {
		font-size: 5em;
		font-family: 'Noto Serif TC', serif;
	}
	
	body {
	  background-color: black;
	}
	
	.eva-heading {
	  display: inline-block;
	  padding: 32px;
	  margin: 0 auto;
	  align-self: baseline;
	  font-family: serif;
	  color: white;
	  text-shadow: 0 0 2px #e19a86, 0 0 1.5px #854535, 0 0 1.5px #5c150c;
	}
	
	.eva-heading * {
	  padding: 0;
	  margin: 0;
	}
	
	.noselect *{
		-webkit-touch-callout: none; /* iOS Safari */
		  -webkit-user-select: none; /* Safari */
		   -khtml-user-select: none; /* Konqueror HTML */
		     -moz-user-select: none; /* Old versions of Firefox */
		      -ms-user-select: none; /* Internet Explorer/Edge */
		          user-select: none; /* Non-prefixed version, currently
                                  supported by Chrome, Edge, Opera and Firefox */
	}
	
	.eva-heading > h1 {
	  font-size: 500%;
	}
	
	.eva-heading > h2 {
	  font-size: 300%;
	}
	
	.eva-heading__title {
	  transform: scale(1, 1.5);
	  line-height: 1.2em;
	  letter-spacing: -.03em;
	}
	
	.eva-heading__episode-number {
	  font-family: sans-serif;
	  font-size: 180%;
	  transform: scale(1, 1.5);
	  letter-spacing: -.06em;
	  margin: 18px 0 26px 0;
	}
	
	.eva-heading__episode-title {
	  transform: scale(1, 1.3);
	  font-size: 170%;
	  line-height: 1em;
	}
	
	.my-a {
		text-decoration: none;
		color: inherit;
	}
	
	.my-a:hover {
		color: red;
		cursor: pointer;
	}
	
</style>

</head>

<body>
<div class="main">
	<div class="noselect" style="padding-left: 15%; padding-top: 5%;">
		<header class="eva-heading">
			<h2 class="eva-heading__title">THE</h2>
			<h2 class="eva-heading__title">MOVIE</h2>
			<h1 class="eva-heading__title">BOOKING</h1>
			<h3 class="eva-heading__episode-number">SYSTEM</h3>
			<h4 class="eva-heading__episode-title">
			  CC's Theater
			</h4>
		</header>
		<header class="eva-heading">
			<h1 style="line-height: 100%; display: inline-block;">
				<a class="my-a" href="/NCU_MIS_SA/movie-list">
					電<br>影
				</a>
			</h1>
			<h1 style="
			 display: inline;
			 position: relative;
			 top: 2rem;
			 left: -1.5rem;
			">、</h1>
			<h1 style="
			 display: inline;
			 position: relative;
			 /* top: 1.5rem; */
			 left: -2.5rem;
			">訂票系統</h1>
		</header>
	</div>
</span>
</div>
</body>

</html>