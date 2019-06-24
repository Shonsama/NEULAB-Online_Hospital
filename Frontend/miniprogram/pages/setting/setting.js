// pages/setting/setting.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isLogin: wx.getStorageSync('isLogin'),
    isBind: wx.getStorageSync('isBind'),
    name: '',
    id: wx.getStorageSync('personInfo').patient_record_id
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  useridInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      id: e.detail.value
    })
  },
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null,
      id: wx.getStorageSync('personInfo').patient_record_id
    })
  },
  clear() {
    wx.clearStorage()
    wx.reLaunch({
      url: '../home/index',
    })
  },
  bind() {
    var _this = this;
    app.showLoadToast('绑定中');
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/bound-exist-record?token=' + wx.getStorageSync('token'),
      data: ({
        patient_account: wx.getStorageSync('patient_account'),
        patient_record_id: _this.data.id
      }),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          wx.showToast({
            title: '绑定成功',
          })
          _this.getPatient()
        } else {
          wx.hideToast();
          app.showErrorModal("请核对病历号后重试", '绑定失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  getPatient: function () {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/get-by-patient-id?token=' + wx.getStorageSync('token'),
      data: ({
        patient_record_id: _this.data.id
      }),
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
          _this.hideModal()
        } else {
          wx.hideToast();
          app.showErrorModal("请核对病历号后重试", '绑定失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '绑定失败');
      }
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this
    if (wx.getStorageSync('isLogin')) {
      that.setData({
        isLogin: true
      });
    }
    if (wx.getStorageSync('isBind')) {
      that.setData({
        isBind: true
      });
    }
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