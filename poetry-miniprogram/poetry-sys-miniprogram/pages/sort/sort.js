// pages/sort/sort.js
const db = wx.cloud.database();
const databases = require('../../const/database');
const aes = require("../../utils/aes_util")
import Api from '../../api/index';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isLogin: false,
        search_data:[],
        search_history: [],
        search_word: "",
        type: 0,
    },
    inputChange(e){
        let word = e.detail.value;
        this.setData({
            search_word: word
        })
    },
    onLoad: function(e) {
        this.getSearchHistory();
        this.checkLogin();
    },
    gotoLogin(){
        wx.reLaunch({
          url: '/pages/mypage/mypage',
        })
    },
    checkLogin(){
        let user = wx.getStorageSync('userInfo');
        if(user === null){
            this.setData({
                isLogin: false
            })
        }else{
            this.setData({
                isLogin: true
            })
        }
    },
    async getSearchHistory(){
        let userOpenId = wx.getStorageSync('userInfo').openid;
        let result = await Api._getSearchHistory(userOpenId);
        result = JSON.parse(result.data).data;
        let word = []
        result.forEach(r => {
            word.push(r.searchWord)
        })
        if(result.length > 0){
            result = [...new Set(word)];
        }
        this.setData({
            search_history: result
        })
    },
    printHistory(e){
        let word = e.currentTarget.dataset.text;
        this.setData({
            search_word: word
        })
        this.getSearchDataByButton()
    },
    async getSearchData(t) {
        if(this.data.search_word.length === 0){
            wx.showToast({
              title: '搜索不能为空！',
              icon: "error",
              duration:2000
            });
            let type = parseInt(t.currentTarget.dataset.type);
            this.setData({
                type: type
            })
        }else{
            let userOpenId = wx.getStorageSync('userInfo').openid;
            let type = parseInt(t.currentTarget.dataset.type);
            let result = await Api._getSearchData(this.data.search_word,type,userOpenId);
            result = JSON.parse(result.data).data;
            this.logicSearch(type, result);
            this.setData({
                type: type
            })
        }
       
    },
    async getSearchDataByButton(){
        if(this.data.search_word.length === 0){
            wx.showToast({
              title: '搜索不能为空！',
              icon: "error",
              duration:2000
            });
            
        }else{
            let userOpenId = wx.getStorageSync('userInfo').openid;
            let type = parseInt(this.data.type);
            let result = await Api._getSearchData(this.data.search_word,type,userOpenId);
            result = JSON.parse(result.data).data;
            this.logicSearch(type, result);
        }
    },
    goToSearchResult(e){
        let type = parseInt(e.currentTarget.dataset.type);
        let id = e.currentTarget.dataset.id;
        switch (type) {
            case 0:
                wx.navigateTo({
                  url: `/pages/poets/detail/poetDetail?id=${id}`,
                })
                break;
            case 1:
                wx.navigateTo({
                  url: `/pages/poem/index/poemIndex?id=${id}`,
                })
            case 2:
                wx.navigateTo({
                  url: `/pages/poets/list/poetList?id=${id}`,
                })
            case 3:
                wx.navigateTo({
                  url: `/pages/rhesis/detail/rhesisDetail?id=${id}`,
                })
            default:
                break;
        }
    },
    logicSearch(type, result){
        let data = [];
        console.log(result);
        if(type === 0){
            result.forEach(r => {
                data.push({id: r[0], data_title: r[3], images: r[4], type: type})
            })
        }else if(type === 1){
            result.forEach(r => {
                data.push({id: r[0], data_title: r[6], data_text: r[7], type: type, images: null})
            })
        }else if(type === 2){
            result.forEach(r => {
                data.push({id: r[0], data_title: r[4], data_text: r[2], type: type, images: r[3]})
            })
        }else if(type === 3){
            result.forEach(r => {
                data.push({id: r[0], data_title: r[3], data_text: "", type: type, images: r[2]})
            })
        }
        this.setData({
            search_data: data
        })
    }
})