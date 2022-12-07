package com.medical.clinic.model;

import java.math.BigDecimal;

public class CancelAppiontementModel {
	
	BigDecimal appiontementId;
	CancellationReasonEnum cancelReason;
	public BigDecimal getAppiontementId() {
		return appiontementId;
	}
	public void setAppiontementId(BigDecimal appiontementId) {
		this.appiontementId = appiontementId;
	}
	public CancellationReasonEnum getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(CancellationReasonEnum cancelReason) {
		this.cancelReason = cancelReason;
	}
	
	
	
}
