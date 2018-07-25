$("header>div>nav>ul>li>a").hover(function(){
		$(this).parent().stop(false,true).animate({"background-position-x":"6px",opacity:"0.7"},{duration:"normal", easing: "easeOutElastic"});
	},function(){
		$(this).parent().stop(false,true).animate({"background-position-x":"10px",opacity:"1"},{duration:"normal", easing: "easeOutElastic"});
	});
		/*<!--- 案例 ---->
			//Nav Start
*/		
		var pagecur=1;//当前页
		var username=getCookie("username");
		$("header>div>nav>ul>li>a").hover(function(){
			$(this).parent().stop(false,true).animate({"background-position-x":"6px",opacity:"0.7"},{duration:"normal", easing: "easeOutElastic"});
		},function(){
			$(this).parent().stop(false,true).animate({"background-position-x":"10px",opacity:"1"},{duration:"normal", easing: "easeOutElastic"});
		});
		//Nav End
	
		//Filter Start
		
		ajaxLogin("username="+username);
		$("#filter>div>p>.signIn").live("click",function(){
			$("#login_bg").css("height",$("body").height()+"px").fadeIn();
			$("#login").fadeIn();
			$("#username").val("");
			$("#password").val("");
			$("#login_err").text("");
		});
		$("#filter>div>p>.signOut").live("click",function(){
			ajaxOut("act=loginout&username="+username);
		});
		$("#login .close").click(closeLogin);
		$("#login .textinput").focusout(loginck);
		$("#signin").click(function(){
			if(loginck()) ajaxLogin("username="+$("#username").val()+"&password="+$("#password").val());
		});
		function closeLogin(){
			$("#login_bg").fadeOut();
			$("#login").fadeOut();
		}
		function loginck(){
			var reg=/[a-z]{3,12}/.test($("#username").val());
			if($("#username").val()==""){
				$("#login_err").text("帐号不能为空");
				return false;
			}	
			if(!reg){
				$("#login_err").text("帐号格式不正确");
				return false;
			}
			if($("#password").val()==""){
				$("#login_err").text("密码不能为空");
				return false;
			}
			$("#login_err").text("");
			return true;
		}
		function getCookie(name){  
			var cookieValue = "";  
			var search = name + "=";  
			if(document.cookie.length>0){
				offset = document.cookie.indexOf(search);  
				if (offset != -1){
					offset += search.length;  
					end = document.cookie.indexOf(";", offset);  
					if (end == -1) end = document.cookie.length;  
					cookieValue = unescape(document.cookie.substring(offset, end))  
				}  
			}  
			return cookieValue;  
		}
		function ajaxLogin(str){
			$.ajax({
				type: "POST",
				url: "http://mc18.eatdou.com/login.php",
				data: str,
				success: function(msg){
					if(msg!="false"){
						document.cookie = "username="+msg;
						$("#filter>div>p").html("欢迎你，"+msg+"！<a href='javascript:void(0)' class='signOut'>Sign Out</a>");
						username=msg;
						closeLogin();
					}else{
						$("#login_err").text("登录失败，请核对帐号后重新登录。");
						document.cookie = "username=";
					}
				},
				error:function(){
					$("#filter>div>p").html("查看更多案例？<a href='javascript:void(0)' class='signIn'>Sign In</a>");
				}
			});
		}
		
		//Filter End
		//Cases Start
		$("#caseslist>.cases>li").live({mouseenter:function(){
			$("p",this).stop(false,true).slideDown("normal","easeOutQuad");
		},mouseleave:function(){
			$("p",this).stop(false,true).slideUp("normal","easeOutQuad");
		}});
		$("#caseslist>.cases>li>img").lazyload({effect:"fadeIn",failurelimit:10});
		$("#loadmore>a").click(function(){
			if($("#loadmore>a").text()=="没有更多案例了..."){
				return false;
			}
			$("#loading").slideDown("normal","easeOutQuad");
			$.get(ajaxUrl+(pagecur+1)+"..html", function(data){
				$("#loading").slideUp("normal","easeOutQuad",function(){
					if(data==0){
						$("#loadmore>a").text("没有更多案例了...");
						return false;
					}
					$("#caseslist>.cases").append(data);
					$("#caseslist>.cases>li>img").lazyload({effect:"fadeIn",failurelimit:10});
				});
				pagecur++;
			});
		})
		//Cases End
					$("#gotop").click(function(){$('body,html').animate({scrollTop:0},500);})