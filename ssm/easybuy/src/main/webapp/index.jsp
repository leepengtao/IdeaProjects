 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>

 <base href="<%=basePath%>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->    
    <script type="text/javascript" src="js/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="js/jquery.bxslider_e88acd1b.js"></script>


    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
        
	<script type="text/javascript" src="js/select.js"></script>
    
	<script type="text/javascript" src="js/lrscroll.js"></script>
    
    <script type="text/javascript" src="js/iban.js"></script>
    <script type="text/javascript" src="js/fban.js"></script>
    <script type="text/javascript" src="js/f_ban.js"></script>
    <script type="text/javascript" src="js/mban.js"></script>
    <script type="text/javascript" src="js/bban.js"></script>
    <script type="text/javascript" src="js/hban.js"></script>
    <script type="text/javascript" src="js/tban.js"></script>
    
	<script type="text/javascript" src="js/lrscroll_1.js"></script>

    <script src="js/ajax.js"></script>
    <script>
        var jq = jQuery.noConflict(); // 让渡
        // Ajax实现商品翻页效果
        $(function () {
            var pageNo = 1;
            jq("#firstPage").click(function () {
                // alert("可以了");
                pageNo = 1;
                nextPage(pageNo)
            });
            jq("#lastPage").click(function () {
                // alert("没问题")
                pageNo = parseInt(jq(".counter").html()) -1;
                if (pageNo<1) {
                    return false;
                }
                nextPage(pageNo)
            });
            jq("#nextPage").click(function () {
                // alert("中")
                pageNo = parseInt(jq(".counter").html()) +1;
                if (pageNo>parseInt(jq(".counterTotal").html())) {
                    return false;
                }
                nextPage(pageNo)
            });
            jq("#finalPage").click(function () {
                // alert("ojbk")
                pageNo = parseInt(jq(".counterTotal").html());
                nextPage(pageNo)
            });
        });

        // 商品翻页: 给ajax设置好url和json字符串
        function nextPage(pageNo) {
            var url= "Index/findProduct?pageNo="+pageNo;
            var data = '';
            table_get(url,data);
        }

        // 返回的结果集的数据处理
        function each(proPage) {
            jq(".counter").empty().append(proPage.pageNo);     // 给当前页清空, 再加上回传回来的新页码
            jq(".counterTotal").empty().append(proPage.totalPages);    // 给总页数清空, 再加上传回来的总页数
            jq.each(proPage.pageList,function (index,product) {
                var str = '';
                str += '<li><div class="name"><a href="/Product/showPro?id='+product.id+'">'+product.name +'</a></div>'
                    +'<div class="price">'
                    +'<font>￥<span>'+product.price+'</span></font> &nbsp;'+product.stock
                    +'</div>'
                    +'<div class="img"><a href="/Product/showPro?id='+product.id+'"><img src="images/'+product.fileName+'" width="185" height="155" /></a></div>'
                    +'</li>';
                jq('#pro_table li:first').remove();// 清空原有页面商品数据: -----------------  注意: 不能删掉jstl的循环标签, 否则不刷新  --------------------
                jq("#pro_table").append(str);
                console.log(str);
            })
        }
    </script>
<title>易买网首页</title>
</head>
<body>  
<c:if test="${empty newsList }">
	<jsp:forward page="/Index/index?pageNo=1"></jsp:forward>
</c:if>
<!--Begin Header Begin-->
<div class="soubg">
	<div class="sou">
    	<!--Begin 所在收货地区 Begin-->
    
        <!--End 所在收货地区 End-->
        <span class="fr">

            <c:if test="${empty userLogin}">
        	    <span class="fl">你好，请<a href="Login.jsp">登录</a>&nbsp; <a href="Register.jsp" style="color:#ff4e00;">免费注册</a>&nbsp;
            </c:if>
            <c:if test="${not empty userLogin}">
                <c:if test="${sessionScope.userLogin.type == 1}">
                    <span class="fl" >你好，${sessionScope.userLogin.userName}&nbsp;|&nbsp;<a href="/UserManage/showAll" style="color:red">后台管理</a>
                </c:if>
                        <c:if test="${sessionScope.userLogin.type == 0}">
        	    &nbsp;<span class="fl">你好，${sessionScope.userLogin.userName}
                </c:if>
            </c:if>
            &nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;
              |&nbsp;<a href="/User/logout">注销</a>&nbsp;|</span>
        	<span class="ss">
            	<div class="ss_list">
                	<a href="#">收藏夹</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">我的收藏夹</a></li>
                                <li><a href="#">我的收藏夹</a></li>
                            </ul>
                        </div>
                    </div>     
                </div>
                <div class="ss_list">
                	<a href="#">客户服务</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
                <div class="ss_list">
                	<a href="#">网站导航</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">网站导航</a></li>
                                <li><a href="#">网站导航</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
            </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>
