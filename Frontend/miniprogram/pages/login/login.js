// pages/Check/check.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: '',
    userid: '',
    passwd: '',
    id: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },
  bind: function () {
    var _this = this;
    if (!_this.data.userid || !_this.data.passwd) {
      app.showErrorModal('账号及密码不能为空', '提醒');
      return false;
    }

    app.showLoadToast('绑定中');
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/mp/login',
      data: ({
        patient_account: _this.data.userid,
        patient_password: _this.data.passwd
      }),
      success: function (res) {
        console.log(res.data.data);
        if (res.data.code === 200) {
          wx.showToast({
            title: '挂号完成',
          })
          _this.setData({
            id: res.data.data.patient.patient_connect_id
          })
          //清除缓存
          wx.removeStorageSync('patient_password');
          wx.removeStorageSync('patient_account');
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
          _this.getPatient()
        } else {
          wx.hideToast();
          app.showErrorModal("请核对密码后重试", '绑定失败');
        }
      },
      fail: function (res) {
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '绑定失败');
      }
    });
  },
  getPatient: function () {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/get-by-patient-id?token=' + wx.getStorageSync('token'),
      data: ({
        patient_record_id: _this.data.id}),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          //清除缓存
          wx.removeStorageSync('personInfo');
          wx.setStorage({
            key: 'personInfo',
            data: res.data.data,
          })
          wx.setStorage({
            key: 'isBind',
            data: true,
          })
          wx.switchTab({
            url: '../home/index',
            success: function (e) {
              var page = getCurrentPages().pop();
              if (page == undefined || page == null)
                return;
                page.onReady();
            }
          })
        } else {
          wx.hideToast();
          app.showErrorModal("请核对密码后重试", '绑定失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '绑定失败');
      }
    });
  },
  radioChange: function (e) {
    var that = this
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    that.value = e.detail.value
  },
  navigate: function () {
    console.log('radio发生change事件，携带value值为：', this.value)
    if(this.value == 1){
      console.log('radio发生change事件，携带value值为：', this.value)
      wx.navigateTo({
        url: '/pages/zhuce/zhuce'
      })
    }
    if (this.value == 2) {
      wx.navigateTo({
        url: '/pages/zhuce1/zhuce1'
      })
    }
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