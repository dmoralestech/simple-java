package PrinterFileParser;

/**
 * Created by darwinmorales on 21/08/2016.
 */
public class SetCommandParser {

    //TODO: more testing
    final static String PJL_SET = "@PJL SET ";
    private String commandModifierClause = null;
    private String optionName = null;
    private String optionNameValue = null;
    private String setStatement = null;

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
//        SetCommandParser parser = new SetCommandParser("@PJL SET LPARAM:ABC BANNERPAGEPRINT = OFF");
//        parser.parse();
//
//        parser = new SetCommandParser("@PJL SET LPARAM : ABC BANNERPAGEPRINT=OFF");
//        parser.parse();
//
//        parser = new SetCommandParser("@PJL SET LPARAM : ABC BANNERPAGEPRINT");
//        parser.parse();
//
//        parser = new SetCommandParser("@PJL SET BANNERPAGEPRINT=\"RICOH Aficio MP C3002 PCL 6\"");
//        parser.parse();
//
//        parser = new SetCommandParser("@PJL SET BANNERPAGEPRINT= \"RICOH Aficio MP C3002 PCL 6\"");
//        parser.parse();
     */

    public void parse() {
        if (setStatement != null && setStatement.startsWith(PJL_SET)) {
            int posEqualSign = setStatement.indexOf('=');
            int startOfOptionNamePos;
            if (posEqualSign > 0) {
                optionNameValue = setStatement.substring(posEqualSign + 1).trim();

                if (setStatement.substring(posEqualSign - 1, posEqualSign).equals(" ")) {
                    startOfOptionNamePos = setStatement.lastIndexOf(" ", posEqualSign - 2);
                    optionName = setStatement.substring(startOfOptionNamePos + 1, posEqualSign - 1);
                } else {
                    startOfOptionNamePos = setStatement.lastIndexOf(" ", posEqualSign - 1);
                    optionName = setStatement.substring(startOfOptionNamePos + 1, posEqualSign);
                }
            } else {
                startOfOptionNamePos = setStatement.lastIndexOf(" ");
                optionName = setStatement.substring(startOfOptionNamePos + 1);
            }
            if (startOfOptionNamePos >= PJL_SET.length()) {
                commandModifierClause = setStatement.substring(PJL_SET.length(), startOfOptionNamePos );
            }
        }
    }

    public String getCommandModifierClause() {
        return commandModifierClause;
    }

    public String getOptionName() {
        return optionName;
    }

    public String getOptionNameValue() {
        return optionNameValue;
    }
}
