package com.shop.deviceshopserver.data;

import java.io.Serializable;

public record Acquisto(String nome, String compatibilita, double prezzo) implements Serializable {
}
