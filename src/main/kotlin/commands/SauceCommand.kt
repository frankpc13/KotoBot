package commands

import com.github.insanusmokrassar.SauceNaoAPI.SauceNaoAPI
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import com.tylerthrailkill.helpers.prettyprint.pp
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Color
import java.util.concurrent.TimeUnit

class SauceCommand(waiter: EventWaiter): Command() {

    private var waiter: EventWaiter
    private val key = "c553b96ce100f4077265dc5af1b7f0bf55fdfe3e"
    private val api = SauceNaoAPI(key)

    init {
        this.name = "sauce"
        this.help = "Gets sauce from sauce nao"
        this.botPermissions = arrayOf(Permission.MESSAGE_ATTACH_FILES)
        this.guildOnly = false
        this.waiter = waiter
    }

    override fun execute(event: CommandEvent?) {
        event?.reply("Hi! send me your sauce AKA some image 😉")
        waiter.waitForEvent(
            MessageReceivedEvent::class.java,
            //make sure its by the same user, and in the same channel, and for safety, a different message
            { e: MessageReceivedEvent ->
                e.author == event!!.author && e.channel == event.channel && e.message != event.message
            },
            { e: MessageReceivedEvent ->
                val requestUrl = e.message.attachments[0].url
                //searchImageSauce(requestUrl, event!!)
            }, 30, TimeUnit.SECONDS,
            { event!!.reply("Sorry, you took too long.") })
    }

    private suspend fun searchImageSauce(url: String, event: CommandEvent) {
        api.use {
            val data = it.request(url)!!
            pp(data)
            val item = data.results[0].data
            event.reply(
                EmbedBuilder()
                    .setColor(Color.CYAN)
                    .setThumbnail(data.results[0].header.thumbnail)
                    .setTitle(item.title)
                    .setDescription(item.extUrls[0])
                    .build()
            )
        }
    }

}