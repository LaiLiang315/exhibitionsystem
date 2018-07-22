layui.use('laydate', function(){
 	 var laydate = layui.laydate;
  
  	//常规用法
 	 laydate.render({
 	   elem: '#test1'
 	 });});

layui.use('form', function(){
	var form = layui.form; 
	form.render();
});
//全选
form.on('checkbox(allChoose)', function(data){
    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
    child.each(function(index, item){
      item.checked = data.elem.checked;
    });
    form.render('checkbox');
});

