$(function() {
	$(".qrm-input-border").click(function() {
		if($(".qrm-pinming").css("background-image").indexOf("qrm-arrow-down") !== -1) {
			if($(".qrm-input").val() == '') {
				$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-top.png)");
				$(".qrm-pinming").css("border", "1px solid #409EFF");
				$(".qrm-border1").show();
			} else {
				var s = $(".qrm-input").val()
				console.log(s)
				var n = (s.split('/')).length - 1;
				console.log(n)
				if(n == 1) {
					$(".qrm-border1").show();
					$(".qrm-border2").show();
				} else if(n == 2) {
					$(".qrm-border1").show();
					$(".qrm-border2").show();
					$(".qrm-border3").show();
				}
				$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-top.png)");
				$(".qrm-pinming").css("border", "1px solid #409EFF");
			}
		} else {
			$(".qrm-border1").hide();
			$(".qrm-border2").hide();
			$(".qrm-border3").hide();
			$(".qrm-pinming").css("border", "1px solid #ddd");
			$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		}
	});
	var lev1;
	var lev2;
	var lev3;
	var lev4;
	$("body").on("click", ".qrm-lev-1>li", function() {
		$(this).addClass("active").siblings("li").removeClass("active");
		lev1 = "";
		lev2 = "";
		lev3 = "";
		lev4 = "";
		var html1 = $(this).children(".li-zi-1").html();
		$(".qrm-lev-2").html(html1);
		$(".qrm-border2").show();
		$(".qrm-border3").hide();
		$(".qrm-lev-3").html("");
		lev1 = $(this).children("span").html();
	});
	$("body").on("click", ".qrm-lev-2>li", function() {
		$(this).addClass("active").siblings("li").removeClass("active");
		if($(this).children(".li-zi-2").html() == undefined) {
			lev2 = $(this).children("span").html();
			$(".qrm-input").val(lev1 + "/" + lev2);
			$(".qrm-border1").hide();
			$(".qrm-border2").hide();
			$(".qrm-border3").hide();
			$(".qrm-pinming").css("border", "1px solid #ddd");
			$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		} else {
			var html2 = $(this).children(".li-zi-2").html();
			lev2 = $(this).children("span").html();
			$(".qrm-border3").show();
			$(".qrm-lev-3").html(html2);
		}
		if($(this).parent().parent().next().children(".qrm-lev").html() == "") {
			$(".qrm-border1").hide();
			$(".qrm-border2").hide();
			$(".qrm-border3").hide();
			$(".qrm-pinming").css("border", "1px solid #ddd");
			$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		}
	});
	$("body").on("click", ".qrm-lev-3>li", function() {
		$(this).addClass("active").siblings("li").removeClass("active");
		var html3 = $(this).children(".li-zi-3").html();
		lev3 = $(this).children("span").html();
		$(".qrm-input").val(lev1 + "/" + lev2 + "/" + lev3);
		$(".qrm-border1").hide();
		$(".qrm-border2").hide();
		$(".qrm-border3").hide();
		$(".qrm-pinming").css("border", "1px solid #ddd");
		$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		if($(this).parent().parent().next().children(".qrm-lev").html() == "") {
			$(".qrm-input").val(lev1 + "/" + lev2);
			$(".qrm-border1").hide();
			$(".qrm-border2").hide();
			$(".qrm-border3").hide();
			$(".qrm-pinming").css("border", "1px solid #ddd");
			$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		}
	});
	$("body").on("click", ".qrm-lev>li", function() {
		if($(this).parent().parent().next().children(".qrm-lev").html() == "") {
			$(".qrm-border1").hide();
			$(".qrm-border2").hide();
			$(".qrm-border3").hide();
			$(".qrm-pinming").css("border", "1px solid #ddd");
			$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
		}
	})
});
$(document).click(function(event) {
	var x1 = $('.box');
	if(!x1.is(event.target) && x1.has(event.target).length === 0) {
		$(".qrm-border1").hide();
		$(".qrm-border2").hide();
		$(".qrm-border3").hide();
		$(".qrm-pinming").css("border", "1px solid #ddd");
		$(".qrm-pinming").css("background-image", "url(../img/qrm-arrow-down.png)");
	}
});