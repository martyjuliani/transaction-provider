package com.juleq.transactions.backend.controller

import com.juleq.transactions.backend.converter.TransactionsConverter
import com.juleq.transactions.backend.model.TransactionsModel
import com.juleq.transactions.backend.service.TransactionsService
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpStatus
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.ArgumentMatchers.anyList
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@RunWith(MockitoJUnitRunner.class)
class TransactionsControllerTest {

    @Mock
    private TransactionsService service
    @Mock
    private TransactionsConverter converter

    @InjectMocks
    private TransactionsController controller

    @BeforeMethod
    void init() {
        initMocks(this);
    }

    @Test
    void 'check correct call of get transactions'() {
        doNothing().when(service).saveTransactions(anyString())
        when(service.getTransactions()).thenReturn(mock(List.class))
        when(converter.toModel(anyList())).thenReturn(mock(TransactionsModel.class))

        def result = controller.getTransactions("someFileLocation.txt")

        assert result.getStatusCode() == HttpStatus.OK
        assert result.getBody() != null

        InOrder order = inOrder(service, converter);
        order.verify(service).saveTransactions(anyString())
        order.verify(service).getTransactions()
        order.verify(converter).toModel(anyList())
    }
}