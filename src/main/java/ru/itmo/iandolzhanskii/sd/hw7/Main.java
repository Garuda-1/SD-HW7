package ru.itmo.iandolzhanskii.sd.hw7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import ru.itmo.iandolzhanskii.sd.hw7.aspect.Profiler;
import ru.itmo.iandolzhanskii.sd.hw7.distribution.NormalDistributionSelector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final NormalDistributionSelector normalDistributionSelector;
    private final Profiler profiler;

    public Main(NormalDistributionSelector normalDistributionSelector, Profiler profiler) {
        this.normalDistributionSelector = normalDistributionSelector;
        this.profiler = profiler;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("mean", true, "Mean of normal ru.itmo.iandolzhanskii.sd.hw7.distribution to sample");
        options.addOption("sdev", true, "Standard deviation of normal ru.itmo.iandolzhanskii.sd.hw7.distribution to sample");
        options.addOption("samples", true, "Count of samples");
        options.addOption("profile_target_package", true, "Package to profile");

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        double mean = Double.parseDouble(commandLine.getOptionValue("mean"));
        double sdev = Double.parseDouble(commandLine.getOptionValue("sdev"));
        long samples = Long.parseLong(commandLine.getOptionValue("samples"));
        String profileTargetPackage = commandLine.getOptionValue("profile_target_package");
        if (profileTargetPackage == null) {
            profileTargetPackage = Main.class.getPackageName();
        }

        profiler.setTargetPackage(profileTargetPackage);

        normalDistributionSelector.sample(mean, sdev, samples);

        profiler.renderStatistics();
    }
}
