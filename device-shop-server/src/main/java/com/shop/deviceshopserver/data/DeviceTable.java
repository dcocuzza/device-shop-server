package com.shop.deviceshopserver.data;

import java.util.ArrayList;
import java.util.List;

public class DeviceTable {
    private final List<Device> dispositivi = new ArrayList<>();

    public DeviceTable(){
        dispositivi.add(new Device("Intel-Core-i7-8550U", "Processore", "Portatile", 50));
        dispositivi.add(new Device("8-GB-SDRAM-DDR4-2400", "RAM", "Portatile", 35));
        dispositivi.add(new Device("NVIDIA-GeForce-MX130", "Scheda Video", "Portatile", 65));
        dispositivi.add(new Device("Logitech-K120-Tastiera-Cablata", "Tastiera", "Tutto", 65));
        dispositivi.add(new Device("HP-M24f", "Schermo", "Tutto", 65));
        dispositivi.add(new Device("MSI-GeForce-GTX-1660", "Scheda Video", "Fisso", 120));
        dispositivi.add(new Device("Amd-Ryzen-7-5700G-8-Core", "Processore", "Fisso", 200));
        dispositivi.add(new Device("Crucial-RAM-8GB-DDR4-2400MHz", "RAM", "Fisso", 26));
    }

    public Device getDevice(String nome){
        return dispositivi.stream().filter(a -> a.nome().equals(nome)).findAny().orElse(null);
    }

    public List<Device> getList(String key){
        return dispositivi.stream().filter(a -> a.nome().indexOf(key) > -1).toList();
    }
    public List<Device> getAllDevice(){
        return dispositivi;
    }
}
