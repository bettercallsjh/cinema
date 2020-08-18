/**
 * Created by Monster on 2017/5/18.
 */

function error(msg, fn) {
    $.notify({
        title: '<strong>错误提示!</strong>',
        message: msg
    }, {
        type: 'danger',
        delay: 2000,
        timer: 1000,
        allow_dismiss: false,
        showProgressbar: true,
        onClose: fn
    });
}


// 定义一个名为 button-counter 的新组件
Vue.component('button-counter', {
    props: ['flag'],
    data: function () {
        return {
            count: 0,
            user: [],

        }
    },
    beforeMount: function () {
        let _this = this;
        let url = "/PersonalCentreController/getUser";

        $.get(url, null, function (data) {
            console.log(data);
            _this.user = data;
        })

    }
    ,
    template: "    <header id=\"topHeader\">\n" +
        "        <div class=\"layout clearfix\">\n" +
        "            <a class=\"logo\" href=\"/business/HTML/index.html\">\n" +
        "                <img class=\"fl\" id=\"topLogo\" src=\"https://www.woniuxy.com/train/img/logo_text.png\n\" style=\"height: 74px\"\n" +
        "                     alt=\"让投资实现梦想\">\n" +
        "            </a>\n" +
        "\n" +
        "            <!-- 导航菜单 -->\n" +
        "            <nav class=\"fl\">\n" +
        "                <ul class=\"fl menu\">\n" +
        "\n" +
        "\n" +
        "                    <li v-if=\"flag === '1'\" class=\"curr\">\n" +
        "                        <a href=\"index.html\" rel=\"nofollow\">首&nbsp;&nbsp;&nbsp;页</a>\n" +
        "                    </li>\n" +
        "                    <li v-else>\n" +
        "                        <a href=\"index.html\" rel=\"nofollow\">首&nbsp;&nbsp;&nbsp;页</a>\n" +
        "                    </li>\n" +
        "\n" +
       
        "                    <li class=\"curr\" v-if=\"flag === '3'\">\n" +
        "                        <a href=\"transaction.html\" rel=\"nofollow\">交易管理</a>\n" +
        "                    </li>\n" +
        "                    <li v-else>\n" +
        "                        <a href=\"transaction.html\" rel=\"nofollow\">交易管理</a>\n" +
        "                    </li>\n" +
        "\n" +
        "                    <li class=\"curr\" v-if=\"flag === '4'\">\n" +
        "                        <a href=\"account.html\" rel=\"nofollow\">账户管理</a>\n" +
        "                    </li>\n" +
        "                    <li v-else>\n" +
        "                        <a href=\"account.html\" rel=\"nofollow\">账户管理</a>\n" +
        "                    </li>\n" +
        "\n" +
        "                </ul>\n" +
        "            </nav>\n" +
        "\n" +
        "\n" +
        "            <!-- 用户已登录的显示信息 -->\n" +
        "            <div class=\"fr user\">\n" +
        "                <div class=\"navList\">\n" +
        "                    <div class=\"userTitle\"><a href=\"#\">{{user.userName}}<span class=\"icon\"></span></a></div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "\n" +
        "        </div>\n" +
        "    </header>\n"
});

let mainVue = new Vue({
    el: '#mainVue',
});

function success(msg, fn) {
    $.notify({
        title: '<strong>成功提示!</strong>',
        message: msg
    }, {
        type: 'success',
        delay: 2000,
        timer: 1000,
        allow_dismiss: false,
        showProgressbar: true,
        onClose: fn
    });
}