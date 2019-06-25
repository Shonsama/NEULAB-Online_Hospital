const app = getApp();
Component({
  options: {
    addGlobalClass: true,
  },
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    list: [{
      title: '中心吸氧',
      type:'检查',
      state:'已开立',
      register_id:'25',
      fee:'80'
    },
      {
        title: '验血',
        type: '检验',
        state: '已开立',
        register_id: '23',
        fee: '60'
      }
    ]
  },
  methods: {
    toChild(e) {
      wx.navigateTo({
        url: '/pages/plugin' + e.currentTarget.dataset.url
      })
    },
    // ListTouch触摸开始
    ListTouchStart(e) {
      this.setData({
        ListTouchStart: e.touches[0].pageX
      })
    },

    // ListTouch计算方向
    ListTouchMove(e) {
      this.setData({
        ListTouchDirection: e.touches[0].pageX - this.data.ListTouchStart > 0 ? 'right' : 'left'
      })
    },

    // ListTouch计算滚动
    ListTouchEnd(e) {
      if (this.data.ListTouchDirection == 'left') {
        this.setData({
          modalName: e.currentTarget.dataset.target
        })
      } else {
        this.setData({
          modalName: null
        })
      }
      this.setData({
        ListTouchDirection: null
      })
    },
    onLoad: function (options) {
      this.load_info();
    },
    load_info: function (){
      app.showLoadToast('加载中');
      var that = this
      var data = {
        'register_info_patient_id': wx.getStorageSync('personInfo').patient_record_id
      }
      var desserts = []
      wx.request({
        method: 'POST',
        url: 'http://localhost:8080/pay/get-prescription-sent?token=' + wx.getStorageSync('token'),
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
            app.showErrorModal("获取药品失败", '失败');
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
        url: 'http://localhost:8080/pay/get-medical-skill-drew?token=' + wx.getStorageSync('token'),
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
            app.showErrorModal("获取医技失败", '失败');
          }
        },
        fail: function (res) {
          console.log(res);
          wx.hideToast();
          app.showErrorModal("服务器繁忙，请稍后再试", '失败');
        }
      });
    },
    charge(item){
      console.log(item)
      var value = item.currentTarget.dataset.variable
      var that = this
      wx.request({
        method: 'POST',
        url: 'http://localhost:8080/pay/pay?token=' + wx.getStorageSync('token'),
        data: ({
          id: value.id,
          type: value.type,
          user_id: 9,
          register_id: value.code
        }),
        success: function (res) {
          console.log(res.data);
          if (res.data.code === 200) {
            that.load_info();
          } else {
            wx.hideToast();
            app.showErrorModal("缴费失败", '失败');
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