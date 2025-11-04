package com.vitoriadeveloper.vicdelivery.delivery.tracking.domain.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID courierId;

    private DeliveryStatus status;

    /*
    OffsetDateTime: representa uma data e hora com fuso horário (útil para sistemas distribuídos).
    */
    private OffsetDateTime placedAt; // Data e hora em que o pedido foi realizado
    private OffsetDateTime assignedAt;  // Data e hora em que o pedido foi atribuído a um entregador
    private OffsetDateTime expectedDeliveryAt; // Data e hora previstas para a entrega
    private OffsetDateTime fulfilledAt;  // Data e hora em que a entrega foi concluída

    private BigDecimal distanceFee;  // Valor cobrado com base na distância
    private BigDecimal courierPayout; // Valor pago ao entregador
    private BigDecimal totalCost;   // Custo total da entrega (pedido + taxas)

    private Integer totalItems;

    private ContactPoint sender;
    private ContactPoint recipient;

    private List<Item> items = new ArrayList<>();

    public static Delivery draft() {
        Delivery delivery = new Delivery();
        delivery.id = UUID.randomUUID();
        delivery.status = DeliveryStatus.DRAFT;
        delivery.totalItems = 0;
        delivery.totalCost = BigDecimal.ZERO;
        delivery.courierPayout = BigDecimal.ZERO;
        delivery.distanceFee = BigDecimal.ZERO;
        return delivery;
    }
}
