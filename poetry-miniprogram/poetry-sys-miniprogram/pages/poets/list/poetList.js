// pages/poets/list/poetList.js
const random = require('../../../utils/random');
import Api from '../../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        poets_list: [],
        current_dynasty: '全部',
        poets_dynasty: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    goToDetailPage(e){
        const id = e.currentTarget.dataset.id;
        wx.navigateTo({
          url: `/pages/poets/detail/poetDetail?id=${id}`,
        })
    },
    onLoad: function (options) {
        this.getAllDynasty();
        this.getPoets();
    },
    async getAllDynasty() {
        let result = await Api._dynasty();
        result = JSON.parse(result.data).data;
        let tmp = []
        result.forEach(r => {
            tmp.push({
                dynasty_name: r.dynastyName,
                id: r.dynastyId,
                color: random.RandomColor()
            })
        })
        this.setData({
            poets_dynasty: tmp
        })
    },
    async getPoets(){
        let dynasty;
        if (this.data.current_dynasty === "全部") {
            dynasty = "-1";
        } else {
            console.log(this.data.poets_dynasty);
            dynasty = this.data.poets_dynasty.find(p => p.dynasty_name === this.data.current_dynasty).id;
        }
        let result = await Api._getPoets({id: dynasty});
        result = JSON.parse(result.data).data;
        this.setData({
            poets_list: result
        })
    },
    changeDynasty(e) {
        let dynasty = e.currentTarget.dataset.id;
        if (dynasty === "-1") {
            this.setData({
                current_dynasty: "全部"
            })
        } else {
            let tmp = [...this.data.poets_dynasty];
            let dy = tmp.find(p => p.id === dynasty);
            this.setData({
                current_dynasty: dy.dynasty_name
            })
        }

        this.getPoets()
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