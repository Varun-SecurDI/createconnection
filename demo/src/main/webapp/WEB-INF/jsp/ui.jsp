<html>
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Application Onboarding</title>
<style>
  Body { 
    font-family: Calibri, Helvetica, sans-serif; 
  } 
  button { 
    background-color: #4CAF50; 
    width: 8%; 
    color: #0F3444; 
    padding: 10px; 
    margin-left: 50px; 
    border: none; 
    cursor: pointer; 
  } 
  form { 
    border: 3px;
    color: #FFFFFF;
    margin-left: -100px; 
  } 
  button:hover { 
    opacity: 0.8; 
  }
 
  label {
    display: inline-block;
    width: 150px; 
    color: #FFFFFF;
  }
 
  .infoBox {
    display: inline-block;
    position: absolute;
    bottom: 10px;
    left: 50px;
    font-size: 16px;
    font-family: Calibri, Helvetica, sans-serif;
  }
 
  .contactBox {
    display: inline-block;
    position: absolute;
    bottom: 10px;
    right: 50px;
    font-size: 16px;
    font-family: Calibri, Helvetica, sans-serif;
  }
</style>
</head>
<img src="https://securdi.com/wp-content/uploads/2021/03/securdilogo3.png.webp" width="130" height="40">
<body background="https://securdi.com/wp-content/uploads/2020/09/site-background.jpg">

<form id="change" method="post">
<div align="center">
<br><br><br><br><br>
 <h1>APPLICATION ONBOARDING</h1>
 <br>
 <label for="connectiontype">ConnectionType</label>
 <select name="ConnectionType" id="connectiontype">
   <option value="REST">REST</option>
   <option value="DB">DATABASE</option>
   <option value="SalesForce">SALESFORCE</option>
   <option value="SERVICENOW">SERVICENOW</option>
   <option value="AD">ACTIVE DIRECTORY On AWS</option>
   <option value="AWS">AWS</option>
 </select>
 <br><br>
 <label for="connectionname">Connection Name</label>
 <input type="text" name="ConnectionName" placeholder="Input Connection Name" required/>
 <button type="submit" onclick="checkAndSubmit()">Generate</button>
 <h2>${errorMsg}</h2><br><br>
</div>

<div class="infoBox">
<img src="https://securdi.com/wp-content/uploads/2021/03/securdilogo3.png.webp" width="60" height="20">
<h4>SecurDI is a well-rounded Cybersecurity solutions<br> provider with services ranging from Advisory,<br> Implementation to Managed services. 
With presence <br> across US, Canada, and India, we provide round-the-clock coverage, with easy access to a 100% certified <br> talent pool. </h4>
</div>
<div class="contactBox">
<h3>Feel free to contact us at <br><a href="https://securdi.com/" target="_blank" style="color:#0F0F0F">securdi.com</a></h3>
<h5>Copyright 2023� Securdi LLP. All rights Reserved.</h5>
</div>

</form>

    <script>
        function checkAndSubmit() {
            // Get the input field and condition checkbox
            var ccbox = document.getElementById("connectiontype").value;

            // Check the condition
            if (ccbox==="REST") {
                // If the condition is met, submit the form
                document.getElementById("change").action="/RESTSTART";
            } 
            else if (ccbox==="DB"){
                document.getElementById("change").action="/DBSTART";
            }
            else if (ccbox==="SalesForce"){
                document.getElementById("change").action="/SALESTART";
            }
            else if (ccbox==="SERVICENOW"){
                document.getElementById("change").action="/SNOWSTART";
            }
            else if (ccbox==="AD"){
                document.getElementById("change").action="/ADSTART";
            }
            else if (ccbox==="AWS"){
                document.getElementById("change").action="/AWSTART";
            }
        }
    </script>

</body>
</html>