package Temperature.command_processer;


public class CommandProcessor {

    private static CommandProcessor commandProcessorRemote = null;

    private CommandProcessor(){}

    public static CommandProcessor makeCommandProcessor(){
        if(commandProcessorRemote == null){
            commandProcessorRemote = new CommandProcessor();
        }
        return commandProcessorRemote;
    }

    //method
    public void execute(Command cmd) {
        cmd.execute();
    }

}