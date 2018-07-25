layui.use('upload', function(){
  var $ = layui.jquery
  ,upload = layui.upload;
  
  //上传轮播图背景
  var uploadInst = upload.render({
    elem: '#test1'
    ,url: '/lx/upload/upload_addPhoto'
	,auto: false
    ,bindAction: '#testListAction1' //指向一个按钮触发上传，一般配合 auto: false 来使用
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });
  
//上传分类LOGO
  var uploadInst = upload.render({
    elem: '#test2'
    ,url: '/lx/upload/upload_addPhoto'
	,auto: false
    ,bindAction: '#testListAction2' //指向一个按钮触发上传，一般配合 auto: false 来使用
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo2').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });
  
//上传作品代表图
  var uploadInst = upload.render({
    elem: '#test3'
    ,url: '/lx/upload/upload_addPhoto'
	,auto: false
    ,bindAction: '#testListAction3' //指向一个按钮触发上传，一般配合 auto: false 来使用
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo3').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });
})