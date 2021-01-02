package org.photo.acg.photo

import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import org.photo.acg.photo.dao.AcgPhotoDao

@ConsoleExperimentalApi
object AcgPhotoPluginMain : KotlinPlugin(JvmPluginDescription.loadFromResource()) {

    private suspend inline fun send(group: Group, messageChain: String, bot: Bot) {
        launch { bot.getGroup(group.id)?.sendMessage(messageChain) }
    }

    private fun setupMsgHandle() {
        globalEventChannel().subscribeGroupMessages {
            always {
                if (message.contentToString() == "acg") {
                    send(group, "你好", bot)
                }
                if (message.contentToString() == "色图") {
                    val acgPhoto = AcgPhotoDao.getAcgPhoto()
                    if(acgPhoto.getCode()==1) {
                        acgPhoto.getData()?.get(0)?.let { it1 -> it1.url?.let { it2 -> send(group, it2, bot) } }
                    }else{
                        acgPhoto.getMsg()?.let { it1 -> send(group, it1,bot) }
                    }
                }
                if (message.contentToString() == "nn" || message.contentToString() == "牛子哥") {
                    send(group, "那你能帮帮我吗", bot)
                }
            }
        }
    }

    override fun onEnable() {
        super.onEnable()
        setupMsgHandle()
    }

    override fun onDisable() {
        super.onDisable()
    }
}