package de.unijena.bioinf.fingerid.jjobs;

import de.unijena.bioinf.ChemistryBase.chem.MolecularFormula;
import de.unijena.bioinf.ChemistryBase.fp.ProbabilityFingerprint;
import de.unijena.bioinf.ChemistryBase.ms.ft.FTree;
import de.unijena.bioinf.fingerid.net.PredictionJJob;
import de.unijena.bioinf.jjobs.BasicDependentJJob;
import de.unijena.bioinf.jjobs.JJob;
import de.unijena.bioinf.sirius.IdentificationResult;
import de.unijena.bioinf.sirius.IdentificationResultAnnotationJJob;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class FingerprintDependentJJob<R> extends BasicDependentJJob<R> implements IdentificationResultAnnotationJJob<R> {
    protected IdentificationResult identificationResult;
    protected ProbabilityFingerprint fp;
    protected MolecularFormula formula;
    protected FTree resolvedTree;

    protected FingerprintDependentJJob(JobType type, IdentificationResult result, ProbabilityFingerprint fp) {
        super(type);
        this.identificationResult = result;
        this.fp = fp;
    }

    protected void initInput() throws ExecutionException {
        if (identificationResult == null || fp == null) {
            final List<JJob<?>> requiredJobs = getRequiredJobs();
            for (JJob j : requiredJobs) {
                if (j instanceof PredictionJJob) {
                    PredictionJJob job = ((PredictionJJob) j);
                    if (job.result != null && job.awaitResult() != null) {
                        identificationResult = job.result;
                        fp = job.awaitResult();
                        resolvedTree = job.ftree;
                        formula = job.ftree.getRoot().getFormula();

                        return;
                    }
                }
            }
            throw new IllegalArgumentException("No Input Data found. " + requiredJobs.toString());
        }
    }

    @Override
    public IdentificationResult getIdentificationResult() {
        return identificationResult;
    }
}
