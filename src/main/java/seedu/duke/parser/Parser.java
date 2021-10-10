package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.SearchMapCommand;
import seedu.duke.commands.AddModCommand;
import seedu.duke.commands.AddUniCommand;
import seedu.duke.modules.Module;
import seedu.duke.universities.UniversityList;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    protected UniversityList universityMasterList;
    protected ArrayList<Module> moduleMasterList;

    public Parser(UniversityList universityMasterList, ArrayList<Module> moduleMasterList) {
        this.universityMasterList = universityMasterList;
        this.moduleMasterList = moduleMasterList;
    }

    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException("matcher class exception", 1);
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);
        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);
        case SearchMapCommand.COMMAND_WORD:
            return new SearchMapCommandParser().parse(arguments, universityMasterList);
        case AddModCommand.COMMAND_WORD:
            return new AddModCommandParser().parse(arguments, moduleMasterList);
        case AddUniCommand.COMMAND_WORD:
            return new AddUniCommandParser().parse(arguments, universityMasterList);
        default:
            throw new ParseException("Command not found", 1);
        }
    }
}
