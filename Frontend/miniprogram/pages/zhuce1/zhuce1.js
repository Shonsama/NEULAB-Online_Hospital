// pages/zhuce1/zhuce1.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid: '',
    passwd: '',
    id: ''
  },
  useridInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      userid: e.detail.value
    })
  },
  passwordInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      passwd: e.detail.value
    })
  },
  idInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      id: e.detail.value
    })
  },
  register() {
    var _this = this;
    app.showLoadToast('注册中');
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/sign-up?token=' + wx.getStorageSync('token'),
      data: ({
        patient_account: _this.data.userid,
        patient_password: _this.data.passwd
      }),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          wx.setStorage({
            key: 'isLogin',
            data: true,
          })
          wx.setStorage({
            key: 'patient_password',
            data: _this.data.passwd,
          })
          wx.setStorage({
            key: 'token',
            data: res.data.data.token,
          })
          wx.setStorage({
            key: 'patient_account',
            data: _this.data.userid
          })
          wx.showToast({
            title: '请稍候',
          })
          _this.bind()
        } else {
          wx.hideToast();
          app.showErrorModal("注册失败", '失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  bind() {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/bound-exist-record?token=' + wx.getStorageSync('token'),
      data: ({
        patient_account: _this.data.userid,
        patient_record_id: _this.data.id
      }),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          wx.showToast({
            title: '请稍候',
          })
          wx.reLaunch({
            url: '../home/index',
          })
        } else {
          wx.hideToast();
          app.showErrorModal("注册失败", '失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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