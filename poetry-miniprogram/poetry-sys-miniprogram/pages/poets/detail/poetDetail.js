// pages/poets/detail/poetDetail.js
import Api from '../../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        poet: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let id = options.id;
        this.getPoetsDetail(id);
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    async getPoetsDetail(id){
        let userInfo = wx.getStorageSync('userInfo').openid;
        let result = await Api._getPoetsDetail(id,userInfo);
        result = JSON.parse(result.data).data;
        this.setData({
            poet: result
        })
    },
    goToPoemPage(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poem/index/poemIndex?id=${id}`,
        })
    }
})