function setHeight(){var i=document.documentElement.clientHeight-90;$("#mainiframe").height(i+"px"),$("#nav").css({height:$(window).outerHeight()+"px"})}$(function(){setHeight(),$(window).on("resize",function(){setHeight()}),$("#nav").on("click",".category",function(){$(this).addClass("active").parents("li").siblings().children(".category").removeClass("active"),$(".subcategory a").removeClass("active"),void 0!=$(this).attr("data-direction")&&("down"==$(this).attr("data-direction")?($(this).siblings(".subcategory").slideDown(),$(this).attr("data-direction","up")):($(this).siblings(".subcategory").slideUp(),$(this).attr("data-direction","down"))),$(this).parents("li").siblings().children(".subcategory").slideUp();var i=$(this).parents("li").siblings().children(".category");i.each(function(i,t){void 0!=$(t).attr("data-direction")&&$(t).attr("data-direction","down")})}),$("#nav .subcategory").on("click","a",function(){$("#nav .category").removeClass("active"),$(this).addClass("active").siblings("a").removeClass("active").parents(".subcategory").parents("li").siblings("li").find(".subcategory a").removeClass("active")})});