package org.photo.acg.photo

import com.alibaba.fastjson.JSONObject
import org.junit.Test
import org.photo.acg.photo.dao.AcgPhotoDao

class TestAcgPhotoDao {
    @Test
    fun test() {
        val acgPhoto = AcgPhotoDao.getAcgPhoto()
        print(JSONObject.toJSONString(acgPhoto))
    }
}