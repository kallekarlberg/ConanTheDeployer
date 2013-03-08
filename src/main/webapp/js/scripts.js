var rootURL = "http://localhost:8383/ConanTheDeployer/deployedApps";

initTabs();
findAllApps();

$(document).on('click','#deployedApps a', function() {
	var name = this.id;
	findByName(name);
});

function findAllApps() {
	console.log('findAll');
	$.getJSON(rootURL,renderList);
}

function findByName(name) {
	console.log('findById: ' + name);
	$.getJSON(rootURL+"?appName="+name, renderDetails);
}

function renderList(data) {
	var list = data.deployedApp;
	$('#deployedApps li').remove();
	$.each(list, function(index, app) {
		$('#deployedApps').append('<li><a href="#" id="'+ app.name+'">'+app.name+'</a></li>');
	});
}

function renderDetails(data) {
	var list = data.deployedApp;
	$('#deployedApps li').remove();
	//add header
	$.each(list, function(index, app) {
		$('#deployedApps').append('<li><a href="#" id="'+ app.id+'">'+app.name+'</a></li>');
	});
}

function initTabs() {
	console.log('init tabs...');
	$('.tabtab').tabTab();
}
