package com.shop.deviceshopserver.api;

import com.shop.deviceshopserver.data.Acquisto;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.service.DeviceService;
import com.shop.deviceshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{id}")
    public List<Acquisto> getCarrello(@PathVariable("id") String id) {
            return deviceService.getCarrello(id);
    }

    @PostMapping(path = "/{id}")
    public String aggiungiCarrello(@PathVariable("id") String id, @RequestBody String disp){
        return deviceService.aggiungiCarrello(id, disp);
    }

    @GetMapping(path = "/find/{key}")
    public List<Device> cercaDispositivi(@PathVariable("key") String key){
        return deviceService.cercaDispositivi(key);
    }

}
