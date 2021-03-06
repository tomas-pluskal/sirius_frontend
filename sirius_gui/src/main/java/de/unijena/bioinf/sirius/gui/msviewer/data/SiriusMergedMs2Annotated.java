package de.unijena.bioinf.sirius.gui.msviewer.data;

import de.unijena.bioinf.ChemistryBase.ms.Deviation;
import de.unijena.bioinf.ChemistryBase.ms.Ms2Experiment;
import de.unijena.bioinf.ChemistryBase.ms.Peak;
import de.unijena.bioinf.ChemistryBase.ms.Spectrum;
import de.unijena.bioinf.ChemistryBase.ms.ft.FTree;
import de.unijena.bioinf.ChemistryBase.ms.utils.SimpleMutableSpectrum;
import de.unijena.bioinf.ChemistryBase.ms.utils.Spectrums;
import de.unijena.bioinf.FragmentationTreeConstruction.model.ProcessedInput;
import de.unijena.bioinf.FragmentationTreeConstruction.model.ProcessedPeak;

import java.util.List;

public class SiriusMergedMs2Annotated extends SiriusSingleSpectrumAnnotated {

    public SiriusMergedMs2Annotated(FTree tree, Ms2Experiment experiment, double minMz, double maxMz) {
        super(tree, merge(experiment, tree), minMz, maxMz);
    }

    public SiriusMergedMs2Annotated(FTree tree, Ms2Experiment experiment) {
        super(tree, merge(experiment, tree));
    }

    private static Spectrum<? extends Peak> merge(Ms2Experiment experiment, FTree tree) {
        ProcessedInput processedInput = tree.getAnnotationOrNull(ProcessedInput.class);
        if (processedInput!=null){
            List<ProcessedPeak> peakList = processedInput.getMergedPeaks();
            SimpleMutableSpectrum spectrum = new SimpleMutableSpectrum();
            for (ProcessedPeak processedPeak : peakList) {
                spectrum.addPeak(processedPeak);
            }
            return spectrum;
        }
        return Spectrums.mergeSpectra(new Deviation(10, 0.1), true, false, experiment.getMs2Spectra());
    }
}
