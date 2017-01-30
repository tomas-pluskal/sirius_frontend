package de.unijena.bioinf.sirius.gui.actions;
/**
 * Created by Markus Fleischauer (markus.fleischauer@gmail.com)
 * as part of the sirius_frontend
 * 29.01.17.
 */

import de.unijena.bioinf.sirius.gui.fingerid.FingerIdDialog;
import de.unijena.bioinf.sirius.gui.utils.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static de.unijena.bioinf.sirius.gui.mainframe.MainFrame.MF;

/**
 * @author Markus Fleischauer (markus.fleischauer@gmail.com)
 */
public class ComputeCSIAction extends AbstractAction {

    public ComputeCSIAction() {
        super("CSI:FingerId");
        putValue(Action.LARGE_ICON_KEY, Icons.FINGER_32);
        putValue(Action.SMALL_ICON, Icons.FINGER_16);
        putValue(Action.SHORT_DESCRIPTION,"Search all computed Experiments with CSI:FingerID");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final FingerIdDialog dialog = new FingerIdDialog(MF, MF.getCsiFingerId(), null, true);
        final int returnState = dialog.run();
        if (returnState == FingerIdDialog.COMPUTE_ALL) {
            MF.getCsiFingerId().computeAll(MF.getCompounds());
        } else if (returnState == FingerIdDialog.COMPUTE) {
            MF.getCsiFingerId().computeAll(MF.getCompoundView().getSelectedValuesList());
        }
    }
}