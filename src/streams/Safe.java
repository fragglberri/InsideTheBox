package streams;

import protoTransparency.Cell;

public class Safe extends Stream {

	public Safe(double initValue) {
		super(initValue);
	}

	@Override
	public void visitCell(Cell c) {
		c.streamValue(this);
	}

}
