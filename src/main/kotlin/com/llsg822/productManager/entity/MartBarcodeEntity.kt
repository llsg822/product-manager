package com.llsg822.productManager.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "mart_barcode",
    uniqueConstraints = [UniqueConstraint(columnNames = ["mart_id", "barcode"])]
)
class MartBarcodeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mart_id")
    val mart: MartEntity,
    @Column(name = "barcode")
    val barcode: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
)
