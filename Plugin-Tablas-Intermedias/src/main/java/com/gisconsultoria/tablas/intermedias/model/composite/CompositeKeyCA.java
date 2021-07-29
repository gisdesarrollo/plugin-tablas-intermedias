package com.gisconsultoria.tablas.intermedias.model.composite;

import java.io.Serializable;

public class CompositeKeyCA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String TAX;
	
	private int DOC;
	
	private String DCT;
		
	public CompositeKeyCA() {}


	public CompositeKeyCA(String tAX, int dOC, String dCT) {
		TAX = tAX;
		DOC = dOC;
		DCT = dCT;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DCT == null) ? 0 : DCT.hashCode());
		result = prime * result + DOC;
		result = prime * result + ((TAX == null) ? 0 : TAX.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKeyCA other = (CompositeKeyCA) obj;
		if (DCT == null) {
			if (other.DCT != null)
				return false;
		} else if (!DCT.equals(other.DCT))
			return false;
		if (DOC != other.DOC)
			return false;
		if (TAX == null) {
			if (other.TAX != null)
				return false;
		} else if (!TAX.equals(other.TAX))
			return false;
		return true;
	}
	
	

}
