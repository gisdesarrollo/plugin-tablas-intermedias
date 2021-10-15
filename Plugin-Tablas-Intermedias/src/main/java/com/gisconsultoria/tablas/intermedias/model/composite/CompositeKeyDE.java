package com.gisconsultoria.tablas.intermedias.model.composite;

import java.io.Serializable;

public class CompositeKeyDE implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String FETAX;
	
	private int FEDOC;
	
	private String FEDCT;
	
	private int FELNID;
	
	public CompositeKeyDE() {}

	public CompositeKeyDE(String tAX, int dOC, String dCT, int lNID) {
		FETAX = tAX;
		FEDOC = dOC;
		FEDCT = dCT;
		FELNID = lNID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FEDCT == null) ? 0 : FEDCT.hashCode());
		result = prime * result + FEDOC;
		result = prime * result + FELNID;
		result = prime * result + ((FETAX == null) ? 0 : FETAX.hashCode());
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
		CompositeKeyDE other = (CompositeKeyDE) obj;
		if (FEDCT == null) {
			if (other.FEDCT != null)
				return false;
		} else if (!FEDCT.equals(other.FEDCT))
			return false;
		if (FEDOC != other.FEDOC)
			return false;
		if (FELNID != other.FELNID)
			return false;
		if (FETAX == null) {
			if (other.FETAX != null)
				return false;
		} else if (!FETAX.equals(other.FETAX))
			return false;
		return true;
	}
	
	
}
