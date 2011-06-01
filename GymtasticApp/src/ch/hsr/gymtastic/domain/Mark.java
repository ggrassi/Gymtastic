package ch.hsr.gymtastic.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * The Class Mark holds the different marktypes, penaltys, bonus and the
 * finalmark.
 */
@Entity 
public class Mark implements Serializable {

    private static final long serialVersionUID = -2064909882686204715L;
    private int id;
    private static int markId = 0;
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
    	setId(++markId);
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
    public Mark(double dMark, double eMarkOne, double emarkTwo, double eMarkThree, double penalty, double bonus) {
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
	return "Mark [dMark=" + dMark + ", eMarkOne=" + eMarkOne + ", emarkTwo=" + eMarkTwo + ", eMarkThree="
		+ eMarkThree + ", penalty=" + penalty + ", bonus=" + bonus + ", finalmark=" + finalMark + "]";
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
     * @param dMark
     *            the new dmark
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
     * @param eMarkOne
     *            the new emark one
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
     * @param emarkTwo
     *            the new emark two
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
     * @param eMarkThree
     *            the new emark three
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
     * @param penalty
     *            the new penalty
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
     * @param bonus
     *            the new bonus
     */
    public void setBonus(double bonus) {
	this.bonus = bonus;
    }

    /**
     * Sets the final mark.
     * 
     * @param finalMark
     *            the new final mark
     */
    public void setFinalMark(double finalMark) {
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
    public void calcFinalMark() {
	finalMark = ((eMarkOne + eMarkTwo + eMarkThree) / 3) + dMark + bonus - penalty;

    }

    public void setId(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

}
