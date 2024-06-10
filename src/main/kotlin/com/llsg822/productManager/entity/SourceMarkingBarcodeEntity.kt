package com.llsg822.productManager.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "source_marking_barcode",
    uniqueConstraints = [UniqueConstraint(columnNames = ["barcode"])]
)
class SourceMarkingBarcodeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "barcode")
    val barcode: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
)