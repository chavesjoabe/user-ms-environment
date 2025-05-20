package org.joabe;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine.Command;

@TopCommand
@Command(name = "producer-command", subcommands = { ProducerUserIdsCommand.class })
public class ProducerCommand {
}
