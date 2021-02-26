<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>    
                
	<script type="text/javascript" src="js/n_nav.js"></script>   
    
    <script type="text/javascript" src="js/num.js">
    	var jq = jQuery.noConflict();
    </script>     
    
    <script type="text/javascript" src="js/shade.js"></script>
    
<title>易买网（购物车）</title>
</head>
<body>  
<!--Begin Header Begin-->
<div class="soubg">
	<c:if test="${empty loginUser }">
		<div class="sou">
	        <span class="fr">
	        	<span class="fl">你好，请<a href="Login.jsp">登录</a>&nbsp; <a href="Regist.jsp" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
	            <span class="fl">&nbsp;关注我们：</span>
	            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
	            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
	        </span>
    	</div>
	</c:if>
	<c:if test="${not empty loginUser }">
		<c:if test="${loginUser.type==1 }">
			<div class="sou">
		        <span class="fr">
		        	<span class="fl">你好，&nbsp;<span style="color:red">${loginUser.userName }</span>欢迎您！</a>&nbsp;|&nbsp;<a href="admin.jsp" style="color:#ff4e00;">后台管理</a>&nbsp;|&nbsp;<a href="UserServlet?opr=logout" style="color:#ff4e00;">注销</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
		            <span class="fl">&nbsp;关注我们：</span>
		            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
		            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
		        </span>
    		</div>
		</c:if>
		<c:if test="${loginUser.type==0 }">
			<div class="sou">
		        <span class="fr">
		        	<span class="fl">你好，&nbsp;<span style="color:red">${loginUser.userName }</span>欢迎您！</a>&nbsp;|&nbsp;<a href="UserServlet?opr=logout" style="color:#ff4e00;">注销</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|</span>       	
		            <span class="fl">&nbsp;关注我们：</span>
		            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
		            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
		        </span>
	    	</div>
    	</c:if>
	</c:if>
</div>
<div class="top">
    <div class="logo"><a href="Index.jsp"><img src="images/logo.png" /></a></div>
    <div class="search">
    	<form>
        	<input type="text" value="" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
    	<div class="car_t">购物车 [ <span>${car.size }</span>]</div>
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
       		<c:if test="${empty loginUser }">
        		<div class="un_login">还未登录！<a href="Login.jsp" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <c:if test="${not empty loginUser }">
            	<c:forEach items="${car.items }" var="items">
            		<ul class="cars">
            			<li>
                			<div class="img"><a href="#"><img src="images/${items.product.fileName }" width="58" height="58" /></a></div>
                    		<div class="name"><a href="#">${items.product.name }</a></div>
                   	 		<div class="price"><font color="#ff4e00">￥${items.product.price }</font> X${items.num }</div>
               	 		</li>
            	</ul>
            	</c:forEach>
            	<div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>${car.totalCost}</span></div>
            <div class="price_a"><a href="BuyCar.jsp">去购物车结算</a></div>
            <!--End 购物车已登录 End-->
            </c:if>            
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
            <div class="leftNav none">
                <ul>      
                    <c:forEach items="${categoryVo }" var="categoryVo">      
                    	<li>
		                    <div class="fj">
	                        	<span class="n_img"><span></span><img src="images/${categoryVo.category.iconClass }" /></span>
	                            <span class="fl">${categoryVo.category.name }</span>
	                        </div>    	                            
	                            <div class="zj">
			                        <div class="zj_l">
			                        <c:forEach items="${categoryVo.categoryListVo }" var="categoryVo2">
			                             <div class="zj_l_c">
			                                 <h2>${categoryVo2.category.name }</h2>
			                                 <c:forEach items="${categoryVo2.categoryListVo }" var="categoryVo3">
			                                     <a href="#">${categoryVo3.category.name }</a>|
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
        	<li><a href="Index.jsp">首页</a></li>
            <li><a href="Food.jsp">美食</a></li>
            <li><a href="Fresh.jsp">生鲜</a></li>
            <li><a href="HomeDecoration.jsp">家居</a></li>
            <li><a href="SuitDress.jsp">女装</a></li>
            <li><a href="MakeUp.jsp">美妆</a></li>
            <li><a href="Digital.jsp">数码</a></li>
            <li><a href="GroupBuying.jsp">团购</a></li>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
