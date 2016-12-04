var i=0;
var prefixCollection = [
     "ActionScript",
     "AppleScript",
     "Asp",
     "BASIC",
     "C",
     "C++",
     "Clojure",
     "COBOL",
     "ColdFusion",
     "Erlang",
     "Fortran",
     "Groovy",
     "Haskell",
     "Java",
     "JavaScript",
     "Lisp",
     "Perl",
     "PHP",
     "Python",
     "Ruby",
     "Scala",
     "Scheme"
   ];
$(document).ready(function(){
        $('#usr').autocomplete({
            source: test()
        });
});

function test(){
  getSuggestion("test");
  return prefixCollection;

}

function getSuggestion(prefix) {
  // var xhttp;
  // xhttp=new XMLHttpRequest();
  // xhttp.onreadystatechange = function() {
  //   if (this.readyState == 4 && this.status == 200) {
  //     displayBotMessage(xhttp.responseText);
  //   }
  // };
  // var str= "predict";
  // xhttp.open("GET", "WebSrcController?act="+str+"&prefix=" +str, true);
  // xhttp.send();
  prefixCollection=[];
  prefixCollection=["yadi", "clovis", "Acc"];

}
