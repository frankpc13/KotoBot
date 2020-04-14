import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Handler : ListenerAdapter {

    private var channelId: Long = 0
    private var authorId: Long = 0

    constructor()

    constructor(channel: MessageChannel, author: User) {
        this.authorId = author.idLong
        this.channelId = channel.idLong
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        println("se recibio un mensaje de ${event.author.name}: ${event.message.contentDisplay}")
        //lets use raw so we have content exactly as the user sent it
        /*if (event.message.contentRaw == "!ping"){
            //remember to call queue()!
            //otherwise our message will never be sent
            event.channel.sendMessage("Pong!").queue()
        }*/
        val author = event.author
        val channel = event.channel
        val prefix = event.message.contentRaw.take(2)

        if(prefix == "y!"){
            val command = event.message.contentRaw.substringAfter(prefix)
            when(command){
                "saluda"->{
                    event.channel.sendMessage("Hola ${author.name} https://media1.tenor.com/images/84b4261485992333a4ef40c988c1684d/tenor.gif?itemid=5931393").queue()
                }
                "detecta"->{
                    event.channel.sendMessage("Enviame las pruebas...").queue()
                    //create an instance for only that channel & author
                    event.jda.addEventListener(Handler(channel, author))
                    if (this.authorId == author.idLong && channel.idLong == this.channelId) {
                        if (event.message.attachments.first().isImage) {
                            val image = event.message.attachments.first().downloadToFile().get()
                        }
                    }
                }
            }
        }
    }
}