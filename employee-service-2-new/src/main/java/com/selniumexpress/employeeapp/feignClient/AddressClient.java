package com.selniumexpress.employeeapp.feignClient;

import com.selniumexpress.employeeapp.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="address-service",url="http://localhost:8081/address-app/api ")
//http://localhost:8080/address-app/api/address/1
public interface AddressClient {//proxy class
@GetMapping("/address/{id}")
AddressResponse getAddressByEmployeeId(@PathVariable("id") int id);//interface has no body
}
//So if you call getAddressByEmployeeId(1), it sends:
//GET http://<SERVICE_URL>/address/1
//And it expects a response body that maps to your AddressResponse class.