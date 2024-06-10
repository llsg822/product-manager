package com.llsg822.productManager.entity

import jakarta.persistence.*

@Entity
@Table(name = "well_known_product")
class WellKnownProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "name")
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
)