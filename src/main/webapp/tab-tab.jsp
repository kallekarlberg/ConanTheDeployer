<%@ include file="include.jsp" %>

<html>
<head>
<meta charset="utf-8" />
<title>Conan</title>
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Cabin|Cardo" />
<link rel="stylesheet" href="static/css/tab-tab.css" />
<link rel="stylesheet" href="static/css/simple.css" />

</head>

<body>

<article class="tabtab pear flex">

<nav>
	<ul>
		<li><a href="#appTab">Apps</a></li>
		<li><a href="#hostTab">Hosts</a></li>
		<li><a href="#bundleTab">Bundles</a></li>
	</ul>
</nav>


<section id="appTab">
	<div class="appListContainer">
	   <header>
	      <h2>Deployed apps</h2>
	      <input id="app_search_filter" placeholder="Filter...">
	   </header>
	   <div id="appsSection">
			<ul id="deployedApps"></ul>
	   </div>
	   <div id="appDetails">
	   		<h5>Details</h5>
	   </div>
	</div>
</section>

<section id="hostTab">
	<div class="container">
	   <header>
	      <h2>Hosts
	   		<input type="image" src="img/add.png" alt="add_host" width="20" height="20">
	      </h2>
	      <input id="host_search_filter" placeholder="Filter...">
	   </header>
	   <div id="hostSection">
			<ul id="allHosts"></ul>
	   </div>
	   <div id="addHost">
	   </div>
	</div>
</section>

<section id="bundleTab">
	<div class="container">
	   <header>
	      <h2>Release bundles</h2>
	   </header>
	</div>
</section>

<div class="footer">
	<p>Logged is as: '<shiro:user><shiro:principal/></shiro:user>' 
		<a href="<c:url value="/logout"/>">
			<img src="img/logout.png" alt="logout" width="20" height="20">
		</a>
	</p>
</div>

</article>


<script src="js/jquery-1.9.1-min.js"></script>
<script src="js/flexie.js"></script>
<script src="js/jquery.tab-tab.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery-fastLiveFilter-1.0.3.js"></script>
<script type="text/javascript" src="http://use.typekit.com/oya4cmx.js"></script>
<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

<script>

$(function() {
	$('#app_search_filter').fastLiveFilter('#deployedApps');
});

$(function() {
	$('#host_search_filter').fastLiveFilter('#allHosts');
});

</script>


</body>
</html>