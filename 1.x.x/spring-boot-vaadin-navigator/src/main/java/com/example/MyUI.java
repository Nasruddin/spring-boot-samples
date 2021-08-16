package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class MyUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		setContent(new MainView());
		
	}

}
