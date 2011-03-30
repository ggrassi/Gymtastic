package domain;

public class Mark {

	private double dMark;
	private double eMarkOne;
	private double emarkTwo;
	private double eMarkThree;
	private double penalty;
	private double bonus;
	private double finalMark;

	public Mark(int dMark, int eMarkOne, int emarkTwo, int eMarkThree,
			int penalty, int bonus, int finalmark) {
		super();
		this.dMark = dMark;
		this.eMarkOne = eMarkOne;
		this.emarkTwo = emarkTwo;
		this.eMarkThree = eMarkThree;
		this.penalty = penalty;
		this.bonus = bonus;
		this.finalMark = generateFinalMark();
	}

	public int generateFinalMark() {
		

		return 5;
	}

	@Override
	public String toString() {
		return "Mark [dMark=" + dMark + ", eMarkOne=" + eMarkOne
				+ ", emarkTwo=" + emarkTwo + ", eMarkThree=" + eMarkThree
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
	    temp = Double.doubleToLongBits(emarkTwo);
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
	    if (Double.doubleToLongBits(emarkTwo) != Double.doubleToLongBits(other.emarkTwo))
		return false;
	    if (Double.doubleToLongBits(finalMark) != Double.doubleToLongBits(other.finalMark))
		return false;
	    if (Double.doubleToLongBits(penalty) != Double.doubleToLongBits(other.penalty))
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

	

}
