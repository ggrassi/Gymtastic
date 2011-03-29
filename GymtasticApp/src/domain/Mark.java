package domain;

public class Mark {

	private int dMark;
	private int eMarkOne;
	private int emarkTwo;
	private int eMarkThree;
	private int penalty;
	private int bonus;
	private int finalMark;

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
		// TODO

		return 0;
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
		result = prime * result + bonus;
		result = prime * result + dMark;
		result = prime * result + eMarkOne;
		result = prime * result + eMarkThree;
		result = prime * result + emarkTwo;
		result = prime * result + finalMark;
		result = prime * result + penalty;
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
		if (bonus != other.bonus)
			return false;
		if (dMark != other.dMark)
			return false;
		if (eMarkOne != other.eMarkOne)
			return false;
		if (eMarkThree != other.eMarkThree)
			return false;
		if (emarkTwo != other.emarkTwo)
			return false;
		if (finalMark != other.finalMark)
			return false;
		if (penalty != other.penalty)
			return false;
		return true;
	}

	public int getdMark() {
		return dMark;
	}

	public void setdMark(int dMark) {
		this.dMark = dMark;
		generateFinalMark();
	}

	public int geteMarkOne() {
		return eMarkOne;
	}

	public void seteMarkOne(int eMarkOne) {
		this.eMarkOne = eMarkOne;
		generateFinalMark();
	}

	public int getEmarkTwo() {
		return emarkTwo;
	}

	public void setEmarkTwo(int emarkTwo) {
		this.emarkTwo = emarkTwo;
		generateFinalMark();
	}

	public int geteMarkThree() {
		return eMarkThree;
	}

	public void seteMarkThree(int eMarkThree) {
		this.eMarkThree = eMarkThree;
		generateFinalMark();
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
		generateFinalMark();
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
		generateFinalMark();
	}

	public int getFinalmark() {
		return finalMark;
	}

	public void setFinalmark(int finalmark) {
		this.finalMark = finalmark;
	}

}
