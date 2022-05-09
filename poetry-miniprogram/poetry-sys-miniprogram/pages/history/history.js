// pages/history/history.js
import Api from '../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        history_list: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getHistoryData()
    },
    
    gotoPoemPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poem/index/poemIndex?id=${id}`,
        })
    },
    goToPoetsPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poets/detail/poetDetail?id=${id}`,
        })
    },
    goToRhesisPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/rhesis/detail/rhesisDetail?id=${id}`,
        })
    },
    async getHistoryData(){
        let userInfo = wx.getStorageSync('userInfo').openid;
        let result = await Api._getHistoryData(userInfo);
        console.log(result);
        result = JSON.parse(result.data).data;
        this.setData({
            history_list: result
        })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

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