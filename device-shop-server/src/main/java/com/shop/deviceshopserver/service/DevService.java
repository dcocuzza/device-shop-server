package com.shop.deviceshopserver.service;

import com.shop.deviceshopserver.data.Acquisto;
import com.shop.deviceshopserver.data.Device;
import com.shop.deviceshopserver.data.DeviceTable;
import com.shop.deviceshopserver.http.HttpSender;
import com.shop.deviceshopserver.session.Status;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;

@Service
public class DevService {

    private final DeviceTable deviceTable = new DeviceTable();

    private final HttpSender sender = new HttpSender();


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
            setService(id, status);
            return "Dispositivo: " + disp + " aggiunto al carrello";
        }

        return "Dispositivo non presente";

    }

    public List<Device> cercaDispositivi(String key){
        return deviceTable.getList(key);
    }

    private Status getSession(String id){
        return sender.getSession(id);
    }

    private void setService(String id, Status status){
        sender.setSession(id, status);
    }



}
