package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;


@Entity
@Table(name = "Trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer tradeId;
    @Column(length = 30)
    @Size(max = 30)
    @NotBlank(message = "Account is mandatory")
    private String account;
    @Column(length = 30)
    @Size(max = 30)
    @NotBlank(message = "Type is mandatory")
    private String type;
    @Column
    @Positive(message = "Must be positive")
    private Double buyQuantity;
    @Column
    @Positive(message = "Must be positive")
    private Double sellQuantity;
    @Column
    @Positive(message = "Must be positive")
    private Double buyPrice;
    @Column
    @Positive(message = "Must be positive")
    private Double sellPrice;
    @Column
    private Timestamp tradeDate;
    @Column(length = 125)
    @Size(max = 125)
    private String security;
    @Column(length = 10)
    @Size(max = 10)
    private String status;
    @Column(length = 125)
    @Size(max = 125)
    private String trader;
    @Column(length = 125)
    @Size(max = 125)
    private String benchmark;
    @Column(length = 125)
    @Size(max = 125)
    private String book;
    @Column
    private String creationName;
    @Column
    private Timestamp creationDate;
    @Column(length = 125)
    @Size(max = 125)
    private String revisionName;
    @Column
    private Timestamp revisionDate;
    @Column(length = 125)
    @Size(max = 125)
    private String dealName;
    @Column(length = 125)
    @Size(max = 125)
    private String dealType;
    @Column(length = 125)
    @Size(max = 125)
    private String sourceListId;
    @Column(length = 125)
    @Size(max = 125)
    private String side;


    public Trade() {
        // Empty constructor is used by JPA to create entities
    }

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }


    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
