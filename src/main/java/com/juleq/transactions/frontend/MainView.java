package com.juleq.transactions.frontend;

import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.service.TransactionsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Route
@Component
@PageTitle("Transaction provider")
@PreserveOnRefresh
public class MainView extends VerticalLayout {
    private final TransactionsService transactionsService;

    public MainView(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;

        H1 heading = new H1("Transaction provider");

        TextArea sourceArea = new TextArea();
        sourceArea.setValueChangeMode(ValueChangeMode.EAGER);
        sourceArea.setMinWidth("600px");
        sourceArea.setMaxWidth("600px");
        sourceArea.setMinHeight("300px");
        sourceArea.setMaxHeight("300px");

        TextArea destinationArea = new TextArea();
        destinationArea.setMinWidth("600px");
        destinationArea.setMaxWidth("600px");
        destinationArea.setMinHeight("300px");
        destinationArea.setMaxHeight("300px");

        Label errorOutput = new Label();
        errorOutput.getStyle().set("color", "red");

        Button button = new Button("Submit");
        button.setMinWidth("100px");
        button.setEnabled(false);

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setMaxWidth("600px");
        controlLayout.add(button);

        registerListeners(sourceArea, destinationArea, errorOutput, button);
        add(heading, sourceArea, controlLayout, destinationArea, errorOutput);

    }

    private String proceedTransactions(String text, Label errorOutput) {
        try {
            String[] lines = text.split("\\r?\\n");
            for (String line: lines) {
                transactionsService.saveTransaction(line);
            }
            String result = toString(transactionsService.getTransactions());
            transactionsService.deleteAll();
            return result;
        } catch (IllegalArgumentException e) {
            errorOutput.setText("Error occurred: " + e.getMessage());
            return "";
        }
    }

    private void registerListeners(TextArea sourceArea, TextArea destinationArea, Label errorOutput, Button button) {
        sourceArea.addValueChangeListener(event -> button.setEnabled(isInputValid(sourceArea)));
        button.addClickListener(event -> {
            errorOutput.setText("");
            String translation = proceedTransactions(sourceArea.getValue(), errorOutput);
            destinationArea.setValue(translation);
        });
    }

    private static boolean isInputValid(TextArea sourceArea) {
        return !sourceArea.getValue().isBlank();
    }

    private String toString(Iterable<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactions) {
            String orderId = transactionsService.getOrderId(t.getPartner(), t.getDateTime());
            sb.append(format("%s|%s|%s%s", t.getPartner(), orderId, t.getName(), System.lineSeparator()));
        }
        return sb.toString();
    }
}

