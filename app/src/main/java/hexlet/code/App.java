package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App {

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested = false;
    @Option(names = { "-v", "--version" }, description = "Print version information and exit.")
    private String version = "gendiff 1.0";
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
