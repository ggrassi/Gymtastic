package domain;

import javax.persistence.Embeddable;

@Embeddable
public class Mark {
	private double dMark;
    private double eMarkOne;
    private double emarkTwo;
    private double eMarkThree;
    private double penalty;
    private double bonus;
    private double finalMark;
    
    
    
	public Mark(double dMark, double eMarkOne, double emarkTwo,
			double eMarkThree, double penalty, double bonus, double finalMark) {
		super();
		this.dMark = dMark;
		this.eMarkOne = eMarkOne;
		this.emarkTwo = emarkTwo;
		this.eMarkThree = eMarkThree;
		this.penalty = penalty;
		this.bonus = bonus;
		this.finalMark = finalMark;
	}
	
	public Mark() {
		super();
	}

	public double getdMark() {
		return dMark;
	}
	public void setdMark(double dMark) {
		this.dMark = dMark;
	}
	public double geteMarkOne() {
		return eMarkOne;
	}
	public void seteMarkOne(double eMarkOne) {
		this.eMarkOne = eMarkOne;
	}
	public double getEmarkTwo() {
		return emarkTwo;
	}
	public void setEmarkTwo(double emarkTwo) {
		this.emarkTwo = emarkTwo;
	}
	public double geteMarkThree() {
		return eMarkThree;
	}
	public void seteMarkThree(double eMarkThree) {
		this.eMarkThree = eMarkThree;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getFinalMark() {
		return finalMark;
	}
	public void setFinalMark(double finalMark) {
		this.finalMark = finalMark;
	}

	public float generateFinalMark() {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}
