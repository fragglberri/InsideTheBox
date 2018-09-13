package antigen;

import java.util.LinkedList;

import repast.simphony.engine.schedule.ScheduledMethod;

public class AntigenSource {

	private LinkedList<Antigen> pool;
	
	public AntigenSource() {
		this.pool = new LinkedList<Antigen>();
	}
	
	@ScheduledMethod(start=0, interval=0.1)
	public void generateAntigen() {
		this.pool.addLast(new Antigen());
	}
	
	public Antigen popAntigen() {
		return this.pool.pollFirst();
	}
}
