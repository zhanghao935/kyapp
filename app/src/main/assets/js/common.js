(function() {
  var pageWidth = 750
  var change = 'orientationchange' in window ? 'orientationchange' : 'resize';
  function calculate() {
      var deviceWidth = document.documentElement.clientWidth;
      if (deviceWidth < 320) {
          deviceWidth = 320;
      } else if (deviceWidth > pageWidth) {
          deviceWidth = pageWidth;
      }
      document.documentElement.style.fontSize = deviceWidth / (pageWidth / 100) + 'px';
      window.rem2px = function(rem) {
          rem = parseFloat(rem);
          return rem * deviceWidth / (pageWidth / 100);
      }
  };
  if (window.addEventListener) {
    window.addEventListener(change, calculate, false);
  } else {
      window.onchange = calculate
  }
  calculate();
})();
