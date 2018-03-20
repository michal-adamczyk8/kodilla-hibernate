package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    InvoiceDao invoiceDao;

    @Test
    public void testInvoiceDaoSave() {
        //Given
        Product product1 = new Product("Milk");
        Product product2 = new Product("Eggs");
        Item item1 = new Item(product1, new BigDecimal(12), 20, new BigDecimal(240));
        Item item2 = new Item(product1, new BigDecimal(12), 1, new BigDecimal(12));
        Item item3 = new Item(product2, new BigDecimal(4), 100, new BigDecimal(400));
        Item item4 = new Item(product2, new BigDecimal(4), 1000, new BigDecimal(4000));
        Invoice invoice = new Invoice("1");
        invoice.getItems().add(item1);
        invoice.getItems().add(item2);
        invoice.getItems().add(item3);
        invoice.getItems().add(item4);

        //When
        invoiceDao.save(invoice);
        int size = invoice.getItems().size();
        int id = invoice.getId();

        //Then
        Assert.assertNotEquals(0, size);

        //CleanUp
        invoiceDao.delete(id);

    }

}
