// index.js
const db = wx.cloud.database();
const databases = require('../../const/database');
import Api from '../../api/index';
Page({
    data: {
        banner_list: [],
        poem_list: [],
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
    toPoemListPage(){
        wx.navigateTo({
          url: '/pages/poem/list/poemList',
        })
    },
    search() {
        wx.redirectTo({
            url: '/pages/athletes/home/athletesHome',
        })
    },
    onLoad: function (e) {
        this.getAllBanner()
        this.getAllpoems()
    },
    onShow: function (e) {
        this.getAllBanner()
    },
    async getAllpoems() {
        let result = await Api._poemlist();
        result = JSON.parse(result.data);
        console.log(result);
        let newPoemList = [];
        result.data.forEach(r => {
            newPoemList.push({id: r.poemListId, poem_url: r.poemListImage, poem_title: r.poemListTitle, poem_desc: r.poemListDesc})
        })
        this.setData({
            poem_list: newPoemList
        })
    
    },
    toSearchPage(e){
        const type = e.currentTarget.dataset.type;
        wx.navigateTo({
          url: `/pages/search/search?type=${type}`,
        })
    },
    toPoetsListPage(){
        wx.navigateTo({
          url: '/pages/poets/list/poetList',
        })
    },
    goToDetailPage(e){
        const id = e.currentTarget.dataset.id
        wx.navigateTo({
          url: `/pages/detail/detail?id=${id}`,
        })
    },
    toHotRhesisPage(){
        wx.navigateTo({
          url: '/pages/rhesis/hot/hotRhesis',
        })
    },
    gotoRhesisPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/rhesis/detail/rhesisDetail?id=${id}`,
        })
    },
    async getAllBanner() {
        let result = await Api._banner();
        result = JSON.parse(result.data);
        let newBanner = []
        result.data.forEach(r => {
            newBanner.push({id: r.rhesisId, banner_title: r.rhesisContent, banner_url: r.rhesisBackground})
        })
        this.setData({
            banner_list: newBanner
        })
        // db.collection(databases.banner).get().then(res => {
        //     this.setData({
        //         banner_list: res.data
        //     })
        // })
    },
    getStatusText(status) {
        switch (status) {
            case 0:
                return "报名中"
            case 1:
                return "检录中"
            case 2:
                return "进行中"
            case 3:
                return "已结束"
            default:
                break;
        }
    },
})