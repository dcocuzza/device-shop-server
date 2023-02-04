package com.shop.deviceshopserver.data;

import java.io.Serializable;

public record Acquisto(Device acquisto, int quantita, double prezzo) implements Serializable {
}
