package com.example;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

public class MainView extends MainViewDesign{
		
	private Navigator navigator;
	
	public MainView() {
		
		navigator = new Navigator(UI.getCurrent(), scroll_panel);
		//navigator.addProvider(springViewProvider);
		
		navigator.addView(ViewsConst.DASHBOARD_VIEW, DashboardView.class);
		navigator.addView(ViewsConst.ORDER_VIEW, OrderView.class);
		
		if(navigator.getState().isEmpty()) {
			navigator.navigateTo(ViewsConst.DASHBOARD_VIEW);
		} else {
			navigator.navigateTo(navigator.getState());
		}
		
		menuButton1.addClickListener(event -> doNavigate(ViewsConst.DASHBOARD_VIEW));
		menuButton2.addClickListener(event -> doNavigate(ViewsConst.ORDER_VIEW));
		
	}
	
	private void doNavigate(String viewName) {
		UI.getCurrent().getNavigator().navigateTo(viewName);
	}
}
