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
					<script type="text/javascript">
						window.categoryId='';
					</script>
					<ol class="breadcrumb">
						<li><a href="${contextPath}/home">Home</a></li>
						<li class="active">All Products</a></li>
					</ol>
					</c:if>
					<c:if test="${CategoryProducts}">
					<script type="text/javascript">
						window.categoryId='${category.id}';
					</script>
					<ol class="breadcrumb">
						<li class="active"><a href="${contextPath}/home">Home</a></li>
						<li class="activr">Category</li>
						<li class="activr">${category.name}</li>
					</ol>
					</c:if>
				</div>
			</div>
		
			<div class="row">
				<div class="col-xs-12">
					<table id="productTable" class="table table-striped table-bordered">
						<thead>
							<tr>
							<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Qty. Available</th>
								<th></th>
								
							</tr>
						</thead>
					</table>
				</div>
			</div>
		
		</div>

	</div>

</div>