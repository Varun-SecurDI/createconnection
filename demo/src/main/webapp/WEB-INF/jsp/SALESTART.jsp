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
        color: 0F3444;   
        padding: 10px;    
        margin-left: 50px;  
        border: none;   
        cursor: pointer;   
         }   
 form {   
        border: 3px;
        color: FFFFFF;
        margin-left: -100px;   
    }   
 button:hover {   
        opacity: solid;   
    }
      
 
 label {
        display: inline-block;
        width: 150px; 
        color: FFFFFF;
 }
 
 .infoBox{
        display: inline-block;
        position: absolute;
        bottom: 10px;
        left: 50px;
        font-size: 16px;
        font-family: Calibri, Helvetica, sans-serif;
 }
 
 .contactBox{
        display: inline-block;
        position: absolute;
        bottom: 10px;
        right: 50px;
        font-size: 16px;
        font-family: Calibri, Helvetica, sans-serif;
 
 }
  
</style>
</head>
<img src = "https://securdi.com/wp-content/uploads/2021/03/securdilogo3.png.webp" width= "130"  height= "40">
<body background = "https://securdi.com/wp-content/uploads/2020/09/site-background.jpg" >

<body>
<form method ="post">
    <div align="center">
<br><br><br><br><br>
      <h1>CONNECTION JSON</h1>

      <br><br>
      <label for="cid">Client ID</label>
      <input type ="text" name="cid" placeholder="Input Client ID" required/>
      <br><br>
      <label for="cis">Client Secret</label>
      <input type ="password" name="cis" placeholder="Input Client Secret" required/>
      <br><br>
      <label for="rtoken">Refresh Token</label>
      <input type ="password" name="rtoken" placeholder="Input Refresh Token" required/>
      <br><br>
      <label for="r_uri">Redirect_Uri</label>
      <input type ="text" name="r_uri" placeholder="Input Redirect URL" required/>
      <br><br>
      <label for="i_url">Instance Url</label>
      <input type ="text" name="i_url" placeholder="Input Instance URL" required/>
      <br><br>
      <label for="obj_imp">Object Imported</label>
      <input type ="text" name="obj_imp" placeholder="Input Object to be Imported" required/>
      <br><br>
      

      <button type="Submit" formaction ="/SALEND">Submit</button>
      <h2> ${errorMsg}</h2><br><br>
</div>

<div class = "infoBox" >
<img src = "https://securdi.com/wp-content/uploads/2021/03/securdilogo3.png.webp" width= "60"  height= "20">
<h4>SecurDI is a well rounded Cybersecurity solutions<br> provider with services ranging from Advisory,<br> Implementation to Managed services. 
With presence <br> across US, Canada and India, we provide round the <br> clock coverage , with easy access to a 100% certified <br> talent pool. </h4>
</div>
<div class ="contactBox" >
<h3>Feel free to contact us at <br><a href = "https://securdi.com/" target= "_blank" style = "color:0F0F0F"> securdi.com</a> </h3>
<h5>Copyright 2023� Securdi LLP. All rights Reserved.</h5>
</div>

</form>


</body>
</html>