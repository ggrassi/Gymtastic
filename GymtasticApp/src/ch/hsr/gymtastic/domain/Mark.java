package ch.hsr.gymtastic.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Mark implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = -2064909882686204715L;
	private double dMark;
	private double eMarkOne;
	private double eMarkTwo;
	private double eMarkThree;
	private double penalty;
	private double bonus;
	private double finalMark = 0;

	public Mark() {
	}

	public Mark(double dMark, double eMarkOne, double emarkTwo,
			double eMarkThree, double penalty, double bonus) {
		super();
		this.dMark = dMark;
		this.eMarkOne = eMarkOne;
		this.eMarkTwo = emarkTwo;
		this.eMarkThree = eMarkThree;
		this.penalty = penalty;
		this.bonus = bonus;
		calcFinalMark();
	}

	@Override
	public String toString() {
		return "Mark [dMark=" + dMark + ", eMarkOne=" + eMarkOne
				+ ", emarkTwo=" + eMarkTwo + ", eMarkThree=" + eMarkThree
				+ ", penalty=" + penalty + ", bonus=" + bonus + ", finalmark="
				+ finalMark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(bonus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dMark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(eMarkOne);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(eMarkThree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(eMarkTwo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(finalMark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(penalty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Mark other = (Mark) obj;
		if (Double.doubleToLongBits(bonus) != Double
				.doubleToLongBits(other.bonus))
			return false;
		if (Double.doubleToLongBits(dMark) != Double
				.doubleToLongBits(other.dMark))
			return false;
		if (Double.doubleToLongBits(eMarkOne) != Double
				.doubleToLongBits(other.eMarkOne))
			return false;
		if (Double.doubleToLongBits(eMarkThree) != Double
				.doubleToLongBits(other.eMarkThree))
			return false;
		if (Double.doubleToLongBits(eMarkTwo) != Double
				.doubleToLongBits(other.eMarkTwo))
			return false;
		if (Double.doubleToLongBits(finalMark) != Double
				.doubleToLongBits(other.finalMark))
			return false;
		if (Double.doubleToLongBits(penalty) != Double
				.doubleToLongBits(other.penalty))
			return false;
		return true;
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
		return eMarkTwo;
	}

	public void setEmarkTwo(double emarkTwo) {
		this.eMarkTwo = emarkTwo;
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
	
	public void setFinalMark(double finalMark){
		this.finalMark = finalMark;
	}

	public double getFinalMark() {
		return finalMark;
	}
	
	public void calcFinalMark(){
		finalMark = ((eMarkOne + eMarkTwo + eMarkThree) / 3) + dMark + bonus
		- penalty;
		
	}

}
