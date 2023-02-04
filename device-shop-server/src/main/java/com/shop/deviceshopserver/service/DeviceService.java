package com.shop.deviceshopserver.service;

import com.shop.deviceshopserver.api.DeviceController;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.data.DeviceTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private DeviceTable deviceTable = new DeviceTable();

    public List<Device> getAllDevice(){
        return deviceTable.getAllDevice();
    }

}
