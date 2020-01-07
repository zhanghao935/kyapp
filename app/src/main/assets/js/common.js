(function () {
    const pageWidth = 750;
    const change = 'orientationchange' in window ? 'orientationchange' : 'resize';

    function calculate() {
        let deviceWidth = document.documentElement.clientWidth;
        if (deviceWidth < 320) {
            deviceWidth = 320;
        } else if (deviceWidth > pageWidth) {
            deviceWidth = pageWidth;
        }
        document.documentElement.style.fontSize = deviceWidth / (pageWidth / 100) + 'px';
        window.rem2px = function (rem) {
            rem = parseFloat(rem);
            return rem * deviceWidth / (pageWidth / 100);
        }
    }

    if (window.addEventListener) {
        window.addEventListener(change, calculate, false);
    } else {
        window.onchange = calculate
    }
    calculate();

    (function ($) {
        $.postData = function (url, data) {
            var paramJson = {};
            $(paramJson).attr("url", url);
            $(paramJson).attr("param", data);
            let postData = window.zjxf.postData(JSON.stringify(paramJson));
            return JSON.parse(postData);
        }
    })(jQuery);


})();
