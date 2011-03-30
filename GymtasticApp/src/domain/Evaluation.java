package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Evaluation {
	private Map<Object, Mark> marks = null;

	public Evaluation() {
		super();
		this.marks = new HashMap<Object, Mark>();
	}
	
	public void insertMark(Mark mark){
		Object key = null;
		marks.put(key, mark);
	}
	
	public void deleteMark(Object key){
		marks.remove(key);
	}
	
	public float getAverageMark(){
		float total = 0;
		Collection c = marks.values();
		Iterator it = c.iterator();
		while(it.hasNext()){
			total += ((Mark) it.next()).generateFinalMark();
		}
		return (total/marks.size());
		
	}	
	

}
