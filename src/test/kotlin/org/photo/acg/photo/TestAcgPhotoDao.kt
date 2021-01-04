package org.photo.acg.photo

import com.alibaba.fastjson.JSONObject
import org.junit.Test
import org.photo.acg.photo.bean.AcgPhotoBean
import org.photo.acg.photo.dao.AcgPhotoDao

class TestAcgPhotoDao {
    @Test
    fun test() {
        val acgPhoto = AcgPhotoDao.getAcgPhoto()
        print(JSONObject.toJSONString(acgPhoto))
    }

    @Test
    fun test2(){
        val data="{\"code\":0,\"count\":1,\"data\":[{\"author\":\"逆流茶会\",\"height\":3508,\"p\":0,\"pid\":79737067,\"r18\":true,\"tags\":[\"R-18\",\"逆流茶会\",\"朱鹭咲澪\",\"魅惑のふともも\",\"魅惑的大腿\",\"極上の乳\",\"极上乳房\",\"尻神様\",\"尻神样\",\"女の子\",\"女孩子\",\"魅惑の谷間\",\"魅惑的乳沟\",\"全裸\",\"completely naked\",\"お風呂\",\"洗澡\"],\"title\":\"洗澡\",\"uid\":50258193,\"url\":\"https://i.pixiv.cat/img-original/img/2020/03/05/11/11/25/79737067_p0.jpg\",\"width\":2480}],\"msg\":\"\",\"quota\":3,\"quotaMinTtl\":6417}"
        val acgPhoto = JSONObject.parseObject(data, AcgPhotoBean::class.java)
        print(acgPhoto.getData()?.get(0)?.url.toString())
    }
}