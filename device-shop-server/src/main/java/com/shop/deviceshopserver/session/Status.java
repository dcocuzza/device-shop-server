package com.shop.deviceshopserver.session;

import com.shop.deviceshopserver.data.Acquisto;

import java.util.List;

public record Status(List<Acquisto> acquisti, int quantitaTotale, double prezzoTotale) {
}
