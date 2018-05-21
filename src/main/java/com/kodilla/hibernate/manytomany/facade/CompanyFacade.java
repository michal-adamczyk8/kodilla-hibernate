package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyFacade {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyFacade.class);

    public List<Company> lookForCompanies(String pieceOfCompanyName) {
        LOGGER.info("Searching for companies whose company name contains " + pieceOfCompanyName);
        List<Company> result = companyDao.retrieveCompaniesWhoseNamesContains(pieceOfCompanyName);
        if (result == null) {
            LOGGER.error("There is no company whose names contains " + pieceOfCompanyName);
            return new ArrayList<>();
        }
        LOGGER.info("This is the list of companies whose names contains " + pieceOfCompanyName);
        return result;
    }

    public List<Employee> lookForEmployees(String pieceOfEmployeeName) {
        LOGGER.info("Searching for employees whose last name contains " + pieceOfEmployeeName);
        List<Employee> result = employeeDao.retrieveEmployeesWhoseNamesContains(pieceOfEmployeeName);
        if (result == null) {
            LOGGER.error("There is no employee whose name contains " + pieceOfEmployeeName);
            return new ArrayList<>();
        }
        LOGGER.info("This is the list of the employees whose last name contains " + pieceOfEmployeeName);
        return result;
    }

}
