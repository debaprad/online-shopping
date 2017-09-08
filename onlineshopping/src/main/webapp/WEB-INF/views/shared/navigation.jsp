 
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
        <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextPath}/home">Online Shopping</a>
            </div>
       
        
             <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
            <li class="nav-item" id="home">
              <a class="nav-link" href="${contextPath}/home">Home
                <!-- <span class="sr-only">(current)</span> -->
              </a>
            </li>
            <li class="nav-item" id="aboutus">
              <a class="nav-link" href="${contextPath}/aboutus">About Us</a>
            </li>
            <li class="nav-item" id="listProducts">
              <a class="nav-link" href="${contextPath}/show/all/products">View Products</a>
            </li>
            <security:authorize access="hasAuthority('ADMIN')">
            <li class="nav-item" id="manageProduct">
              <a class="nav-link" href="${contextPath}/manage/product">Manage Product</a>
            </li>
            </security:authorize>
            <li class="nav-item" id="contactus">
              <a class="nav-link" href="${contextPath}/contactus">Contact Us</a>
            </li>
            
          </ul>

			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li class="nav-item" id="signup"><a class="nav-link"
						href="${contextPath}/register">Sign Up</a></li>
					<li class="nav-item" id="login"><a class="nav-link"
						href="${contextPath}/login">Login</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userModel"><a
						class="btn btn-default dropdown-toggle" href="javascript:void(0)"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="true"> ${userModel.fullName} <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<security:authorize access="hasAuthority('USER')">
								<li id="cart"><a href="${contextPath}/cart/show"> <span
										class="glyphicon glyphicon-shopping-cart"></span>&#160;<span
										class="badge">${userModel.cart.cartLines}</span> - &#8377;
										${userModel.cart.grandTotal}
								</a></li>
								<li role="separator" class="divider"></li>
							</security:authorize>
							<li id="logout"><a href="${contextPath}/perform-logout">Logout</a>
							</li>
						</ul>
					</li>
					</security:authorize>
				
			</ul>
		</div>
      </div>
    </nav>