<!--End Menu End--> 
<div class="i_bg">  
    <div class="content mar_20">
    	<img src="images/img1.jpg" />        
    </div>
    
    <!--Begin 第一步：查看购物车 Begin -->
    <div class="content mar_20">
    	<table border="0" class="car_tab" style="width:1200px; margin-bottom:50px;" cellspacing="0" cellpadding="0">
          <tr>
            <td class="car_th" width="490">商品名称</td>
            <td class="car_th" width="140">单价</td>
            <td class="car_th" width="150">购买数量</td>
            <td class="car_th" width="130">小计</td>
            <td class="car_th" width="140">库存</td>
            <td class="car_th" width="150">操作</td>
          </tr>
          <c:forEach items="${car.items }" var="item">
	          	<tr class="car_tr">
	            <td>
	            	<div class="c_s_img"><img src="images/${item.product.fileName }" width="73" height="73" /></div>
	                ${item.product.name }
	            </td>
	            <td align="center">${item.product.price }</td>
	            <td align="center">
	            	<div class="c_num">
	            		<input type="hidden" value="${item.product.id }" class="car_id">
	                    <input type="button" value="" onclick="jianUpdate1(jq(this));" class="car_btn_1" />
	                	<input type="text" value="${item.num }" name="" class="car_ipt" />  
	                    <input type="button" value="" onclick="addUpdate1(jq(this));" class="car_btn_2" />
	                </div>
	            </td>
	            <td align="center" style="color:#ff4e00;" class="pro_cost">￥${item.cost }</td>
	            <td align="center">${item.product.stock }</td>
	            <td align="center"><a href="ProductServlet?opr=removeItem&proId=${item.product.id }">删除</a></td>
	          </tr>
         </c:forEach>       
          
         <tr height="70">
          	<td colspan="6" style="font-family:'Microsoft YaHei'; border-bottom:0;">
            	<label class="r_rad"><input type="checkbox" name="clear" checked="checked" /></label><label class="r_txt">清空购物车</label>
                <span class="fr">商品总价：<b style="font-size:22px; color:#ff4e00;">￥${car.totalCost}</b></span>
            </td>
         </tr>
         <tr valign="top" height="150">
          	<td colspan="6" align="right">
            	<a href="Index.jsp"><img src="images/buy1.gif" /></a>&nbsp; &nbsp; 
            	<a href="AddressServlet?opr=BuyCar_2"><img src="images/buy2.gif" /></a>
            </td>
          </tr>
        </table>
        
    </div>
	<!--End 第一步：查看购物车 End--> 
    
    
    <!--Begin 弹出层-删除商品 Begin-->
    <div id="fade" class="black_overlay"></div>
    <div id="MyDiv" class="white_content">             
        <div class="white_d">
            <div class="notice_t">
                <span class="fr" style="margin-top:10px; cursor:pointer;" onclick="CloseDiv('MyDiv','fade')"><img src="images/close.gif" /></span>
            </div>
            <div class="notice_c">
           		
                <table border="0" align="center" style="font-size:16px;" cellspacing="0" cellpadding="0">
                  <tr valign="top">
                    <td>您确定要把该商品移除购物车吗？</td>
                  </tr>
                  <tr height="50" valign="bottom">
                    <td><a href="#" class="b_sure">确定</a><a href="#" class="b_buy">取消</a></td>
                  </tr>
                </table>
                    
            </div>
        </div>
    </div>    
    <!--End 弹出层-删除商品 End-->
    
    
    <!--Begin Footer Begin -->
    <div class="b_btm_bg bg_color">
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
