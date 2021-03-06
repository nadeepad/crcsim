package org.renci.epi.util;

import java.io.File;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
import org.apache.commons.lang3.StringUtils;
import java.io.FilenameFilter;

/**
 *
 * Provide services for locating data.
 *
 */
public class DataLocator {

    private String _dataRoot = null;

    public DataLocator () {
	this.setDataRoot ( new String [] { "", "dev", "var", "crcsim" } );
    }

    // set the root folder to which others are relative
    public void setDataRoot (String [] parts) {
	_dataRoot = StringUtils.join (parts, File.separatorChar);
    }

    // file generated by joining rti synthetic population and pums.
    public String getSyntheticPopulationPath (String fileName) {
	return this.join (new String [] { _dataRoot, "generated", fileName });
    }
    public File [] getSyntheticPopulationExports () {
	final String pattern = "export.*";
	File directory = new File (join (new String [] { _dataRoot, "generated", "exports" })); //fileName }));
	FilenameFilter filenameFilter = new FilenameFilter () {
		public boolean accept (File dir, String name) {
		    return name.toLowerCase().matches (pattern);
		}
	    };
	return directory.listFiles (filenameFilter);
    }
    // input to and output files from the RTI ABM
    public String getModelOutputPath () {
	return this.join (new String [] { _dataRoot, "model", "output" } );
    }
    public String getModelInputFileName () {
	return this.join (new String [] { _dataRoot, "..", "crcsim", "model", "population.tsv" });
    }
    public String getModelInputPath () {
	return this.join (new String [] { _dataRoot, "..", "crcsim", "model", "pops" });
    }    
    public String getModelInputFileName (String inputFileName) {
	return this.join (new String [] { _dataRoot, "..", "crcsim", "model", "pops", inputFileName });
    }

    // geocoding census shapefile data
    public String getCountyPolygonFileName () {
	return this.join (new String [] { _dataRoot, "census2010", "tl_2010_37_county10.shp" });
    }

    // geocoding census shapefile data
    public String getCensusBlockPolygonFileName () {
	return this.join (new String [] { _dataRoot, "census2010", "tl_2010_37_tabblock10.shp" });
    }

    // geocoded output data
    public String getGeocodedOutputPath () {
	return this.join ( new String [] { _dataRoot, "generated", "geocoded" } );
    }

    // join
    private String join (String [] parts) {
	return StringUtils.join (parts, File.separatorChar);
    }


}
