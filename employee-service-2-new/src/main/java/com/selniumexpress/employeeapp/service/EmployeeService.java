package com.selniumexpress.employeeapp.service;

import com.selniumexpress.employeeapp.entity.Employee;
import com.selniumexpress.employeeapp.feignClient.AddressClient;
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
private AddressClient addressClient;
    public EmployeeResponse getEmployeeById(int id){
    //   AddressResponse addressResponse=new AddressResponse();
        //converting employee->>EmployeeResponse  given to==>controller
        Employee employee=employeeRepo.findById(id).get();//db call

        EmployeeResponse employeeResponse=modelMapper.map(employee,EmployeeResponse.class);
        AddressResponse addressResponse=addressClient.getAddressByEmployeeId(id);
        //ithe vr apan addresresponse ghetla ani khali set kela
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }
}
