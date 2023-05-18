CREATE DATABASE IF NOT EXISTS demo;

USE demo;

DROP TABLE IF EXISTS BidList;
DROP TABLE IF EXISTS Trade;
DROP TABLE IF EXISTS CurvePoint;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS BidList;
DROP TABLE IF EXISTS RuleName;
DROP TABLE IF EXISTS Users;


CREATE TABLE IF NOT EXISTS BidList
(
    bidListId    tinyint(4) AUTO_INCREMENT,
    account      VARCHAR(30) NOT NULL,
    type         VARCHAR(30) NOT NULL,
    bidQuantity  DOUBLE,
    askQuantity  DOUBLE,
    bid          DOUBLE,
    ask          DOUBLE,
    benchmark    VARCHAR(125),
    bidListDate  TIMESTAMP,
    commentary   VARCHAR(125),
    security     VARCHAR(125),
    status       VARCHAR(10),
    trader       VARCHAR(125),
    book         VARCHAR(125),
    creationName VARCHAR(125),
    creationDate TIMESTAMP,
    revisionName VARCHAR(125),
    revisionDate TIMESTAMP,
    dealName     VARCHAR(125),
    dealType     VARCHAR(125),
    sourceListId VARCHAR(125),
    side         VARCHAR(125),

    PRIMARY KEY (bidListId)
);

CREATE TABLE IF NOT EXISTS Trade
(
    tradeId      tinyint(4) AUTO_INCREMENT,
    account      VARCHAR(30) NOT NULL,
    type         VARCHAR(30) NOT NULL,
    buyQuantity  DOUBLE,
    sellQuantity DOUBLE,
    buyPrice     DOUBLE,
    sellPrice    DOUBLE,
    tradeDate    TIMESTAMP,
    security     VARCHAR(125),
    status       VARCHAR(10),
    trader       VARCHAR(125),
    benchmark    VARCHAR(125),
    book         VARCHAR(125),
    creationName VARCHAR(125),
    creationDate TIMESTAMP,
    revisionName VARCHAR(125),
    revisionDate TIMESTAMP,
    dealName     VARCHAR(125),
    dealType     VARCHAR(125),
    sourceListId VARCHAR(125),
    side         VARCHAR(125),

    PRIMARY KEY (tradeId)
);

CREATE TABLE IF NOT EXISTS CurvePoint
(
    id           tinyint(4) AUTO_INCREMENT,
    curveId      tinyint,
    asOfDate     TIMESTAMP,
    term         DOUBLE,
    value        DOUBLE,
    creationDate TIMESTAMP,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Rating
(
    id           tinyint(4) AUTO_INCREMENT,
    moodysRating VARCHAR(125),
    sandpRating  VARCHAR(125),
    fitchRating  VARCHAR(125),
    orderNumber  tinyint,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS RuleName
(
    id          tinyint(4) AUTO_INCREMENT,
    name        VARCHAR(125),
    description VARCHAR(125),
    json        VARCHAR(125),
    template    VARCHAR(512),
    sqlStr      VARCHAR(125),
    sqlPart     VARCHAR(125),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Users
(
    id       tinyint(4) AUTO_INCREMENT,
    username VARCHAR(125) NOT NULL,
    password VARCHAR(125) NOT NULL,
    fullname VARCHAR(125) NOT NULL,
    role     VARCHAR(125) NOT NULL,

    PRIMARY KEY (id)
);
