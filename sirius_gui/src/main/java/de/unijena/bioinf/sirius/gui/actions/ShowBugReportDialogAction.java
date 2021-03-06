package de.unijena.bioinf.sirius.gui.actions;
/**
 * Created by Markus Fleischauer (markus.fleischauer@gmail.com)
 * as part of the sirius_frontend
 * 29.01.17.
 */

import de.unijena.bioinf.sirius.gui.dialogs.BugReportDialog;
import de.unijena.bioinf.sirius.gui.configs.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static de.unijena.bioinf.sirius.gui.mainframe.MainFrame.MF;

/**
 * @author Markus Fleischauer (markus.fleischauer@gmail.com)
 */
public class ShowBugReportDialogAction extends AbstractAction {

    public ShowBugReportDialogAction() {
        super("Bug Report");
        putValue(Action.LARGE_ICON_KEY, Icons.BUG_32);
        putValue(Action.SHORT_DESCRIPTION,"Report a bug or send a feature request");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new BugReportDialog(MF);
    }
}
