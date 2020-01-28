package com.juleq.transactions.backend.controller

import com.juleq.transactions.backend.entity.Transaction
import com.juleq.transactions.backend.service.TransactionsService
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpStatus
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.time.LocalDateTime

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@RunWith(MockitoJUnitRunner.class)
class TransactionsControllerTest {

    @Mock
    private TransactionsService service

    @InjectMocks
    private TransactionsController controller

    @BeforeMethod
    void init() {
        initMocks(this);
    }

    @Test
    void 'check correct call of get transactions'() {
        doNothing().when(service).saveTransactions(anyString())
        doNothing().when(service).deleteAll()
        when(service.getTransactions()).thenReturn([new Transaction()])
        when(service.getOrderId(anyString(), any(LocalDateTime.class))).thenReturn("orderId")

        def result = controller.getTransactions("line\n")

        assert result.getStatusCode() == HttpStatus.OK
        assert result.getBody() != null

        InOrder order = inOrder(service);
        order.verify(service).saveTransactions(anyString())
        order.verify(service).getTransactions()
        order.verify(service).getOrderId(anyString(), any(LocalDateTime.class))
        order.verify(service).deleteAll()
    }
}