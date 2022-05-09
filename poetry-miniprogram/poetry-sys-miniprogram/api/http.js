const pubUrl = "http://localhost:8081/api/poetry"
const http = (options) =>{
  return new Promise((resolve,reject) => {
    wx.request({
      url: pubUrl+options.url,
      method:options.method || 'get',
      data:options.data || {},
      dataType: JSON,
      header: options.header || {
        'content-type':'application/json'
      },
      success:resolve,
      fail:reject
    })
  })
}
export default http