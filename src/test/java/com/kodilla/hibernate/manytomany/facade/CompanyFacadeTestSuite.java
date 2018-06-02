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
    public void shouldRetrieveEmployeeWithPieceOfName() {
        Employee employee1 = new Employee("Krzysztof", "Olejniczak");
        Employee employee2 = new Employee("Jan", "Kowalski");
        Employee employee3 = new Employee("Tony", "Soprano");
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);

        //When
        List<Employee> retrievedEmployees = companyFacade.lookForEmployees("lejnicz");
        String nameOfRetrievedEmployee = retrievedEmployees.get(0).getLastName();

        //Then
        Assert.assertEquals(1, retrievedEmployees.size());
        Assert.assertEquals("Olejniczak", nameOfRetrievedEmployee);

        //Clean Up
        employeeDao.delete(employee1);
    }

    @Test
    public void shouldRetrieveCompaniesWithNameLike() {
        //Given
        Company company1 = new Company("Facebook");
        Company company2 = new Company("Mercer");
        Company company3 = new Company("Adidas");
        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);

        //When
        List<Company> retrievedCompanies = companyFacade.lookForCompanies("dida");
        String nameOfRetrievedCompany = retrievedCompanies.get(0).getName();

        //Then
        Assert.assertEquals(1, retrievedCompanies.size());
        Assert.assertEquals("Adidas", nameOfRetrievedCompany);

        //Clean Up
        companyDao.delete(company3);
    }

    @Test
    public void shouldRetrieveNoEmployees() {
        //Given
        Employee employee1 = new Employee("Krzysztof", "Wawrzyniak");
        Employee employee2 = new Employee("Jan", "Kowalski");
        Employee employee3 = new Employee("Tony", "Soprano");
        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);

        //When
        List<Employee> retrievedEmployees = companyFacade.lookForEmployees("lejnicz");

        //Then
        Assert.assertNotEquals(1, retrievedEmployees.size());
    }

    @Test
    public void shouldRetrieveNoCompanies() {
        //Given
        Company company1 = new Company("Facebook");
        Company company2 = new Company("Mercer");
        Company company3 = new Company("Nike");
        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);

        //When
        List<Company> retrievedCompanies = companyFacade.lookForCompanies("dida");

        //Then
        Assert.assertNotEquals(1, retrievedCompanies.size());
    }
}
