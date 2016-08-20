package PrinterFileParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class ValidateCommandLine {
    private String[] args;
    private String sourceFullPath;
    private String destFullPath;
    private boolean addNewOptions = false;
    private Map<String, String> optionsArgs = new HashMap<>();

    public ValidateCommandLine(String[] args) {
        this.args = args;
    }

    public boolean parse() throws Exception {

        for (String arg : args) {
            String[] keys = arg.split("=");
            String key = keys[0];
            String value = keys[1];
            if (key.startsWith("-")) {
                key = key.substring(1);
            }
            optionsArgs.put(key.toUpperCase(), value);
        }

        sourceFullPath = optionsArgs.get("SOURCE");
        destFullPath = optionsArgs.get("DESTINATION");
        addNewOptions = Boolean.valueOf(optionsArgs.get("ADDNEW"));

        if (sourceFullPath == null) {
            throw new Exception("Source File Path is empty");
        }

        if (destFullPath == null) {
            throw new Exception("Destination File Path is empty");
        }

        //make sure the source file exists
        if (!(new File(sourceFullPath).exists())) {
            throw new Exception("Source file doesn't exist: " + sourceFullPath);
        }

        //make sure the dest File can be written
        File destFile = new File(destFullPath);
        if (destFile.exists()) {
            destFile.delete();
            if (!destFile.canWrite()) {
                throw new Exception("Cannot write to destination path: " + destFullPath);
            }
        }

        if (optionsArgs.size() < 3) {
            throw new Exception("There were no options passed");
        }

        return true;
    }

    public String getSourceFullPath() {
        return sourceFullPath;
    }

    public String getDestFullPath() {
        return destFullPath;
    }

    public boolean isAddNewOptions() {
        return addNewOptions;
    }

    public Map<String, String> getOptionArgs() {
        return optionsArgs;
    }
}
