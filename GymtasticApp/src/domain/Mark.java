package domain;

import javax.persistence.Embeddable;

@Embeddable
public class Mark {
	private double dMark;
<<<<<<< HEAD
    private double eMarkOne;
    private double emarkTwo;
    private double eMarkThree;
    private double penalty;
    private double bonus;
    private double finalMark;
    
    
    
	public Mark(double dMark, double eMarkOne, double emarkTwo,
			double eMarkThree, double penalty, double bonus, double finalMark) {
=======
	private double eMarkOne;
	private double eMarkTwo;
	private double eMarkThree;
	private double penalty;
	private double bonus;
	private double finalMark;

	public Mark(double dMark, double eMarkOne, double emarkTwo, double eMarkThree,
			double penalty, double bonus, double finalmark) {
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
		super();
		this.dMark = dMark;
		this.eMarkOne = eMarkOne;
		this.eMarkTwo = emarkTwo;
		this.eMarkThree = eMarkThree;
		this.penalty = penalty;
		this.bonus = bonus;
<<<<<<< HEAD
		this.finalMark = finalMark;
	}
	
	public Mark() {
		super();
=======
		this.finalMark = generateFinalMark();
	}

	public double generateFinalMark() {
		double eMark;
		eMark = (eMarkOne+eMarkTwo+eMarkThree)/3;
		return eMark+dMark+bonus-penalty;
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
	    if (Double.doubleToLongBits(bonus) != Double.doubleToLongBits(other.bonus))
		return false;
	    if (Double.doubleToLongBits(dMark) != Double.doubleToLongBits(other.dMark))
		return false;
	    if (Double.doubleToLongBits(eMarkOne) != Double.doubleToLongBits(other.eMarkOne))
		return false;
	    if (Double.doubleToLongBits(eMarkThree) != Double.doubleToLongBits(other.eMarkThree))
		return false;
	    if (Double.doubleToLongBits(eMarkTwo) != Double.doubleToLongBits(other.eMarkTwo))
		return false;
	    if (Double.doubleToLongBits(finalMark) != Double.doubleToLongBits(other.finalMark))
		return false;
	    if (Double.doubleToLongBits(penalty) != Double.doubleToLongBits(other.penalty))
		return false;
	    return true;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
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
<<<<<<< HEAD
		return emarkTwo;
=======
	    return eMarkTwo;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
	}
	public void setEmarkTwo(double emarkTwo) {
<<<<<<< HEAD
		this.emarkTwo = emarkTwo;
=======
	    this.eMarkTwo = emarkTwo;
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
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
