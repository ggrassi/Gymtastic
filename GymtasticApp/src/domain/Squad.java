package domain;

import java.io.Serializable;
import java.util.List;

public class Squad implements Serializable{
	private static int id;
	private int squadId=0;
	private List<Athlet> athlets = null;
	
	public Squad(){
		squadId=id++;
	}
	
}
