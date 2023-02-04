package com.shop.deviceshopserver.session;

import com.shop.deviceshopserver.data.Acquisto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record Status(List<Acquisto> acquisti, int quantitaTotale, double prezzoTotale, Instant timestamp) implements Serializable {
}
