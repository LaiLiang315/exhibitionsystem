var marginleft=0;
$(document).ready(function(){
	getProductionTypeInfo();
});

// 首页查询分类信息
function getProductionTypeInfo() {
	$.ajax({
		type:'POST',
		url:'/exhibitionsystem/carouselManagement/carouselManagement_querryCarousel',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	// console.log(result);
	    	var listCarouselDTO = JSON.parse(result);
	    	putProductionTypeInfo(listCarouselDTO);
	    	countwidth();
	    	putCarouselInfo(listCarouselDTO);
	    }
	})
}
// 将获取到的分类信息放到首页相应位置
function putProductionTypeInfo(listCarouselDTO){
	var str="";// 初始化中间层li
	var strStart='<li class="first-item">网站建设</li>';// 定义第一个li
	var strOver='<li class="last-item">网站设计</li>';// 定义最后一个li
	var typeInfo= document.querySelector("#li-list");// 定位放入的位置
	var length = listCarouselDTO.length;
	// 遍历result
	for(var i=0;i<length;i++){
		var logo = listCarouselDTO[i].type.production_type_logo;// 获取logo
		var typename = listCarouselDTO[i].type.production_type_name;// 获取分类名称
		str=str+'<li style="background-image: url('+logo+');">'+typename+'</li>';// 生成中间层菜单
	}
	var strAll=strStart+str+strOver;// 拼接三层li
	typeInfo.innerHTML=strAll;// 插入标签
	console.log("typebar加载完毕")
}
// 插入轮播图信息
function putCarouselInfo(listCarouselDTO){
	var str="";// 初始化li
	var typeInfo= document.querySelector("#banner_img");// 定位放入的位置
	var display = "";// 标签显示
	var length=listCarouselDTO.length;
	// 遍历result
	for(var i=0;i<length;i++){
		// 判断该分类是否有轮播图
		if(listCarouselDTO[i].listcarouselpicture.length>0){
			if(i==0){
				str=str+'<li style="background-image:url('+listCarouselDTO[i].listcarouselpicture[0].carousel_picture+'); display:block;">';}
			else{
				str=str+'<li style="background-image:url('+listCarouselDTO[i].listcarouselpicture[0].carousel_picture+'); display:none;">';
			}
		      str+='<div class="wrapper">'+
		        '<div class="ad_txt">'+
		          '<h2>'+listCarouselDTO[i].type.production_type_title+'</h2>'+
		          '<p>'+listCarouselDTO[i].type.production_type_discription+'</p>'+
		        '</div>'+
		        '<div class="ad_img"> <img src="'+listCarouselDTO[i].type.production_type_picture+'"  width="506" height="404" alt="" /> </div>'+
		      '</div>'+
		    '</li>';// 生成中间层菜单
		}
	}
	typeInfo.innerHTML=str;// 插入标签
	console.log("加载完毕")
}
// 计算类型栏宽度
function countwidth(){
	var lii=document.getElementById("li-list").getElementsByTagName("li");
	console.log("tubiaoshu"+lii.length)
	var Nli=lii.length-2;	// 图标个数
	var nbanner_ctr=document.getElementById("banner_ctr");
	var NliWidth=nbanner_ctr.offsetWidth/Nli;
	var ulWidth=Nli*115+20*2;
	console.log("ulWidth"+ulWidth)
	marginleft=(960-ulWidth)/2;
	console.log("marginleft"+marginleft)
	document.getElementById("li-list").style.width=ulWidth+"px";
	document.getElementById("banner_ctr").style.width=ulWidth+"px";
	document.getElementById("dudu").style.marginLeft=marginleft+"px";
	document.getElementById("drag_ctr").style.left=marginleft+20+"px";
	document.getElementById("drag_arrow").style.left=marginleft+20+"px";
}
	$("header>div>nav>ul>li>a").hover(function(){
		$(this).parent().stop(false,true).animate({"background-position-x":"6px",opacity:"0.7"},{duration:"normal", easing: "easeOutElastic"});
	},function(){
		$(this).parent().stop(false,true).animate({"background-position-x":"10px",opacity:"1"},{duration:"normal", easing: "easeOutElastic"});
	});
