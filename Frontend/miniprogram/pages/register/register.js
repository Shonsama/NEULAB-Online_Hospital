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
    fee:'未知',
    picker_constants: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_departments: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_registerLevel: ['喵喵喵', '汪汪汪', '哼唧哼唧'],
    picker_doctors: [],
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
    this.load_constants()
    this.load_departs()
    this.load_registerLevels()
  },
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
    })
    // this.load_doctors()
  },
  PickerChange1(e) {
    console.log(e);
    this.setData({
      index1: e.detail.value,
      fee: this.data.picker_registerLevel[e.detail.value].register_level_fee
    })
    this.load_doctors()
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
  load_constants() {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080//maintenance/constant/get?token=' + wx.getStorageSync('token'),
      data: ({
        constant_type: 'payment_type'
      }),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          _this.setData({
            picker_constants: res.data.data
          })
        } else {
          wx.hideToast();
          app.showErrorModal("获取结算类别失败", '失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  load_registerLevels() {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/maintenance/register-level/get-all?token=' + wx.getStorageSync('token'),
      data: ({}),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          _this.setData({
            picker_registerLevel: res.data.data
          })
        } else {
          wx.hideToast();
          app.showErrorModal("获取挂号级别失败", '失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  load_departs() {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/maintenance/department/get-all?token=' + wx.getStorageSync('token'),
      data: ({}),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          _this.setData({
            picker_departments: res.data.data
          })
        } else {
          wx.hideToast();
          app.showErrorModal("获取科室失败", '失败');
        }
      },
      fail: function (res) {
        console.log(res);
        wx.hideToast();
        app.showErrorModal("服务器繁忙，请稍后再试", '失败');
      }
    });
  },
  load_doctors() {
    var _this = this;
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/register/get-all-doctor?token=' + wx.getStorageSync('token'),
      data: ({
        department_id: _this.data.picker_departments[_this.data.index].department_id,
        register_level_id: _this.data.picker_registerLevel[_this.data.index1].register_level_id
      }),
      success: function (res) {
        console.log(res.data);
        if (res.data.code === 200) {
          _this.setData({
            picker_doctors: res.data.data
          })
        } else {
          wx.hideToast();
          app.showErrorModal("获取医生失败", '失败');
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