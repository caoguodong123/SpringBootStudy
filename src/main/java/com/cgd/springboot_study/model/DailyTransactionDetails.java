package com.cgd.springboot_study.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTransactionDetails {
    private Integer id;

    private String tradeId;

    //@Excel(name="商品种类",orderNum = "2")

    private String commodityType;
    private  String multiCommodityType ;

    private Double leftQuantity;

    //@Excel(name="数量",orderNum = "8")
    private Double quantity;

    @Excel(name="成交手数",orderNum = "5")
    private Double amount;

    @Excel(name="成交价格",orderNum = "4")
    private Double price;

    //@Excel(name="成交金额",orderNum = "9")
    private Double value;

    @Excel(name="成交编号",orderNum = "0")
    private String num;

    @Excel(name="成交时间",orderNum = "6")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private Double contractSize;

    private Date tradeDate;

    private String type;

    private String recordCode;

    @Excel(name="合约",orderNum = "1")
    private String contractId;

    private String commodityTypeDetail;

    //@Excel(name="到期日",orderNum = "3")
    private Date commodityTypeDate;

    private String brokerageCompany;

    private String authorizationId;

    @Excel(name="账户",orderNum = "9")
    private String account;

    @Excel(name="买/卖",orderNum = "2")
    private String longShort;

    @Excel(name="开/平",orderNum = "3")
    private String openClose;

    private String contractType;

    private String callPut;

    private String currencyType;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date expirationDate;

    private Double strikePrice;

    private String orderBy;

    private String dataFrom;

    private String importBy;

    private String department;

    private String branchOffice;

    private String attribute1;

    private String attribute2;

    private Integer userId;

    private Date createTime;

    private Date modifyTime;

    private String status;

    private String purchaseSell;

    private String buySell;

    //计划工厂匹配使用
    private Integer matchId;

    private Double matchedQuantity;

    private String spotFutureType;

    private String planTimeId;

    private double breakEven;
    //手数
    private double lots ;

    private Integer speculationHedging ;
    //导入导出使用
    @Excel(name="投机/套保",orderNum = "7")
    private String speculationHedging1 ;

    //排序
    private String sort;
    private String order;

    //用于界面显示

    //保证金（暂存保证金）
//    private Double longSpeculationDeposit;
//    private Double longHedgingDeposit;
//    private Double shortSpeculationDeposit;
//    private Double shortHedgingDeposit;
    //保证金金额
    private Double deposit;
    //保证金比率
    private Double depositRate;

    //单位
    private String unit;

    //交易员（在数据库中存储id）
    @Excel(name="交易员",orderNum = "8")
    private String trader;

    public DailyTransactionDetails() {
    }

    public DailyTransactionDetails(Double amount, Double price, String num, Date time, String contractId, String account, String longShort, String openClose, String speculationHedging1, String trader) {
        this.amount = amount;
        this.price = price;
        this.num = num;
        this.time = time;
        this.contractId = contractId;
        this.account = account;
        this.longShort = longShort;
        this.openClose = openClose;
        this.speculationHedging1 = speculationHedging1;
        this.trader = trader;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Double getMatchedQuantity() {
        return matchedQuantity;
    }

    public void setMatchedQuantity(Double matchedQuantity) {
        this.matchedQuantity = matchedQuantity;
    }

    public String getSpotFutureType() {
        return spotFutureType;
    }

    public void setSpotFutureType(String spotFutureType) {
        this.spotFutureType = spotFutureType;
    }

    public String getPlanTimeId() {
        return planTimeId;
    }

    public void setPlanTimeId(String planTimeId) {
        this.planTimeId = planTimeId;
    }

    public double getBreakEven() {
        return breakEven;
    }

    public void setBreakEven(double breakEven) {
        this.breakEven = breakEven;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType == null ? null : commodityType.trim();
    }

    public Double getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(Double leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getContractSize() {
        return contractSize;
    }

    public void setContractSize(Double contractSize) {
        this.contractSize = contractSize;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode == null ? null : recordCode.trim();
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getCommodityTypeDetail() {
        return commodityTypeDetail;
    }

    public void setCommodityTypeDetail(String commodityTypeDetail) {
        this.commodityTypeDetail = commodityTypeDetail == null ? null : commodityTypeDetail.trim();
    }

    public Date getCommodityTypeDate() {
        return commodityTypeDate;
    }

    public void setCommodityTypeDate(Date commodityTypeDate) {
        this.commodityTypeDate = commodityTypeDate;
    }

    public String getBrokerageCompany() {
        return brokerageCompany;
    }

    public void setBrokerageCompany(String brokerageCompany) {
        this.brokerageCompany = brokerageCompany == null ? null : brokerageCompany.trim();
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId == null ? null : authorizationId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getLongShort() {
        return longShort;
    }

    public void setLongShort(String longShort) {
        this.longShort = longShort == null ? null : longShort.trim();
    }

    public String getOpenClose() {
        return openClose;
    }

    public void setOpenClose(String openClose) {
        this.openClose = openClose == null ? null : openClose.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public String getCallPut() {
        return callPut;
    }

    public void setCallPut(String callPut) {
        this.callPut = callPut == null ? null : callPut.trim();
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy == null ? null : orderBy.trim();
    }

    public String getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(String dataFrom) {
        this.dataFrom = dataFrom == null ? null : dataFrom.trim();
    }

    public String getImportBy() {
        return importBy;
    }

    public void setImportBy(String importBy) {
        this.importBy = importBy == null ? null : importBy.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice == null ? null : branchOffice.trim();
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1 == null ? null : attribute1.trim();
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2 == null ? null : attribute2.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPurchaseSell() {
        return purchaseSell;
    }

    public void setPurchaseSell(String purchaseSell) {
        this.purchaseSell = purchaseSell == null ? null : purchaseSell.trim();
    }

    public double getLots() {
        return lots;
    }

    public void setLots(double lots) {
        this.lots = lots;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getSpeculationHedging() {
        return speculationHedging;
    }

    public void setSpeculationHedging(Integer speculationHedging) {
        this.speculationHedging = speculationHedging;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(Double depositRate) {
        this.depositRate = depositRate;
    }

    public String getSpeculationHedging1() {
        return speculationHedging1;
    }

    public void setSpeculationHedging1(String speculationHedging1) {
        this.speculationHedging1 = speculationHedging1;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getMultiCommodityType() {
        return multiCommodityType;
    }

    public void setMultiCommodityType(String multiCommodityType) {
        this.multiCommodityType = multiCommodityType;
    }

    public String getBuySell() {
        return buySell;
    }

    public void setBuySell(String buySell) {
        this.buySell = buySell;
    }


}