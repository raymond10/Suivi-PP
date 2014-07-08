/* pour mettre le focus sur le premier champ texte */
function focus() {
  if (document.forms.length>0) {
    var forms = document.forms[0]
    var inputFields = forms.getElementsByTagName("input");
    
    for (i=0;i<inputFields.length;i++) {
      f = inputFields[i];

      if (f.type!='hidden') {
        f.focus();
        break;
      }
      
    }
    
  }

}


/* pour cacher les profils */

function cache(id) {
  obj = document.getElementById(id);
  if (obj) {
    if (obj.className=='hide') {
      obj.className='show';
    }
    else {
      obj.className='hide';
    }
  }
}  
  
/* pour effacer un champ texte */

function effacer(id) {
  obj = document.getElementById(id)
    
  if (obj) {
    obj.value="";
  }
}



/* soumettre un formulaire changement de filtre */

function changeFiltre(obj) {
	f = document.getElementById("action");
	f.value="filtre";
	document.forms.command.submit();
	
}
