package antigen;

public class Antigen {

	private static int nextId = 0;
	
	private int id;
	private AntigenType type;
	
	public Antigen() {
		this.id = Antigen.nextId++;

		AntigenType[] values = AntigenType.values();
		this.type = values[this.id % values.length];
	}
	
	public AntigenType getType() {
		return this.type;
	}
	
	public String toString() {
		return "Antigen " + this.id;
	}
}
