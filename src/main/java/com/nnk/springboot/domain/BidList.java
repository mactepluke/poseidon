package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "BidList")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer bidListId;
    @Column(length = 30)
    @Size(max = 30)
    @NotBlank(message = "Account is mandatory")
    private String account;
    @Column(length = 30)
    @Size(max = 30)
    @NotBlank(message = "Type is mandatory")
    private String type;
    @Column
    @NotNull(message = "Bid Quantity is mandatory")
    @Positive(message = "Must be positive")
    private Double bidQuantity;
    @Column
    @Positive(message = "Must be positive")
    private Double askQuantity;
    @Column
    @Positive(message = "Must be positive")
    private Double bid;
    @Column
    @Positive(message = "Must be positive")
    private Double ask;
    @Column(length = 125)
    @Size(max = 125)
    private String benchmark;
    @Column
    private Timestamp bidListDate;
    @Column(length = 125)
    @Size(max = 125)
    private String commentary;
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
    private String book;
    @Column(length = 125)
    @Size(max = 125)
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

    public BidList() {
        // Empty constructor is used by JPA to create entities
    }

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
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

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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
