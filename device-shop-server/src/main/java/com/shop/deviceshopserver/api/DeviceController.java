package com.shop.deviceshopserver.api;

import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.service.DeviceService;
import com.shop.deviceshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("device")
@RestController
public class DeviceController {

    @Autowired
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getAllDevice(){
        return deviceService.getAllDevice();
    }

   /* @GetMapping("/status")
    public Status getCarrello() {

    }*/

}
