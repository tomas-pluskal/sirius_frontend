package de.unijena.bioinf.sirius.gui.mainframe.experiments;
/**
 * Created by Markus Fleischauer (markus.fleischauer@gmail.com)
 * as part of the sirius_frontend
 * 26.01.17.
 */

import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.swing.DefaultEventSelectionModel;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import de.unijena.bioinf.sirius.gui.structure.ExperimentContainer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.LinkedList;
import java.util.List;

import static de.unijena.bioinf.sirius.gui.mainframe.Workspace.COMPOUNT_LIST;

/**
 * @author Markus Fleischauer (markus.fleischauer@gmail.com)
 */
public class ExperimentList {



    final JTextField searchField;
    final FilterList<ExperimentContainer> compoundList;
    final DefaultEventSelectionModel<ExperimentContainer> compountListSelectionModel;

    private final List<ExperimentListChangeListener> listeners = new LinkedList<>();

    public ExperimentList() {
        searchField = new JTextField();



        compoundList = new FilterList<>(new ObservableElementList<>(COMPOUNT_LIST, GlazedLists.beanConnector(ExperimentContainer.class)),
                new TextComponentMatcherEditor<>(searchField, new TextFilterator<ExperimentContainer>() {
                    @Override
                    public void getFilterStrings(List<String> baseList, ExperimentContainer element) {
                        baseList.add(element.getGUIName());
                        baseList.add(element.getIonization().toString());
                        baseList.add(String.valueOf(element.getFocusedMass()));
                    }
                }, true));


        compountListSelectionModel = new DefaultEventSelectionModel<>(compoundList);

        compountListSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    notifyListenerSelectionChange();
                }
            }
        });
        compoundList.addListEventListener(new ListEventListener<ExperimentContainer>() {
            @Override
            public void listChanged(final ListEvent<ExperimentContainer> listChanges) {
                notifyListenerDataChange(listChanges);
            }
        });
    }

    private void notifyListenerDataChange(ListEvent<ExperimentContainer> event) {
        for (ExperimentListChangeListener l : listeners) {
            event.reset();//this is hell important to reset the iterator
            l.listChanged(event,compountListSelectionModel);
        }
    }

    private void notifyListenerSelectionChange() {
        for (ExperimentListChangeListener l : listeners) {
            l.listSelectionChanged(compountListSelectionModel);
        }
    }

    //API methods
    public void addChangeListener(ExperimentListChangeListener l) {
        listeners.add(l);
    }

    public void removeChangeListener(ExperimentListChangeListener l) {
        listeners.remove(l);
    }

    public DefaultEventSelectionModel<ExperimentContainer> getCompoundListSelectionModel() {
        return compountListSelectionModel;
    }

    public FilterList<ExperimentContainer> getCompoundList() {
        return compoundList;
    }


}