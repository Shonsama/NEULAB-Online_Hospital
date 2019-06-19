// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: null,
    index1: null,
    index2: null,
    index3: null,
    fee:'20',
    picker_constants: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_departments: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_registerLevel: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_doctors: ['喵喵喵', '汪汪汪', '哼唧哼唧']

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
    })
  },
  PickerChange1(e) {
    console.log(e);
    this.setData({
      index1: e.detail.value
    })
  },
  PickerChange2(e) {
    console.log(e);
    this.setData({
      index2: e.detail.value
    })
  },
  PickerChange3(e) {
    console.log(e);
    this.setData({
      index3: e.detail.value
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