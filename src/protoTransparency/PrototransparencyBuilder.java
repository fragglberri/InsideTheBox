package protoTransparency;

import antigen.AntigenSource;
import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;
import streams.Danger;
import streams.Safe;
import streams.Stream;

public class PrototransparencyBuilder implements ContextBuilder<Object> {
	public final static String CONTEXT_ID = "ProtoTransparency";
	
	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId(CONTEXT_ID);
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		int cellCount = (Integer) params.getInteger("cells");
		// grid proportional to cell size
		int gridWidth = cellCount;
		int gridHeight = cellCount;
		
		Grid<Object> grid = GridFactoryFinder.createGridFactory(null).createGrid("Grid",
				context, GridBuilderParameters.singleOccupancy2D(new SimpleGridAdder<Object>(),
								new StrictBorders(), gridWidth, gridHeight));

		Stream ss = new Safe(1.0);
		Stream ds = new Danger(1.0);
		AntigenSource as = new AntigenSource();
		
		context.add(ss);
		context.add(ds);
		
		context.add(as);
		
		for (int i = 0; i < cellCount; ++i) {
			Cell c = new Cell(as, cellCount);
			
			// add this cell as an observer to the stream s
			ss.addObserver(c);
			ds.addObserver(c);
			
			context.add( c );
			
			int gridPosX = i / cellCount;
			int gridPosY = Math.floorMod(i, cellCount);
			
			grid.moveTo(c, gridPosX, gridPosY);
		}
		
		return context;
	}
}
