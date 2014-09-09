package com.yahoo.markcwal.tipcalculator;


import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity extends Activity {
	
	private ArrayList<Float> tips;
	private int activeButton = 0;
	private ArrayList<Button> btns;

	private int not_selected = 0xFF1DF0DB;
	private int selected = 0xFFF01D32;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip);
		tips = new ArrayList<Float>();
		tips.add((float) .1);
		tips.add((float).15);
		tips.add((float).2);
		
		
		btns = new ArrayList<Button>();
		btns.add((Button) findViewById(R.id.btnTip1));
		btns.add((Button) findViewById(R.id.btnTip2));
		btns.add((Button) findViewById(R.id.btnTip3));
		
		
		setupEditTextListener();
	}
	
	//Todo: Put Buttons in an array, and then change the color
	//based on which one is active and which ones are not
	
	public void buttonClick(View v)
	{
		if (v.getId() == R.id.btnTip1)
		{
			activeButton = 0;
		} else if (v.getId() == R.id.btnTip2)
		{
			activeButton = 1;
		} else if (v.getId() == R.id.btnTip3)
		{
			activeButton = 2;
		}
		
		for (int i = 0; i < 3; i++)
		{
			if (i == activeButton)
			{
				btns.get(i).setBackgroundColor(selected);
			}
			else
			{
				btns.get(i).setBackgroundColor(not_selected);
			}
		}
		
		calculateTip();
	}
	
	public void setupEditTextListener()
	{
		EditText etNewItem = (EditText) findViewById(R.id.etTotal);
		
		etNewItem.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				calculateTip();
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void calculateTip()
	{
		EditText etNewItem = (EditText) findViewById(R.id.etTotal);
		String newItem = etNewItem.getText().toString();
		
		float totalAmount =  0;
		
		try{
			totalAmount = Float.parseFloat(newItem);
		} catch (Exception e)
		{
			
		}
		
		TextView tvTip = (TextView) findViewById(R.id.tvTip);
		
		String tip = "Tip is: $";
		float tipAmount = tips.get(activeButton);
		
		tip = tip + String.format("%.2f", (float)(tipAmount * totalAmount));
		
		tvTip.setText(tip);
		
	}
}
