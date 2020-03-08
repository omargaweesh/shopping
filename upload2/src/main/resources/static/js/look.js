
$(document).ready(function(){
	//getElement();
	var ct=0;
    
    $(".par").on('click',".ap",function(){
        ct++;
        var id=$(this).closest(".b").find(".va").text();
        var name=$(this).closest(".b").find("h3").text();
        var img=$(this).closest(".b").find("img").attr("src");
        var count=$(this).closest(" .b").find(".v").text();
        object={
        	id:id,
        	name:name,
        	price:count,
        	picpath:img
        };
        var data=new FormData();
        data.append("ob",JSON.stringify(object));
        $.ajax({
        	method:"POST",
        	url:"/send",
        	data:data,
            processData: false,
            contentType: false,
        	success:function(){
        		console.log("success");
        	},
        	error:function(e){
        		console.log("error"+e)
        	}
        });
        console.log(object);
        $(".navbar-light .navbar-nav  .nav-link .bas span").text(ct);
        
    });
    
  
});