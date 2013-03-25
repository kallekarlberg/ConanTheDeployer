var rootURL = "http://localhost:8383/ConanTheDeployer/deployedApps";

initTabs();
draw();
findAllApps();

$(document).on('click','#appTable a', function() {
	var id = this.id;
	$.getJSON(rootURL+'?id='+id,renderRelease);
});

function draw() {
	$('#qRelease').hide()
}

function findAllApps() {
	console.log('findAll');
	$.getJSON(rootURL,renderList);
}

function renderRelease(app) {
	var a = app.deployedApp[0];
	console.log('releaseId: ' + a.id);
	$('#relAppId').val(a.id);
	$('#relAppName').val(a.name);
	$('#relAppVer').val(a.version);
	$('#qRelease').show()
}

function releaseApp() {
	
	alert($('#relAppName').val() +':'+$('#relAppVer').val()+ ' released');
}

function zebraRows(selector, className) {
	$(selector).removeClass(className).addClass(className);
}

$(document).ready(function() {
	zebraRows('tr:odd td', 'odd');
	
	$('tbody tr').hover(function(){
	  $(this).find('td').addClass('hovered');
	}, function(){
	  $(this).find('td').removeClass('hovered');
	});
	
	//default each row to visible
	$('tbody tr').addClass('visible');
	
	$('#search').show();
	
	$('#filter').keyup(function(event) {
		//if esc is pressed or nothing is entered
    	if (event.keyCode == 27 || $(this).val() == '') {
			//if esc is pressed we want to clear the value of search box
			$(this).val('');
			
			//we want each row to be visible because if nothing is entered then all rows are matched.
      		$('tbody tr').removeClass('visible').show().addClass('visible');
    	}

		else {
     		filter('tbody tr', $(this).val());
    	}

		//reapply zebra rows
		$('.visible td').removeClass('odd');
			zebraRows('.visible:even td', 'odd');
		});
	
		//grab all header rows
		$('thead th').each(function(column) {
			$(this).addClass('sortable').click(function() {
				var findSortKey = function($cell) {
				return $cell.find('.sort-key').text().toUpperCase() + ' ' + $cell.text().toUpperCase();
			};
		var sortDirection = $(this).is('.sorted-asc') ? -1 : 1;
						
		//step back up the tree and get the rows with data for sorting
		var $rows = $(this).parent().parent().parent().find('tbody tr').get();
						
		//loop through all the rows and find 
		$.each($rows, function(index, row) {
			row.sortKey = findSortKey($(row).children('td').eq(column));
		});
						
		//compare and sort the rows alphabetically
		$rows.sort(function(a, b) {
			if (a.sortKey < b.sortKey) return -sortDirection;
			if (a.sortKey > b.sortKey) return sortDirection;
			return 0;
		});
						
		//add the rows in the correct order to the bottom of the table
		$.each($rows, function(index, row) {
			$('tbody').append(row);
			row.sortKey = null;
		});
						
		//identify the column sort order
		$('th').removeClass('sorted-asc sorted-desc');
		var $sortHead = $('th').filter(':nth-child(' + (column + 1) + ')');
		sortDirection == 1 ? $sortHead.addClass('sorted-asc') : $sortHead.addClass('sorted-desc');
						
		//identify the column to be sorted by
		$('td').removeClass('sorted').filter(':nth-child(' + (column + 1) + ')').addClass('sorted');
						
		$('.visible td').removeClass('odd');
			zebraRows('.visible:even td', 'odd');
		});
	});
});

function filter(selector, query) {
	query = $.trim(query); 
 	query = query.replace(/ /gi, '|'); //add OR for regex
  
	$(selector).each(function() {
    	($(this).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
  	});
}

function renderList(data) {
	var list = data.deployedApp;
	$('#appTable').remove("tr:gt(0)");
	$.each(list, function(index, app) {
		$('#appTable').append('<tr><td>'+ app.when+'</td><td>'+app.name+'</td><td>'+ app.version+'</td><td>'+ app.host+'</td><td>'+ app.environment+'</td><td>'+ app.who+'</td><td><a href="#release" id="'+app.id+'">Q-release</a></td></tr>');
	});
}

function initTabs() {
	console.log('init tabs...');
	$('.tabtab').tabTab();
}
