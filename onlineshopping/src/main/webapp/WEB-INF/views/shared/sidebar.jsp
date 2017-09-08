<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <h3 class="">Shop Name</h3>
          <div class="list-group">
          <c:forEach items="${categories}" var="category">
          	<a href="${contextPath}/show/category/${category.id}/product" class="list-group-item" id="a_${category.name}">${category.name}</a>
          </c:forEach>
            
            
          </div>