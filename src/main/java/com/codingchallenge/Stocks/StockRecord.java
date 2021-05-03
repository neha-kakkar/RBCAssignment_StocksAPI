package com.codingchallenge.Stocks;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Table(name="STOCK_RECORDS")
public class StockRecord {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUARTER")
    private String quarter;

    @Column(name = "STOCK")
    private String stock;

    @Column(name = "DATE")
    private String date;

    @Column(name = "OPEN")
    private String open;

    @Column(name = "HIGH")
    private String high;

    @Column(name = "LOW")
    private String low;

    @Column(name = "CLOSE")
    private String close;

    @Column(name = "VOLUME")
    private String volume;

    @Column(name = "PERCENT_CHANGE_PRICE")
    private String percentChangePrice;

    @Column(name = "PERCENT_CHANGE_VOLUME_OVER_LAST_WEEK")
    private String percentChangeVolumeOverLastWeek;

    @Column(name = "PREVIOUS_WEEKS_VOLUME")
    private String previousWeeksVolume;

    @Column(name = "NEXT_WEEKS_OPEN")
    private String nextWeeksOpen;

    @Column(name = "NEXT_WEEKS_CLOSE")
    private String nextWeeksClose;

    @Column(name = "PERCENT_CHANGE_NEXT_WEEKS_PRICE")
    private String percentChangeNextWeeksPrice;

    @Column(name = "DAYS_TO_NEXT_DIVIDEND")
    private String daysToNextDividend;

    @Column(name = "PERCENT_RETURN_NEXT_DIVIDEND")
    private String percentReturnNextDividend;

    public StockRecord(long l, String s, String aa, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14) {
        this.id = l;
        this.quarter = s;
        this.stock = aa;
        this.date = s1;
        this.open = s2;
        this.high = s3;
        this.low = s4;
        this.close = s5;
        this.volume = s6;
        this.percentChangePrice = s7;
        this.percentChangeVolumeOverLastWeek = s8;
        this.previousWeeksVolume = s9;
        this.nextWeeksOpen = s10;
        this.nextWeeksClose = s11;
        this.percentChangeNextWeeksPrice = s12;
        this.daysToNextDividend = s13;
        this.percentReturnNextDividend = s14;
    }

    public StockRecord(){
    }
}


