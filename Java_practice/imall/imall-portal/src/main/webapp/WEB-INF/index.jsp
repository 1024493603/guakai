<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>天天生鲜-首页</title>
	<%@ include file="header.jsp"%>
	<link rel="stylesheet" type="text/css" href="/static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/static/js/slide.js"></script>
</head>
<body>
	<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎来到天天生鲜!</div>
			<div class="fr">
				<div class="login_info fl">
					欢迎您：<em>张 山</em>
				</div>
				<div class="login_btn fl">
					<a href="login.html">登录</a>
					<span>|</span>
					<a href="register.html">注册</a>
				</div>
				<div class="user_link fl">
					<span>|</span>
					<a href="user_center_info.html">用户中心</a>
					<span>|</span>
					<a href="cart.html">我的购物车</a>
					<span>|</span>
					<a href="user_center_order.html">我的订单</a>
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="index.jsp" class="logo fl"><img src="/static/images/logo.png"></a>
		<div class="search_con fl">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
		<div class="guest_cart fr">
			<a href="/cart/getCartListPage" class="cart_name fl">我的购物车</a>
			<div class="goods_count fl" id="show_count">1</div>
		</div>
	</div>

	<div class="navbar_con">
		<div class="navbar">
			<h1 class="fl">全部商品分类</h1>
			<ul class="navlist fl">
				<li><a href="">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<div class="center_con clearfix">
		<ul class="subnav fl">
			<c:forEach items="${topCategoryList}" var="topCategory">	<%--遍历一级分类--%>
				${topCategory.name}<br/>
				<c:forEach items="${secondCategoryList}" var="secondCategory">	<%--遍历二级分类--%>
					<c:if test="${secondCategory.parentId==topCategory.id}">
						<a href="/product/getProductListPage?id=${secondCategory.id}">${secondCategory.name}</a>
					</c:if>
				</c:forEach>
				<hr/>
			</c:forEach>
		</ul>
		<div class="slide fl">
			<ul class="slide_pics">
				<li><img src="/static/images/slide.jpg" alt="幻灯片"></li>
				<li><img src="/static/images/slide02.jpg" alt="幻灯片"></li>
				<li><img src="/static/images/slide03.jpg" alt="幻灯片"></li>
				<li><img src="/static/images/slide04.jpg" alt="幻灯片"></li>
			</ul>
			<div class="prev"></div>
			<div class="next"></div>
			<ul class="points"></ul>
		</div>
		<div class="adv fl">
			<a href="#"><img src="/static/images/adv01.jpg"></a>
			<a href="#"><img src="/static/images/adv02.jpg"></a>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model01">新鲜水果</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">鲜芒</a>
				<a href="#">加州提子</a>
				<a href="#">亚马逊牛油果</a>
			</div>
			<a href="#" class="goods_more fr" id="fruit_more">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner01.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">草莓</a></h4>
					<a href="#"><img src="/static/images/goods/goods003.jpg"></a>
					<div class="prize">¥ 30.00</div>
				</li>
				<li>
					<h4><a href="#">葡萄</a></h4>
					<a href="#"><img src="/static/images/goods/goods002.jpg"></a>
					<div class="prize">¥ 5.50</div>
				</li>
				<li>
					<h4><a href="#">柠檬</a></h4>
					<a href="#"><img src="/static/images/goods/goods001.jpg"></a>
					<div class="prize">¥ 3.90</div>
				</li>
				<li>
					<h4><a href="#">奇异果</a></h4>
					<a href="#"><img src="/static/images/goods/goods012.jpg"></a>
					<div class="prize">¥ 25.80</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model02">海鲜水产</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">河虾</a>
				<a href="#">扇贝</a>				
			</div>
			<a href="#" class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner02.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">青岛野生海捕大青虾</a></h4>
					<a href="#"><img src="/static/images/goods/goods018.jpg"></a>
					<div class="prize">¥ 48.00</div>
				</li>
				<li>
					<h4><a href="#">扇贝</a></h4>
					<a href="#"><img src="/static/images/goods/goods019.jpg"></a>
					<div class="prize">¥ 46.00</div>
				</li>
				<li>
					<h4><a href="#">冷冻秋刀鱼</a></h4>
					<a href="#"><img src="/static/images/goods/goods020.jpg"></a>
					<div class="prize">¥ 19.00</div>
				</li>
				<li>
					<h4><a href="#">基围虾</a></h4>
					<a href="#"><img src="/static/images/goods/goods021.jpg"></a>
					<div class="prize">¥ 25.00</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model03">猪牛羊肉</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">鲜芒</a>
				<a href="#">加州提子</a>
				<a href="#">亚马逊牛油果</a>
			</div>
			<a href="#" class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner03.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model04">禽类蛋品</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">鲜芒</a>
				<a href="#">加州提子</a>
				<a href="#">亚马逊牛油果</a>
			</div>
			<a href="#" class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner04.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model05">新鲜蔬菜</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">鲜芒</a>
				<a href="#">加州提子</a>
				<a href="#">亚马逊牛油果</a>
			</div>
			<a href="#" class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner05.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="list_model">
		<div class="list_title clearfix">
			<h3 class="fl" id="model06">速冻食品</h3>
			<div class="subtitle fl">
				<span>|</span>
				<a href="#">鲜芒</a>
				<a href="#">加州提子</a>
				<a href="#">亚马逊牛油果</a>
			</div>
			<a href="#" class="goods_more fr">查看更多 ></a>
		</div>

		<div class="goods_con clearfix">
			<div class="goods_banner fl"><img src="/static/images/banner06.jpg"></div>
			<ul class="goods_list fl">
				<li>
					<h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
				<li>
					<h4><a href="#">维多利亚葡萄</a></h4>
					<a href="#"><img src="/static/images/goods.jpg"></a>
					<div class="prize">¥ 38.00</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="footer">
		<div class="foot_link">
			<a href="#">关于我们</a>
			<span>|</span>
			<a href="#">联系我们</a>
			<span>|</span>
			<a href="#">招聘人才</a>
			<span>|</span>
			<a href="#">友情链接</a>		
		</div>
		<p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>
	<script type="text/javascript" src="js/slideshow.js"></script>
	<script type="text/javascript">
		BCSlideshow('focuspic');
		var oFruit = document.getElementById('fruit_more');
		var oShownum = document.getElementById('show_count');

		var hasorder = localStorage.getItem('order_finish');

		if(hasorder)
		{
			oShownum.innerHTML = '2';
		}

		oFruit.onclick = function(){
			window.location.href = 'list.html';
		}
	</script>
</body>
</html>