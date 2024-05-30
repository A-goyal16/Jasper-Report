package org.paycorp.JasperReport.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.paycorp.JasperReport.dto.Address;
import org.paycorp.JasperReport.repository.AddressRepository;
import org.paycorp.JasperReport.service.JReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;


@RestController
public class SpringBootJReportApp {
 
    @Autowired
    private AddressRepository repository;
    @Autowired
    private JReportService service;
 
    @GetMapping("/getAddress")
    public List<Address> getAddress() {
        List<Address> address = (List<Address>) repository.findAll();
        return address;
    }
    
    @PostMapping("/saveaddress")
    public Address saveAddress(@RequestBody Address address)
    {
    	return repository.save(address);
    }
         
    @PostMapping("/jasperpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
       response.setContentType("application/pdf");
        service.exportJasperReport(response);
    }
    
    @PostMapping("/jasperxls/export")
    public void createXLS(HttpServletResponse response) throws JRException, IOException
    {
    	response.setContentType("application/vnd.ms-excel");
    	service.exportJasperReportt(response);
    }
}
 