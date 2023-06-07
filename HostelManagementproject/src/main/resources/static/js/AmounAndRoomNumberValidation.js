
function calculateDateDiffAndFee() 
{
		// Get the date values from the input fields
		
        var d1=document.getElementById("startDate").value;
        var d2=document.getElementById("endDate").value;
		var startDate = new Date(d1);
		var endDate = new Date(d2);
		
		// check whether room number bednumber is matching or not
		
		var roomNumber=document.getElementById("roomNumber").value;
		var bedId=document.getElementById("bedId").value;
		//document.writeln(roomNumber);
        //document.writeln(bedId);
		var temp=(roomNumber*10)+1;
		
		if(endDate>startDate  && bedId>=temp && bedId<=(temp+3))
		{

			// Calculate the difference between the two dates in milliseconds
			var diffInMilliSeconds = endDate.getTime() - startDate.getTime();

			// Convert the difference to days
			var diffInDays = diffInMilliSeconds / (1000 * 60 * 60 * 24);

			// Round the result to the nearest integer
			diffInDays = Math.round(diffInDays);
            document.getElementById("endDate").style.borderColor="white";
            document.getElementById("endDate").style.backgroundColor="white";
            document.getElementById("bedId").style.borderColor="white";
            document.getElementById("bedId").style.backgroundColor="white";
			
			if(roomNumber>=101 && roomNumber<=105)
			{
				var fee=14000;
				var totalAmount=(fee/30)*diffInDays;
				totalAmount=Math.round(totalAmount);
				 document.getElementById("amount").value = totalAmount;
			}
			else if(roomNumber>=201 && roomNumber<=205)
			{
				var fee=10000;
				var totalAmount=(fee/30)*diffInDays;
				totalAmount=Math.round(totalAmount);
				 document.getElementById("amount").value = totalAmount;
			}
			else if(roomNumber>=301 && roomNumber<=305)
			{
				var fee=8000;
				var totalAmount=(fee/30)*diffInDays;
				totalAmount=Math.round(totalAmount);
				 document.getElementById("amount").value = totalAmount;
			}
			else
			{
				var fee=6000;
				var totalAmount=(fee/30)*diffInDays;
				totalAmount=Math.round(totalAmount);
				document.getElementById("amount").value = totalAmount;
			}
			
 
		// Display the result on the webpage
		        
	     }
	     else
	     {
			 document.getElementById("endDate").style.borderColor="red";
             document.getElementById("endDate").style.backgroundColor="red";
			 document.getElementById("bedId").style.borderColor="red";
             document.getElementById("bedId").style.backgroundColor="red";
			 
		 }
	    
	}
	