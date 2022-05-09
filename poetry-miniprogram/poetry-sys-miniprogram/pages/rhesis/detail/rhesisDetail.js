// pages/rhesis/detail/rhesisDetail.js
import Api from "../../../api/index";
Page({

    /**
     * 页面的初始数据
     */
    data: {
        rhesis: {},
        rhesis_list: [],
        isCollect: false,
        isLogin: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.checkLogin();
        this.getRhesisDetail(options.id)
    },
    gotoPoemPage(e) {
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/poem/index/poemIndex?id=${id}`,
        })
    },
    async getRhesisDetail(id) {
        let userOpenId = wx.getStorageSync('userInfo').openid;
        let collect = await Api._checkCollect(id, userOpenId);
        let result = await Api._getRhesisDetail(id, userOpenId);
        result = JSON.parse(result.data).data;
        collect = JSON.parse(collect.data).data;
        let rhesis_split = result.rhesisContent.split("，")
        this.setData({
            rhesis_list: rhesis_split,
            rhesis: result,
            isCollect: collect
        });
    },
    gotoLogin() {
        wx.reLaunch({
            url: '/pages/mypage/mypage',
        })
    },
    checkLogin() {
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
    async doCollect() {
        let status = this.data.isCollect;
        let userOpenId = wx.getStorageSync('userInfo').openid;
        let re = "";
        if (!status) {
            re = await Api._collect({
                id: this.data.rhesis.rhesisId,
                type: 1,
                openid: userOpenId,
                option: 1
            });
            re = JSON.parse(re.data).data;
        } else {
            re = await Api._collect({
                id: this.data.rhesis.rhesisId,
                type: 1,
                openid: userOpenId,
                option: 0
            });
            re = JSON.parse(re.data).data;

        }
        if (re === true) {
            wx.showToast({
                title: '收藏成功！',
                duration: 2000,
            })
        } else {
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