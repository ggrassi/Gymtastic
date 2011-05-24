package ch.hsr.gymtastic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Mark holds the different marktypes, penaltys, bonus and the finalmark.
 */
@Entity
public class Mark implements Serializable {

	private static final long serialVersionUID = -2064909882686204715L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double dMark;
	private double eMarkOne;
	private double eMarkTwo;
	private double eMarkThree;
	private double penalty;
	private double bonus;
	private double finalMark = 0;

	/**
	 * Instantiates a new mark.
	 */
	public Mark() {
	}

	/**
	 * Instantiates a new mark.
	 *
	 * @param dMark the d mark
	 * @param eMarkOne the e mark one
	 * @param emarkTwo the emark two
	 * @param eMarkThree the e mark three
	 * @param penalty the penalty
	 * @param bonus the bonus
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mark [dMark=" + dMark + ", eMarkOne=" + eMarkOne
				+ ", emarkTwo=" + eMarkTwo + ", eMarkThree=" + eMarkThree
				+ ", penalty=" + penalty + ", bonus=" + bonus + ", finalmark="
				+ finalMark + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/**
	 * Gets the dmark.
	 *
	 * @return the dmark
	 */
	public double getdMark() {
		return dMark;
	}

	/**
	 * Sets the dmark.
	 *
	 * @param dMark the new dmark
	 */
	public void setdMark(double dMark) {
		this.dMark = dMark;
	}

	/**
	 * Gets the emark one.
	 *
	 * @return the emark one
	 */
	public double geteMarkOne() {
		return eMarkOne;
	}

	/**
	 * Sets the emark one.
	 *
	 * @param eMarkOne the new emark one
	 */
	public void seteMarkOne(double eMarkOne) {
		this.eMarkOne = eMarkOne;
	}

	/**
	 * Gets the emark two.
	 *
	 * @return the emark two
	 */
	public double getEmarkTwo() {
		return eMarkTwo;
	}

	/**
	 * Sets the emark two.
	 *
	 * @param emarkTwo the new emark two
	 */
	public void setEmarkTwo(double emarkTwo) {
		this.eMarkTwo = emarkTwo;
	}

	/**
	 * Gets the emark three.
	 *
	 * @return the emark three
	 */
	public double geteMarkThree() {
		return eMarkThree;
	}

	/**
	 * Sets the emark three.
	 *
	 * @param eMarkThree the new emark three
	 */
	public void seteMarkThree(double eMarkThree) {
		this.eMarkThree = eMarkThree;
	}

	/**
	 * Gets the penalty.
	 *
	 * @return the penalty
	 */
	public double getPenalty() {
		return penalty;
	}

	/**
	 * Sets the penalty.
	 *
	 * @param penalty the new penalty
	 */
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	/**
	 * Gets the bonus.
	 *
	 * @return the bonus
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * Sets the bonus.
	 *
	 * @param bonus the new bonus
	 */
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Sets the final mark.
	 *
	 * @param finalMark the new final mark
	 */
	public void setFinalMark(double finalMark){
		this.finalMark = finalMark;
	}

	/**
	 * Gets the final mark.
	 *
	 * @return the final mark
	 */
	public double getFinalMark() {
		return finalMark;
	}
	
	/**
	 * Calculates the final mark.
	 */
	public void calcFinalMark(){
		finalMark = ((eMarkOne + eMarkTwo + eMarkThree) / 3) + dMark + bonus
		- penalty;
		
	}

	public void setId(int id) {
	    this.id = id;
	}

	public int getId() {
	    return id;
	}

}
