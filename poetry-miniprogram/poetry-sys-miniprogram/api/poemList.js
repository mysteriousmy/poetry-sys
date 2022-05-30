import http from './http.js'

const _poemListDetail = (id) => {
    return http({
        url: `/poemList/detail/${id}`
    })
}

export default{
    _poemListDetail
}
