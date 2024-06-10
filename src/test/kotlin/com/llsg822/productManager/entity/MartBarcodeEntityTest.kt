package com.llsg822.productManager.entity

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.hibernate.exception.ConstraintViolationException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
class MartBarcodeEntityTest {
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    @DisplayName("마트당 바코드는 유일해야 한다.")
    @Transactional
    fun t1() {
        // given
        val m1 = MartEntity(
            name = "m1"
        )
        val p1 = ProductEntity(
            name = "p1"
        )
        val mb1 = MartBarcodeEntity(
            mart = m1,
            barcode = "1",
            product = p1,
        )
        entityManager.persist(m1)
        entityManager.persist(p1)
        entityManager.persist(mb1)

        entityManager.flush()

        // then
        Assertions.assertThatThrownBy {
            // when
            val mb2 = MartBarcodeEntity(
                mart = m1,
                barcode = "1",
                product = p1,
            )
            entityManager.persist(mb2)
            entityManager.flush()
        }.isInstanceOf(ConstraintViolationException::class.java)
    }

    @Test
    @DisplayName("마트가 다르다면 바코드는 같을 수 있다.")
    @Transactional
    fun t2() {
        // given
        val m1 = MartEntity(
            name = "m1"
        )
        val m2 = MartEntity(
            name = "m2"
        )
        val p1 = ProductEntity(
            name = "p1"
        )
        val mb1 = MartBarcodeEntity(
            mart = m1,
            barcode = "1",
            product = p1,
        )
        entityManager.persist(m1)
        entityManager.persist(m2)
        entityManager.persist(p1)
        entityManager.persist(mb1)

        entityManager.flush()

        // then
        Assertions.assertThatCode {
            // when
            val mb2 = MartBarcodeEntity(
                mart = m2,
                barcode = "1",
                product = p1,
            )
            entityManager.persist(mb2)
            entityManager.flush()
        }.doesNotThrowAnyException()
    }
}