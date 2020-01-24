package com.juleq.transactions.backend.controller


import com.juleq.transactions.backend.service.TransactionProviderService
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.MockitoAnnotations.initMocks

@RunWith(MockitoJUnitRunner.class)
class TransactionProviderControllerTest {

    @Mock
    private TransactionProviderService service

    @InjectMocks
    private TransactionProviderController controller

    @BeforeMethod
    void init() {
        initMocks(this);
    }

    @Test
    void 'check transaction with correct format'() {
        def location = "transaction.txt"
        def result = controller.getTransactions(location)

        assert result == ["TODO"]
    }
}