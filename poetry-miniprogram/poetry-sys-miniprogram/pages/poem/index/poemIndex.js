// pages/poem/index/poemIndex.js
import Api from '../../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current: 0,
        poem: {
            poemTitle: "纯棉",
            poemAnnotation: "dqwdhquiwdhqwiudhiqwhdiwqdhiqwhdiowqhdoiwqhdowqdowqhdoqwdhowqhdowqihdoiwqihdoiwqhdowqihdowqidhwqoihdoqwhdoqwhdowqhdowqihdqwohdwqoidhowqdhowqd"
        },
        poem_list:[
            "春眠不觉晓，处处闻啼鸟。",
            "夜来风雨声，花落知多少。",
            "wdwd夜来风雨声，花落知多dwdwdwd少。"
        ],
        poem_appre_list:[],
        poem_anno_list:[],
        poem_tran_list:[],
        poem_crea_list:[],
        isCollect: false,
        isLogin: false,
    },
    setStatus(e){
        let status = parseInt(e.currentTarget.dataset.type);
        this.setData({
            current: status
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let id = options.id;
        this.getPoemData(id);
        this.checkLogin();
        this.setData({
            isCollect: true
        })
    },
    gotoLogin(){
        wx.reLaunch({
          url: '/pages/mypage/mypage',
        })
    },
    checkLogin(){
        let user = wx.getStorageSync('userInfo');
        if (user === null) {
            this.setData({
                isLogin: false
            })
            wx.showModal({
                title: '提示：',
                content: '请登录系统进一步使用功能！',
                showCancel: false,
                success: function (e) {
                    if (e.confirm) {
                        wx.reLaunch({
                            url: '/pages/mypage/mypage',
                        })
                    }
                }
            })
        }else{
            this.setData({
                isLogin: true
            })
        }
    },
    async getPoemData(id){
        let userOpenId = wx.getStorageSync('userInfo').openid;
        let result = await Api._getPoemDetail(id,userOpenId);
        let collect = await Api._checkCollect(id,userOpenId);
        result = JSON.parse(result.data).data;
        collect = JSON.parse(collect.data).data;
        console.log(result);
        let content = result.poemContent.split("\\");
        let appre = result.poemAppreciation.split("\\");
        let anno = result.poemAnnotation.split("\\");
        let create = result.poemCreateScene.split("\\");
        let tran = result.poemTranslates.split("\\");
        this.setData({
            poem_tran_list: tran,
            poem_appre_list: appre,
            poem_anno_list: anno,
            poem_crea_list: create,
            poem_list: content,
            poem: result,
            isCollect: collect
        })
    },
    async doCollect(){
        let status = this.data.isCollect;
        let userOpenId = wx.getStorageSync('userInfo').openid;
        let re = "";
        if(!status){
            re = await Api._collect({id: this.data.poem.poemId, type: 0, openid: userOpenId, option: 1});
            re = JSON.parse(re.data).data;
        }else{
            re = await Api._collect({id: this.data.poem.poemId, type: 0, openid: userOpenId, option: 0});
            re = JSON.parse(re.data).data;

        }
        if(re === true){
            wx.showToast({
              title: '收藏成功！',
              duration: 2000,
            })
        }else{
            wx.showToast({
              title: '取消收藏成功！',
              duration: 2000
            })
        }
        this.setData({
            isCollect: re
        })
    }
})