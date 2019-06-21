// pages/zhuce/zhuce.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid: '',
    passwd: '',
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
  nameInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      name: e.detail.value
    })
  },
  genderInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      gender: e.detail.value
    })
  },
  addressInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      address: e.detail.value
    })
  },
  birthInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      birth: e.detail.value
    })
  },
  ageInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      age: e.detail.value
    })
  },
  idInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      id: e.detail.value
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