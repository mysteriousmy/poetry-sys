const spider = (options) =>{
  return new Promise((resolve,reject) => {
    wx.request({
      url: options.url,
      method:options.method || 'get',
      success:resolve,
      fail:reject
    })
  })
}
export default spider