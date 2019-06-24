// pages/zhuce/zhuce.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    index: null,
    picker: ['男','女'],
    userid: '',
    passwd: '',
    name: '',
    gender: '',
    address: '',
    birth: '',
    age: '',
    id: '',
    date: '1960-01-01',
    connect_id: ''
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
  DateChange(e) {
    this.setData({
      date: e.detail.value
    })
    this.setData({
      age: new Date().getFullYear() - this.data.date.slice(0, 4)
    })
  },
  genderInput: function (e) {
    console.log(e.detail.value)
    this.setData({
      gender: e.detail.value
    })
  },
  PickerChange(e) {
    console.log(e);
    this.setData({
      index: e.detail.value
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
  register() {
    var _this = this;
    var url = 'http://localhost:8080/patient/sign-up?token=' + wx.getStorageSync('token')
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/sign-up',
      data: ({
        patient_account: _this.data.userid,
        patient_password: _this.data.passwd
      }),
      success: function (res) {
        console.log(res.data);
        wx.showToast({
          title: '请稍候',
        })
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
    var url = 'http://localhost:8080/patient/bound-exist-record?token=' + wx.getStorageSync('token')
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/bound-exist-record',
      data: ({
        patient_account: _this.data.userid,
        patient_record_id: _this.data.connect_id
      }),
      success: function (res) {
        console.log(res.data);
        wx.showToast({
          title: '请稍候',
        })
        if (res.data.code === 200) {
          wx.setStorage({
            key: 'isBind',
            data: true,
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
  addPatient() {
    var _this = this;
    var patientGender
    if (_this.data.picker[_this.data.index] === '男') {
      patientGender = 'true'
    } else {
      patientGender = 'false'
    }
    var url = 'http://localhost:8080/patient/add?token=' + wx.getStorageSync('token')
    wx.request({
      method: 'POST',
      url: 'http://localhost:8080/patient/add',
      data: ({
        patient_gender: patientGender,
        patient_name: _this.data.name,
        patient_credit_id: _this.data.id,
        patient_birthDate: _this.data.date,
        patient_address: _this.data.address,
        patient_age: _this.data.age
      }),
      success: function (res) {
        console.log(res.data);
        wx.showToast({
          title: '请稍候',
        })
        if (res.data.code === 200) {
          _this.setData({
            connect_id: res.data.data.patient_record_id
          })
          wx.setStorage({
            key: 'personInfo',
            data: res.data.data,
          })
          _this.register()
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