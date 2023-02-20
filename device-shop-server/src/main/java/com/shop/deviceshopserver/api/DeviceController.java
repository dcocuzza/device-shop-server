package com.shop.deviceshopserver.api;

import com.shop.deviceshopserver.data.Acquisto;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.service.DevService;
import com.shop.deviceshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("device")
@RestController
public class DeviceController {

    @Autowired
    private final DevService devService;

    public DeviceController(DevService devService){
        this.devService = devService;
    }

    @GetMapping
    public List<Device> getAllDevice(){
        return devService.getAllDevice();
    }

    @GetMapping(path = "/{id}")
    public List<Acquisto> getCarrello(@PathVariable("id") String id) {
            return devService.getCarrello(id);
    }

    @PostMapping(path = "/{id}")
    public String aggiungiCarrello(@PathVariable("id") String id, @RequestBody String disp){
        return devService.aggiungiCarrello(id, disp);
    }

    @GetMapping(path = "/find/{key}")
    public List<Device> cercaDispositivi(@PathVariable("key") String key){
        return devService.cercaDispositivi(key);
    }

}
