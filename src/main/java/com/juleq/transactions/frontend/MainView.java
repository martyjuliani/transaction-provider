package com.juleq.transactions.frontend;

import com.juleq.transactions.backend.service.TransactionsService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route
@Component
@PageTitle("Transaction provider")
@PreserveOnRefresh
public class MainView extends VerticalLayout {

    private final TransactionsService providerService;

    public MainView(TransactionsService providerService) {
        H1 heading = new H1("Transaction provider");

        TextArea sourceArea = new TextArea();
        sourceArea.setValueChangeMode(ValueChangeMode.EAGER);
        sourceArea.setMinWidth("600px");
        sourceArea.setMaxWidth("600px");
        sourceArea.setMinHeight("200px");
        sourceArea.setMaxHeight("200px");

        TextArea destinationArea = new TextArea();
        destinationArea.setMinWidth("600px");
        destinationArea.setMinHeight("200px");
        destinationArea.setMinHeight("200px");
        destinationArea.setMaxHeight("200px");
        this.providerService = providerService;
    }
}

