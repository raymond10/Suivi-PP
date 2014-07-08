function showdetail(obj) {
	
}

function hidedetail(obj) {
	
}

function attachEvent() {
	divP = document.getElementById('ING2');
	if (divP!='undefined') {
		UVP = divP.getElementsByTagName('table');
		divs = UVP[0].getElementsByTagName('div');
		 for( var i = 0 ; i < divs.length ; i++ ) {
		 	divs[i].onmousemove = function() {showdetail(divs[i]);}
		 	divs[i].onmouseout = function() {hidedetail(divs[i]);}
			/*
			if (element.addEventListener)
				element.addEventListener(type, element.$handle, false);
			else
				element.attachEvent("on" + type, element.$handle);
			*/
		}		 	
		 }
		 
		 
}



var Profil = {
	init: function() {
		var func=function() { Profil.windowLoaded(); };
		if (window.addEventListener)
    	window.addEventListener('load', func, false);
    else if (window.attachEvent)
      window.attachEvent('onload', func);
	
	},
	
	windowLoaded:function() {
		attachEvent();
	}
}



Profil.init();

/*

  mouseOver: function(e) {
    if (this.highlightElem && this.highlightElem.className=='enabled-hover') {
      // required for Safari
      this.highlightElem.className='enabled';
      this.highlightElem=null;
    }
    var elem=Event.element(e);
    if (this.openMenuAnchor && this.openMenuAnchor!=elem)
      this.hideSubMenu();
    if (elem.className=='enabled') {
      elem.className='enabled-hover';
      this.highlightElem=elem;
    }
  },

  mouseOut: function(e) {
    var elem=Event.element(e);
    if (elem.className=='enabled-hover') elem.className='enabled';
    if (this.highlightElem==elem) this.highlightElem=null;
  },
*/