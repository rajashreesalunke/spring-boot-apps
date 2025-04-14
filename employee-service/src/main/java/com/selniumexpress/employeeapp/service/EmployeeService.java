package com.selniumexpress.employeeapp.service;

import com.selniumexpress.employeeapp.entity.Employee;
import com.selniumexpress.employeeapp.repo.EmployeeRepo;
import com.selniumexpress.employeeapp.response.AddressResponse;
import com.selniumexpress.employeeapp.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;//but need to configure your rest template ,spring wont do it
    @Value("${addressservice.base.url}")// he apan application.properties madhe taklela ahe already,ata inject this in given..
    private String addressBaseURL;
    public EmployeeResponse getEmployeeById(int id){
        //addresResponse=>set data by making a rest api call
       AddressResponse addressResponse=new AddressResponse();
        //converting employee->>EmployeeResponse  given to==>controller
        Employee employee=employeeRepo.findById(id).get();//db call

        EmployeeResponse employeeResponse=modelMapper.map(employee,EmployeeResponse.class);

        addressResponse=restTemplate.getForObject(addressBaseURL+"/address/{id}", AddressResponse.class,id);//external rest api
        //ithe vr apan addresresponse ghetla ani khali set kela
        employeeResponse.setAddressResponse(addressResponse);

        //here instead of manually writing the response/dto we will use modelMapper (dependency lagegi)
//        EmployeeResponse employeeResponse=new EmployeeResponse();
//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setEmail(employee.getEmail());
//        employeeResponse.setBloodGroup(employee.getBloodGroup());
        return employeeResponse;
    }
}
