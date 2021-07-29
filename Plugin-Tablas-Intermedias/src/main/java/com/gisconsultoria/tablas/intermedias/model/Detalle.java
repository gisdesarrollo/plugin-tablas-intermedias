package com.gisconsultoria.tablas.intermedias.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.gisconsultoria.tablas.intermedias.model.composite.CompositeKeyDE;

@Entity
@IdClass(CompositeKeyDE.class)
@Table(name ="F55421DE")
public class Detalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String TAX;
	
	@Id
	private int DOC;
	
	@Id
	private String DCT;
	
	@Id
	private int LNID;
	
	private String MCU;
	
	private String STST;
	
	private String KCOO;
	
	private int DOCO;
	
	private String DCTO;
	
	private String DLFE;
	
	private int URAB;
	
	private int URAT;
	
	@Column(name ="`DESC`")
	private String DESC;
	
	@Column(name ="`USER`")
	private String USER;
	
	private String JOBN;
	
	private String PID;
	
	private int UPMJ;
	
	private int UPMT;

	public String getTAX() {
		return TAX;
	}

	public void setTAX(String tAX) {
		TAX = tAX;
	}

	public int getDOC() {
		return DOC;
	}

	public void setDOC(int dOC) {
		DOC = dOC;
	}

	public String getDCT() {
		return DCT;
	}

	public void setDCT(String dCT) {
		DCT = dCT;
	}

	public int getLNID() {
		return LNID;
	}

	public void setLNID(int lNID) {
		LNID = lNID;
	}

	public String getMCU() {
		return MCU;
	}

	public void setMCU(String mCU) {
		MCU = mCU;
	}

	public String getSTST() {
		return STST;
	}

	public void setSTST(String sTST) {
		STST = sTST;
	}

	public String getKCOO() {
		return KCOO;
	}

	public void setKCOO(String kCOO) {
		KCOO = kCOO;
	}

	public int getDOCO() {
		return DOCO;
	}

	public void setDOCO(int dOCO) {
		DOCO = dOCO;
	}

	public String getDCTO() {
		return DCTO;
	}

	public void setDCTO(String dCTO) {
		DCTO = dCTO;
	}

	public String getDLFE() {
		return DLFE;
	}

	public void setDLFE(String dLFE) {
		DLFE = dLFE;
	}

	public int getURAB() {
		return URAB;
	}

	public void setURAB(int uRAB) {
		URAB = uRAB;
	}

	public int getURAT() {
		return URAT;
	}

	public void setURAT(int uRAT) {
		URAT = uRAT;
	}

	public String getDESC() {
		return DESC;
	}

	public void setDESC(String dESC) {
		DESC = dESC;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getJOBN() {
		return JOBN;
	}

	public void setJOBN(String jOBN) {
		JOBN = jOBN;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public int getUPMJ() {
		return UPMJ;
	}

	public void setUPMJ(int uPMJ) {
		UPMJ = uPMJ;
	}

	public int getUPMT() {
		return UPMT;
	}

	public void setUPMT(int uPMT) {
		UPMT = uPMT;
	}
	
	
	
}