<div class="top">
    <div class="logo"><a href="index.jsp"><img src="images/logo.png" /></a></div>
    <div class="search">
    	<form>
        	<input type="text" value="" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
    	<div class="car_t">购物车 [ <span>3</span> ]</div>
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
        	<div class="un_login">还未登录！<a href="Login.jsp" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
            	<li>
                	<div class="img"><a href="#"><img src="images/car1.jpg" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">法颂浪漫梦境50ML 香水女士持久清新淡香 送2ML小样3只</a></div>
                    <div class="price"><font color="#ff4e00">￥399</font> X1</div>
                </li>
                <li>
                	<div class="img"><a href="#"><img src="images/car2.jpg" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">香奈儿（Chanel）邂逅活力淡香水50ml</a></div>
                    <div class="price"><font color="#ff4e00">￥399</font> X1</div>
                </li>
                <li>
                	<div class="img"><a href="#"><img src="images/car2.jpg" width="58" height="58" /></a></div>
                    <div class="name"><a href="#">香奈儿（Chanel）邂逅活力淡香水50ml</a></div>
                    <div class="price"><font color="#ff4e00">￥399</font> X1</div>
                </li>
            </ul>
            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>1058</span></div>
            <div class="price_a"><a href="#">去购物车结算</a></div>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
<!--End Header End--> 
<!--Begin Menu Begin-->
<div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->    
    	<div class="nav">
        	<div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul> 
                	<c:forEach items="${cvList}" var="cv">
                	<!-- 一级目录 -->
                	 <li>
                		<div class="fj">
                        	<span class="n_img"><span></span><img src="images/${cv.category.iconClass }" /></span>
                            <span class="fl">${cv.category.name }</span>
                        </div>
                        <div class="zj" style="top:-40px;">
                            <div class="zj_l">
                            	<!-- 二级目录 -->
                            	<c:forEach items="${cv.categoryVoList }" var="cv2" >
                                <div class="zj_l_c">
                                    <h2>${cv2.category.name }</h2>
                                    <!-- 三级目录 -->
                                    <c:forEach items="${cv2.categoryVoList }" var="cv3">
                                    <a href="#">${cv3.category.name }</a>|
                               		</c:forEach>
                                </div>
                               </c:forEach>
                            </div>
                            <div class="zj_r">
                                <a href="#"><img src="images/n_img1.jpg" width="236" height="200" /></a>
                                <a href="#"><img src="images/n_img2.jpg" width="236" height="200" /></a>
                            </div>
                        </div>
                     </li>
                	</c:forEach>
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">                                                                                                                                               
        	<li><a href="Index.html">首页</a></li>
            <li><a href="Food.html">美食</a></li>
            <li><a href="Fresh.html">生鲜</a></li>
            <li><a href="HomeDecoration.html">家居</a></li>
            <li><a href="SuitDress.html">女装</a></li>
            <li><a href="MakeUp.html">美妆</a></li>
            <li><a href="Digital.html">数码</a></li>
            <li><a href="GroupBuying.html">团购</a></li>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
