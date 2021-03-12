package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.Permission

class SayCommand: Command() {

    init {
        this.name = "say"
        this.help = "Tell to the bot what to say"
        this.botPermissions = arrayOf(
                Permission.ADMINISTRATOR,
                Permission.MESSAGE_HISTORY,
                Permission.MESSAGE_MANAGE
        )
        this.guildOnly = false
    }

    override fun execute(event: CommandEvent?) {
        val message = event!!.message.contentRaw.removePrefix("!kotosay ")
        event!!.reply(message)
    }

}