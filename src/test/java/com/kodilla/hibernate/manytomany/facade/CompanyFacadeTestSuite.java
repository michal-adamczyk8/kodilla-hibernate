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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyFacadeTestSuite {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CompanyFacade companyFacade;

    @Test
    public void testCompanyFacade() {
        //Given
        Employee employee1 = new Employee("Krzysztof", "Olejniczak");
        Employee employee2 = new Employee("Jan", "Kowalski");
        Employee employee3 = new Employee("Tony", "Soprano");
        Company company1 = new Company("Facebook");
        Company company2 = new Company("Mercer");
        Company company3 = new Company("Adidas");
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);
        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);
        //When
        List<Employee> retrievedEmployees = companyFacade.lookForEmployees("lejnicz");
        List<Company> retrievedCompanies = companyFacade.lookForCompanies("dida");
        String nameOfRetrievedEmployee = retrievedEmployees.get(0).getLastName();
        String nameOfRetrievedCompany = retrievedCompanies.get(0).getName();

        //Then
        Assert.assertEquals(1, retrievedCompanies.size());
        Assert.assertEquals("Olejniczak", nameOfRetrievedEmployee);
        Assert.assertEquals(1, retrievedEmployees.size());
        Assert.assertEquals("Adidas", nameOfRetrievedCompany);
        employeeDao.delete(employee1);
        companyDao.delete(company3);
    }
}
