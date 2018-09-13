package protoTransparency;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

/**
 * The 2D style for Schelling Model Agents.  
 *
 * @author Eric Tatara
 */

public class CellStyle2D extends DefaultStyleOGL2D {

	@Override
	public Color getColor(Object o) {
		Cell c = (Cell) o;
		
		if (0 >= c.getLifespan())
			return Color.RED;
		else
			return Color.GREEN;
	}
}
