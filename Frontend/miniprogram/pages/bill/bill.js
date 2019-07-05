const app = getApp();
Component({
  options: {
    addGlobalClass: true,
  },
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    list: []
  },
  methods: {
    toChild(e) {
      wx.navigateTo({
        url: '/pages/plugin' + e.currentTarget.dataset.url
      })
    },
    onLoad: function (options) {
      this.load_info();
    },
    load_info: function () {
      app.showLoadToast('加载中');
      var that = this
      var data = {
        'register_info_patient_id': wx.getStorageSync('personInfo').patient_record_id
      }
      var desserts = []
      wx.request({
        method: 'POST',
        url: 'http://localhost:8080/pay/get-prescription-returned-or-paid?token=' + wx.getStorageSync('token'),
        data: (data),
        success: function (res) {
          console.log(res.data);
          if (res.data.code === 200) {
            var i
            for (i = 0; i < res.data.data.length; i++) {
              var data = {
                id: res.data.data[i].prescription_id,
                code: res.data.data[i].prescription_register_info_id,
                name: res.data.data[i].prescription_name,
                state: res.data.data[i].prescription_execute_state,
                type: res.data.data[i].prescription_type,
                number: res.data.data[i].prescription_fee,
                time: res.data.data[i].prescription_pay_time,
                hint: res.data.data[i].prescription_id + res.data.data[i].prescription_type
              }
              console.log(data)
              desserts.push(data)
            }
            that.setData({
              list: desserts
            })
          } else {
            wx.hideToast();
            app.showErrorModal("获取记录失败", '失败');
          }
        },
        fail: function (res) {
          console.log(res);
          wx.hideToast();
          app.showErrorModal("服务器繁忙，请稍后再试", '失败');
        }
      });
      wx.request({
        method: 'POST',
        url: 'http://localhost:8080/pay/get-medical-skill-canceled-or-paid?token=' + wx.getStorageSync('token'),
        data: (data),
        success: function (res) {
          console.log(res.data);
          if (res.data.code === 200) {
            var i
            for (i = 0; i < res.data.data.length; i++) {
              var data = {
                id: res.data.data[i].medical_skill_id,
                code: res.data.data[i].medical_skill_register_info_id,
                name: res.data.data[i].medical_skill_name,
                state: res.data.data[i].medical_skill_execute_state,
                type: res.data.data[i].medical_skill_type,
                number: res.data.data[i].medical_skill_fee,
                time: res.data.data[i].medical_skill_pay_time,
                hint: res.data.data[i].medical_skill_id + res.data.data[i].medical_skill_type
              }
              console.log(data)
              desserts.push(data)
            }
            that.setData({
              list: desserts
            })
            wx.showToast({
              title: '请稍候',
            })
          } else {
            wx.hideToast();
            app.showErrorModal("获取记录失败", '失败');
          }
        },
        fail: function (res) {
          console.log(res);
          wx.hideToast();
          app.showErrorModal("服务器繁忙，请稍后再试", '失败');
        }
      });
    }
  }
});