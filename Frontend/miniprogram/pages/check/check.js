// pages/Check/check.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    record_id: '',
    name: '',
    gender: '',
    address: '',
    birth: '',
    age: '',
    id: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    if (wx.getStorageSync('personInfo')) {
      that.setData({
        record_id: wx.getStorageSync('personInfo').patient_record_id,
        name: wx.getStorageSync('personInfo').patient_name,
        gender: wx.getStorageSync('personInfo').patient_gender,
        address: wx.getStorageSync('personInfo').patient_address,
        birth: wx.getStorageSync('personInfo').patient_birthDate,
        age: wx.getStorageSync('personInfo').patient_age,
        id: wx.getStorageSync('personInfo').patient_credit_id
      });
    }
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