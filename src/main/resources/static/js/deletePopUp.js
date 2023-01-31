//This function is called when the page is loaded
window.onload=function(){
	
	//Gets all buttons into an HtmlCollection on the screen with the name of delete
	let buttonList = document.getElementsByClassName('delete');
	//alert("The number of delete buttons is: " + buttonList.length);	
	//for loop through all buttons in list
	let i = 0;
	for (i = 0; i < buttonList.length; i++) {
		var self = buttonList[i];
		//add event listener on click
		self.addEventListener('click', function (e) {
			//alert("e.currentTarget.value: " + e.currentTarget.value);
			//alert("self value before click is: " + self.value)
			//Actual popup
		    if (window.confirm('Are you sure you want to delete?')) {
				//alert("Clicked yes");
				//Go through with the delete, nothing changes here
		    } else {	
				//alert("Clicked no");
				//Don't go through with the delete
				//change the value that is sent to the controller to -1 to be caught and ignored 
				e.currentTarget.value = -1;
				
		    }
		    //alert("self value after click is: " + self.value);
		    			
		});
	}
}