package com.codingchallenge.Stocks;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
interface StockRepository extends JpaRepository<StockRecord, String> {

    boolean existsById(Long id);

    //@Lock(LockModeType.PESSIMISTIC_READ)
    //@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @Query("select ss from StockRecord ss where ss.stock = ?1")
    List<StockRecord> findByStockName(String stockName);
}
