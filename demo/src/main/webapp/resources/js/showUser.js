//将一个表单的数据返回成JSON对象
$.fn.serializeObject = function() {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name]) {
      if (!o[this.name].push) {
        o[this.name] = [ o[this.name] ];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
};

$(document).ready(
		
    function() {
//    	var url = 'showUser';
//        var arg =null;
//        $.get(url,arg,function(returnData){
//            console.log(returnData);
//        });
      $("#submit").click(function() {
        var jsonuserinfo = $.toJSON($('#form').serializeObject());
        alert(jsonuserinfo);
        jQuery.ajax( {
          type : 'POST',
          contentType : 'application/json',
          url : 'user/add',
          data : jsonuserinfo,
          dataType : 'json',
          success : function(data) {
            alert("新增成功！");
          },
          error : function(data) {
            alert("error")
          }
        });
      });
    });