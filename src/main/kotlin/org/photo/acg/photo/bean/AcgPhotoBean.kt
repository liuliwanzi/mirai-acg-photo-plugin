package org.photo.acg.photo.bean

class AcgPhotoBean {
    /**
     * code : 0
     * msg :
     * quota : 9
     * quota_min_ttl : 7200
     * count : 1
     * data : [{"pid":70037927,"p":0,"uid":955690,"title":"プリンツ・オイゲン","author":"おりーぶ","url":"https://i.pixiv.cat/img-original/img/2018/08/05/21/02/05/70037927_p0.jpg","r18":false,"width":900,"height":1663,"tags":["アズレン","碧蓝航线","アズールレーン","プリンツ・オイゲン(アズールレーン)","欧根亲王(碧蓝航线)","プリンツ・オイゲン","欧根亲王","プリケツ・オイゲン(アズールレーン)","尻神様","尻神样","色褪せないエガオ","永不褪色的笑容","水着","泳装","プリンケツ・オイゲン(アズールレーン)"]}]
     */
    private var code: Int? = null
    private var msg: String? = null
    private var quota: Int? = null
    private var quotaMinTtl: Int? = null
    private var count: Int? = null
    private var data: List<DataDTO?>? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String?) {
        this.msg = msg
    }

    fun getQuota(): Int? {
        return quota
    }

    fun setQuota(quota: Int?) {
        this.quota = quota
    }

    fun getQuotaMinTtl(): Int? {
        return quotaMinTtl
    }

    fun setQuotaMinTtl(quotaMinTtl: Int?) {
        this.quotaMinTtl = quotaMinTtl
    }

    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int?) {
        this.count = count
    }

    fun getData(): List<DataDTO?>? {
        return data
    }

    fun setData(data: List<DataDTO?>?) {
        this.data = data
    }

    class DataDTO {
        /**
         * pid : 70037927
         * p : 0
         * uid : 955690
         * title : プリンツ・オイゲン
         * author : おりーぶ
         * url : https://i.pixiv.cat/img-original/img/2018/08/05/21/02/05/70037927_p0.jpg
         * r18 : false
         * width : 900
         * height : 1663
         * tags : ["アズレン","碧蓝航线","アズールレーン","プリンツ・オイゲン(アズールレーン)","欧根亲王(碧蓝航线)","プリンツ・オイゲン","欧根亲王","プリケツ・オイゲン(アズールレーン)","尻神様","尻神样","色褪せないエガオ","永不褪色的笑容","水着","泳装","プリンケツ・オイゲン(アズールレーン)"]
         */
        var pid: Int? = null
        var p: Int? = null
        var uid: Int? = null
        var title: String? = null
        var author: String? = null
        var url: String? = null
        var r18: Boolean? = null
        var width: Int? = null
        var height: Int? = null
        var tags: List<String>? = null
    }
}