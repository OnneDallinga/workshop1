package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.view.View;

public class ProductController extends Controller {
	
	public ProductController(View theView, Model theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
	}

	@Override
	public void runView() {
		// TODO Auto-generated method stub

	}

}
