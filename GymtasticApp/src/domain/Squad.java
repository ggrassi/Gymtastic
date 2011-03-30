package domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Squad implements Serializable {
	private static int id;
	private int squadId = 0;
	private Set<Athlet> athlets = null;

	public Squad() {
		squadId = id++;
		athlets = new HashSet<Athlet>();
	}

	public void addAthlete(Athlet athlet) {
		if (athlet != null) {
			athlets.add(athlet);
		}
	}

}
