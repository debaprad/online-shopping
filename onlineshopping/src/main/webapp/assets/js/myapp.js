$(function() {
	switch(menu)
	{
		case 'About Us':
			$('#aboutus').addClass("active");
			break;
		case 'contact Us':
			$('#contactus').addClass("active");
			break;
		case 'All Products':
			$('#listProducts').addClass("active");
			break;
		default:
			if(menu=="home")
			{
				break;
			}
			$('#listProducts').addClass("active");
			$('#a_'+menu).addClass("active");
			break;
	}
});