package org.hwbot.api.generic.dto;

import java.math.BigDecimal;

/**
 * Utility class to represent a submission rank and points for the Query API
 */
public class XmlRankingElement {
	public Integer globalrank;
	public Integer hwrank;
	public String wrpoints;
	public String globalpoints;
	public String hwpoints;
	public String gtppoints;
	public String htppoints;

	public XmlRankingElement() {	
	}
	
	public XmlRankingElement(Integer globalrank, Integer hwrank) {
		this.globalrank = globalrank;
		this.hwrank = hwrank;
	}
	
	public XmlRankingElement(Integer globalrank, Integer hwrank, BigDecimal wrpoints, BigDecimal globalpoints, BigDecimal hwpoints, Float gtppoints, Float htppoints) {
		this.globalrank = globalrank;
		this.hwrank = hwrank;
		this.wrpoints = wrpoints.toString();
		this.globalpoints = globalpoints.toString();
		this.hwpoints = hwpoints.toString();
		this.gtppoints = gtppoints.toString();
		this.htppoints = htppoints.toString();	
	}
}
