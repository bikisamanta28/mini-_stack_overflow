function doLike(userId,pid){
	

	$.ajax({
		url:'likeServlet',
		data:{var1:userId,var2:pid},
		success:function(data){
			
			console.log(data);
			if(data.trim()=='done'){
				let c=$(".like-counter").html();
				c++;
				$(".like-counter").html(c);
			}
		},
		error:function(data){
			console.log(data)
		}
	})
}