<!--End Menu End--> 
<div class="i_bg bg_color">
	<div class="i_ban_bg">
		<!--Begin Banner Begin-->
    	<div class="banner">    	
            <div class="top_slide_wrap">
                <ul class="slide_box bxslider">
                    <li><img src="images/ban1.jpg" width="740" height="401" /></li>
                    <li><img src="images/ban1.jpg" width="740" height="401" /></li> 
                    <li><img src="images/ban1.jpg" width="740" height="401" /></li> 
                </ul>	
                <div class="op_btns clearfix">
                    <a href="#" class="op_btn op_prev"><span></span></a>
                    <a href="#" class="op_btn op_next"><span></span></a>
                </div>        
            </div>
        </div>
        <script type="text/javascript">
        (function(){
            $(".bxslider").bxSlider({
                auto:true,
                prevSelector:jq(".top_slide_wrap .op_prev")[0],nextSelector:jq(".top_slide_wrap .op_next")[0]
            });
        })();
        </script>
        <!--End Banner End-->
        <div class="inews">
        	<div class="news_t">
            	<span class="fr"><a href="#">更多 ></a></span>新闻资讯
            </div>
            <ul>
            	<c:forEach items="${newsList }" var="news">
            	<li><span>[ 特惠 ]</span><a href="#">${news.title}</a></li>
            	</c:forEach>
            </ul>
            <div class="charge_t">
            	话费充值<div class="ch_t_icon"></div>
            </div>
            <form>
            <table border="0" style="width:205px; margin-top:10px;" cellspacing="0" cellpadding="0">
              <tr height="35">
                <td width="33">号码</td>
                <td><input type="text" value="" class="c_ipt" /></td>
              </tr>
              <tr height="35">
                <td>面值</td>
                <td>
                	<select class="jj" name="city">
                      <option value="0" selected="selected">100元</option>
                      <option value="1">50元</option>
                      <option value="2">30元</option>
                      <option value="3">20元</option>
                      <option value="4">10元</option>
                    </select>
                    <span style="color:#ff4e00; font-size:14px;">￥99.5</span>
                </td>
              </tr>
              <tr height="35">
                <td colspan="2"><input type="submit" value="立即充值" class="c_btn" /></td>
              </tr>
            </table>
            </form>
        </div>
    </div>
    <!--Begin 热门商品 Begin-->
    
    <!--Begin 限时特卖 Begin-->
    
    <!--End 限时特卖 End-->
    <div class="content mar_20">
    	<img src="images/mban_1.jpg" width="1200" height="110" />
    </div>
	<!--Begin 进口 生鲜 Begin-->
    <div class="i_t mar_10">
    	<span class="fl">商品列表</span>                
        <span class="i_mores fr">
        </span>
    </div>
    <div class="content">
    	<div class="fresh_left">
        	<div class="fre_ban">
            	<div id="imgPlay1">
                    <ul class="imgs" id="actor1">
                        <li><a href="#"><img src="images/fre_r.jpg" width="211" height="286" /></a></li>
                        <li><a href="#"><img src="images/fre_r.jpg" width="211" height="286" /></a></li>
                        <li><a href="#"><img src="images/fre_r.jpg" width="211" height="286" /></a></li>
                    </ul>
                    <div class="prevf">上一张</div>
                    <div class="nextf">下一张</div> 
                </div>   
            </div>
            <div class="fresh_txt">
            	<div class="fresh_txt_c">
                	<a href="#">进口水果</a><a href="#">奇异果</a><a href="#">西柚</a><a href="#">海鲜水产</a><a href="#">品质牛肉</a><a href="#">奶粉</a><a href="#">鲜活禽蛋</a><a href="#">进口酒</a><a href="#">进口奶粉</a><a href="#">鲜活禽蛋</a>
                </div>
            </div>
        </div>
        <div class="fresh_mid">
        	<ul id="pro_table">
        		<c:forEach items="${proPage.pageList }" var = "product">
        		<li>
                	<div class="name"><a href="/Product/showPro?id=${product.id }">${product.name }</a></div>
                    <div class="price">
                    	<font>￥<span>${product.price }</span></font> &nbsp; ${product.stock }
                    </div>
                    <div class="img"><a href="/Product/showPro?id=${product.id }"><img src="images/${product.fileName }" width="185" height="155" /></a></div>
                </li>
        		</c:forEach>
                
            </ul>
           
        </div>
        <div class="fresh_right">
        	<ul>
            	<li><a href="#"><img src="images/fre_b1.jpg" width="260" height="220" /></a></li>
                <li><a href="#"><img src="images/fre_b2.jpg" width="260" height="220" /></a></li>
            </ul>
        </div>
    </div>    
    <!--End 进口 生鲜 End-->
   
    <!--End 数码家电 End-->
    <!--Begin 猜你喜欢 Begin-->
    <div class="i_t mar_10">
    	<span class="fl">猜你喜欢</span>
    	<span  style="padding-left:400px;">
    		当前页 / 总页数 [ <span class="counter">${proPage.pageNo }</span> /  <span class="counterTotal">${proPage.totalPages }</span> ]&nbsp;&nbsp;&nbsp;&nbsp;
			<%--<a href="Index?pageNo=1">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<a href="Index?pageNo=${proPage.pageNo-1 }">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<a href="Index?pageNo=${proPage.pageNo+1 }">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<a href="Index?pageNo=${proPage.totalPages }">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <a href="#" id="firstPage">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" id="lastPage" >上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" id="nextPage">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" id="finalPage">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
    	 
    </div>    
    <div class="like">        	
        <div id="featureContainer1">
            <div id="feature1">
                <div id="block1">
                    <div id="botton-scroll1">
                        <ul class="featureUL">
                            <li class="featureBox">
                                <div class="box">
                                    <div class="imgbg">
                                        <a href="#"><img src="images/hot1.jpg" width="160" height="136" /></a>
                                    </div>                                        
                                    <div class="name">
                                        <a href="#">
                                        <h2>德国进口</h2>
                                        德亚全脂纯牛奶200ml*48盒
                                        </a>
                                    </div>
                                    <div class="price">
                                        <font>￥<span>189</span></font> &nbsp; 26R
                                    </div>
                                </div>
                            </li>
                            <li class="featureBox">
                                <div class="box">
                                    <div class="imgbg">
                                        <a href="#"><img src="images/hot2.jpg" width="160" height="136" /></a>
                                    </div>                                        
                                    <div class="name">
                                        <a href="#">
                                        <h2>iphone 6S</h2>
                                        Apple/苹果 iPhone 6s Plus公开版
                                        </a>
                                    </div>
                                    <div class="price">
                                        <font>￥<span>5288</span></font> &nbsp; 25R
                                    </div>
                                </div>
                            </li>
                            <li class="featureBox">
                                <div class="box">
                                    <div class="imgbg">
                                        <a href="#"><img src="images/hot3.jpg" width="160" height="136" /></a>
                                    </div>                                        
                                    <div class="name">
                                        <a href="#">
                                        <h2>倩碧特惠组合套装</h2>
                                        倩碧补水组合套装8折促销
                                        </a>
                                    </div>
                                    <div class="price">
                                        <font>￥<span>368</span></font> &nbsp; 18R
                                    </div>
                                </div>
                            </li>
                            <li class="featureBox">
                                <div class="box">
                                    <div class="imgbg">
                                        <a href="#"><img src="images/hot4.jpg" width="160" height="136" /></a>
                                    </div>                                        
                                    <div class="name">
                                        <a href="#">
                                        <h2>品利特级橄榄油</h2>
                                        750ml*4瓶装组合 西班牙原装进口
                                        </a>
                                    </div>
                                    <div class="price">
                                        <font>￥<span>280</span></font> &nbsp; 30R
                                    </div>
                                </div>
                            </li>
                            <li class="featureBox">
                                <div class="box">
                                    <div class="imgbg">
                                        <a href="#"><img src="images/hot4.jpg" width="160" height="136" /></a>
                                    </div>                                        
                                    <div class="name">
                                        <a href="#">
                                        <h2>品利特级橄榄油</h2>
                                        750ml*4瓶装组合 西班牙原装进口
                                        </a>
                                    </div>
                                    <div class="price">
                                        <font>￥<span>280</span></font> &nbsp; 30R
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <a class="l_prev" href="javascript:void();">Previous</a>
                <a class="l_next" href="javascript:void();">Next</a>
            </div>
        </div>
    </div>
    <!--End 猜你喜欢 End-->
    
    <!--Begin Footer Begin -->
    <div class="b_btm_bg b_btm_c">
        <div class="b_btm">
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b1.png" width="62" height="62" /></td>
                <td><h2>正品保障</h2>正品行货  放心购买</td>
              </tr>
            </table>
			<table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b2.png" width="62" height="62" /></td>
                <td><h2>满38包邮</h2>满38包邮 免运费</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b3.png" width="62" height="62" /></td>
                <td><h2>天天低价</h2>天天低价 畅选无忧</td>
              </tr>
            </table>
            <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
              <tr>
                <td width="72"><img src="images/b4.png" width="62" height="62" /></td>
                <td><h2>准时送达</h2>收货时间由你做主</td>
              </tr>
            </table>
        </div>
    </div>
    <div class="b_nav">
    	<dl>                                                                                            
        	<dt><a href="#">新手上路</a></dt>
            <dd><a href="#">售后流程</a></dd>
            <dd><a href="#">购物流程</a></dd>
            <dd><a href="#">订购方式</a></dd>
            <dd><a href="#">隐私声明</a></dd>
            <dd><a href="#">推荐分享说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">配送与支付</a></dt>
            <dd><a href="#">货到付款区域</a></dd>
            <dd><a href="#">配送支付查询</a></dd>
            <dd><a href="#">支付方式说明</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">会员中心</a></dt>
            <dd><a href="#">资金管理</a></dd>
            <dd><a href="#">我的收藏</a></dd>
            <dd><a href="#">我的订单</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">服务保证</a></dt>
            <dd><a href="#">退换货原则</a></dd>
            <dd><a href="#">售后服务保证</a></dd>
            <dd><a href="#">产品质量保证</a></dd>
        </dl>
        <dl>
        	<dt><a href="#">联系我们</a></dt>
            <dd><a href="#">网站故障报告</a></dd>
            <dd><a href="#">购物咨询</a></dd>
            <dd><a href="#">投诉与建议</a></dd>
        </dl>
        <div class="b_tel_bg">
        	<a href="#" class="b_sh1">新浪微博</a>            
        	<a href="#" class="b_sh2">腾讯微博</a>
            <p>
            服务热线：<br />
            <span>400-123-4567</span>
            </p>
        </div>
        <div class="b_er">
            <div class="b_er_c"><img src="images/er.gif" width="118" height="118" /></div>
            <img src="images/ss.png" />
        </div>
    </div>
    <div class="btmbg">
		<div class="btm">
        	备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
            <img src="images/b_1.gif" width="98" height="33" /><img src="images/b_2.gif" width="98" height="33" /><img src="images/b_3.gif" width="98" height="33" /><img src="images/b_4.gif" width="98" height="33" /><img src="images/b_5.gif" width="98" height="33" /><img src="images/b_6.gif" width="98" height="33" />
        </div>    	
    </div>
    <!--End Footer End -->    
