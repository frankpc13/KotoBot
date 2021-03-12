import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import com.jagrosh.jdautilities.examples.command.AboutCommand
import com.jagrosh.jdautilities.examples.command.PingCommand
import com.jagrosh.jdautilities.examples.command.ShutdownCommand
import commands.CatCommand
import commands.PurifyChatFromBotsCommand
import commands.SauceCommand
import commands.SayCommand
import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import java.awt.Color
import net.dv8tion.jda.api.Permission
import java.util.*


fun main() {
    //JDA-Utilities
    // define an eventwaiter, dont forget to add this to the JDABuilder!
    val waiter = EventWaiter()
    val client = CommandClientBuilder()
    client.useDefaultGame()
    client.setEmojis("😄","🤨","😭")
    client.setPrefix("!koto")
    client.setOwnerId("199802242038104064")
    // add  s commands
    client.addCommands( // command to show information about the bot
        AboutCommand(
            Color.BLUE,
            "an example bot",
            arrayOf("Cool commands", "Nice examples", "Lots of fun!"),
            Permission.ADMINISTRATOR
        ),
        // command to show a random cat
        CatCommand(),
        // command to make a random choice
        //ChooseCommand(),
        // command to say hello
        //HelloCommand(waiter),
        // command to check bot latency
        PingCommand(),
        // command to shut off the bot
        ShutdownCommand(),
        SauceCommand(waiter),
            PurifyChatFromBotsCommand(),
            SayCommand()
    )
    val dotenv = Dotenv.load()
    JDABuilder.createDefault(dotenv["DISCORD_TOKEN"])
            .addEventListeners(waiter)
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.playing("loading..."))
            .addEventListeners(waiter, client.build())
            .build()

    //deprecated
   /* JDABuilder(AccountType.BOT)
        .setToken(token)
        .setStatus(OnlineStatus.DO_NOT_DISTURB)
        .setActivity(Activity.playing("loading..."))
        .addEventListeners(waiter, client.build())
        .build()*/
}

