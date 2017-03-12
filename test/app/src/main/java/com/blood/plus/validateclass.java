package com.blood.plus;

public class validateclass {
int error;
String errormsg;
int y,dy;
String name,phone,email,state,city,place,bloodgroup,dob,gender,password,rpassword,message;

public validateclass()
{
}
public validateclass(String phn)
{
	this.phone=phn;
}

 public validateclass(String Sname,String Sphone,String Semail,String Sstate,String Scity,String Splace,String Sbloodgroup,String Sdob,String Sgender,String Spassword,String Srpassword,int year,int dyear)
	{

	 name=Sname;
	 phone=Sphone;
	 email=Semail;
	 state=Sstate;
	 city=Scity;
	 place=Splace;
	 bloodgroup=Sbloodgroup;
	 dob=Sdob;
	 gender=Sgender;
	 password=Spassword;
	 rpassword=Srpassword;
	 y=year;
	 dy=dyear;

	}
 public validateclass(String Sphone,String Sstate,String Scity,String Splace)
 {
	 phone=Sphone;
	 state=Sstate;
	 city=Scity;
	 place=Splace;
 }

 public validateclass(String Sname,String Sphone,String Semail,String Smessage,int a)
 {
  name=Sname;
  email=Semail;
  phone=Sphone;
  message=Smessage;
 }
 
 String valid()
 {
	  boolean b;
	  b=password.equals(rpassword);
	  if(name.length()==0)
		  return("Enter a valid name");
	 if(name.length()<3||name.length()>20)
		return "Enter a valid name"; 
	 if(y-dy<16||y-dy>40)
		 return "To become a donar,your age must be between 16 to 40 ";
	 //else if(bloodgroup.length()>4||bloodgroup.length()<2)
		//  return "Enter a valid blood group";
	 boolean v=true;
	 v=isNumeric(phone);
	 if(phone.length()!=10)
		 v=false;
	
	if(v==false)
	    return "Enter a valid phone number";

	 else if(valid_email()==0)
		 return"Enter a valid email ";
	 //else if(state.length()<3||city.length()<3||place.length()<3||state.length()>20||city.length()>20||place.length()>20)
	   //  return "Enter a valid address";
	
	 else if(!b)	      
	      return "Reenter correct password";
	 else if(password.length()<6)
		 return "The password must contain atleast 6 characters";
	
	    
	return "valid";
		 
 }

 
 String contactValid()
 {
	
	  if(name.length()==0)
		  return("Enter a valid name ");
	 if(name.length()<3||name.length()>20)
		return "Enter a valid name"; 

	 boolean v=true;
	 v=isNumeric(phone);
	 if(phone.length()!=10)
		 v=false;
	
	if(v==false)
	    return "Enter a valid Phone no";

	 else if(valid_email()==0)
		 return"Enter a valid email id";
	 //else if(state.length()<3||city.length()<3||place.length()<3||state.length()>20||city.length()>20||place.length()>20)
	   //  return "Enter a valid address";
	if(message.length()<3||message.length()>100)
		return "Enter a valid feedback"; 
    	
	    
	return "valid";
		 
 }

 
 String updatevalid()
 {
	 boolean v=true;
	 v=isNumeric(phone);
	 if(phone.length()!=10)
		 v=false;
	
	if(v==false)
	    return "Enter a valid phone no";

		// else if(state.length()<3||city.length()<3||place.length()<3||state.length()>20||city.length()>20||place.length()>20)
		  //   return "Enter a valid address";
		 else
	        return "valid";
 }
 
 public static boolean isNumeric(String str)  
 {  
   try  
   {  
     double d = Double.parseDouble(str);  
   }  
   catch(NumberFormatException nfe)  
   {  
     return false;  
   }  
   return true;  
 }
 
 int valid_email()
 {
	 int i=email.indexOf("@");
	 int k=email.lastIndexOf("@");
	 int j=email.lastIndexOf(".");
	 if(email.length()<13||i<2||j<5)
		 return 0;
	 else
	 if(i>j||i!=k)
		 return 0;
	 else if(j+2>=email.length())
		 return 0;
	 else if(j-i<2)
		 return 0;
	 return 1;
	 
	 
 }
 
 
 
 int validemail(String eemail)
 {
	 int i=eemail.indexOf("@");
	 int k=eemail.lastIndexOf("@");
	 int j=eemail.lastIndexOf(".");
	 if(eemail.length()<13||i<2||j<5)
		 return 0;
	 else
	 if(i>j||i!=k)
		 return 0;
	 else if(j+2>=eemail.length())
		 return 0;
	 else if(j-i<2)
		 return 0;
	 return 1;
	 
	 
 } 
 
 
 public boolean phoneValid()
 {
	 boolean v=true;
	 v=isNumeric(phone);
	 if(phone.length()!=10)
		 v=false;
	
	if(v==false)
	    return false;
		 else
	        return true;
	 
	 
 }
 
	
}
