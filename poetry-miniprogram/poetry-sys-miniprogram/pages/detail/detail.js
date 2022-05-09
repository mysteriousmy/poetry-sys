// pages/detail/detail.js
const uuid= require('../../utils/uuid')
const aes = require("../../utils/aes_util")
import api from '../../api/poemList'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        poem_list: {},
        classes: [],
        colleges: [],
        sort_data: [],
        showModalOfAlthetes: false,
        althetes_modal_info: "",
        showEditModal: false,
        temp_text: "",
        temp_target_id: "",
        loading: true,
        isFocus: false,
        banner: {
            //是否显示指示点：
            // 是否显示面板指示点
            indicatorDots: true,
            // 滑动方向是否为纵向
            vertical: false,
            // 自动切换
            autoplay: true,
            // 采用衔接滑动
            circular: true,
            // 自动切换时间间隔2s
            interval: 2000,
            // 滑动动画时长0.5s
            duration: 500,
            // 前边距，可用于露出前一项的一小部分，接受 px 和 rpx 值
            previousMargin: 0,
            // 后边距，可用于露出后一项的一小部分，接受 px 和 rpx 值
            nextMargin: 0
        }
    },
    inputChange(e) {
        this.setData({
            temp_text: e.detail.value
        })
    },
    gotoPoemPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poem/index/poemIndex?id=${id}`,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    async getPoemListDetail(id){
        let result = await api._poemListDetail(id);
        result = JSON.parse(result.data).data;
        let poemList = {title: result.poemListTitle, image: result.poemListImage, desc: result.poemListDesc, poems: result.poems}
        console.log(poemList);
        this.setData({
            poem_list: poemList
        })
    },
    onLoad: function (options) {
        let poemListId = options.id;
        this.getPoemListDetail(poemListId);
    },
})