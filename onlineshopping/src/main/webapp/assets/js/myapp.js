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
		case 'Manage Product':
			$('#manageProduct').addClass("active");
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
				lengthMenu: [[5,10,-1],["5 Record","10 Record","All"]],
				pageLegth:5,
				ajax:{
					url:jsonUrl,
					dataSrc:''
				},
			columns : [ {
				data : 'code',
				mRender : function(data,type,row)
				{
					return '<a href="'+window.contextRoot+'/show/'+row.id+'/product" ><img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/></span></a>';
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
					/*
					 * s += '<a
					 * href="'+window.contextRoot+'/show/'+data+'/product"
					 * class="btn btn-primary"><span class="glyphicon
					 * glyphicon-eye-open"></span></a> &#160;';
					 */
					if(row.quantity < 1)
						{
						if(userRole=='ADMIN')
						{
						s +='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
						
						}
						else{
						s +='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart disabled"></span></a>';
						}
						}
					else
						{
						if(userRole=='ADMIN')
							{
							s +='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
							
							}
						else
							{
							s +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							
							}
						}
					
					return s;
				}
			} ]
			
			});
			
		}
	
	
	$alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
	    	$alert.fadeOut('slow');
		   }, 3000
		);		
	}
	
	
	
//-------------------------
var $productsTable = $('#productsTable');
	
	
	if($productsTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/All/Admin/Product';
		console.log(jsonUrl);
		
		$productsTable.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ], [ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [		
					           	{data: 'id'},
					           	{data: 'code',
					           	 bSortable: false,
					           		mRender: function(data,type,row) {
					           			return '<img src="' + window.contextRoot
										+ '/resources/images/' + data
										+ '.jpg" class="dataTableImg"/>';					           			
					           		}
					           	},
					           	{
									data : 'name'
								},
								{
									data : 'brand'
								},
								{
									data : 'quantity',
									mRender : function(data, type, row) {

										if (data < 1) {
											return '<span style="color:red">Out of Stock!</span>';
										}

										return data;

									}
								},
								{
									data : 'unitPrice',
									mRender : function(data, type, row) {
										return '&#8377; ' + data
									}
								},
								{
									data : 'active',
									bSortable : false,
									mRender : function(data, type, row) {
										var str = '';
										if(data) {											
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'" checked="checked">  <div class="slider round"> </div></label>';
											
										}else {
											str += '<label class="switch"> <input type="checkbox" value="'+row.id+'">  <div class="slider round"> </div></label>';
										}
										
										return str;
									}
								},
								{
									data : 'id',
									bSortable : false,
									mRender : function(data, type, row) {

										var str = '';
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

										return str;
									}
								}					           	
					],
					
					
					initComplete: function () {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change' , function() {							
							var dText = (this.checked)? 'You want to activate the Product?': 'You want to de-activate the Product?';
							var checked = this.checked;
							var checkbox = $(this);
							debugger;
						    bootbox.confirm({
						    	size: 'medium',
						    	title: 'Product Activation/Deactivation',
						    	message: dText,
						    	callback: function (confirmed) {
							        if (confirmed) {
							            $.ajax({							            	
							            	type: 'GET',
							            	url: window.contextRoot + '/manage/product/'+checkbox.prop('value')+'/activation',
							        		timeout : 100000,
							        		success : function(data) {
							         			//bootbox.alert(data);							        										        			
							        		},
							        		error : function(e) {
							        			//bootbox.alert('ERROR: '+ e);
							        			//display(e);
							        		}						            	
							            });
							        }
							        else {							        	
							        	checkbox.prop('checked', !checked);
							        }
						    	}
						    });																											
						});
							
					}
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
$registerForm = $('#registerForm');
	
	if($registerForm.length) {
		
		$registerForm.validate({			
				rules: {
					firstName: {
						minlength: 4
					},
					email: {
						required: true,
						email					
					},
					password: {
						required: true,
						minlength: 4				
					}
				},
				messages: {					
					firstName: {
						minlength: 'Please enter atleast four characters'
					},
					email: {
						email: 'Please enter valid email'
					},
					password: {
						minlength: 'Please enter atleast four characters'
					}
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");
					
					// add the error label after the input element
					error.insertAfter(element);
				}		
			}
		
		);
		
	}
	

	$categoryForm = $('#categoryForm');
		
		if($categoryForm.length) {
			
			$categoryForm.validate({			
					rules: {
						name: {
							required: true,
							minlength: 4
						},
						description: {
							required: true					
						}				
					},
					messages: {					
						name: {
							required: 'please enter category name',
							minlength: 'Please enter atleast four characters'
						},
						description: {
							required: 'Please enter description'
						}					
					},
					errorElement : "em",
					errorPlacement : function(error, element) {
						// Add the 'help-block' class to the error element
						error.addClass("help-block");
						
						// add the error label after the input element
						error.insertAfter(element);
					}		
				}
			
			);
			
		}
	
	
	
/*validating the loginform*/
	
	// validating the product form element	
	// fetch the form element
	$loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
						
					},
					password: {
						required: true
					}				
				},
				messages: {					
					username: {
						required: 'Please enter your email!',
						email: 'Please enter a valid email address!'
					},
					password: {
						required: 'Please enter your password!'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					// Add the 'help-block' class to the error element
					error.addClass("help-block");
					
					// add the error label after the input element
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
	
});