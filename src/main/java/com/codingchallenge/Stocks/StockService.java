package com.codingchallenge.Stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StockService {

    private static Logger log = Logger.getLogger("CodingChallenge");
    List<StockRecord> allRecords = new ArrayList<>();

    @Autowired
    private StockRepository stockRepository;

    public ResponseEntity<List<StockRecord>> getStocks(String stockName) {
        List<StockRecord> records = stockRepository.findByStockName(stockName);
        return records.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(records, HttpStatus.OK);
    }

 /*   public ResponseEntity<StockRecord> saveSingleStockRecord(@RequestBody StockRecord stockRecord){
        StockRecord savedRecord = stockRepository.save(stockRecord);
        if(stockRepository.existsById(savedRecord.getId()))
            return ResponseEntity.ok("Stock Record Created Successfully with Id = " + savedRecord.getId());
        else return ResponseEntity.unprocessableEntity().body("Failed to create the stock record.");
    }
*/
    public StockRecord saveSingleStockRecord(StockRecord stockRecord){
        return stockRepository.save(stockRecord);
    }

    private void handleLine(String s) {
        StockRecord sr = new StockRecord();
        String[] strArr = s.split(",");
        sr.setQuarter(strArr[0]);
        sr.setStock(strArr[1]);
        sr.setDate(strArr[2]);
        sr.setOpen(strArr[3]);
        sr.setHigh(strArr[4]);
        sr.setLow(strArr[5]);
        sr.setClose(strArr[6]);
        sr.setVolume(strArr[7]);
        sr.setPercentChangePrice(strArr[8]);
        sr.setPercentChangeVolumeOverLastWeek(strArr[9]);
        sr.setPreviousWeeksVolume(strArr[10]);
        sr.setNextWeeksOpen(strArr[11]);
        sr.setNextWeeksClose(strArr[12]);
        sr.setPercentChangeNextWeeksPrice(strArr[13]);
        sr.setDaysToNextDividend(strArr[14]);
        sr.setPercentReturnNextDividend(strArr[15]);

        allRecords.add(sr);
    }

    public ResponseEntity<String> uploadRecordsFromFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().skip(1)
                .forEach(this::handleLine);

            List<StockRecord> uploadedRecords = stockRepository.saveAll(allRecords);
            return uploadedRecords.isEmpty() ?
                    new ResponseEntity<>("No records uploaded to database.", HttpStatus.NOT_IMPLEMENTED) : new ResponseEntity<>("Data successfully uploaded.", HttpStatus.OK);
        }
    }

