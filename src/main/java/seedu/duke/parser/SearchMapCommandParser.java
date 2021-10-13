package seedu.duke.parser;

import seedu.duke.commands.SearchMapCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMapCommandParser {

    private Logger logger = Logger.getLogger("SearchMapCommandLog");

    public SearchMapCommand parse(String arguments, UniversityList universityMasterList,
                                  UniversityList universitySelectedList, ModuleList moduleSelectedList)
            throws ParseException {
        logger.log(Level.INFO, "start parsing searchmap command");
        String universityName = arguments.trim();
        if (universityName.length() == 0) {
            logger.log(Level.WARNING, "no university given");
            throw new ParseException("no description given", 1);
        }

        University university = searchForUniversity(universityName, universityMasterList);
        if (university == null) {
            logger.log(Level.WARNING, "university not found");
            throw new ParseException("university does not exist", 1);
        }
        assert university.getName() != null;
        logger.log(Level.INFO, "parse success");
        return new SearchMapCommand(university, universitySelectedList, moduleSelectedList);
    }

    private University searchForUniversity(String universityName, UniversityList universityMasterList) {
        for (University university : universityMasterList.getList()) {
            if (universityName.equals(university.getName())) {
                return university;
            }
        }
        return null;
    }
}
