package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyFacadeTestSuite {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testCompanydFacade() {
        //Given
        Employee employee1 = new Employee("Michał", "Adamczyk");
        Employee employee2 = new Employee("Jan", "Kowalski");
        Employee employee3 = new Employee("Tony", "Soprano");
        Company company1 = new Company("Facebook");
        Company company2 = new Company("Mercer");
        Company company3 = new Company("Kodilla");
        CompanyFacade companyFacade = new CompanyFacade();
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);
        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);
        //When
        int employeeResult = employeeDao.retrieveEmployeesWhoseNamesContains("dam").size();
        int companyResult = companyDao.retrieveCompaniesWhoseNamesContains("book").size();
        //Then
        Assert.assertEquals(1, employeeResult);
        Assert.assertEquals(1, companyResult);
    }
}