/*<!--- 首页 ---->*/
	$('.ad_img,#banner_ctr,#client').pngFix();
	$(window).scroll(function(){
		$(this).scrollTop()>80?$("#navbg").stop(false,true).animate({opacity:"1"},"normal"):$("#navbg").stop(false,true).animate({opacity:"0.8"},"normal");
	});
	// Banner Start
	var curIndex=0;
	var time=800;
	var slideTime=5000;
	var adTxt=$("#banner_img>li>div>.ad_txt");
	var adImg=$("#banner_img>li>div>.ad_img");
	var int=setInterval("autoSlide()",slideTime);

	$("#banner_ctr>#dudu>ul").click(function(event){
		if(event.target.class!='first-item'&&event.target.class!='ast-item'){
		show($(event.target).index("#banner_ctr>#dudu>ul>li[class!='first-item'][class!='last-item']"));
		window.clearInterval(int);
		int=setInterval("autoSlide()",slideTime);
		}
	});

	function autoSlide(){
		curIndex+1>=$("#banner_img>li").size()?curIndex=-1:false;
		console.log("=========ttt"+$("#banner_img>li").size());
		show(curIndex+1);
		
	}
	function show(index){
		$.easing.def="easeOutQuad";
		$("#drag_ctr,#drag_arrow").stop(false,true).animate({left:index*115+marginleft+20},300);
		console.log(index+"index")
		var lis = document.getElementById("li-list").getElementsByTagName("li");
		$("#banner_img>li").eq(curIndex).stop(false,true).fadeOut(time);
		adTxt.eq(curIndex).stop(false,true).animate({top:"340px"},time);
		adImg.eq(curIndex).stop(false,true).animate({right:"120px"},time);
		setTimeout(function(){
			$("#banner_img>li").eq(index).stop(false,true).fadeIn(time);
			adTxt.eq(index).children("p").css({paddingTop:"50px",paddingBottom:"50px"}).stop(false,true).animate({paddingTop:"0",paddingBottom:"0"},time);
			adTxt.eq(index).css({top:"0",opacity:"0"}).stop(false,true).animate({top:"170px",opacity:"1"},time);
			adImg.eq(index).css({right:"-50px",opacity:"0"}).stop(false,true).animate({right:"10px",opacity:"1"},time);
		},200)
		curIndex=index;
		show_scrolList();
	}
	// Banner End
	// Cases Start
	$("#cases>ul").hover(function(){
		if($.support.transition){
			$("#cases>ul>li").hover(function(){
				$("img",this).stop(false,true).transition({
					perspective: '300px',
					rotateY: '180deg',
					opacity: '0'
				});
				$("p",this).css({display:'block',opacity:'0',rotateY: '-180deg'}).stop(false,true).transition({
					perspective: '300px',
					rotateY: '0deg',
					opacity: '1'
				});
			},function(){
				$("img",this).show().stop(false,true).transition({
					perspective: '300px',
					rotateY: '0deg',
					opacity: '1'
				});
				$("p",this).stop(false,true).transition({
					perspective: '300px',
					rotateY: '180deg',
					opacity: '0'
				});
			});
		}else{
			$("#cases>ul>li").hover(function(){
				$("img",this).stop(false,true).slideUp("fast");
				$("p",this).stop(false,true).slideDown("fast");
			},function(){
				$("img",this).stop(false,true).slideDown("fast");
				$("p",this).stop(false,true).slideUp("fast");
			});
		}
	})
	$("#cases>ul>li>img").lazyload({effect:"fadeIn",failurelimit:10});
						$("#gotop").click(function(){$('body,html').animate({scrollTop:0},500);})


//展示分类作品信息
function show_scrolList(iquery_data) {
	console.log("执66666666666666行");						
	var formData = new FormData();
	formData.append("showAll",1);
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_showPicturesVO",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		cache: false,  
	    processData: false,  
	    contentType: false,
		success : function(result) {
			if(result.success=true){
				console.log("result"+JSON.parse(result));
				var vo=JSON.parse(result);
				// 显示article信息列表
				var card_table_info =  document.querySelector("#productionList");		//获取文档元素
				card_table_info.innerHTML;
				var str = "";
				
				// 遍历json集合
				for (var i = 0; i < vo.listProductionDTO[1].listInfo.length; i++) {
					// 得到每条数据
			
					var object = vo.listProductionDTO[1].listInfo[i];//DTO[0,1,2]分别为类型1,2,3的图片集合
					// 得到各条数据的某个信息
					// 得到各条数据的某个信息
					str+= ''
					// 遍历是把article_id的值传给checkbox的value(为后期的批量删除)
					str+= '<li><img src="/exhibitionsystem/productionManagement/productionManagement_IoReadImage?uploadFileName='+object.production_info_name+'"   width="240" height="152" alt="成都城市设计研究中心"/>'+
					      '<p> <strong>成都城市设计研究中心</strong>成都市城市设计研究中心（英文缩写：CDUDC）是以城市设计和研究为主要方向的研究性机构，提....<br/>'+
					        '<a href="case/gov/22.html"  class="btn_blue">查看品牌故事</a>'+
					      '</p>'+
					    '</li>';
				
				card_table_info.innerHTML = str;
				}}else{
				console.log("传值失败");
			}	
		}
	});
}
	
