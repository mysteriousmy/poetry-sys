import http from './http.js'
import spider from './spider.js'
const _banner = () => {
    return http({
        url: "/rhesis",
    })
}
const _poemlist =() => {
    return http({
        url: "/poemLists/random"
    })
}
const _dynasty = () => {
    return http({
        url: "/dynasty"
    })
}
const _poemsList = (data) => {
    return http({
        url: "/poem/list",
        data: data
    });
}
const _getPoets = (id) =>{
    return http({
        url: "/poets/list",
        data: id
    })
}
const _getPoetsDetail = (id, openid) => {
    return http({
        url: `/poets/detail/${id}?openid=${openid}`,
    })
}
const _getHRhesis = () => {
    return http({
        url: `/rhesis/list`
    })
}
const _practice = (id) => {
    return spider({
        url: `http://www.doczj.com/doc/3c18413675${id}.html`
    })
}
const _getRhesisDetail = (id, openid) => {
    return http({
        url: `/rhesis/detail/${id}?openid=${openid}`
    })
}
const _checkCollect = (data,id) => {
    return http({
        url: `/user/check/collect/${data}?openid=${id}`
    })
}
const _collect = (data) => {
    return http({
        url: `/user/collect`,
        data: data,
        method: 'post'
    })
}
const _getPoemDetail = (id, openid) => {
    return http({
        url: `/poem/detail/${id}?openid=${openid}`
    })
}
const _getSearchHistory = (id) => {
    return http({
        url: `/search/history/${id}`
    })
}
const _getSearchData = (word, type, openid) => {
    return http({
        url: `/search?word=${word}&type=${type}&openid=${openid}`
    })
}
const _findCollect = (type, openid) => {
    return http({
        url: `/user/collect/list?type=${type}&openid=${openid}`
    })
}
const _getHistoryData = (openid) => {
    return http({
        url: `/user/history/list?openid=${openid}`
    })
}
export default{
    _banner,
    _poemlist,
    _dynasty,
    _poemsList,
    _getPoets,
    _getPoetsDetail,
    _getHRhesis,
    _getRhesisDetail,
    _checkCollect,
    _collect,
    _getPoemDetail,
    _getSearchHistory,
    _getSearchData,
    _findCollect,
    _practice,
    _getHistoryData
}