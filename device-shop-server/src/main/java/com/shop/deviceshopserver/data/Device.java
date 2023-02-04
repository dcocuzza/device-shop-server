package com.shop.deviceshopserver.data;

import java.io.Serializable;

public record Device(String nome, String tipologia, String compatibilita, double prezzo) implements Serializable {
}
