package org.photo.acg.photo

import com.alibaba.fastjson.JSONObject
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.code.parseMiraiCode
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChain
import okhttp3.Request
import org.photo.acg.photo.dao.AcgPhotoDao

@ConsoleExperimentalApi
object AcgPhotoPluginMain : KotlinPlugin(JvmPluginDescription.loadFromResource()) {

    private suspend inline fun send(group: Group, messageChain: String, bot: Bot) {
        launch { bot.getGroup(group.id)?.sendMessage(messageChain) }
    }

    private suspend inline fun send(group: Group, messageChain: MessageChain, bot: Bot) {
        launch { bot.getGroup(group.id)?.sendMessage(messageChain) }
    }

    private suspend inline fun sendImage(group: Group, url: String, bot: Bot) {
        val request = Request.Builder().url(url).build()
        val response = AcgPhotoDao.getOkhttpClient().newCall(request).execute()
        val byteStream = response.body!!.byteStream()
        launch { bot.getGroup(group.id)?.sendImage(byteStream) }
    }

    private fun setupMsgHandle() {
        globalEventChannel().subscribeGroupMessages {
            always {
                if (message.contentToString() == "acg") {
                    send(group, "你好", bot)
                }
                if (message.contentToString() == "你好") {
                    val id = sender.id
                    val at = At(id).toMiraiCode() + "你好！"
                    send(group, at.parseMiraiCode(), bot)
                }
                if (message.contentToString() == "百度") {
                    sendImage(group, "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png", bot)
                }
                if (message.contentToString() == "色图") {
                    val acgPhoto = AcgPhotoDao.getAcgPhoto()
                    logger.info(JSONObject.toJSONString(acgPhoto))
                    if (acgPhoto.getCode() == 0) {
                        val url = acgPhoto.getData()?.get(0)?.url.toString()
                        sendImage(group, url, bot)
                    } else {
                        acgPhoto.getMsg()?.let { it1 -> send(group, it1, bot) }
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