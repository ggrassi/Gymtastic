package domain;

import java.util.List;

public class Squad {
	private static int id;
	private int squadId=0;
	private List<Athlet> athlets = null;
	
	public Squad(){
		squadId=id++;
	}
	
}
