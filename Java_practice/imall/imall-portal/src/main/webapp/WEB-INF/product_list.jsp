<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="header.jsp"%>
	<title>天天生鲜-商品列表</title>
	<link rel="stylesheet" type="text/css" href="/static/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
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
		<a href="index.html" class="logo fl"><img src="/static/images/logo.png"></a>
		<div class="search_con fl">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
		<div class="guest_cart fr">
			<a href="#" class="cart_name fl">我的购物车</a>
			<div class="goods_count fl">1</div>
		</div>
	</div>

	<div class="navbar_con">
		<div class="navbar clearfix">
			<div class="subnav_con fl">
				<h1>全部商品分类</h1>	
				<span></span>			
				<ul class="subnav">
					<li><a href="#" class="fruit">新鲜水果</a></li>
					<li><a href="#" class="seafood">海鲜水产</a></li>
					<li><a href="#" class="meet">猪牛羊肉</a></li>
					<li><a href="#" class="egg">禽类蛋品</a></li>
					<li><a href="#" class="vegetables">新鲜蔬菜</a></li>
					<li><a href="#" class="ice">速冻食品</a></li>
				</ul>
			</div>
			<ul class="navlist fl">
				<li><a href="">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">手机生鲜</a></li>
				<li class="interval">|</li>
				<li><a href="">抽奖</a></li>
			</ul>
		</div>
	</div>

	<div class="breadcrumb">
		<a href="#">全部分类</a>
		<span>></span>
		<a href="#">新鲜水果</a>
	</div>

	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_goods">
				<h3>新品推荐</h3>
				<ul>
					<li>
						<a href="#"><img src="/static/images/goods/goods001.jpg"></a>
						<h4><a href="#">进口柠檬</a></h4>
						<div class="prize">￥3.90</div>
					</li>
					<li>
						<a href="#"><img src="/static/images/goods/goods002.jpg"></a>
						<h4><a href="#">玫瑰香葡萄</a></h4>
						<div class="prize">￥16.80</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<div class="sort_bar">
				<a href="#" class="active">默认</a>
				<a href="#">价格</a>
				<a href="#">人气</a>
			</div>

			<ul class="goods_type_list clearfix">
				<c:forEach items="${list}" var="product">
					<li>
						<a href="/product/getProductDetailPage?id=${product.id}">
							<img src="${product.mainImageUrl}"></a>
						<h4>${product.name}</h4>
						<div class="operate">
							<span class="prize">￥${product.price}</span>
							<span class="unit">${product.stock}</span>
							<a href="#" class="add_goods" title="加入购物车"></a>
						</div>
					</li>
				</c:forEach>

			</ul>

			<div class="pagenation">
				<a href="#"><上一页</a>
				<a href="#" class="active">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
				<a href="#">下一页></a>
			</div>
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
	
</body>
</html>