package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Evaluation {

    @OneToMany
    private Map<DeviceType, Mark> marks = null;
    
  
    public Map<DeviceType, Mark> getMarks() {
        return marks;
    }

    public void setMarks(Map<DeviceType, Mark> marks) {
        this.marks = marks;
    }

    //mark und a nur für test
    private Mark mark = new Mark(4,5,3,5,3,4,3);
    private DeviceType a = DeviceType.FLOOR_EXCERCISE;

    public Evaluation() {
	super();
	this.marks = new HashMap<DeviceType, Mark>();
	insertMark(DeviceType.FLOOR_EXCERCISE, mark);
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
