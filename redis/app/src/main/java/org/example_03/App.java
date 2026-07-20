package org.example_03;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.CommandInfo;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("redis://localhost:6379")) {
            Map<String, CommandInfo> commands = jedis.command();
            for (Map.Entry<String, CommandInfo> command : commands.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Command: ").append(command.getKey()).append("\n");
                CommandInfo commandInfo = command.getValue();
                sb.append("Name").append(commandInfo.getName()).append("\n");
                sb.append("Tips: ").append(commandInfo.getTips()).append("\n");
                sb.append("Arity: ").append(commandInfo.getArity()).append("\n");
                sb.append("Flags: ").append(commandInfo.getFlags()).append("\n");
                sb.append("First Key: ").append(commandInfo.getFirstKey()).append("\n");
                sb.append("Last Key: ").append(commandInfo.getLastKey()).append("\n");
                sb.append("Step: ").append(commandInfo.getStep()).append("\n");
                sb.append("Subcommands: ").append(commandInfo.getSubcommands()).append("\n");
                System.out.println(sb);
            }
        }
    }

}
