package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Evaluation{

	@OneToMany
	private Map<DeviceType, Mark> marks = null;
	// mark und a nur für test
	private Mark markTest = new Mark(4, 5, 3, 5, 3, 4, 3);

	public Evaluation() {
<<<<<<< HEAD
		super();
		this.marks = new HashMap<DeviceType, Mark>();
		insertMark(DeviceType.FLOOR_EXCERCISE, markTest);
=======
		this.marks = new HashMap<Object, Mark>();
>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
	}
	
	public Map<DeviceType, Mark> getMarks() {
		return marks;
	}
	
	public void setMarks(Map<DeviceType, Mark> marks) {
		this.marks = marks;
	}
	

	public void insertMark(DeviceType devType, Mark mark) {
		marks.put(devType, mark);
	}

	public void deleteMark(DeviceType devType) {
		marks.remove(devType);
	}

	public float getAverageMark() {
		float total = 0;
		Collection<Mark> c = marks.values();
		Iterator<Mark> it = c.iterator();
		while (it.hasNext()) {
			total += it.next().generateFinalMark();
		}
		return (total / marks.size());

	}

}
