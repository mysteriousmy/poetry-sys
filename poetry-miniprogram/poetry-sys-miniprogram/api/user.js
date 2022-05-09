import http from './http.js'

const _check = (data) => {
    return http({
        url: "/user/check",
        data: data,
        method: "post"
    })
}
const _login = (data) => {
    return http({
        url: "/user/doLogin",
        data: data,
        method: "post"
    })
}


export default{
    _check,
    _login,

}