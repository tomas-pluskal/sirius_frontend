package de.unijena.bioinf.sirius.gui.actions;
/**
 * Created by Markus Fleischauer (markus.fleischauer@gmail.com)
 * as part of the sirius_frontend
 * 29.01.17.
 */

import de.unijena.bioinf.sirius.gui.dialogs.SettingsDialog;
import de.unijena.bioinf.sirius.gui.configs.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static de.unijena.bioinf.sirius.gui.mainframe.MainFrame.MF;

/**
 * @author Markus Fleischauer (markus.fleischauer@gmail.com)
 */
public class ShowSettingsDialogAction extends AbstractAction {
    public ShowSettingsDialogAction() {
        super("Settings");
        putValue(Action.LARGE_ICON_KEY, Icons.GEAR_32);
        putValue(Action.SHORT_DESCRIPTION,"Open Sirius settings");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new SettingsDialog(MF);
    }
}
