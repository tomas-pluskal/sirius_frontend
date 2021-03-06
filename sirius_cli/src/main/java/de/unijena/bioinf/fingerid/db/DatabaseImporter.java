package de.unijena.bioinf.fingerid.db;

import de.unijena.bioinf.ChemistryBase.chem.InChI;
import de.unijena.bioinf.ChemistryBase.fp.CdkFingerprintVersion;
import de.unijena.bioinf.fingerid.net.WebAPI;
import org.openscience.cdk.exception.CDKException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows the import of custom databases
 */
public class DatabaseImporter {


    public DatabaseImporter() {
    }

    public static void importDatabase(String dbPath, List<String> files) {
        final CustomDatabase db = CustomDatabase.createNewdatabase(new File(dbPath).getName(), new File(dbPath), (CdkFingerprintVersion) WebAPI.getFingerprintVersion());
        final List<File> inchiorsmiles = new ArrayList<>();
        for (String f : files) inchiorsmiles.add(new File(f));
        try {
            db.buildDatabase(inchiorsmiles, new CustomDatabase.AbstractImporterListener() {
                @Override
                public void newInChI(InChI inchi) {
                    System.out.println(inchi.in2D + " imported");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CDKException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nDatabase imported. Use --fingerid_db=\"" + dbPath + "\" to search in this database");
    }

}
