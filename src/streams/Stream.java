package streams;

import java.util.Observable;

import protoTransparency.Cell;
import repast.simphony.engine.schedule.ScheduledMethod;

public abstract class Stream extends Observable {

	private double value;
	
	public Stream(double initValue) {
		this.value = initValue;
	}
	
	public double getCurrentValue() {
		return value;
	}
	
	@ScheduledMethod(start=0,interval=3)
	public void emitValue() {
		// TODO: generate meaningful value
		this.value = 1.0;
		
		this.setChanged();
		this.notifyObservers(this.value);
	}
	
	public abstract void visitCell(Cell c);
}