</div>

</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>


<div class="top">
    <div class="logo"><a href="${ctx}/Home?action=index"><img src="${ctx}/statics/images/logo.png"></a></div>
    <div class="search">
        <form>
            <input txype="text" value="" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn">
        </form>
        <span class="fl">
            <a href="javascript:void(0)">咖啡</a>
            <a href="javascript:void(0)">iphone 6S</a>
            <a href="javascript:void(0)">新鲜美食</a>
            <a href="javascript:void(0)">蛋糕</a>
            <a href="javascript:void(0)">日用品</a>
            <a href="javascript:void(0)">连衣裙</a>
        </span>
    </div>
    <div class="i_car">
        <div class="car_t">购物车 [ <span>3</span> ]</div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
            <c:if test="${sessionScope.loginUser==null}">
                <div class="un_login">还未登录！<a href="${ctx}/Login?action=toLogin" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:if test="${sessionScope.cart2==null || sessionScope.cart2.items.size()<1}"> 您尚未购买任何物品，是否进入<a href="${ctx}/Home?action=index">商品页</a>进行购买！</c:if>
                <c:if test="${sessionScope.cart2.items.size()>=1}">
                    <li>
                        <div class="img">
                            <a href="javascript:void(0)">
                                <img src="${ctx}/statics/images/car1.jpg" width="58" height="58">
                            </a>
                        </div>
                        <div class="name">
                            <a href="javascript:void(0)">法颂浪漫梦境50ML 香水女士持久清新淡香 送2ML小样3只</a>
                        </div>
                        <div class="price">
                            <font color="#ff4e00">￥399</font>X1
                        </div>
                    </li>
                </c:if>
            </ul>
            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>1058</span></div>
            <div class="price_a"><a href="javascript:void(0)">去购物车结算</a></div>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>