package com.vshare.engine.pojos;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class   PaymentDetailsPojo implements Serializable {

 private static final Long serializableId = 123453453465L;	
	
private Long paymentDetailsId;
 
private BigDecimal  creditedamount;

private BigDecimal debitedamount;

private BigDecimal currentbalance;

private String referalid;

public Long getPaymentDetailsId() {
	return paymentDetailsId;
}

public void setPaymentDetailsId(Long paymentDetailsId) {
	this.paymentDetailsId = paymentDetailsId;
}

public BigDecimal getCreditedamount() {
	return creditedamount;
}

public void setCreditedamount(BigDecimal creditedamount) {
	this.creditedamount = creditedamount;
}

public BigDecimal getDebitedamount() {
	return debitedamount;
}

public void setDebitedamount(BigDecimal debitedamount) {
	this.debitedamount = debitedamount;
}



public BigDecimal getCurrentbalance() {
	return currentbalance;
}

public void setCurrentbalance(BigDecimal currentbalance) {
	this.currentbalance = currentbalance;
}

public String getReferalid() {
	return referalid;
}

public void setReferalid(String referalid) {
	this.referalid = referalid;
}


}
