package com.blood.plus;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DonarAdapter extends BaseAdapter {
	LayoutInflater inflater;
	List<node> donars;
	Context context;
	
	public DonarAdapter(Context context,List<node> donars) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.donars=donars;
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return donars.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return donars.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View ConvertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView line1 = null,line2 = null,line3 = null,line4=null,line5=null,line6=null;
		View v=inflater.inflate(R.layout.list_element,null);
		
			line1 =(TextView) v.findViewById(R.id.textView1);
			line2 =(TextView) v.findViewById(R.id.textView2);
			line3 =(TextView) v.findViewById(R.id.textView3);
			line4 =(TextView) v.findViewById(R.id.textView6);
			line5 =(TextView) v.findViewById(R.id.textView8);
			line6 =(TextView) v.findViewById(R.id.textView9);
			
		line1.setText(donars.get(position).getGroup());
		line2.setText(donars.get(position).getCity());
		line3.setText(donars.get(position).getName());
		line4.setText(donars.get(position).getPhone());	
		line5.setText(donars.get(position).getPlace());	
		line6.setText(String.valueOf(String.format("%.2f",donars.get(position).getDistance())));	
		return v;
	}

}
