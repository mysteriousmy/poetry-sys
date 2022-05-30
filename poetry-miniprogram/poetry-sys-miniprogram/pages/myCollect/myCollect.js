// pages/myCollect/myCollect.js
const db = wx.cloud.database();
const databases = require('../../const/database');
import Api from '../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        collect_list: [],
        type: 0,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    goToDetailPage(e){
        const id = e.currentTarget.dataset.id
        
        if(this.data.type === '0'){
            wx.navigateTo({
              url: `/pages/poem/index/poemIndex?id=${id}`,
            })
        }else{
            wx.navigateTo({
              url: `/pages/rhesis/detail/rhesisDetail?id=${id}`,
            })
        }
        
        wx.navigateTo({
          url: `/pages/detail/detail?id=${id}`,
        })
    },
    onLoad: function (options) {
        this.setData({
            type: options.type
        })
        this.getPoemCollect()
        
    },
    onShow: function () {
        this.getPoemCollect()
    },
    async getPoemCollect(){
        let userInfo = wx.getStorageSync('userInfo').openid;
        let result = await Api._findCollect(this.data.type,userInfo);
        result = JSON.parse(result.data).data;
        if(this.data.type === '0'){
            this.setData({
                collect_list: result
            })
        }else{
            let data = [];
            result.forEach(r => {
                data.push({id: r.rhesisId, collect_title: r.rhesisContent, collect_url: r.rhesisBackground})
            })
            this.setData({
                collect_list: data
            })
        }
        console.log(result);
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */


    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})