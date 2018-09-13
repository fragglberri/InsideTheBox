package streams;

import protoTransparency.Cell;

public class Danger extends Stream {

	public Danger(double initValue) {
		super(initValue);
	}

	@Override
	public void visitCell(Cell c) {
		c.streamValue(this);
	}

}

