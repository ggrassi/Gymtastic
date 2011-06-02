package ch.hsr.gymtastic.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * The Class Mark holds the different marktypes, penaltys, bonus and the
 * finalmark.
 */
@Entity
public class Mark implements Serializable, Cloneable {

	private static final long serialVersionUID = -2064909882686204715L;
	private int id = 0;
	private double dMark;
	private double eMarkOne;
	private double eMarkTwo;
	private double eMarkThree;
	private double penalty;
	private double bonus;
	private double finalMark = 0.0;

	/**
	 * Instantiates a new mark.
	 */
	public Mark() {
	}

	/**
	 * Instantiates a new mark.
	 * 
	 * @param dMark
	 *            the d mark
	 * @param eMarkOne
	 *            the e mark one
	 * @param emarkTwo
	 *            the emark two
	 * @param eMarkThree
	 *            the e mark three
	 * @param penalty
	 *            the penalty
	 * @param bonus
	 *            the bonus
	 */
	public Mark(double dMark, double eMarkOne, double emarkTwo,
			double eMarkThree, double penalty, double bonus) {
		this.dMark = dMark;
		this.eMarkOne = eMarkOne;
		this.eMarkTwo = emarkTwo;
		this.eMarkThree = eMarkThree;
		this.penalty = penalty;
		this.bonus = bonus;
		calcFinalMark();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mark [dMark=" + dMark + ", eMarkOne=" + eMarkOne
				+ ", emarkTwo=" + eMarkTwo + ", eMarkThree=" + eMarkThree
				+ ", penalty=" + penalty + ", bonus=" + bonus + ", finalmark="
				+ getFinalMark() + "]";
	}

	public double getdMark() {
		return dMark;
	}

	public void setdMark(double dMark) {
		this.dMark = dMark;
		calcFinalMark();
	}

	public double geteMarkOne() {
		return eMarkOne;
	}

	public void seteMarkOne(double eMarkOne) {
		this.eMarkOne = eMarkOne;
		calcFinalMark();
	}

	public double getEmarkTwo() {
		return eMarkTwo;
	}

	public void setemarkTwo(double emarkTwo) {
		this.eMarkTwo = emarkTwo;
		calcFinalMark();
	}

	public double geteMarkThree() {
		return eMarkThree;
	}

	public void seteMarkThree(double eMarkThree) {
		this.eMarkThree = eMarkThree;
		calcFinalMark();
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
		calcFinalMark();
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
		calcFinalMark();
	}

	public double getFinalMark() {
		return finalMark;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFinalMark(double finalMark) {
		this.finalMark = finalMark;
	}

	/**
	 * Calculates the final mark.
	 */
	private void calcFinalMark() {

		finalMark = ((eMarkOne + eMarkTwo + eMarkThree) / 3) + dMark + bonus
				- penalty;

	}

}
