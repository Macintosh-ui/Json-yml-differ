package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Parameters(paramLabel = "filepath1", description = "path to the first file.")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to the second file.")
    private String filepath2;
    @Option(names = {"-f", "--outputFormat"}, defaultValue = "stylish",
            description = "output outputFormat [default: stylish]", paramLabel = "outputFormat")
    private String outputFormat;
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (outputFormat.equals("stylish")) {
            Differ.generate(filepath1, filepath2);
        } else {
            Differ.generate(filepath1, filepath2, outputFormat);
        }
        return 1;
    }
}
