package com.shop.deviceshopserver.service;

import com.shop.deviceshopserver.api.DeviceController;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.data.DeviceTable;
import com.shop.deviceshopserver.session.DiskSer;
import com.shop.deviceshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class DeviceService {

    private final DeviceTable deviceTable = new DeviceTable();
    private final DiskSer diskSer = new DiskSer();
    private final Map<String, Status> sessions = new HashMap<>();
    private final int exipireTime = 200;

    public List<Device> getAllDevice(){
        return deviceTable.getAllDevice();
    }





    private void dropSessions(){
        List<Entry<String, Status>> expired = sessions.entrySet().stream().filter(e -> isExpired(e.getValue())).map(e -> saveToDisk(e)).toList();
        if(expired.size() > 0)
            sessions.entrySet().removeAll(expired);
    }
    private Status refresh(String id, Status s){
        Status refreshed = new Status(s.acquisti(), s.quantitaTotale(), s.prezzoTotale(), Instant.now());
        sessions.put(id, refreshed);
        return refreshed;
    }
    private boolean isExpired(Status s){
        return s.timestamp().plus(exipireTime, ChronoUnit.MILLIS).isBefore(Instant.now());
    }
    private Entry<String, Status> saveToDisk(Entry<String, Status> e){
        diskSer.write(e.getKey(), e.getValue());
        return e;
    }

}
