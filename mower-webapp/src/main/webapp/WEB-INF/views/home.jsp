<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Home</title>
    <style type="text/css">
    	.cell { width : 50px; height: 50px;}
    	.cut { background-image:url(resources/img/cut-lawn.jpg); }
    	.uncut { background-image:url(resources/img/uncut-lawn.jpg); }
    	.mower-NORTH { background-image:url(resources/img/mower-north.png); }
    	.mower-EAST { background-image:url(resources/img/mower-east.png); }
    	.mower-SOUTH { background-image:url(resources/img/mower-south.png); }
    	.mower-WEST { background-image:url(resources/img/mower-west.png); }
    </style>
</head>
<body>
		<div class="row-fluid">
		
			<div class="span6">
				<p>
					Pour piloter la tondeuse, chargez un fichier
				</p>
				
				<form method="post" action="post_file" enctype="multipart/form-data">
				        <input type="file" name="file" />
				        <input type="submit" class="btn btn-primary" />
				</form>
			</div>
			<div class="span6">
<!-- 				<div id="message" class="alert alert-info"> -->
<!-- 					Tondeuse -->
<!-- 				</div> -->
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">Fichiers disponibles</li>
						<li><a href="loadFile?file=enonce.txt">enonce.txt</a></li>
						<li><a href="loadFile?file=lol.txt">lol.txt</a></li>
						<li><a href="loadFile?file=xebia.txt">xebia.txt</a></li>
					</ul>
				</div>
			</div>
		
			<div class="span12">
			
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success">
						<spring:message text="${message}" />
					</div>	
				</c:if>
			
				<c:if test="${not empty map}">
					<table id="grid">
						<c:forEach var="yind" begin="0" end="${map.height}" >
							<c:set var="y" value="${map.height - yind}" />
							<c:forEach var="x" begin="0" end="${map.width}">
								<c:if test="${x == 0}">
									<tr>
								</c:if>
								<td id="cell_${x}_${y}" class="cell uncut"></td>
								<c:if test="${x == map.width}">
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</table>
				</c:if>
			
			</div>
		</div>
		
		<script>
		
			var currentIndex = 0;
			var mowerCoords = null;
			var $previous = null;
			var $current = null;
			
			function performMove() {
				
				if (currentIndex >= mowerCoords.length) {
					return;
				}
				
				$previous = $current;
				
				var currentCoords = mowerCoords[currentIndex++];
				$current  = $('#cell_' + currentCoords.x + '_' + currentCoords.y);
				
				if ($previous && $previous != $current) {
					$previous.removeClass('cut uncut mower-NORTH mower-EAST mower-SOUTH mower-WEST');
					$previous.fadeIn().addClass('cut');
				}

				$current.removeClass('cut uncut mower-NORTH mower-EAST mower-SOUTH mower-WEST');
				$current.fadeIn().addClass('mower-' + currentCoords.direction);
			}
			
			$(document).ready(function() {
				$.get('get_moves', function(data) {
					console.debug(data);
					mowerCoords = data;
					console.debug(parseInt($('#anim_speed').val()));
					setInterval('performMove()', 100 );
				}, "json");
			});
		</script>
		
</body>
</html>