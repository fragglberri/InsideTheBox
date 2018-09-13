package protoTransparency;

import java.util.Observable;
import java.util.Observer;

import antigen.Antigen;
import antigen.AntigenSource;
import repast.simphony.engine.schedule.ScheduledMethod;
import streams.Safe;
import streams.Danger;
import streams.Stream;

public class Cell implements Observer {

	private double s;
	private double d;
	
	private double k;
	private double m;
	
	private double lifespan;
	
	private AntigenSource as;
	
	public Cell(AntigenSource as, int cellCount) {
		this.as = as;
		this.lifespan = Math.random() * cellCount;
	}
	
	@ScheduledMethod(start=0, interval=1.0)
	public void grabAntigen() {
		int r = (int) (Math.random() * 5.0);
		
		//System.out.println("Grabbing Antigens...");
		for (int i = 0; i < r; ++i) {
			Antigen a = as.popAntigen();
			if (null == a) 
				break;
			
			//System.out.println("Grabbed " + a);
		}
	}
	
	@Override
	public void update(Observable o, Object v) {
		double kPre = this.k;
		double mPre = this.m;
		
		Stream s = (Stream) o;
		// visitor pattern: double dispatch, updates either d or s depending on the type of the stream
		s.visitCell(this);
		
		this.k = kPre + (this.d - 2 * this.s);
		this.m = mPre + (this.d + this.s);
		
		this.lifespan -= this.m;
		
		// goes to analysis
		if (0 >= this.lifespan) {
			
		}
	}

	public double getLifespan() {
		return this.lifespan;
	}
	
	public void streamValue(Safe safeStream) {
		this.s = safeStream.getCurrentValue();
	}

	public void streamValue(Danger dangerStream) {
		this.d = dangerStream.getCurrentValue();
	}
}
