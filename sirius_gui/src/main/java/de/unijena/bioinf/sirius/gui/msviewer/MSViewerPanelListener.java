package de.unijena.bioinf.sirius.gui.msviewer;

import java.util.List;

public interface MSViewerPanelListener {

	void peaksMarked(List<Integer> indices);

	void peaksMarkedPerDrag(List<Integer> indices);

//	public void peaksSelected(List<Integer> indices);

	void markingsRemoved();

}
