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
	private String FETAX;
	
	@Id
	private int FEDOC;
	
	@Id
	private String FEDCT;
	
	@Id
	private int FELNID;
	
	private String FEMCU;
	
	@Column(name ="FESTST")
	private String FESTST;
	
	private String FEKCOO;
	
	private int FEDOCO;
	
	private String FEDCTO;
	
	private String FEDLFE;
	
	private int FEURAB;
	
	private int FEURAT;
	
	@Column(name ="`FEDESC`")
	private String FEDESC;
	
	@Column(name ="`FEUSER`")
	private String FEUSER;
	
	private String FEJOBN;
	
	private String FEPID;
	
	private int FEUPMJ;
	
	private int FEUPMT;

	public String getFETAX() {
		return FETAX;
	}

	public void setFETAX(String fETAX) {
		FETAX = fETAX;
	}

	public int getFEDOC() {
		return FEDOC;
	}

	public void setFEDOC(int fEDOC) {
		FEDOC = fEDOC;
	}

	public String getFEDCT() {
		return FEDCT;
	}

	public void setFEDCT(String fEDCT) {
		FEDCT = fEDCT;
	}

	public int getFELNID() {
		return FELNID;
	}

	public void setFELNID(int fELNID) {
		FELNID = fELNID;
	}

	public String getFEMCU() {
		return FEMCU;
	}

	public void setFEMCU(String fEMCU) {
		FEMCU = fEMCU;
	}

	public String getFESTST() {
		return FESTST;
	}

	public void setFESTST(String fESTST) {
		FESTST = fESTST;
	}

	public String getFEKCOO() {
		return FEKCOO;
	}

	public void setFEKCOO(String fEKCOO) {
		FEKCOO = fEKCOO;
	}

	public int getFEDOCO() {
		return FEDOCO;
	}

	public void setFEDOCO(int fEDOCO) {
		FEDOCO = fEDOCO;
	}

	public String getFEDCTO() {
		return FEDCTO;
	}

	public void setFEDCTO(String fEDCTO) {
		FEDCTO = fEDCTO;
	}

	public String getFEDLFE() {
		return FEDLFE;
	}

	public void setFEDLFE(String fEDLFE) {
		FEDLFE = fEDLFE;
	}

	public int getFEURAB() {
		return FEURAB;
	}

	public void setFEURAB(int fEURAB) {
		FEURAB = fEURAB;
	}

	public int getFEURAT() {
		return FEURAT;
	}

	public void setFEURAT(int fEURAT) {
		FEURAT = fEURAT;
	}

	public String getFEDESC() {
		return FEDESC;
	}

	public void setFEDESC(String fEDESC) {
		FEDESC = fEDESC;
	}

	public String getFEUSER() {
		return FEUSER;
	}

	public void setFEUSER(String fEUSER) {
		FEUSER = fEUSER;
	}

	public String getFEJOBN() {
		return FEJOBN;
	}

	public void setFEJOBN(String fEJOBN) {
		FEJOBN = fEJOBN;
	}

	public String getFEPID() {
		return FEPID;
	}

	public void setFEPID(String fEPID) {
		FEPID = fEPID;
	}

	public int getFEUPMJ() {
		return FEUPMJ;
	}

	public void setFEUPMJ(int fEUPMJ) {
		FEUPMJ = fEUPMJ;
	}

	public int getFEUPMT() {
		return FEUPMT;
	}

	public void setFEUPMT(int fEUPMT) {
		FEUPMT = fEUPMT;
	}

		
	
}
