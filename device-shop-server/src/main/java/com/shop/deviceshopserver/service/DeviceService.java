package com.shop.deviceshopserver.service;

import com.shop.deviceshopserver.api.DeviceController;
import com.shop.deviceshopserver.data.Acquisto;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.data.DeviceTable;
import com.shop.deviceshopserver.session.DiskSer;
import com.shop.deviceshopserver.session.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public List<Acquisto> getCarrello(String id){
        return getSession(id).acquisti();
    }


    public String aggiungiCarrello(String id, String disp){
        Status status = getSession(id);
        Device device = deviceTable.getDevice(disp);
        if(device != null){
            Optional<Acquisto> controllo = status.acquisti().stream().filter(a -> !a.compatibilita().equals(device.compatibilita()) && !device.compatibilita().equals("Tutto") && !a.compatibilita().equals("Tutto")).findAny();

            if (controllo.isPresent())
                return "Ci sono dispositivi incompatibili";

            status.acquisti().add(new Acquisto(device.nome(), device.compatibilita(), device.prezzo()));
            return "Dispositivo: " + disp + " aggiunto al carrello";
        }

        return "Dispositivo non presente";

    }

    public List<Device> cercaDispositivi(String key){
        return deviceTable.getList(key);
    }

    private Status getSession(String id){
        Status s = sessions.get(id);
        if(s != null)
            return refresh(id, s);
        dropSessions();
        s = diskSer.read(id);
        if(s != null)
            return refresh(id, s);
        s = new Status(new ArrayList<>(), Instant.now());
        sessions.put(id, s);
        return s;
    }

    public void checkout(String id){
        getSession(id).acquisti().clear();
    }

    private void dropSessions(){
        List<Entry<String, Status>> expired = sessions.entrySet().stream().filter(e -> isExpired(e.getValue())).map(e -> saveToDisk(e)).toList();
        if(expired.size() > 0)
            sessions.entrySet().removeAll(expired);
    }
    private Status refresh(String id, Status s){
        Status refreshed = new Status(s.acquisti(), Instant.now());
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
