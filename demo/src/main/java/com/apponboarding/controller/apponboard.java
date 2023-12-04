package com.apponboarding.controller;


import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.HttpsURLConnection;
import org.json.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class apponboard {

	public String accessToken;
	public String CT;
	public String CN;
	private static final String pasword="V@run95?";
	
	@GetMapping("/ui")
	
	public String accesstoken() throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	{      
		  
	      //Creating KeyPair generator object
	      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	      
	      //Initializing the key pair generator
	      keyPairGen.initialize(2048);
	      
	      //Generate the pair of keys
	      KeyPair pair = keyPairGen.generateKeyPair();   
	      
	      //Getting the public key from the key pair
	      PublicKey publicKey = pair.getPublic();  

	      //Creating a Cipher object
	      Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

	      //Initializing a Cipher object
	      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		  
	      //Add data to the cipher
	      byte[] input = pasword.getBytes();	  
	      cipher.update(input);
		  
	      //encrypting the data
	      byte[] cipherText = cipher.doFinal();	 
	   //   System.out.println("Original Password"+new String (cipherText));
	      
	      //Initializing the same cipher for decryption
	      cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
	      
	      //Decrypting the text
	      byte[] decipheredText = cipher.doFinal(cipherText);
	      String decyrptpassword=new String(decipheredText);
     //   System.out.println("Original Password"+decyrptpassword);

   	URL url = new URL("https://securdi-partner.saviyntcloud.com/ECM/api/login");
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json");
      String body = "{\"username\":\"vambrale\",\"password\":\""+decyrptpassword+"\"}";  
      DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
      outputStream.writeBytes(body);
      outputStream.flush();
      outputStream.close();
      //get status code like 200/404/401  etc  
      int responseCode = connection.getResponseCode();
      System.out.println("Response code for Access Token Generation : " + responseCode);
      // to read the access token complete body
      InputStream inputStream = connection.getInputStream();
      Scanner scanner = new Scanner(inputStream);
      String responseBody = scanner.useDelimiter("\\A").next();
      System.out.println("Response body: " + responseBody);
      //to read only the access token from the body
      JSONObject jsonObject = new JSONObject(responseBody);
      accessToken = jsonObject.getString("access_token");
      System.out.println("Access Token: " + accessToken);
                         
      // Close the connection
      scanner.close();
      connection.disconnect();
      
      
      
      return "ui";
}
	
	@PostMapping("/RESTSTART")
	
	public String restconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
	{
		CN=ConnectionName;
		CT = ConnectionType;
		System.out.println("Connection Type: "+CT);
		System.out.println("Connection Name: "+CN);		
		return "RESTSTART";
	
	}
	
	 @PostMapping("/DBSTART")
		
		public String dbconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
		{
			CN=ConnectionName;
			CT = ConnectionType;
			System.out.println("Connection Type: "+CT);
			System.out.println("Connection Name: "+CN);		
			return "DBSTART";
		
		}
	 
	 @PostMapping("/SALESTART")
		
		public String salesforceconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
		{
			CN=ConnectionName;
			CT = ConnectionType;
			System.out.println("Connection Type: "+CT);
			System.out.println("Connection Name: "+CN);		
			return "SALESTART";
		
		}
	 
	 @PostMapping("/SNOWSTART")
		
		public String servicenowconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
		{
			CN=ConnectionName;
			CT = ConnectionType;
			System.out.println("Connection Type: "+CT);
			System.out.println("Connection Name: "+CN);		
			return "SNOWSTART";
		
		}
	 
	 @PostMapping("/ADSTART")
		
		public String adconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
		{
			CN=ConnectionName;
			CT = ConnectionType;
			System.out.println("Connection Type: "+CT);
			System.out.println("Connection Name: "+CN);		
			return "ADSTART";
		
		}
	 
	 @PostMapping("/AWSTART")
		
		public String awsconnector(ModelMap model, @RequestParam String ConnectionType, @RequestParam String ConnectionName)throws IOException
		{
			CN=ConnectionName;
			CT = ConnectionType;
			System.out.println("Connection Type: "+CT);
			System.out.println("Connection Name: "+CN);		
			return "AWSTART";
		
		}


 @PostMapping("/RESTEND")
 
  public String restjson1(ModelMap model, @RequestParam String url, @RequestParam String cid, @RequestParam String cis, @RequestParam String atoken, @RequestParam String rtoken,  @RequestParam String uname, @RequestParam String pwd, @RequestParam String dname)throws IOException
	{
	 if(CT.contains("REST")) {
	    System.out.println("Connector is: REST");			
	 	System.out.println("Requested URL: "+url);
	 	System.out.println("Requested Client ID: "+cid);
	 	System.out.println("Requested Client Secret: "+cis);
	 	System.out.println("Requested Access Token: "+atoken);
	 	System.out.println("Requested Refresh Token: "+rtoken);
		String cjson1 = "{\"authentications\":{\"userAuth\":{\"authType\":\"oauth2\",\"url\":\""+url+"/services/oauth2/token?grant_type=refresh_token&client_id="+cid+"&client_secret="+cis+"&refresh_token="+rtoken+"\",\"httpMethod\":\"POST\",\"httpParams\":{},\"httpHeaders\":{\"contentType\":\"application/json\"},\"httpContentType\":\"application/json\",\"expiryError\":\"INVALID_SESSION_ID\",\"authError\":[\"INVALID_SESSION_ID\"],\"timeOutError\":\"Readtimedout\",\"retryFailureStatusCode\":[401,403],\"errorPath\":\"errorCode\",\"maxRefreshTryCount\":5,\"tokenResponsePath\":\"access_token\",\"tokenType\":\"Bearer\",\"accessToken\":\"Bearer "+atoken+"\"}}}";
		System.out.println("Requested JSON: "+cjson1);
		
		URL url1 = new URL("https://securdi-partner.saviyntcloud.com/ECM/api/v5/testConnection");
	      Map<String,Object> param = new LinkedHashMap<>();
	        param.put("connectiontype", CT);
	        param.put("connectionName", CN );
	        param.put("ConnectionJSON", cjson1);
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> para : param.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(para.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(para.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        HttpsURLConnection connection1 = (HttpsURLConnection) url1.openConnection();
	        connection1.setRequestMethod("POST");
	        connection1.setDoOutput(true);
	        connection1.setRequestProperty("Authorization","Bearer " + accessToken);
	        connection1.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	        connection1.setRequestProperty( "charset", "utf-8");
	        connection1.setUseCaches( true );
	        connection1.getOutputStream().write(postDataBytes);
	        
	        int responseCode2 = connection1.getResponseCode();
	        System.out.println("Connection Creation Response : " + responseCode2); 
	        
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	        	              	
	           response.append(inputLine);
	        }
	        System.out.println("Details of Connection: " +response );
	        in.close();
	        connection1.disconnect();
	        
	        model.put("errorMsg", response);
	 }	
	        else
	   	 {
	   		 model.put("errorMsg", "invalid input");
	   	 }
	   	return "RESTEND";
	}
 

@PostMapping("/DBEND")


public String dbjson2(ModelMap model, @RequestParam String url,  @RequestParam String uname, @RequestParam String pwd, @RequestParam String dname)throws IOException
{

 
	 if(CT.contains("DB")) {
		 System.out.println("Connector is: DATABASE");
		 URL url2 = new URL("https://securdi-partner.saviyntcloud.com/ECM/api/v5/testConnection");
	     Map<String,Object> param2 = new LinkedHashMap<>();
	       param2.put("connectiontype", CT);
	       param2.put("connectionName", CN );
	       param2.put("USERNAME", uname);
	       param2.put("PASSWORD", pwd);
	       param2.put("DRIVERNAME", dname);
	       param2.put("URL", url);
	       StringBuilder postData2 = new StringBuilder();
	       for (Map.Entry<String,Object> para2 : param2.entrySet()) {
	           if (postData2.length() != 0) postData2.append('&');
	           postData2.append(URLEncoder.encode(para2.getKey(), "UTF-8"));
	           postData2.append('=');
	           postData2.append(URLEncoder.encode(String.valueOf(para2.getValue()), "UTF-8"));
	       }
	       byte[] postDataBytes2 = postData2.toString().getBytes("UTF-8");
	       HttpsURLConnection connection2 = (HttpsURLConnection) url2.openConnection();
	       connection2.setRequestMethod("POST");
	       connection2.setDoOutput(true);
	       connection2.setRequestProperty("Authorization","Bearer " + accessToken);
	       connection2.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	       connection2.setRequestProperty( "charset", "utf-8");
	       connection2.setUseCaches( true );
	       connection2.getOutputStream().write(postDataBytes2);
	       
	       int responseCode2 = connection2.getResponseCode();
	       System.out.println("Connection Creation Response : " + responseCode2); 
	       
	       
	       BufferedReader in = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
	       String inputLine;
	       StringBuilder response2 = new StringBuilder();
	       while ((inputLine = in.readLine()) != null) {
	       	              	
	          response2.append(inputLine);
	       }
	       System.out.println("Details of Connection: " +response2 );
	       in.close();
	       connection2.disconnect();

	       model.put("errorMsg", response2);
	       }
	 
	 else
	 {
		 model.put("errorMsg", "invalid input");
	 }
	return "DBEND";
	}	
 
@PostMapping("/SALEND")

public String saleforcejson1(ModelMap model, @RequestParam String obj_imp, @RequestParam String r_uri,  @RequestParam String i_url, @RequestParam String cid, @RequestParam String cis, @RequestParam String rtoken)throws IOException
	{
	 if(CT.contains("SalesForce")) {
	    System.out.println("Connector is: SALESFORCE");			
	 	System.out.println("Requested Client ID: "+cid);
	 	System.out.println("Requested Client Secret: "+cis);
	 	System.out.println("Requested Refresh Token: "+rtoken);
	 	System.out.println("Requested Redirect URI: "+r_uri);
	 	System.out.println("Requested Instance URL: "+i_url);
	 	System.out.println("Requested Objects: "+obj_imp);
		
		URL url1 = new URL("https://securdi-partner.saviyntcloud.com/ECM/api/v5/testConnection");
	      Map<String,Object> param = new LinkedHashMap<>();
	        param.put("connectiontype", CT);
	        param.put("connectionName", CN );
	        param.put("CLIENT_ID", cid);
	        param.put("CLIENT_SECRET", cis);
	        param.put("REFRESH_TOKEN", rtoken);
	        param.put("REDIRECT_URI", r_uri);
	        param.put("INSTANCE_URL", i_url);
	        param.put("OBJECT_TO_BE_IMPORTED",obj_imp);
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> para : param.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(para.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(para.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        HttpsURLConnection connection1 = (HttpsURLConnection) url1.openConnection();
	        connection1.setRequestMethod("POST");
	        connection1.setDoOutput(true);
	        connection1.setRequestProperty("Authorization","Bearer " + accessToken);
	        connection1.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	        connection1.setRequestProperty( "charset", "utf-8");
	        connection1.setUseCaches( true );
	        connection1.getOutputStream().write(postDataBytes);
	        
	        int responseCode2 = connection1.getResponseCode();
	        System.out.println("Connection Creation Response : " + responseCode2); 
	        
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	        	              	
	           response.append(inputLine);
	        }
	        System.out.println("Details of Connection: " +response );
	        in.close();
	        connection1.disconnect();
	        
	        model.put("errorMsg", response);
	 }	
	        else
	   	 {
	   		 model.put("errorMsg", "invalid input");
	   	 }
	   	return "SALEND";
	}

@PostMapping("/AWSEND")

public String awsjson1(ModelMap model, @RequestParam String acid, @RequestParam String email, @RequestParam String cara, @RequestParam String e_id, @RequestParam String gov) throws IOException
{
	if(CT.contains("AWS")) {
	    System.out.println("Connector is: AWS");			
	 	System.out.println("Requested AWS ACCOUNT ID: "+acid);
	 	System.out.println("Requested ADMIN EMAIL: "+email);
	 	System.out.println("Requested CROSS ACCOUNT ROLE ARN: "+cara);
	 	System.out.println("Requested EXTERNAL ID: "+e_id);
	 	System.out.println("Requested PULL GOV REGION ONLY: "+gov);
		
		URL url1 = new URL("https://securdi-partner.saviyntcloud.com/ECM/api/v5/testConnection");
	      Map<String,Object> param = new LinkedHashMap<>();
	        param.put("connectiontype", CT);
	        param.put("connectionName", CN );
	        param.put("AWS_ACCOUNT_ID", acid);
	        param.put("ADMIN_EMAIL", email);
	        param.put("CROSS_ACCOUNT_ROLE_ARN", cara);
	        param.put("EXTERNAL ID", e_id);
	        param.put("PULL_GOV_REGION_ONLY", gov);
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String,Object> para : param.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(para.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(para.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	        HttpsURLConnection connection1 = (HttpsURLConnection) url1.openConnection();
	        connection1.setRequestMethod("POST");
	        connection1.setDoOutput(true);
	        connection1.setRequestProperty("Authorization","Bearer " + accessToken);
	        connection1.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
	        connection1.setRequestProperty( "charset", "utf-8");
	        connection1.setUseCaches( true );
	        connection1.getOutputStream().write(postDataBytes);
	        
	        int responseCode2 = connection1.getResponseCode();
	        System.out.println("Connection Creation Response : " + responseCode2); 
	        
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
	        String inputLine;
	        StringBuilder response = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	        	              	
	           response.append(inputLine);
	        }
	        System.out.println("Details of Connection: " +response );
	        in.close();
	        connection1.disconnect();
	        
	        model.put("errorMsg", response);
	 }	
	        else
	   	 {
	   		 model.put("errorMsg", "invalid input");
	   	 }
	   	return "AWSEND";
	}
}


