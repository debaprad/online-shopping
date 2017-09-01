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
	
    
	var $table=$("#productTable");  //taking the table
	
	if($table.length)   //excute jquery table only when table is loaded
		{
		var jsonUrl='';
		if(window.categoryId=='')
			{
				jsonUrl=window.contextRoot+'/json/data/All/Product';
			}
		else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/Product';
		}
			$table.DataTable({
				lengthMenu: [[3,5,10,-1],["3 Record","5 Record","10 Record","All"]],
				pageLegth:5,
				ajax:{
					url:jsonUrl,
					dataSrc:''
				},
			columns : [ {
				data : 'code',
				mRender : function(data,type,row)
				{
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
				}
			},{
				data : 'name'
			}, {
				data : 'brand'
			}, {
				data : 'unitPrice',
				mRender : function(data,type,row) {
					return '&#8377;' + data;
				}
			}, {
				data : 'quantity',
				mRender: function(data,type,row)
				{
					if(data<1)
						{
							return '<span style="color:red">Out Of Stock!</span>';
						}
					return data;
				}
					
			}, 
			{
				data: 'id',
				mRender : function(data,type,row)
				{
					var s='';
					s += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
					
					if(row.quantity < 1)
						{
						s +='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart disabled"></span></a>';
						}
					else
						{
						s +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
					
					return s;
				}
			} ]
			
			});
			
		}
	
	
	
	
	
	/*
	 * if ($table.length) { // console.log('Inside the table!');
	 * 
	 * var jsonUrl = ''; if (window.categoryId == '') { jsonUrl =
	 * window.contextRoot + '/json/data/All/Product'; } else { jsonUrl =
	 * window.contextRoot + '/json/data/category/' + window.categoryId +
	 * '/products'; }
	 * 
	 * $table .DataTable({
	 * 
	 * lengthMenu : [ [ 3, 5, 10, -1 ], [ '3 Records', '5 Records', '10
	 * Records', 'ALL' ] ], pageLength : 5, ajax : { url : jsonUrl, dataSrc : '' },
	 * columns : [ { data : 'name' }, { data : 'brand' }, { data : 'unitPrice' }, {
	 * data : 'quantity' } ] }); }
	 */

	
});