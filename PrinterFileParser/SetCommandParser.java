package PrinterFileParser;

/**
 * Created by darwinmorales on 21/08/2016.
 */
public class SetCommandParser {
    private String commandModifier = "";
    private String commandModifierValue = "";
    private String optionName = "";
    private String optionNameValue = "";
    private String setStatement = "";

    public SetCommandParser(String setStatement) {
        this.setStatement = setStatement;
    }

    /*
        parseSetStatement("@PJL SET BANNERPAGEPRINT");
        parseSetStatement("@PJL SET LPARM:ABC BANNERPAGEPRINT");
        parseSetStatement("@PJL SET LPARM:ABC BANNERPAGEPRINT=OFF");
        parseSetStatement("@PJL SET LPARM : ABC BANNERPAGEPRINT = OFF");
        parseSetStatement("@PJL SET PRINTINFO=\"DUPLEX_NUP=00000001/SCALING=3/OTHER=00000000\"");
        parseSetStatement("@PJL SET PRINTINFO = \"DUPLEX_NUP=00000001/SCALING=3/OTHER=00000000\"");
        parseSetStatement("@PJL SET BANNERPAGEPRINT");
     */

    public void parse() {
        String[] tokens = setStatement.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].indexOf(':') == 0) {
                commandModifier = tokens[i - 1];
                commandModifierValue = tokens[i + 1];
            } else if (tokens[i].indexOf(':') > 0) {
                if (commandModifier == null) {
                    int pos = tokens[i].indexOf(':');
                    commandModifier = tokens[i].substring(0, pos);
                    commandModifierValue = tokens[i].substring(pos + 1);
                }
            } else if (tokens[i].indexOf('=') == 0) {
                optionName = tokens[i - 1];
                optionNameValue = tokens[i + 1];
            } else if (tokens[i].indexOf('=') > 0) {
                if (optionName == null) {
                    int pos = tokens[i].indexOf('=');
                    optionName = tokens[i].substring(0, pos);
                    optionNameValue = tokens[i].substring(pos + 1);
                }
            }
        }
        if (optionName == null) {
            if (!(tokens[tokens.length - 1].equalsIgnoreCase("@PJL") ||
                    tokens[tokens.length - 1].equalsIgnoreCase("SET"))
                    ) {
                optionName = tokens[tokens.length - 1];
            }
        }
    }

    public String getCommandModifier() {
        return commandModifier;
    }

    public String getCommandModifierValue() {
        return commandModifierValue;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getOptionNameValue() {
        return optionNameValue;
    }
}
