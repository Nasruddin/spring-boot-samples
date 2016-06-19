package starter;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by Nasir on 05-09-2015.
 */
@SpringView(name = "dashboard")
public class DashboardView extends VerticalLayout implements View{

    @PostConstruct
    protected void initialize() {
        addComponent(new Label("Dashboard"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
