package domain;

public enum DeviceType {

    FLOOR_EXCERCISE(0, "Boden"),
    POMMEL_HORSE (1, "Pferd"),
    STILL_RINGS (2, "Ringe"),
    VAULT (3, "Sprung"),
    PARALLEL_BARS (4, "Barren"),
    HIGH_BAR(5, "Reck");
    
    private final int index;
    private final String descr;
    
    DeviceType(int index, String descr){
	this.index = index;
	this.descr = descr;
    }
    
    public String toString(){
	return descr;
	
    }
    
}
