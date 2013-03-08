$.getJSON("http://localhost:9001/deploy", function(json) {
    	if (json != "") {
        	('#deployedApps').html('<table class="allApps">
        	<theader>
        		<tr>
        			<td>App</td>
        			<td>Version</td>
        			<td>Host</td>
        		</tr>
        	</theader>
        	<tbody>
        		for (var i=0;i<json.length;i++) {
        			<tr>
        				<td>json[i].name</td>
        				<td>json[i].version</td>
        				<td>json[i].host</td>
        			</tr>
				}
        	</tbody>
        	</table>');
            console.log(json);
     	} else {
     		alert("nothing found...");
        	('#deployedApps').html('<table></table>');
      	}
	});
    
    
    $(document).ready(function() {
	$("#infoText").text("The DOM is now loaded and can be manipulated.");

	$('#deployedApps').html("<h2 class='loading'>Loading...</h2>");
	
	$.ajax({ 
   		type: "GET",
   		dataType: "json",
   		url: "http://localhost:9001/deploy",
   		success: function(data){        
     		alert(data);
	   	}
	});
});