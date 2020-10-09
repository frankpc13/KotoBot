package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import kotlinx.coroutines.*
import models.DataResponseCats
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.ChannelType
import network.AnimeDetectionAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.awt.Color

class CatCommand: Command() {
    init {
        this.name = "cat"
        this.help = "shows a random cat"
        this.botPermissions = arrayOf(Permission.MESSAGE_EMBED_LINKS)
        this.guildOnly = false
    }

    override fun execute(event: CommandEvent?) {
        runBlocking {
            val image = call()
            event?.reply(EmbedBuilder()
                .setColor(Color.GREEN)
                .setImage(image)
                .build())
        }
    }

    private suspend fun call(): String {
        return AnimeDetectionAPI.meowClient.getRandomCatAsync().await().file
    }
}