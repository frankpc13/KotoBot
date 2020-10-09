package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.tylerthrailkill.helpers.prettyprint.pp
import net.dv8tion.jda.api.Permission

class PurifyChatFromBotsCommand: Command() {

    init {
        this.name = "purify"
        this.help = "Deletes chat generated from bots"
        this.botPermissions = arrayOf(Permission.ADMINISTRATOR, Permission.MESSAGE_HISTORY, Permission.MESSAGE_MANAGE)
        this.guildOnly = false
    }

    override fun execute(event: CommandEvent?) {
        if (event?.member!!.id == "199802242038104064" || event.member.id == "342864920687280128") {
            val member = event?.textChannel?.members?.find { x -> x.id == "235088799074484224" }
            event!!.replyWarning("Starting delete process")
            if (member != null) {
                //val messages = event.textChannel?.history?.retrievedHistory?.filter { x -> x.member!!.id == member.id}
                event.textChannel?.getHistoryBefore(event.message, 100)!!.queue {
                    for (message in it.retrievedHistory) {
                        if (message.member!!.id == member!!.id) {
                            pp(message.contentDisplay)
                            message.delete().queue()
                        }
                    }
                }

            }
            print(event?.textChannel?.members?.find { x -> x.id == "235088799074484224" })
            event!!.replySuccess("Finish cleaning task!")
        } else {
         event.replyError("Need to be admin or related u moron!")
        }
    }

}