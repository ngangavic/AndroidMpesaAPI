# Android Mpesa Daraja API library



Project setup 
1. add this in build.gradle   
     ```gradle
     allprojects  { 
		  repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		 }
  	   } 
       
  2.  add the dependency
         ```gradle
      dependencies {
	        implementation 'com.github.ngangavic:AndroidMpesaAPI:v1.0'
	     }
  
  3. add the internet permission 
      ```xml
       <uses-permission android:name="android.permission.INTERNET"/>
       
  4. enable strict mode
      ```java
      int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
  
  5. set the environment
      ```java
        Settings.setBusiness_short_code("174379"); //business code
        Settings.setCallback_url("http://www.yourcallbackurl.com"); //callback url
        Settings.setConsumer_key("AL4cs1jYio03B97Bvri5SWaPsQ1upawY"); //your consumer key
        Settings.setConsumer_secret("tIO5wyY43Gobzt6C"); // your consumer secret
        Settings.setPhone("254708374149"); //phone number
        Settings.setStk_push_url("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest");
        Settings.setTimeout_url("http://timeout url"); //your timeout url
        Settings.setTransaction_desc("testing my api"); //transaction description
        Settings.setTransaction_type("CustomerPayBillOnline"); //let this remain
        Settings.setAmount("1"); //enter amount
        Settings.setAccess_token_url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials");
        Settings.setAccount_reference("vic10020"); //account refrence
        Settings.setPasskey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"); //passkey
        
6. call the verification method
    ```java
     if (verification().equals("0")){
         Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
     }else{
         Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
     }
