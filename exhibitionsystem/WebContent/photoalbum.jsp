<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">

<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SVG路径动画 | SVG特效| 网页特效库</title>
	<meta name="keywords" content="SVG特效, SVG路径动画, css3动画, 网页特效" />
	<meta name="description" content="Card Expansion Effect with SVG clipPath" />
	<meta name="keywords" content="clipPath, svg, effect, layout, expansion, images, grid, polygon" />
	<meta name="author" content="Claudio Calautti for Codrops" />
	<link rel="shortcut icon" href="favicon.ico">
	<link rel="stylesheet" type="text/css" href="css/photoalbum/normalize.css" />
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="css/photoalbum/demo.css" />
	<link rel="stylesheet" type="text/css" href="css/photoalbum/card.css" />
	<link rel="stylesheet" type="text/css" href="css/photoalbum/pattern.css" />

	<script>
	if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
		var root = document.getElementsByTagName('html')[0];
		root.setAttribute('class', 'ff');
	};
	</script>
</head>
<body class="demo-1">
	<div class="container">
		<div class="content" style="">
			<!-- trianglify pattern container -->
			<div class="pattern pattern--hidden"></div>
			<!-- cards -->
			<div class="wrapper">
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="card">
					<div class="card__container card__container--closed">
						<svg class="card__image" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 1920 500" preserveAspectRatio="xMidYMid slice">
							<defs>
								<clipPath id="clipPath1">
									<!-- r = 992 = hyp = Math.sqrt(960*960+250*250) -->
									<circle class="clip" cx="960" cy="250" r="992"></circle>
								</clipPath>
							</defs>
							<image clip-path="url(#clipPath1)" width="1920" height="500" xlink:href="images/exhibition/a.jpg"></image>
						</svg>
						<div class="card__content">
							<i class="card__btn-close fa fa-times"></i>
							<div class="card__caption">
								<h2 class="card__title">Tristan and Isolde</h2>
								<p class="card__subtitle">A modern day love story</p>
							</div>
							<div class="card__copy">
								<div class="meta">
									<img class="meta__avatar" src="img/authors/1.png" alt="author01" />
									<span class="meta__author">Gerry Sutherland</span>
									<span class="meta__date">06/19/2015</span>
								</div>
								<p>Business model canvas bootstrapping deployment startup. In A/B testing pivot niche market alpha conversion startup down monetization partnership business-to-consumer success for investor mass market business-to-business.</p>
								<p>Release creative social proof influencer iPad crowdsource gamification learning curve network effects monetization. Gamification business plan mass market www.discoverartisans.com direct mailing ecosystem seed round sales long tail vesting period.</p>
								<p>Product management ramen bootstrapping seed round venture holy grail technology backing partner network entrepreneur beta marketing value proposition. Android stealth conversion scrum project network effects. Creative alpha long tail conversion stealth growth hacking iteration investor A/B testing prototype customer. Startup www.discoverartisans.com direct mailing launch party partnership market ramen metrics focus value proposition.</p>
								<p>Stock infrastructure seed round sales paradigm shift technology user experience focus gamification. Partnership metrics business plan stealth business-to-business. Deployment graphical user interface monetization. Twitter incubator scrum project entrepreneur branding burn rate ramen backing paradigm shift virality crowdsource.</p>
								<p>Social proof MVP ecosystem. Ramen launch party pitch deployment stealth. Vesting period MVP equity. Focus creative partnership founders iteration agile development infographic.</p>
								<p>Low hanging fruit burn rate innovator user experience niche market A/B testing creative launch party product management release. Www.discoverartisans.com influencer business model canvas user experience gamification paradigm shift startup research &amp; development iPad agile development. Strategy incubator infographic success marketing buzz A/B testing responsive web design. Traction research &amp; development pitch seed money venture niche market accelerator network effects.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>		
	
	<!-- /container -->
	<!-- JS -->
	<script src="js/photoalbum/vendors/trianglify.min.js"></script>
	<script src="js/photoalbum/vendors/TweenMax.min.js"></script>
	<script src="js/photoalbum/vendors/ScrollToPlugin.min.js"></script>
	<script src="js/photoalbum/vendors/cash.min.js"></script>
	<script src="js/photoalbum/Card-circle.js"></script>
	<script src="js/photoalbum/demo.js"></script>
</body>

</html>
