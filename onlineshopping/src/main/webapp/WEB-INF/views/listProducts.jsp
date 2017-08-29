<div class="container">
	<div class="row">
	
		<!-- display side bar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>
		
		
		<!-- display actual products -->
		<div class="col-md-9">
			<!-- added breadcrum -->
			<div class="row">
				<div class="col-lg-12">
				<c:if test="${AllProducts}">
					<ol class="breadcrumb">
						<li><a href="${contextPath}/home">Home</a></li>
						<li class="active">All Products</a></li>
					</ol>
					</c:if>
					<c:if test="${CategoryProducts}">
					<ol class="breadcrumb">
						<li class="active"><a href="${contextPath}/home">Home</a></li>
						<li class="activr">Category</li>
						<li class="activr">${category.name}</li>
					</ol>
					</c:if>
				</div>
			</div>
		</div>

	</div>

</div>