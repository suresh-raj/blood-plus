package com.blood.plus;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
public class SpinnerAdapter {

	public static ArrayAdapter<String> adapter;
	public static ArrayAdapter<String> adapter_city;
	public static String[] cities;
	private String[] populate;

	Context context;
	public String[] zones={"Tamilnadu"};
	public String[] Tamilnadu={"Madurai",
			"Dindigulg",
			"Trichy",
			"Coimbatore",
			"Chennai",
			"Selam",
			"Erode",
			"tanjore"};
	public String[] Bheri={"Banke",
			"Bardiya",
			"Dailekh",
			"Jajarkot",
			"Surkhet",

};
	public String[] Dhawalagiri={"Baglung",
			"Mustang",
			"Myagdi",
			"Parbat",
};
	public String[] Gandaki={"Gorkha",
			"Kaski",
			"Lamjung",
			"Manang",
			"Syangja",
			"Tanahu"};
	public String[] Janakpur={"Dhanusa",
			"Dolakha",
			"Mohattari",
			"Ramechhap",
			"Sarlahi",
			"Sindhuli"};
	public String[] Karnali={"Dolpa",
			"Humla",
			"Jumla",
			"Kalikot",
			"Mugu"};
	public String[] Kosi={"Bhojpur",
			"Dhankuta",
			"Morang",
			"Sankhuwasabha",
			"Sunsari",
			"Terhathum"
};
	public String[] Lumbini={"Arghakhanchi",
			"Gulmi",
			"Kapilvastu",
			"Nawalparasi",
			"Palpa",
			"Rupandehi",
};
	public String[] Mahakali={"Baitadi",
			"Dadheldhura",
			"Darchula",
			"Kanchanpur",

};
	public String[] Mechi={"Ilam",
			"Jhapa",
			"Panchthar",
			"Taplejung"};
	public String[] Narayani={"Bara",
			"Chitawan",
			"Makwanpur",
			"Parsa",
			"Rautahat"};
	public String[] Rapti={"Dang",
			"Pyuthan",
			"Rolpa",
			"Rukum",
			"Salyan"
};
	public String[] Sagarmatha={"Khotang",
			"Okhaldhunga",
			"Saptari",
			"Siraha",
			"Solukhumbu",
			"Udayapur"
};
	public String[] Seti={"Achham",
			"Bajhang",
			"Bajura",
			"Doti",
			"Kailali",
};
	
	public String[] groups={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
	
	
	
	
	
	
	public SpinnerAdapter(Context c) {
		// TODO Auto-generated constructor stub
		this.context=c;
		
	}
	
	
	public String getValue(int id,int position)
	{
		String string = "bla..bla";
		
		switch(id)
		{
		case R.id.bloodSpinner:
			string=groups[position];
			break;
		case R.id.zoneSpinner:
			string=zones[position];
			break;	
	/*	case R.id.citySpinner:
			string=districts[position];
			break;*/
		}
		return string;
		
		
	}
	
	public ArrayAdapter getAdapter(int id)
	{
		switch(id)
		{
		case R.id.bloodSpinner:
			adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, groups);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;
		case R.id.zoneSpinner:
			adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, zones);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			break;	
	/*	case R.id.citySpinner:
			adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, districts);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;*/
		default:
			adapter=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, new String[]{" "});
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			break;
			
		}
		
		return adapter;
	}
	
	public ArrayAdapter getAdapterFor(int pos)
	{
		
		
		switch(pos)
		{
		case 0:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Tamilnadu );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
				break;
		case 1:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Bheri );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
				break;
		case 2:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Dhawalagiri );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
				
		case 3:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Gandaki );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 4:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Janakpur );
 			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 5:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Karnali);
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 6:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Kosi );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 7:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Lumbini );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 8:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Mahakali );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 9:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Mechi);
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 10:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Narayani);
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 11:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Rapti);
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 12:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,Sagarmatha );
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		case 13:
			adapter_city=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Seti);
			adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				break;
		}
		
		
		
		
		return adapter_city;
		
	}
	
	
	
}


