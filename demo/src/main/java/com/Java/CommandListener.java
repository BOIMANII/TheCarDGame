package com.Java;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("deck")) { // Check if the command is "deck"
            // Send an ephemeral response
            event.reply("Your deck has been generated!")
                .setEphemeral(true) // This makes the response visible only to the user
                .queue();
        }
    }
}
