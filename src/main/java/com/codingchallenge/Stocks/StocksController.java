package com.codingchallenge.Stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class StocksController {
    private static Logger log = Logger.getLogger("CodingChallenge");

    @Autowired
    private StockService stockService;

    @GetMapping("/stockRecords/{stockName}")
    public ResponseEntity<List<StockRecord>> getStockRecords(@PathVariable(name = "stockName") String stockName) {
        return stockService.getStocks(stockName);
    }

    @PostMapping("/addStock")
    @ResponseBody
    public ResponseEntity<StockRecord> saveStockRecord(@RequestBody StockRecord stockRecord){
        stockRecord = stockService.saveSingleStockRecord(stockRecord);
        return new ResponseEntity<>(stockRecord, HttpStatus.CREATED);
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
    @PostMapping("/stockRecords/bulkUpload")
    public ResponseEntity<String> uploadBulkRecords(@RequestParam("file") MultipartFile file) throws IOException {
        return stockService.uploadRecordsFromFile(file);
    }
}
