package com.Java;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;

public class BotInitializer extends ListenerAdapter {

    public static int[] own = new int[65];

    // Constructor to initialize the array
    public static void main(String[] args) {
        try {
            // Retrieve the bot token
            for (int i = 0; i < 64; i++) {
                if (i == 0) {
                    own[0] = 1; 
                }else {
                    own[i] = 0;
                }

            }

            String botToken = "BOTTOKEN";

            JDABuilder builder = JDABuilder.createDefault(botToken)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT);

            // Build the bot instance
            var jda = builder.build();

            // Wait for the bot to initialize
            jda.awaitReady();

            // Get the guild by its ID
            Guild guild = jda.getGuildById("1359157677292261449"); // Replace YOUR_GUILD_ID with the ID of your Discord
            // server

            if (guild != null) {
                guild.updateCommands().addCommands(
                        Commands.slash("deck", "Generate a private deck message"),
                        Commands.slash("play", "Start a game with specific parameters")
                                .addOption(OptionType.INTEGER, "number", "Enter a number", true) // First required option
                ).queue(
                        (__) -> System.out.println("Slash commands registered successfully."),
                        (error) -> System.err.println("Failed to register slash commands: " + error.getMessage())
                );
            } else {
                System.err.println("Guild not found. Ensure the bot is in the server.");
            }

            // Register event listeners
            jda.addEventListener(new BotInitializer());

            System.out.println("Bot is now initialized!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        
        String[] cars = "Corolla, Civic, Sentra, Altima, Camry, Accord, Elantra, Fusion, Cruze, Mazda3, Impreza, Lancer, 370Z, G35, Sienna, Odyssey, Sportage, Rogue, Murano, Eclipse Cross, Crosstrek, Forester,  Lancer Evolution, WRX STI, Civic Type R, GR Corolla, GR86, Mustang GT, MX5 Miata, Camaro, 370Z Nismo, Challenger Scat Pack, Charger Scat Pack, S5, Ioniq 5 N, Supra MK5, I8, X3,  X5, Macan, Cayenne, GL350, M3, C63 AMG, C43 AMG, M4, GTR, AMG GTS, Emira, Evora, Mustang Dark Horse, M5, 718 Cayman, 718 Boxster, Cayman GT4 RS, Boxster Spyder RS, Gallardo, R8 V8, AMG GTR, 911 Carerra, M5 Competition, S63 AMG, R8 V10, Huracan, 570S, 600 LT, 911 GT3, 296 GTB, 458 Italia, 488 GTB, GTR Nismo, Viper ACR, 720S, AMG GT Black Series, SLR Mclaren, SF90 Stradale, Aventador, F8 Tributo, 911 GT3 RS, Huracan STO, Revuelto, Senna, Aventador SV, 812 Competizione, P1, Laferrari, 918 Spyder, Veyron 16.4, Agera, Huayra"
                .split(", ");
        String[] top = "190, 200, 190, 210, 207, 200, 200, 205, 200, 200, 185, 195, 195, 195, 195, 195, 185, 195, 250, 255, 270, 250, 250, 255, 250, 280, 280, 250, 250, 250, 250, 280, 270, 280, 290, 250, 325, 310, 290, 280, 280, 315, 308, 325, 318, 293, 314, 320, 330, 330, 328, 318, 325, 330, 330, 290, 341, 334, 325, 350, 340, 340, 296, 310, 325, 335, 350, 340, 350, 350, 350, 407, 440, 385"
                .split(", ");
        String[] accel = "8.5-10, 7.5-9, 8-9, 7-8.5, 7-8.5, 8-9, 7.5-9, 7-8.5, 8.5-9.5, 7.5-9, 8-9, 8-9, 8-9.5, 8.5-9.5, 8-9.5, 8-9, 9-10, 8-9, 5-5.4, 5.7, 5.5-5.9, 4.3-4.5, 4-4.3, 5, 4.3-4.5, 4.2-4.5, 4-4.5, 5, 4.1-4.3, 4.4, 3.8, 4.3, 4.1, 3.9-4.1, 4.6, 2.9-3.2, 3.8, 4.3, 4.7-5.1, 4.7-5.1, 3.4, 3.4, 3.7, 3.6, 4.2, 3.6-3.8, 3.7, 3.2, 2.9-3.2, 2.9, 3.4, 3.4, 2.9, 2.7-2.9, 3.5, 2.8, 3.4, 3.2, 2.8, 2.5, 2.9, 3.2, 3, 3.7, 2.8, 2.8, 2.8-2.9, 2.8, 2.6, 2.5, 2.5, 2.8, 2.8-3.2"
                .split(", ");
        System.out.println("Command received: " + event.getName()); // Log the command name

        if (event.getName().equals("deck")) {
            // Handle the /deck command

            // Loop through cars and build embed messages
            for (int i = 0; i < own.length; i++) {
                if (own[i] == 1) {
                    int level = 0;
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle(cars[i]); // Title of the car
                    if (i <= 10) {
                        level = 0; 
                    }else if (i <= 19) {
                        level = 1; 
                    }else if (i <= 35) {
                        level = 2; 
                    }else if (i <= 32) {
                        level = 3; 
                    }else if (i <= 39) {
                        level = 4; 
                    }else if (i <= 48) {
                        level = 5; 
                    }else if (i <= 56) {
                        level = 6; 
                    }else if (i <= 65) {
                        level = 7;
                    }
                    embed.setDescription("Level: " + level); // Subtitle

                    embed.addField("Stats",
                            "Top Speed: " + top[i] + "\n"
                            + "0 to 100 Speed: " + accel[i],
                            false);
                    embed.setColor(Color.ORANGE);

                    // Build the file path dynamically
                    File image = new File("demo/src/Resources/" + i + ".png");

                    // Check if the file exists before attempting to send it
                    if (!image.exists()) {
                        event.reply("File not found: " + image.getAbsolutePath()).setEphemeral(true).queue();
                        continue;
                    }

                    // Send the embed and attach the image
                    event.replyFiles(FileUpload.fromData(image)).setEmbeds(embed.build()).setEphemeral(true).queue();
                }
            }
        }
        if (event.getName().equals("play")) {
            int repeat = event.getOption("number").getAsInt();
        
            // Check bounds to prevent ArrayIndexOutOfBoundsException
            if (repeat > own.length || repeat > cars.length) {
                event.reply("The number you entered exceeds the available cars. Please try again with a smaller number.")
                     .setEphemeral(true)
                     .queue();
                return;
            }
        
            // Defer reply to prevent timeout
            event.deferReply().setEphemeral(true).queue();
        
            Random rng = new Random(); // Initialize RNG
        
            for (int i = 0; i < repeat; i++) {
                int loop = 0;
                int roll = 0;
                do {
                    roll = rng.nextInt(66); // Generate a random number between 0 and 65
                    if (roll > 19 || loop != -1) {
                        if (loop < 3) {
                            loop++;
                        }
                    } else {
                        loop = -1; // Exit condition
                    }
                } while (loop != -1);
        
                // Send response for each iteration to reduce memory overhead
                if (own[i] != 1) {
                    own[i] = 1; // Mark the car as owned
                    event.getHook().sendMessage("You rolled a " + i + " and received a " + cars[i] + "!").queue();
                } else {
                    event.getHook().sendMessage("You rolled a " + i + ", but you already have this car.").queue();
                }
            }
        }
        
    }
}

/*
 * event.getChannel().sendMessage(cars[i] + "").queue();
 * File file = new File("demo/src/Resources/" + i + ".png");
 * if (!file.exists()) {
 * event.getChannel().sendMessage("File not found: " +
 * file.getAbsolutePath()).queue();
 * return; // Stop execution if the file doesn't exist
 * }
 * // Upload the file
 * event.getChannel().sendFiles(net.dv8tion.jda.api.utils.FileUpload.fromData(
 * file)).queue();
 * 
 * 
 * 
 */
