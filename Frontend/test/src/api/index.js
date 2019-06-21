import axios from 'axios'

// global.baseURL = 'https://neuvwo.com/'
global.baseURL = 'http://localhost/'
// 返回在vue模板中的调用接口
export default { // 自定义判断元素类型JS
  requestCache: {},
  toType: function (obj) {
    return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
  }, // 参数过滤函数
  filterNull: function (o) {
    for (let key in o) {
      if (o[key] === null) {
        delete o[key]
      }
      if (this.toType(o[key]) === 'string') {
        o[key] = o[key].trim()
      } else if (this.toType(o[key]) === 'object') {
        o[key] = this.filterNull(o[key])
      } else if (this.toType(o[key]) === 'array') {
        o[key] = this.filterNull(o[key])
      }
    }
    return o
  },
  /*
    接口处理函数
    自动附加IP 和 Token
  */
  apiAxios: function (method, url, params, success, failure, cacheable) {
    if (typeof cacheable === 'undefined') {
      cacheable = false
    }
    if (params) {
      params = this.filterNull(params)
    }
    let token = sessionStorage.getItem('token')
    if (token === null || token === '') {
      location.href = '/#/error/401'
    }

    // 本地数据一小时缓存机制 by LLG (默认不缓存)
    if (cacheable) {
      let lastUpdateTime = localStorage.getItem(method + '-' + url + JSON.stringify(params) + '-time')
      if (lastUpdateTime !== null) {
        lastUpdateTime = Number(lastUpdateTime)
      }
      // 2个小时内的重复接口+重复参数的访问将不再向服务器请求
      if (new Date().getTime() - lastUpdateTime < 7200000) {
        let cachedData = JSON.parse(localStorage.getItem(method + '-' + url + JSON.stringify(params)))
        if (success) {
          success(cachedData)
        }
        return
      }
    }

    let that = this

    axios({
      method: method,
      url: url + '?token=' + token,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null,
      withCredentials: false
    }).then(function (res) {
      if (res.data.code === that.code.tokenInvalid) {
        location.href = '/#/error/401'
      }
      if (success) {
        success(res.data)
        if (cacheable) {
          // 请求成功后获取缓存
          localStorage.setItem(method + '-' + url + JSON.stringify(params), JSON.stringify(res.data))
          localStorage.setItem(method + '-' + url + JSON.stringify(params) + '-time', String(new Date().getTime()))
        }
      }
      console.log(JSON.stringify(res.data))
    }).catch(function (err) {
      if (err) {
        console.log('API error: ' + err.toString())
        failure(err)
      }
    })
  },
  get: function (url, params, success, failure, cacheable) {
    return this.apiAxios('GET', url, params, success, failure, cacheable)
  },
  post: function (url, params, success, failure, cacheable) {
    return this.apiAxios('POST', url, params, success, failure, cacheable)
  },
  put: function (url, params, success, failure, cacheable) {
    return this.apiAxios('PUT', url, params, success, failure, cacheable)
  },
  delete: function (url, params, success, failure, cacheable) {
    return this.apiAxios('DELETE', url, params, success, failure, cacheable)
  },
  code: {
    success: '200',
    temporaryUnavailable: '300',
    fail: '400',
    tokenInvalid: '500'
  }
}
